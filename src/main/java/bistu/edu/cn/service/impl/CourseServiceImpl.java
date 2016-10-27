package bistu.edu.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.CourseDaoI;
import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.CourseHelper;
import bistu.edu.cn.service.CourseServiceI;

@Service("courseService")
public class CourseServiceImpl implements CourseServiceI{
	
	@Autowired
	private CourseDaoI courseDao;
	
	@Autowired
	private TokenDaoI tokenDao;
	
	@Override
	public List<CourseHelper> getCourseList(String id,String token) {
		Boolean success = tokenDao.query(id, token);
		if (success)
		{
			return courseDao.getCourseList(id);
		}
		return null;
	}

}
