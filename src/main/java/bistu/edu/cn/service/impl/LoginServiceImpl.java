package bistu.edu.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.TokenHelper;
import bistu.edu.cn.service.LoginServiceI;

@Service("tokenService")
public class LoginServiceImpl implements LoginServiceI
{
	
	@Autowired
	private TokenDaoI tokenDao;

	@Override
	public TokenHelper getToken(User user) 
	{
		TokenHelper helper = tokenDao.get(user);
		if(null == helper)
		{
			return tokenDao.create(user);
		}
		return helper;
	}
	
}
