package bistu.edu.cn.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Exam {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "courseCode")
	private String courseCode;

	@Column(name = "time")
	private String time;

	@Column(name = "onwhere")
	private String where;

	@Column(name = "type")
	private String type;

	@Column(name = "number")
	private String number;

	@Column(name = "campus")
	private String campus;

	public Exam() {
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

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

}
