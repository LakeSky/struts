package thettian.study.struts.application.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import thettian.study.struts.application.model.User;
import thettian.study.struts.application.service.PublicService;
import thettian.study.struts.base.BaseAction;

@Namespace("/")
public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248817557475572614L;
	private User user;
	@Resource
	private PublicService publicService;

	@Action(value = "index", results = { @Result(name = SUCCESS, location = "/index.jsp") })
	public String index() {
		return SUCCESS;
	}

	@Action(value = "login", results = {
			@Result(name = SUCCESS,location = "/tasks.jsp"),
			@Result(name = ERROR,location = "/index.jsp")
	})
	public String login(){
		User _u = publicService.findUserByusername(this.user.getUsername());
		if(_u != null){
			if(_u.getPassword().equals(this.user.getPassword())){
				this.session.put("user", this.user);
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
