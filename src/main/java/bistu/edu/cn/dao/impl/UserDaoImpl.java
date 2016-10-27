package bistu.edu.cn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bistu.edu.cn.dao.UserDaoI;
import bistu.edu.cn.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDaoI
{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) 
	{
		Session session  = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Serializable Login(User usr) 
	{
		Session session  = sessionFactory.openSession();
		session.beginTransaction();
		Query<User> query = session.createQuery("select user from User user where user.id = :id and user.passwd = :passwd")
				.setParameter("id", usr.getId()).setParameter("passwd", usr.getPasswd());
		List<User> users = query.list();
		User user = null;
		for(User u : users)
		{
			System.out.println("user not null!");
			user = new User();
			user.setId(u.getId());
			user.setPasswd(u.getPasswd());
			user.setTimestamp(u.getTimestamp());
		}
		session.getTransaction().commit();
		session.close();
		return user;
	}


}
