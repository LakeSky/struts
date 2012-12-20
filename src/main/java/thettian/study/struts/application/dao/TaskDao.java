package thettian.study.struts.application.dao;

import java.util.List;

import thettian.study.struts.application.model.Department;
import thettian.study.struts.application.model.Task;
import thettian.study.struts.application.model.User;
import thettian.study.struts.base.BaseDao;

public interface TaskDao extends BaseDao<Task, Integer>{
	public List<Task> findByExeuserAndFinTimeIsNull(User exeuser);
	
	public List<Task> findByExeuserAndFinTimeIsNotNull(User exeuser);
	
	public List<Task> findByExeuser(User exeuser);
}
