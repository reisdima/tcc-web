/*    */ package br.ufjf.ice.integra3.ws.login;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name = "loginResponse", propOrder = {"_return"})
/*    */ public class LoginResponse
/*    */ {
/*    */   @XmlElement(name = "return")
/*    */   protected WsLoginResponse _return;
/*    */   
/* 47 */   public WsLoginResponse getReturn() { return this._return; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void setReturn(WsLoginResponse value) { this._return = value; }
/*    */ }


/* Location:              C:\Users\victo\Documents\UFJF\TP\Gest�o\IntegraAPI-2018.jar!/br/ufjf/ice/integra3/ws/login/LoginResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */