package bistu.edu.cn.dao;

import java.util.List;

import bistu.edu.cn.model.Cte;
import bistu.edu.cn.model.User;

public interface CteDaoI {
	List<Cte> getCteList(String id);
}
