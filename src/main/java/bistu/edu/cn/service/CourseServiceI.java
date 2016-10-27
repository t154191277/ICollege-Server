package bistu.edu.cn.service;

import java.util.List;

import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.CourseHelper;

public interface CourseServiceI {

	List<CourseHelper> getCourseList(String id,String token);
}
