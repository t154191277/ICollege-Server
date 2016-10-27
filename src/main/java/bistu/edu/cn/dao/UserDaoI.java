package bistu.edu.cn.dao;

import java.io.Serializable;

import bistu.edu.cn.model.User;

/**
 * UserDaoI
 * @author wmlove
 *
 */
public interface UserDaoI {
	
	/**
	 * save user
	 * @param user
	 * @return
	 */
	void save(User user);
	
	Serializable Login(User user);
	
}
