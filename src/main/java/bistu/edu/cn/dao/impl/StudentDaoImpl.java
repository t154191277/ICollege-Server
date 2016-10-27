package bistu.edu.cn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bistu.edu.cn.dao.StudentDaoI;
import bistu.edu.cn.model.Student;
import bistu.edu.cn.model.User;
import bistu.edu.cn.model.help.StudentHelper;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDaoI{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<StudentHelper> getStudentList(String id)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<Student> query = session.createQuery("select student from Student student where student.id = :id").setParameter("id", id);
		List<Student> list = query.list();
		List<StudentHelper> helperList = new ArrayList<StudentHelper>();
		for(Student s:list){
			StudentHelper helper = new StudentHelper();
			helper.setId(s.getId());
			helper.setIdcart(s.getIdcart());
			helper.setMarjoy(s.getMarjoy());
			helper.setDepartment(s.getDepartment());
			helper.setStuName(s.getStuName());
			helper.set_class(s.get_class());
			helperList.add(helper);
		}
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String jsonString = gson.toJson(list);
		session.getTransaction().commit();
		session.close();
		return helperList;
	}

}
