package thettian.study.struts.application.service;

import thettian.study.struts.application.model.Department;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;

public interface AdminService {

	/**
	 * 分发任务
	 * 
	 * @param task
	 *            任务信息
	 * @return
	 */
	public Task DistriTask(Task task);

	/**
	 * 添加部门信息
	 * 
	 * @param department
	 * @return
	 */
	public Department addDepartment(Department department);

	/**
	 * 添加任务信息
	 * 
	 * @param task
	 * @return
	 */
	public Task addTask(Task task);

	/**
	 * 删除部门信息
	 * 
	 * @param deptId
	 * @return
	 */
	public Department deleteDepartment(Integer deptId);

	/**
	 * 删除任务信息
	 * 
	 * @param taskId
	 * @return
	 */
	public Task deleteTask(Integer taskId);
	
	/**
	 * 更新部门信息
	 * @param dept 部门信息
	 * @return
	 */
	public Department updateDepartment(Department dept);
	
	/**
	 * 更新任务信息
	 * @param task 任务信息
	 * @return
	 */
	public Task updateTask(Task task);

	public User deleteUser(Integer userId);

	public User updateUser(User user);
}
