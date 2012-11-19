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
import thettian.study.struts.application.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

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
	public List<Task> findMyDoneTasks(Integer userId) {
		User _exeuser = userDao.findOne(userId);
		return taskDao.findByExeuserAndFinTimeIsNotNull(_exeuser);
	}

	@Override
	@Transactional
	public Task submitTask(Integer taskId) {
		Task _task = taskDao.findOne(taskId);
		if(_task != null){
			Date _d = new Date();
			_task.setFinTime(_d);
			taskDao.save(_task);
		}
		return _task;
	}

}
