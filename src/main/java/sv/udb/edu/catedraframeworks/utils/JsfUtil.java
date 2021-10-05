package sv.udb.edu.catedraframeworks.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class JsfUtil {


     public static HttpServletRequest getRequest(){
         return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     }

}
