package thettian.study.struts.base;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * base action
 * 
 * @author ethan
 */
@Results({ @Result(name = "manager", location = "/manager/manager.jsp"),
		@Result(name = "admin", location = "/admin/admin.jsp"),
		@Result(name = "worker", location = "/worker/worker.jsp"),
		@Result(name = "logout", location = "/index.jsp") })
public class BaseAction extends ActionSupport implements ServletContextAware,
		ServletResponseAware, ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected ServletContext servletContext;

	protected HttpServletRequest httpServletRequest;

	protected HttpServletResponse httpServletResponse;

	protected HttpSession httpSession;

	protected Map<String, Object> session;
	// 网页中提示的信息
	private String Tips;
	// 提示类型，三种：info,success,error
	private String AlertType;

	public String getTips() {
		return Tips;
	}

	public void setTips(String tips) {
		Tips = tips;
	}

	public String getAlertType() {
		return AlertType;
	}

	public void setAlertType(String alertType) {
		AlertType = alertType;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.httpServletResponse = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.httpServletRequest = request;
		this.httpSession = request.getSession();
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
