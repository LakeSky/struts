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
import thettian.study.struts.application.model.enumtype.Role;
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
	
	@Override
	public User updateUser(User _user){
		return userDao.save(_user);
	}
	
	@Override
	public Department findDeptById(Integer id){
		return departmentDao.findOne(id);
	}

	@Override
	public User findUserById(Integer id) {
		return userDao.findOne(id);
	}
	
	@Override
	public List<User> findUserByManager(){
		List<User> ls = userDao.findAll();
		for(int i=ls.size()-1;i>=0;i--){
			if(ls.get(i).getRole() != Role.MANAGER)
				ls.remove(i);
		}
		if(ls.size()>0)
			return ls;
		else
			return null;
	}
	
	@Override
	public Task findTaskById(Integer taskid){
		return taskDao.findOne(taskid);
	}
}
