package thettian.study.struts.application.service;

import java.util.List;

import thettian.study.struts.application.model.Department;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;

public interface PublicService {
	
	/**
	 * 依据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public User findUserByusername(String username);
	
	/**
	 * 查询所有的用户信息
	 * @return
	 */
	public List<User> findAllUsers();
	
	/**
	 * 查询所有的部门信息
	 * @return
	 */
	public List<Department> findAllDepartments();
	
	/**
	 * 查询所有的任务信息
	 * @return
	 */
	public List<Task> findAllTasks();

	/**
	 * 更新个人信息
	 * @param _user
	 * @return
	 */
	public User updateUser(User _user);

	/**
	 * 根据ID查询部门
	 * @param id
	 * @return
	 */
	public Department findDeptById(Integer id);
	
	public User findUserById(Integer id);

	public List<User> findUserByManager();

	public Task findTaskById(Integer taskid);
}
