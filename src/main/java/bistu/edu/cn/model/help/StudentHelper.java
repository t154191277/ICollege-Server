package bistu.edu.cn.model.help;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentHelper {
	private String id;
	
	private String stuName;
	
	private String idcart;
	
	private String department;
	
	private String marjoy;
	
	private String _class;
	

	public StudentHelper() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getIdcart() {
		return idcart;
	}

	public void setIdcart(String idcart) {
		this.idcart = idcart;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMarjoy() {
		return marjoy;
	}

	public void setMarjoy(String marjoy) {
		this.marjoy = marjoy;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}
	
	
}
