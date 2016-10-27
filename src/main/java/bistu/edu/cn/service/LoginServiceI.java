package bistu.edu.cn.service;

import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.TokenHelper;

public interface LoginServiceI {
	TokenHelper getToken(User user);
}
