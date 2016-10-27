package bistu.edu.cn.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.TokenHelper;
import bistu.edu.cn.service.UserServiceI;
import bistu.edu.cn.utils.DESUtil;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("basePackage")
@Action(value="users",results = {
		@Result(name = "success",type = "redirectAction",params = {"actionName","users"})})
@Namespace("/api")
public class UsersController implements ModelDriven<Object>{
	
	@Autowired
	private UserServiceI userService;
	
	private User model = new User();
	
	private Collection<TokenHelper> list;
	
	public HttpHeaders index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String token = request.getParameter("token");
//		list = userService.
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    public HttpHeaders create() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpSession session = request.getSession();
    	String id = session.getAttribute("id").toString();
    	String passwd = session.getAttribute("passwd").toString();
    	System.out.println(id+","+passwd);
        return new DefaultHttpHeaders("success")
           	.setLocationId(model.getId());
    }

	@Override
	public Object getModel() {
		return list != null ? list : model;
	}
	
}
