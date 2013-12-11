package thettian.study.struts.application.action;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sun.rowset.internal.Row;

import thettian.study.struts.application.model.Department;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;
import thettian.study.struts.application.model.enumtype.Role;
import thettian.study.struts.application.service.AdminService;
import thettian.study.struts.application.service.ManagerService;
import thettian.study.struts.application.service.PublicService;
import thettian.study.struts.base.BaseAction;

@Namespace("/admin")
public class AdminAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248817557475572614L;
	private User user;
	private Task task;
	private Department dept;

	private String[] deptid;
	private String[] userids;
	private String[] taskids;
	private String taskid;
	private String userid;
	private String role;

	private InputStream downloadStream;
	@Resource
	private PublicService publicService;

	@Resource
	private AdminService adminService;

	@Resource
	private ManagerService managerService;

	/**
	 * 返回index页面
	 * 
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = SUCCESS, location = "/admin/index.jsp") })
	public String index() {
		return SUCCESS;
	}

	@Action(value = "report", results = { @Result(type = "stream", params = {
			"contentType", "application/vnd.ms-excel", "contentDisposition",
			"attachment;filename=${fileName}", "inputName", "downloadStream" }, name = SUCCESS, location = "/admin/index.jsp") })
	public String report() throws Exception {
		return downloadReport();
	}

	public String getFileName() {
		return "Report.xls";
	}

	@SuppressWarnings("deprecation")
	public String downloadReport() throws Exception {
		List<Task> ls = publicService.findAllTasks();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFFont font = workbook.createFont();
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFSheet sheet = workbook.createSheet("sheet1");
		sheet.setColumnWidth(1, 10 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 30 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 20 * 256);
		sheet.setColumnWidth(7, 20 * 256);
		sheet.setColumnWidth(8, 20 * 256);
		HSSFRow row = sheet.createRow(1);
		row.setHeightInPoints((float) 30);
		HSSFCell cell = null;
		String[] str = { "编号", "任务名称", "任务内容", "责任部门", "责任人", "截止日期", "状态",
				"完成时间" };
		for (int i = 0; i < 8; i++) {
			cell = row.createCell(i + 1);
			cell.setCellValue(str[i]);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			cell.setCellStyle(style);
		}

		HSSFFont ff = workbook.createFont();
		ff.setFontName("宋体");
		ff.setFontHeightInPoints((short) 10);
		ff.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFont(ff);
		style1.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style1.setWrapText(true);
		style1.setBorderLeft((short) 1);
		style1.setBorderRight((short) 1);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		for (int i = 0; i < ls.size(); i++) {
			row = sheet.createRow(i + 2);
			row.setHeightInPoints((float) 30);
			cell = row.createCell(1);
			cell.setCellValue(ls.get(i).getId());
			cell.setCellStyle(style1);

			cell = row.createCell(2);
			cell.setCellValue(ls.get(i).getName());
			cell.setCellStyle(style1);

			cell = row.createCell(3);
			cell.setCellValue(ls.get(i).getContent());
			cell.setCellStyle(style1);

			cell = row.createCell(4);
			cell.setCellValue(ls.get(i).getExedep().getName());
			cell.setCellStyle(style1);

			cell = row.createCell(5);
			cell.setCellValue(ls.get(i).getExeuser().getUsername());
			cell.setCellStyle(style1);

			cell = row.createCell(6);
			cell.setCellValue(ls.get(i).getLastTime().toString());
			cell.setCellStyle(style1);

			cell = row.createCell(7);
			cell.setCellValue(ls.get(i).getStatus());
			cell.setCellStyle(style1);

			cell = row.createCell(8);
			if (ls.get(i).getFinTime() == null)
				cell.setCellValue("无");
			else
				cell.setCellValue(ls.get(i).getFinTime().toString());
			cell.setCellStyle(style1);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		byte[] byteArray = outputStream.toByteArray();
		downloadStream = new ByteArrayInputStream(byteArray, 0,
				byteArray.length);

		outputStream.close();
		return SUCCESS;
	}

	@Action(value = "print", results = { @Result(name = SUCCESS, location = "/admin/print.jsp") })
	public String print() {
		return SUCCESS;
	}

	/**
	 * 返回修改密码页面
	 * 
	 * @return
	 */
	@Action(value = "newpass", results = { @Result(name = SUCCESS, location = "/admin/newpass.jsp") })
	public String newPass() {
		return SUCCESS;
	}

	/**
	 * 返回新建部门页面
	 * 
	 * @return
	 */
	@Action(value = "newdept", results = { @Result(name = SUCCESS, location = "/admin/newdept.jsp") })
	public String newDept() {
		return SUCCESS;
	}

	/**
	 * 新建部门 action
	 * 
	 * @return
	 */
	@Action(value = "adddept", results = { @Result(name = SUCCESS, location = "/admin/newdept.jsp") })
	public String addDept() {
		try {
			adminService.addDepartment(dept);
			setAlertType("success");
			setTips("添加部门成功！");
		} catch (Exception e) {
			setAlertType("error");
			setTips("该部门eeeeeeeee！");
		}
		return SUCCESS;
	}

	/**
	 * 新建部门 action
	 * 
	 * @return
	 */
	@Action(value = "addtask", results = { @Result(name = SUCCESS, location = "/admin/newtask.jsp") })
	public String addTask() {
		try {
			User _u = publicService.findUserById(Integer.parseInt(userid));
			Department _dept = publicService.findDeptById(_u.getDepartment()
					.getId());
			task.setExedep(_dept);
			task.setExeuser(_u);
			adminService.addTask(task);
			setAlertType("success");
			setTips("添加任务成功！");
		} catch (Exception e) {

		}
		return SUCCESS;
	}

	/**
	 * 删除选中部门
	 * 
	 * @return
	 */
	@Action(value = "deletedept", results = { @Result(name = SUCCESS, location = "/admin/checkdept.jsp") })
	public String deleteDept() {
		if (deptid != null && deptid.length > 0) {
			for (int i = 0; i < deptid.length; i++) {
				try {
					adminService.deleteDepartment(Integer.parseInt(deptid[i]));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "deleteemp", results = { @Result(name = SUCCESS, location = "/admin/checkperson.jsp") })
	public String deleteEmp() {
		if (userids != null && userids.length > 0) {
			for (int i = 0; i < userids.length; i++) {
				try {
					adminService.deleteUser(Integer.parseInt(userids[i]));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "deletetask", results = { @Result(name = SUCCESS, location = "/admin/checktask.jsp") })
	public String deleteTask() {
		if (taskids != null && taskids.length > 0) {
			for (int i = 0; i < taskids.length; i++) {
				try {
					adminService.deleteTask(Integer.parseInt(taskids[0]));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 返回新建任务页面
	 * 
	 * @return
	 */
	@Action(value = "newtask", results = { @Result(name = SUCCESS, location = "/admin/newtask.jsp") })
	public String newTask() {
		return SUCCESS;
	}

	@Action(value = "newperson", results = { @Result(name = SUCCESS, location = "/admin/newperson.jsp") })
	public String newPerson() {
		return SUCCESS;
	}

	@Action(value = "addperson", results = { @Result(name = SUCCESS, location = "/admin/newperson.jsp") })
	public String addPerson() {
		try {
			Department _dept = publicService.findDeptById(Integer
					.parseInt(deptid[0]));
			user.setDepartment(_dept);
			if (role.equals("1"))
				user.setRole(Role.MANAGER);
			else if (role.equals("2"))
				user.setRole(Role.USER);
			managerService.addUser(user);
			setAlertType("success");
			setTips("添加人员成功！");
		} catch (Exception e) {
			setAlertType("error");
			setTips("该人员已存在！");
		}
		return SUCCESS;
	}

	/**
	 * 返回查看任务页面
	 * 
	 * @return
	 */
	@Action(value = "checktask", results = { @Result(name = SUCCESS, location = "/admin/checktask.jsp") })
	public String checkTask() {
		return SUCCESS;
	}

	/**
	 * 返回查看部门列表页面
	 * 
	 * @return
	 */
	@Action(value = "checkdept", results = { @Result(name = SUCCESS, location = "/admin/checkdept.jsp") })
	public String checkDept() {
		return SUCCESS;
	}

	@Action(value = "checkperson", results = { @Result(name = SUCCESS, location = "/admin/checkperson.jsp") })
	public String checkPerson() {
		return SUCCESS;
	}

	@Action(value = "updateemp", results = { @Result(name = SUCCESS, location = "/admin/updateemp.jsp") })
	public String updateEmp() {
		User _u = publicService.findUserById(Integer.parseInt(userid));
		System.out.println(_u.getId());
		user = new User();
		user.setId(_u.getId());
		user.setUsername(_u.getUsername());
		user.setDepartment(_u.getDepartment());
		user.setPassword(_u.getPassword());
		user.setRole(_u.getRole());
		return SUCCESS;
	}

	@Action(value = "updatetask", results = {
			@Result(name = SUCCESS, location = "/admin/updatetask.jsp"),
			@Result(name = "distributetask", location = "/admin/distributetask.jsp") })
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
		if (_task.getStatus().equals("未下达"))
			return SUCCESS;
		return "distributetask";
	}

	/**
	 * 更新员工信息
	 * 
	 * @return
	 */
	@Action(value = "updateitem", results = { @Result(name = SUCCESS, location = "/admin/checktask.jsp") })
	public String updateItem() {
		Task _task = publicService.findTaskById(task.getId());
		User _u = publicService.findUserById(Integer.parseInt(userid));
		Department _dept = publicService.findDeptById(_u.getDepartment()
				.getId());
		_task.setContent(task.getContent());
		_task.setExedep(_dept);
		_task.setExeuser(_u);
		if (task.getStatus().equals("已完成")) {
			Date current = new Date();
			_task.setFinTime(current);
		} else
			_task.setFinTime(null);
		_task.setLastTime(task.getLastTime());
		_task.setName(task.getName());
		_task.setStatus(task.getStatus());
		adminService.updateTask(_task);
		return SUCCESS;
	}

	/**
	 * 更新员工信息
	 * 
	 * @return
	 */
	@Action(value = "updateuser", results = { @Result(name = SUCCESS, location = "/admin/checkperson.jsp") })
	public String updateUser() {
		User _u = publicService.findUserById(user.getId());
		Department _dept = publicService.findDeptById(Integer
				.parseInt(deptid[0]));
		_u.setDepartment(_dept);
		if (role.equals("1"))
			_u.setRole(Role.MANAGER);
		else if (role.equals("2"))
			_u.setRole(Role.USER);
		_u.setPassword(user.getPassword());
		adminService.updateUser(_u);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取所有User信息
	 * 
	 * @return
	 */
	public List<User> getUserinfo() {
		List<User> ls = publicService.findAllUsers();
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}

	/**
	 * 获取manager的信息
	 * 
	 * @return
	 */
	public List<User> getManagers() {
		List<User> ls = publicService.findUserByManager();
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}

	/**
	 * 获取所有部门信息
	 * 
	 * @return
	 */
	public List<Department> getDepartments() {
		List<Department> ls = publicService.findAllDepartments();
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}

	/**
	 * 根据日期进行分组
	 */
	public List<Integer> getGroup() {
		List<Task> ls = publicService.findAllTasks();
		List<Integer> rs = new ArrayList<Integer>();
		Date currentDate = new Date();

		for (int i = 0; i < ls.size(); i++) {
			long k = (ls.get(i).getLastTime().getTime() - currentDate.getTime())
					/ (3600 * 24 * 1000);
			if (k > 7)
				rs.add(1);
			else if (k < 7 && k >= 0)
				rs.add(0);
			else
				rs.add(-1);
		}
		for (int i = 0; i < ls.size(); i++)
			System.out.println(rs.get(i));
		if (rs.size() > 0)
			return rs;
		else
			return null;
	}

	/**
	 * 获取所有task信息
	 * 
	 * @return
	 */
	public List<Task> getTasks() {
		List<Task> ls = publicService.findAllTasks();
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String[] getDeptid() {
		return deptid;
	}

	public void setDeptid(String[] deptid) {
		this.deptid = deptid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String[] getUserids() {
		return userids;
	}

	public void setUserids(String[] userids) {
		this.userids = userids;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String[] getTaskids() {
		return taskids;
	}

	public void setTaskids(String[] taskids) {
		this.taskids = taskids;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}
}
