package bistu.edu.cn.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.SocketMetadata.Helper;

import bistu.edu.cn.dao.TokenDaoI;
import bistu.edu.cn.model.Token;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.TokenHelper;

@Repository("tokenDao")
public class TokenDaoImpl implements TokenDaoI{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public TokenHelper get(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Token> query = session.createQuery("select token from Token token where token.id = :id")
				.setParameter("id", user.getId());
		List<Token> list = query.list();
		TokenHelper helper = null;
		for(Token t : list)
		{
			helper = new TokenHelper();
			helper.setToken(t.getToken());
		}
		session.getTransaction().commit();
		session.close();
		return helper;
	}
	
	@Override
	public Boolean query(String id,String token) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Token> query = session.createQuery("select token from Token token where token.id = :id and token.token = :token")
				.setParameter("id", id).setParameter("token", token);
		List<Token> list = query.list();
		TokenHelper helper = null;
		session.getTransaction().commit();
		session.close();
		return list.size() == 1;
	}

	@Override
	public TokenHelper create(User user) {
		Token token = new Token();
		String tokenStr = UUID.randomUUID().toString();
		token.setId(user.getId());
		token.setToken(tokenStr);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(token);
		session.getTransaction().commit();
		session.close();
		
		TokenHelper helper = new TokenHelper();
		helper.setToken(tokenStr);
		return helper;
	}
}
