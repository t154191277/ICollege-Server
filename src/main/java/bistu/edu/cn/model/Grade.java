package bistu.edu.cn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {
	
	@Column(name = "id")
	private String id;

	@Column(name = "schoolyear")
	private String schoolyear;

	@Column(name = "semester")
	private String semester;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "credit")
	private String credit;
	
	@Id
	@Column(name = "courseId")
	private String courseId;

	@Column(name = "point")
	private Float point;

	@Column(name = "peacegrade")
	private Float peacegrade;

	@Column(name = "midgrade")
	private Integer midgrade;

	@Column(name = "finalgrade")
	private Integer finalgrade;

	@Column(name = "testgrade")
	private Integer testgrade;

	@Column(name = "grade")
	private String grade;

	@Column(name = "makeupgrade")
	private Integer makeupgrade;

	@Column(name = "regrade")
	private Integer regrade;

	@Column(name = "department")
	private String department;

	@Column(name = "tip")
	private String tip;

	public Grade() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolyear() {
		return schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Float getPoint() {
		return point;
	}

	public void setPoint(Float point) {
		this.point = point;
	}

	public Float getPeacegrade() {
		return peacegrade;
	}

	public void setPeacegrade(Float peacegrade) {
		this.peacegrade = peacegrade;
	}

	public Integer getMidgrade() {
		return midgrade;
	}

	public void setMidgrade(Integer midgrade) {
		this.midgrade = midgrade;
	}

	public Integer getFinalgrade() {
		return finalgrade;
	}

	public void setFinalgrade(Integer finalgrade) {
		this.finalgrade = finalgrade;
	}

	public Integer getTestgrade() {
		return testgrade;
	}

	public void setTestgrade(Integer testgrade) {
		this.testgrade = testgrade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getMakeupgrade() {
		return makeupgrade;
	}

	public void setMakeupgrade(Integer makeupgrade) {
		this.makeupgrade = makeupgrade;
	}

	public Integer getRegrade() {
		return regrade;
	}

	public void setRegrade(Integer regrade) {
		this.regrade = regrade;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
