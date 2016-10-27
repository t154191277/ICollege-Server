package bistu.edu.cn.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import bistu.edu.cn.dao.GradeDaoI;
import bistu.edu.cn.model.Grade;
import bistu.edu.cn.model.User;

@Repository
public class GradeDaoImpl implements GradeDaoI{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Grade> getGradeList(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Grade> query = session.createQuery("select grade from Grade grade where grade.id = :id").setParameter("id", id);
		List<Grade> gradeList = query.list();
		String jsonStr = new Gson().toJson(gradeList);
		session.getTransaction().commit();
		session.close();
		return gradeList;
	}

}
