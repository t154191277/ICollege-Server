package bistu.edu.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.CteDaoI;
import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.Cte;
import bistu.edu.cn.model.User;
import bistu.edu.cn.service.CteServiceI;

@Service("cteService")
public class CteServiceImpl implements CteServiceI {
	
	@Autowired
	private CteDaoI cteDao;
	
	@Autowired
	private TokenDaoI tokenDao;

	@Override
	public List<Cte> getCteList(String id, String token) {
		Boolean success = tokenDao.query(id, token);
		if (success)
		{
			return cteDao.getCteList(id);
		}
		return null;
	}

}
