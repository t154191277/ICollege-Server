package bistu.edu.cn.dao;

import java.util.List;

import bistu.edu.cn.model.Grade;
import bistu.edu.cn.model.User;

public interface GradeDaoI {
	List<Grade> getGradeList(String id);
}
