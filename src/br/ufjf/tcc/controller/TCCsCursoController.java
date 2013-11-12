package br.ufjf.tcc.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Label;

import br.ufjf.tcc.business.TCCBusiness;
import br.ufjf.tcc.model.TCC;

public class TCCsCursoController extends CommonsController {

	private List<String> years;
	private String emptyMessage = "Não há TCCs para este curso.";
	private List<TCC> tccs = null;
	private List<TCC> filterTccs = tccs;
	private String filterString = "";
	private String filterYear = "Todos";

	@Init
	public void init() {
		tccs = new TCCBusiness().getTCCsByCurso(getUsuario().getCurso());
		filterTccs = tccs;

		years = new ArrayList<String>();
		if (tccs != null && tccs.size() > 0) {
			for (TCC tcc : tccs) {
				if (tcc.getDataEnvioFinal() != null) {
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(tcc.getDataEnvioFinal().getTime());
					int year = cal.get(Calendar.YEAR);
					if (!years.contains("" + year))
						years.add("" + year);
				}
			}
			Collections.sort(years, Collections.reverseOrder());
		}
		years.add(0, "Todos");

		this.filtra();
	}

	public String getEmptyMessage() {
		return emptyMessage;
	}

	public List<String> getYears() {
		return years;
	}

	public String getFilterYear() {
		return filterYear;
	}

	public void setFilterYear(String filterYear) {
		this.filterYear = filterYear;
	}

	public List<TCC> getFilterTccs() {
		return filterTccs;
	}

	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public String getTccYear(@BindingParam("tcc") TCC tcc) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(tcc.getDataEnvioFinal().getTime());
		return "" + cal.get(Calendar.YEAR);
	}

	@Command
	public void getEachTccYear(@BindingParam("tcc") TCC tcc,
			@BindingParam("lbl") Label lbl) {
		if (tcc.getDataEnvioFinal() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(tcc.getDataEnvioFinal().getTime());
			lbl.setValue("" + cal.get(Calendar.YEAR));
		} else
			lbl.setValue("Não finalizada");
	}

	@NotifyChange("filterTccs")
	@Command
	public void filtra() {
		String filter = filterString.toLowerCase().trim();
		if (tccs != null) {
			List<TCC> temp = new ArrayList<TCC>();
			for (TCC tcc : tccs) {
				if ((filterYear == "Todos" || filterYear
						.contains(getTccYear(tcc)))
						&& (filter == "" || (tcc.getNomeTCC().toLowerCase()
								.contains(filter)
								|| tcc.getAluno().getNomeUsuario()
										.toLowerCase().contains(filter)
								|| tcc.getOrientador().getNomeUsuario()
										.toLowerCase().contains(filter)
								|| tcc.getPalavrasChave().toLowerCase()
										.contains(filter) || tcc.getResumoTCC()
								.toLowerCase().contains(filter))))
					temp.add(tcc);
			}

			filterTccs = temp;
		} else {
			filterTccs = tccs;
		}
		
		emptyMessage = "Não há TCCs válidas para esses filtros.";
		BindUtils.postNotifyChange(null, null, null, "emptyMessage");
	}
	
	@Command
	public void showTCC(@BindingParam("tcc") TCC tcc){
		Sessions.getCurrent().setAttribute("tcc", tcc);
		Executions.sendRedirect("/pages/visualiza-tcc.zul");
	}

	@Command
	public void downloadPDF(@BindingParam("tcc") TCC tcc) {
		InputStream is = Sessions.getCurrent().getWebApp()
				.getResourceAsStream("files/" + tcc.getArquivoTCCFinal());
		Filedownload.save(is, "application/pdf", tcc.getNomeTCC() + ".pdf");
	}

	@Command
	public void downloadExtra(@BindingParam("tcc") TCC tcc) {
		if (tcc.getArquivoExtraTCCFinal() != null
				&& tcc.getArquivoExtraTCCFinal() != "") {
			InputStream is = Sessions
					.getCurrent()
					.getWebApp()
					.getResourceAsStream(
							"files/" + tcc.getArquivoExtraTCCFinal());
			Filedownload.save(is, "application/x-rar-compressed",
					tcc.getNomeTCC() + ".rar");
		}
	}

}
