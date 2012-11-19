package thettian.study.struts.application.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thettian.study.struts.application.dao.DepartmentDao;
import thettian.study.struts.application.dao.TaskDao;
import thettian.study.struts.application.dao.UserDao;
import thettian.study.struts.application.model.Department;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;
import thettian.study.struts.application.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Resource
	private UserDao userDao;
	@Resource
	private DepartmentDao departmentDao;
	@Resource
	private TaskDao taskDao;
	
	@Override
	public Task DistriTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Department addDepartment(Department department,User manager) {
		User _u = userDao.save(manager);
		department.setManager(_u);
		departmentDao.save(department);
		return department;
	}

	@Override
	public Task addTask(Task task) {
		return taskDao.save(task);
	}

	@Override
	public Department deleteDepartment(Integer deptId) {
		Department _d = departmentDao.findOne(deptId);
		if(_d != null)
			departmentDao.delete(deptId);
		return _d;
	}

	@Override
	public Task deleteTask(Integer taskId) {
		Task _t = taskDao.findOne(taskId);
		if(_t != null)
			taskDao.delete(taskId);
		return _t;
	}

	@Override
	public Department updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

}
