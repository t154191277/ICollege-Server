package bistu.edu.cn.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import bistu.edu.cn.model.Course;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.CourseHelper;
import bistu.edu.cn.service.CourseServiceI;

@ParentPackage("basePackage")
@Action(value = "courses",results = {
	@Result(name="success", type="redirectAction", params = {"actionName" , "courses"})})
@Namespace("/api")
public class CoursesController implements ModelDriven<Object>{
	
	@Autowired
	private CourseServiceI courseService;
	
	private String id;
	
	private Collection<CourseHelper> list = null;
	
	public HttpHeaders index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		list = courseService.getCourseList(id, token);
		return new DefaultHttpHeaders("index").disableCaching();
	}

	@Override
	public Object getModel() {
		return list;
	}
}
