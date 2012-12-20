package thettian.study.struts.application.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;
import thettian.study.struts.application.service.AdminService;
import thettian.study.struts.application.service.ManagerService;
import thettian.study.struts.application.service.PublicService;
import thettian.study.struts.base.BaseAction;

@Namespace("/worker")
public class UserAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5428206906109613797L;
	
	private Task task;
	private String userid;
	private String taskid;
	
	@Resource
	private PublicService publicService;

	@Resource
	private AdminService adminService;
	
	@Resource
	private ManagerService managerService;
	
	@Action(value = "index", results = { @Result(name = SUCCESS, location = "/worker/index.jsp") })
	public String index() {
		return SUCCESS;
	}
	
	@Action(value = "newpass", results = { @Result(name = SUCCESS, location = "/worker/newpass.jsp") })
	public String newpass() {
		return SUCCESS;
	}
	
	@Action(value = "checktask", results = { @Result(name = SUCCESS, location = "/worker/checktask.jsp") })
	public String checkTask() {
		return SUCCESS;
	}
	
	@Action(value = "updatetask", results = { @Result(name = SUCCESS, location = "/worker/updatetask.jsp") })
	public String updateTask() {
		Task _task = publicService.findTaskById(Integer.parseInt(taskid));
		task = new Task();
		task.setContent(_task.getContent());
		task.setExedep(_task.getExedep());
		task.setExeuser(_task.getExeuser());
		task.setFinTime(_task.getFinTime());
		task.setId(_task.getId());
		task.setLastTime(_task.getLastTime());
		task.setName(_task.getName());
		task.setStatus(_task.getStatus());
		return SUCCESS;
	}
	
	@Action(value = "updateitem", results = { @Result(name = SUCCESS, location = "/worker/checktask.jsp") })
	public String updateItem() {
		Task _task = publicService.findTaskById(task.getId());
		if(task.getStatus().equals("已完成"))
		{
			if(task.getFinTime()!= null)
				return SUCCESS;
			Date current = new Date();
			_task.setFinTime(current);
		}
		_task.setStatus(task.getStatus());
		adminService.updateTask(_task);
		return SUCCESS;
	}
	
	/**
	 * 根据日期进行分组
	 */
	public List<Integer> getGroup() {
		User u = (User) session.get("user");
		List<Task> ls = managerService.findMyTasks(u.getId());
		List<Integer> rs = new ArrayList<Integer>();
		Date currentDate = new Date();
		
		for(int i=0;i<ls.size();i++)
		{
			long k = (ls.get(i).getLastTime().getTime() - currentDate.getTime())/(3600*24*1000);
			if( k > 7)
				rs.add(1);
			else if( k < 7 && k >= 0)
				rs.add(0);
			else
				rs.add(-1);
		}
		if (rs.size() > 0)
			return rs;
		else
			return null;
	}
	
	public List<Task> getTasks() {
		User u = (User) session.get("user");
		List<Task> ls = managerService.findMyTasks(u.getId());
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

}
