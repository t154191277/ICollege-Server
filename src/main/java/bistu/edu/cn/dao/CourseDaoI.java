package bistu.edu.cn.dao;

import java.util.List;

import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.CourseHelper;

public interface CourseDaoI {
	List<CourseHelper> getCourseList(String id);
}
