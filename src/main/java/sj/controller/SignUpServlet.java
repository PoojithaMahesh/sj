package sj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.dao.StudentDao;
import sj.dto.Student;

public class SignUpServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String address=req.getParameter("address");
	long phone=Long.parseLong(req.getParameter("phone"));
	String course=req.getParameter("course");
	
	double dfees=Double.parseDouble(getServletContext().getInitParameter("developmentfees"));
	double tfees=Double.parseDouble(getServletContext().getInitParameter("testingfees"));
	
	
	
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setName(name);
	student.setCourse(course);
	student.setPassword(password);
	student.setPhone(phone);
	
	if(course.equals("Development")) {
		student.setFees(dfees);
	}else {
		student.setFees(tfees);
	}
	
	StudentDao studentDao=new StudentDao();
	List<Student> list=studentDao.getAllStudents();
	boolean value=true;
	for(Student st:list) {
		if(email.equals(st.getEmail())) {
			value=false;
			break;
		}
	}
	
	if(value) {
//		email is not present in the database so we can let him to signup
		studentDao.signupStudent(student);
		PrintWriter printWriter=resp.getWriter();
		printWriter.print("yes signedUpsuccessfully");
	}else {
//		email already present means that email is already taken by other student
		req.setAttribute("message", "Sorry EMail Already exist please give different email");
		RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
		dispatcher.include(req, resp);
		
	}
	
	
	
	
	
}
}
