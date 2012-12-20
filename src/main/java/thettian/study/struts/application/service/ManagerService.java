package thettian.study.struts.application.service;

import java.util.List;

import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;

public interface ManagerService {
	/**
	 * 查询用户未做任务
	 * @param userId 用户id
	 * @return
	 */
	public List<Task> findMyUndoTasks(Integer userId);
	
	/**
	 * 查询用户已做任务
	 * @param userId 用户id
	 * @return
	 */
	public List<Task> findMyDoneTasks(Integer userId);
	
	/**
	 * 提交任务
	 * @param taskId 任务id
	 * @return
	 */
	public Task submitTask(Integer taskId);
	
	/**
	 * 分发任务
	 * @param task 任务信息
	 * @return
	 */
	public Task DistriTask(Task task);
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public User addUser(User user);
	
	/**
	 * 删除用户信息
	 * @param userId
	 * @return
	 */
	public User deleteUser(Integer userId);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public User updateUser(User user);

	public List<User> findUserByDept(Integer deptid);

	public List<Task> findMyTasks(Integer deptId);

	public List<Task> findDeptTasks(Integer userId);
}
