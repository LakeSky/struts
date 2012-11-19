package thettian.study.struts.application.dao;

import thettian.study.struts.application.model.User;
import thettian.study.struts.base.BaseDao;

public interface UserDao extends BaseDao<User, Integer>{
	
	public User findByUsername(String username);
}
