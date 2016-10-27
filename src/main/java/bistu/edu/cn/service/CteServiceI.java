package bistu.edu.cn.service;

import java.util.List;

import bistu.edu.cn.model.Cte;
import bistu.edu.cn.model.User;


public interface CteServiceI {
	List<Cte> getCteList(String id,String token);
}
