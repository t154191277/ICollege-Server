package bistu.edu.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.MakeUpDaoI;
import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.MakeUp;
import bistu.edu.cn.model.User;
import bistu.edu.cn.service.MakeUpServiceI;

@Service("makeUpService")
public class MakeUpServiceImpl implements MakeUpServiceI {
	
	@Autowired
	private MakeUpDaoI makeUpDao;
	
	@Autowired
	private TokenDaoI tokenDao;

	@Override
	public List<MakeUp> getMakeUpList(String id, String token) {
		Boolean success = tokenDao.query(id, token);
		if (success)
		{
			return makeUpDao.getMakeUpList(id);
		}
		return null;
	}

}
