package bistu.edu.cn.service;

import java.util.List;

import bistu.edu.cn.model.Student;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.StudentHelper;

public interface StudentServiceI {
	List<StudentHelper> getStudentList(String id, String token);
}
