package bistu.edu.cn.dao;

import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.TokenHelper;

public interface TokenDaoI {
	
	Boolean query(String id,String token);
	
	TokenHelper get(User user);
	
	TokenHelper create(User user);
}
