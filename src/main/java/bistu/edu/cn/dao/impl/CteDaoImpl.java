package bistu.edu.cn.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import bistu.edu.cn.dao.CteDaoI;
import bistu.edu.cn.model.Cte;
import bistu.edu.cn.model.User;

@Repository
public class CteDaoImpl implements CteDaoI{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Cte> getCteList(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Cte> query = session.createQuery("select cte from Cte cte where cte.id = :id").setParameter("id", id);
		List<Cte> cteList = query.list();
		String jsonStr = new Gson().toJson(cteList);
		session.getTransaction().commit();
		session.close();
		return cteList;
	}

}
