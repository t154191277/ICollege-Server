package bistu.edu.cn.service;

import java.util.List;

import bistu.edu.cn.model.Grade;
import bistu.edu.cn.model.User;

public interface GradeServiceI {
	List<Grade> getGradeList(String id, String token);
}
