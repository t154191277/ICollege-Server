package bistu.edu.cn.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import bistu.edu.cn.dao.MakeUpDaoI;
import bistu.edu.cn.model.Grade;
import bistu.edu.cn.model.MakeUp;
import bistu.edu.cn.model.User;

@Repository
public class MakeUpDaoImpl implements MakeUpDaoI {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MakeUp> getMakeUpList(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<MakeUp> query = session.createQuery("select makeup from MakeUp makeup where makeup.id = :id").setParameter("id", id);
		List<MakeUp> list = query.list();
		String jsonStr = new Gson().toJson(list);
		session.getTransaction().commit();
		session.close();
		return list;
	}

}
