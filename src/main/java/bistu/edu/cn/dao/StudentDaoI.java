package bistu.edu.cn.dao;

import java.io.Serializable;
import java.util.List;

import bistu.edu.cn.model.Student;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.StudentHelper;

public interface StudentDaoI {
	List<StudentHelper> getStudentList(String id);
}
