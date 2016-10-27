package bistu.edu.cn.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.UserDaoI;
import bistu.edu.cn.model.User;
import bistu.edu.cn.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI{
	
	@Autowired
	private UserDaoI userDao;

	@Override
	public Serializable login(User user) {
		//查询数据库是否有此user
		User usr = (User) userDao.Login(user);
		
		if (null == usr)
		{
			//数据库中没有则调用脚本尝试模拟登陆，
			//模拟登陆
			{
				System.out.println("模拟登陆");
			}
			if(false)
			{
				//将账户密码储存到数据库
				userDao.save(user);
			}
			//模拟登录失败则账户密码错误
			else 
			{
				return null;
			}
			//
		}
		//验证成功4
		else
		{
			return true;
		}
		return false;
	}

}
