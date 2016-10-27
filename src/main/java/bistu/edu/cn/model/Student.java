package bistu.edu.cn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@Entity
@Table(name = "student")
public class Student implements Serializable{
	
	@Id
	@Column(name = "id")
	private String id;
	
	@JsonIgnore
	@Column(name = "stuName")
	private String stuName;
	
	@Column(name = "idcart")
	private String idcart;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "marjoy")
	private String marjoy;
	
	@Column(name = "class")
	private String _class;
	
	@ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	private List<Course> courses = new ArrayList<>();
	
	public Student() {
		super();
	}

	@JsonIgnore
	public List<Course> getCourses() {
		return courses;
	}
	@JsonIgnore
	public void setCourses(List<Course> courses) {
		this.courses = courses;
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
