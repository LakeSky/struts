package thettian.study.struts.application.service;

import java.util.List;

import thettian.study.struts.application.model.Task;

public interface UserService {
	
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
}
