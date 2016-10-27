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
import bistu.edu.cn.model.help.TokenHelper;
import bistu.edu.cn.service.CourseServiceI;
import bistu.edu.cn.service.LoginServiceI;
import bistu.edu.cn.service.UserServiceI;
import bistu.edu.cn.utils.DESUtil;

@ParentPackage("basePackage")
@Action(value = "logins",results = {
	@Result(name="success", type="redirectAction", params = {"actionName" , "logins"})})
@Namespace("/api")
public class LoginsController implements ModelDriven<Object>{
	
	@Autowired
	private UserServiceI userServiceI;
	
	@Autowired
	private LoginServiceI loginServiceI;
	
	private String id;
	
	private TokenHelper token;
	
	public HttpHeaders index(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try {
			String id = DESUtil.decrypt(request.getParameter("id"));
			String passwd = DESUtil.decrypt(request.getParameter("passwd"));
			User user = new User();
			System.out.println("id="+id+"passwd="+passwd);
			user.setId(id);
			user.setPasswd(passwd);
			
			Boolean b = (Boolean) userServiceI.login(user);
			if (b)
			{
				System.out.println("验证成功，获取token...");
				token = loginServiceI.getToken(user);
			}
			
		} catch (Exception e) {
			System.out.println("账户或者密码错误！");
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	@Override
	public Object getModel() {
		return token;
	}
}
