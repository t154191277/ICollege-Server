package bistu.edu.cn.model.help;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import bistu.edu.cn.model.Student;

import com.google.gson.annotations.Expose;

public class CourseHelper {
	@Expose
	private String id;

	@Expose
	private String courseCode;

	@Expose
	private String courseName;

	@Expose
	private String courseType;

	@Expose
	private String teacher;

	@Expose
	private float credit;

	@Expose
	private List<String> courseTime = new ArrayList<String>();

	@Expose
	private List<String> place = new ArrayList<String>();
	
	public CourseHelper() {
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

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public List<String> getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(List<String> courseTime) {
		this.courseTime = courseTime;
	}

	public List<String> getPlace() {
		return place;
	}

	public void setPlace(List<String> place) {
		this.place = place;
	}
	
	

}
