package bistu.edu.cn.service;

import java.util.List;

import bistu.edu.cn.model.MakeUp;
import bistu.edu.cn.model.User;

public interface MakeUpServiceI {
	List<MakeUp> getMakeUpList(String id, String token);
}
