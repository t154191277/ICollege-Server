package bistu.edu.cn.service;

import java.io.Serializable;

import bistu.edu.cn.model.User;

/**
 * 测试
 * @author wmlove
 *
 */
public interface UserServiceI {
	
	
	/**
	 * 登录
	 * @param id
	 * @return
	 */
	Serializable login(User user);
}
