package bistu.edu.cn.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bistu.edu.cn.dao.CourseDaoI;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.Course;
import bistu.edu.cn.model.help.CourseHelper;

@Repository("courseDao")
public class CourseDaoImpl implements CourseDaoI{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<CourseHelper> getCourseList(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Course> query = session.createQuery("select c from Course c join c.students s where s.id = :id").setParameter("id", id);
		List<Course> list = query.list();
		List<CourseHelper> helpList =new ArrayList<CourseHelper>();
		for(Course course : list){
			List<String> timelist = new ArrayList<String>();
			String times = course.getCourseTime();
			String[] time = times.split(";");
			for(String s : time){
				System.out.println(s);
				timelist.add(s);
			}
			List<String> placelist = new ArrayList<String>();
			String places = course.getPlace();
			String[] place = places.split(";");
			for(String s:place){
				System.out.println(s);
				placelist.add(s);
			}
			
			CourseHelper help = new CourseHelper();
			help.setCourseCode(course.getCourseCode());
			help.setCourseName(course.getCourseName());
			help.setId(course.getId());
			help.setCourseType(course.getCourseType());
			help.setCourseTime(timelist);
			help.setPlace(placelist);
			help.setCredit(course.getCredit());
			help.setTeacher(course.getTeacher());
			helpList.add(help);
		}
		Gson g= new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
		String jsonString = g.toJson(helpList);
		session.getTransaction().commit();
		session.close();
		return helpList.size() > 0 ? helpList : null;
	}
}
