package sv.udb.edu.catedraframeworks.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class JsfUtil {


     public static HttpServletRequest getRequest(){
         return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     }

     
     public static void setFlashMessage(String doctorId, String msg) {
    	FacesContext.getCurrentInstance().getExternalContext().getFlash().put(doctorId, msg); 
     }
     
     public static void setErrorMessage(String doctorId, String msg) {
    	 FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg, null);
    	 FacesContext.getCurrentInstance().addMessage(doctorId, mensaje);
     }
    
     
}
