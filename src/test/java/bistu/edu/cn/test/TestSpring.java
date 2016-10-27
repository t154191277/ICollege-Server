//package bistu.edu.cn.test;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import bistu.edu.cn.model.User;
//import bistu.edu.cn.service.CourseServiceI;
//import bistu.edu.cn.service.CteServiceI;
//import bistu.edu.cn.service.GradeServiceI;
//import bistu.edu.cn.service.MakeUpServiceI;
//import bistu.edu.cn.service.StudentServiceI;
//import bistu.edu.cn.service.UserServiceI;
//
//public class TestSpring {
////	@Test
//	public void testUserService(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
//		CourseServiceI userServiceI = (CourseServiceI) ac.getBean("userService");
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
////		userServiceI.getCourseJson(user);
//	}
//	
////	@Test
//	public void testStudentService(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
//		StudentServiceI studentServiceI = (StudentServiceI) ac.getBean("studentService");
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String s = studentServiceI.getStudentList(user);
//		System.out.println(s);
//	}
//	
//	@Test
//	public void testCourseService(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
//		CourseServiceI userServiceI = (CourseServiceI) ac.getBean("courseService");
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
////		String s = userServiceI.getCourseList(user);
////		System.out.println(s);
//	}
//	
////	@Test
//	public void testCteService(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
//		CteServiceI cteServiceI = (CteServiceI) ac.getBean("cteService");
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String s = cteServiceI.getCteJson(user);
//		System.out.println(s);
//	}
//	
////	@Test
//	public void testGradeService(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
//		GradeServiceI gradeServiceI = (GradeServiceI) ac.getBean("gradeService");
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String s = gradeServiceI.getGradeJson(user);
//		System.out.println(s);
//	}
//	
////	@Test
//	public void testMakeUpService(){
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-hibernate.xml"});
//		MakeUpServiceI gradeServiceI = (MakeUpServiceI) ac.getBean("makeUpService");
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String s = gradeServiceI.getGradejson(user);
//		System.out.println(s);
//	}
//}
