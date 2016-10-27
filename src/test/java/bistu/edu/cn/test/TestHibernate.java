package bistu.edu.cn.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bistu.edu.cn.model.User;
import bistu.edu.cn.service.UserServiceI;

public class TestHibernate {
	
	private UserServiceI userService;
	
	@Before
	public void before(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
		userService = (UserServiceI) ac.getBean("userService");
	}
	
	@Test
	public void testSaveMethod(){
		User user = new User();
		user.setId("20140113");
		user.setPasswd("www");
		user.setTimestamp(new Date());
		userService.login(user);
	}
}
