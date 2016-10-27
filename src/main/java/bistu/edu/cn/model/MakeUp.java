package bistu.edu.cn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "makeup")
public class MakeUp {

	@Column(name = "id")
	private String id;

	@Id
	@Column(name = "courseCode")
	private String courseCode;

	@Column(name = "courseName")
	private String courseName;

	@Column(name = "time")
	private String time;

	@Column(name = "onwhere")
	private String where;

	@Column(name = "type")
	private String type;

	@Column(name = "number")
	private String number;

	public MakeUp() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
