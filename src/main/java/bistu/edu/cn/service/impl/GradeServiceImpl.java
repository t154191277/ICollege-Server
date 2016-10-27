package bistu.edu.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.GradeDaoI;
import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.Grade;
import bistu.edu.cn.model.User;
import bistu.edu.cn.service.GradeServiceI;

@Service("gradeService")
public class GradeServiceImpl implements GradeServiceI {
	
	@Autowired
	private GradeDaoI gradeDao;

	@Autowired
	private TokenDaoI tokenDao;
	
	@Override
	public List<Grade> getGradeList(String id, String token) {
		Boolean success = tokenDao.query(id, token);
		if (success)
		{
			return gradeDao.getGradeList(id);
		}
		return null;
	}

}
