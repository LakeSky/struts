package thettian.study.struts.application.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import thettian.study.struts.application.dao.DepartmentDao;
import thettian.study.struts.application.dao.TaskDao;
import thettian.study.struts.application.dao.UserDao;
import thettian.study.struts.application.model.Department;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;
import thettian.study.struts.application.service.PublicService;

@Service("publicService")
public class PublicServiceImpl implements PublicService {
	@Resource
	private UserDao userDao;
	@Resource
	private DepartmentDao departmentDao;
	@Resource
	private TaskDao taskDao;

	@Override
	public User findUserByusername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentDao.findAll();
	}

	@Override
	public List<Task> findAllTasks() {
		return taskDao.findAll();
	}

}
