//package bistu.edu.cn.action;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.Date;
//import java.util.Enumeration;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.ParentPackage;
//import org.apache.struts2.util.IteratorFilterSupport.EnumerationIterator;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.opensymphony.xwork2.ActionContext;
//
//import bistu.edu.cn.dao.CourseDaoI;
//import bistu.edu.cn.model.User;
//import bistu.edu.cn.service.CourseServiceI;
//import bistu.edu.cn.service.CteServiceI;
//import bistu.edu.cn.service.GradeServiceI;
//import bistu.edu.cn.service.MakeUpServiceI;
//import bistu.edu.cn.service.StudentServiceI;
//import bistu.edu.cn.service.UserServiceI;
//
//
//@ParentPackage("basePackage")
//@Action(value="struts2Test")
//@Namespace("/api")
//public class TestAction {
//	private static int i = 0;
//	
//	@Autowired
//	private UserServiceI userService;
//	
//	@Autowired
//	private CourseServiceI courseService;
//	
//	@Autowired
//	private StudentServiceI studentService;
//	
//	@Autowired
//	private MakeUpServiceI makeUpService;
//	
//	@Autowired
//	private GradeServiceI gradeServiceI;
//	
//	@Autowired
//	private CteServiceI cteServiceI;
//
//	public void test(){
//		System.out.println("进入TestAction");
//		userService.test();
//	}
//	
//	public void saveUser(){
//		User user = new User();
//		user.setId("201586565");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		userService.save(user);
//	}
//	
//	public void testRequest() 
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		InputStream isr;
//		try {
//			isr = request.getInputStream();
//			String s = null;
//			s = IOUtils.toString(isr,"utf-8");
//			System.out.println("res = " + s);
//			System.out.println("method = " + request.getMethod());
//			System.out.println("contenttype = " +request.getContentType());
//			System.out.println("address = " + request.getRemoteAddr());
//			System.out.println("pathinfo = " + request.getPathInfo());
//			System.out.println("pathinfo = " + request.getCharacterEncoding());
//			System.out.println("connection = " + request.getHeader("connection"));
//
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setContentType("text/html;charset=utf-8");
//			response.setCharacterEncoding("utf-8");
//			OutputStream os;
//			os = response.getOutputStream();
//			os.write(("from server" + i++).getBytes());
//			os.flush();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void testCourse(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		User user = new User();
//		user.setId("2014011338");
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String info = courseService.getCourseJson(user);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		OutputStream os;
//		try {
//			os = response.getOutputStream();
//			os.write(info.getBytes("utf-8"));
//			os.flush();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void testStudent(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String id = request.getParameter("id");
//		User user = new User();
//		user.setId(id);
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String info = studentService.getStudentJson(user);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		OutputStream os;
//		try {
//			os = response.getOutputStream();
//			os.write(info.getBytes("utf-8"));
//			os.flush();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	public void testMakeUp(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String id = request.getParameter("id");
//		User user = new User();
//		user.setId(id);
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String info = makeUpService.getGradejson(user);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		OutputStream os;
//		try {
//			os = response.getOutputStream();
//			os.write(info.getBytes("utf-8"));
//			os.flush();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void testGrade(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String id = request.getParameter("id");
//		User user = new User();
//		user.setId(id);
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String info = gradeServiceI.getGradeJson(user);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		OutputStream os;
//		try {
//			os = response.getOutputStream();
//			os.write(info.getBytes("utf-8"));
//			os.flush();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void testCte(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String id = request.getParameter("id");
//		User user = new User();
//		user.setId(id);
//		user.setPasswd("qqq");
//		user.setTimestamp(new Date());
//		String info = cteServiceI.getCteJson(user);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		OutputStream os;
//		try {
//			os = response.getOutputStream();
//			os.write(info.getBytes("utf-8"));
//			os.flush();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	   
//}
