package br.ufjf.tcc.business;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.tcc.model.CalendarioSemestre;
import br.ufjf.tcc.model.Curso;
import br.ufjf.tcc.model.TCC;
import br.ufjf.tcc.model.Usuario;
import br.ufjf.tcc.persistent.impl.TCCDAO;

public class TCCBusiness {
	private List<String> errors;
	private TCCDAO tccDao;

	public TCCBusiness() {
		this.tccDao = new TCCDAO();
		this.errors = new ArrayList<String>();
	}

	public List<String> getErrors() {
		return errors;
	}

	// validação dos formulários
	public boolean validate(TCC tcc) {
		errors.clear();

		validateName(tcc.getNomeTCC());
		if(errors.size() < 1)
			tcc.setNomeTCC(tcc.getNomeTCC().toUpperCase());
		validateOrientador(tcc.getOrientador());

		return errors.size() == 0 ? true : false;
	}

	public void validateName(String nomeTCC) {
		if (nomeTCC == null || nomeTCC.trim().length() == 0)
			errors.add("Informe o nome do TCC;\n");		
	}

	public void validateOrientador(Usuario orientador) {
		if (orientador == null)
			errors.add("Informe o seu orientador;\n");
	}

	// Comunicação com o TCCDAO
	public List<TCC> getPublicListByCurso(Curso curso) {
		return tccDao.getPublicListByCurso(curso);
	}

	public List<TCC> getAll() {
		return tccDao.getAll();
	}

	public boolean save(TCC tcc) {
		return tccDao.salvar(tcc);
	}

	public boolean edit(TCC tcc) {
		return tccDao.editar(tcc);
	}
	
	public boolean userHasTCC(Usuario user){
		return tccDao.userHasTCC(user);
	}

	public List<TCC> getTCCsByCurso(Curso curso) {
		return tccDao.getTCCsByCurso(curso);
	}
	
	public TCC getCurrentTCCByAuthor(Usuario user, CalendarioSemestre currentCalendar) {
		if(currentCalendar != null)
			return tccDao.getCurrentTCCByAuthor(user, currentCalendar);
		else
			return null;
	}
	
	public TCC getTCCById(int id) {
		return tccDao.getTCCById(id);
	}
	
	public List<TCC> getTCCsByOrientador(Usuario user) {
		return tccDao.getTCCsByOrientador(user);
	}

}
