package bistu.edu.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bistu.edu.cn.dao.StudentDaoI;
import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.Student;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.StudentHelper;
import bistu.edu.cn.service.StudentServiceI;

@Service("studentService")
public class StudentServiceImpl implements StudentServiceI{
	
	@Autowired
	private StudentDaoI studentDao;
	
	@Autowired
	private TokenDaoI tokenDao;

	@Override
	public List<StudentHelper> getStudentList(String id, String token) {
		Boolean success = tokenDao.query(id, token);
		if (success)
		{
			return studentDao.getStudentList(id);
		}
		return null;
	}

}
