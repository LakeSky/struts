package thettian.study.struts.application.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import thettian.study.struts.application.model.User;
import thettian.study.struts.application.model.enumtype.Role;
import thettian.study.struts.application.service.PublicService;
import thettian.study.struts.base.BaseAction;

@Namespace("/")
public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248817557475572614L;
	private User user;
	private String newpass;

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	@Resource
	private PublicService publicService;

	@Action(value = "index", results = { @Result(name = SUCCESS, location = "/index.jsp") })
	public String index() {
		return SUCCESS;
	}

	@Action(value = "login", results = { @Result(name = ERROR, location = "/index.jsp") })
	public String login() {
		User _u = publicService.findUserByusername(this.user.getUsername());
		if (_u != null) {
			if (_u.getPassword().equals(this.user.getPassword())) {
				this.session.put("user", _u);
				if (_u.getRole() == Role.ADMIN)
					return "admin";
				else if (_u.getRole() == Role.MANAGER)
					return "manager";
				else if (_u.getRole() == Role.USER)
					return "worker";
			}
		}
		return ERROR;
	}

	@Action(value = "updatepass", results = {
			@Result(name = ERROR, location = "/index.jsp"),
			@Result(name = "admin", location = "/admin/newpass.jsp"),
			@Result(name = "manager", location = "/manager/newpass.jsp"),
			@Result(name = "worker", location = "/worker/worker.jsp"),
			@Result(name = "logout", location = "/index.jsp") })
	public String User_Updatepass() {
		User u = (User) session.get("user");
		logger.info("用户：{}，尝试修改密码。", u.getUsername());
		if (u.getPassword().equals(user.getPassword())) {
			u.setPassword(newpass);
			publicService.updateUser(u);
			setAlertType("success");
			setTips("修改密码成功！");
		} else {
			setAlertType("error");
			setTips("原密码错误！");
		}
		if (u.getRole() == Role.ADMIN)
			return "admin";
		else if (u.getRole() == Role.MANAGER)
			return "manager";
		else if (u.getRole() == Role.USER)
			return "worker";
		else
			return ERROR;
	}

	@Action(value = "logoutAction", results = { @Result(name = SUCCESS, location = "/index.jsp") })
	public String User_Logout() {
		User u = (User) session.get("user");
		logger.info("用户:{}，登出。", u.getUsername());
		session.clear();
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
