package bistu.edu.cn.dao;

import java.util.List;

import bistu.edu.cn.model.MakeUp;
import bistu.edu.cn.model.User;

public interface MakeUpDaoI {
	List<MakeUp> getMakeUpList(String id);
}
