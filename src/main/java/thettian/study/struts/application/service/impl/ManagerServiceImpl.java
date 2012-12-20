package thettian.study.struts.application.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thettian.study.struts.application.dao.TaskDao;
import thettian.study.struts.application.dao.UserDao;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;
import thettian.study.struts.application.model.enumtype.Role;
import thettian.study.struts.application.service.ManagerService;

@Service("manageService")
public class ManagerServiceImpl implements ManagerService {
	@Resource
	private UserDao userDao;
	@Resource
	private TaskDao taskDao;

	@Override
	public List<Task> findMyUndoTasks(Integer userId) {
		User _exeuser = userDao.findOne(userId);
		return taskDao.findByExeuserAndFinTimeIsNull(_exeuser);
	}

	@Override
	public List<Task> findMyTasks(Integer userId) {
		User _exeuser = userDao.findOne(userId);
		List<Task> ls = taskDao.findByExeuser(_exeuser);
		for (int i = ls.size() - 1; i >= 0; i--)
			if (ls.get(i).getStatus().equals("未下达"))
				ls.remove(i);
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}
	
	@Override
	public List<Task> findDeptTasks(Integer deptId) {
		List<Task> ls = taskDao.findAll();
		for (int i = ls.size() - 1; i >= 0; i--)
			if ((ls.get(i).getExeuser().getDepartment().getId() != deptId) || ls.get(i).getStatus().equals("未下达"))
				ls.remove(i);
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}

	@Override
	public List<Task> findMyDoneTasks(Integer userId) {
		User _exeuser = userDao.findOne(userId);
		return taskDao.findByExeuserAndFinTimeIsNotNull(_exeuser);
	}

	@Override
	@Transactional
	public Task submitTask(Integer taskId) {
		Task _task = taskDao.findOne(taskId);
		if (_task != null) {
			Date _d = new Date();
			_task.setFinTime(_d);
			taskDao.save(_task);
		}
		return _task;
	}

	@Override
	public Task DistriTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User addUser(User user) {
		userDao.save(user);
		return user;
	}

	@Override
	@Transactional
	public User deleteUser(Integer userId) {

		User _u = userDao.findOne(userId);
		if (_u != null)
			userDao.delete(userId);
		return _u;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByDept(Integer deptid) {
		List<User> ls = userDao.findAll();
		for (int i = ls.size() - 1; i >= 0; i--) {
			if (ls.get(i).getDepartment() != null) {
				if (ls.get(i).getDepartment().getId() != deptid)
					ls.remove(i);
			} else
				ls.remove(i);
		}
		if (ls.size() > 0)
			return ls;
		else
			return null;
	}
}
