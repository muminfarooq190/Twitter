

package Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;


public abstract class servletEssentials extends dbessentials {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private JspWriter jspwriter;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public JspWriter getJspwriter() {
        return jspwriter;
    }

    public void setJspwriter(JspWriter jspwriter) {
        this.jspwriter = jspwriter;
    }
    
    
}
