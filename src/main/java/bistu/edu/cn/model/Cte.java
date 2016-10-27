package bistu.edu.cn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cte")
public class Cte {

	
	@Column(name = "id")
	private String id;

	@Column(name = "schoolyear")
	private String schoolyear;

	@Column(name = "semester")
	private String semester;

	@Column(name = "rate")
	private String rate;

	@Id
	@Column(name = "ticketNum")
	private String ticket;

	@Column(name = "date")
	private String date;

	@Column(name = "total")
	private String total;

	@Column(name = "listenting")
	private String listenting;

	@Column(name = "reading")
	private String reading;

	@Column(name = "writing")
	private String writing;

	@Column(name = "complex")
	private String complex;

	public Cte() {
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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getListenting() {
		return listenting;
	}

	public void setListenting(String listenting) {
		this.listenting = listenting;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getComplex() {
		return complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}

}
