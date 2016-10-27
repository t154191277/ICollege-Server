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
import bistu.edu.cn.model.Grade;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.CourseHelper;
import bistu.edu.cn.service.CourseServiceI;
import bistu.edu.cn.service.GradeServiceI;

@ParentPackage("basePackage")
@Action(value = "grades",results = {
	@Result(name="success", type="redirectAction", params = {"actionName", "grades"})})
@Namespace("/api")
public class GradesController implements ModelDriven<Object>{
	
	@Autowired
	private GradeServiceI gradeServiceI;
	
	private CourseHelper model = new CourseHelper();
	
	private String id;
	
	private Collection<Grade> list;
	
	public HttpHeaders index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		list = gradeServiceI.getGradeList(id, token);
		return new DefaultHttpHeaders("index").disableCaching();
	}

	@Override
	public Object getModel() {
		return (list!=null ? list : model);
	}
}
