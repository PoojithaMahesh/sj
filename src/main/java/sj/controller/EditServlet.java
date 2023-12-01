package sj.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.dao.StudentDao;
import sj.dto.Student;
@WebServlet("/edit")
public class EditServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String address=req.getParameter("address");
	long phone=Long.parseLong(req.getParameter("phone"));
	String course=req.getParameter("course");
	
	Student student=new Student();
	student.setAddress(address);
	student.setCourse(course);
	student.setEmail(email);
	student.setId(id);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	
	ServletContext context=getServletContext();
	double dfees=Double.parseDouble(context.getInitParameter("developmentfees"));
	double tfees=Double.parseDouble(context.getInitParameter("testingfees"));
	
	if(course.equals("Development")) {
		student.setFees(dfees);
	}else {
		student.setFees(tfees);
	}
	
	StudentDao dao=new StudentDao();
	dao.UpdateStudent(student);
	
	
	
	
	
	
	
	
	
	
	Cookie[] cookies=req.getCookies();
	String nameWhoChangedTheDetails=null;
	for(Cookie cookie:cookies) {
		if(cookie.getName().equals("namewhologgein")) {
			nameWhoChangedTheDetails=cookie.getValue();
		}
	}
	PrintWriter printWriter=resp.getWriter();
	printWriter.print(nameWhoChangedTheDetails);
	req.setAttribute("studentNameWhoChangedTheDetails", nameWhoChangedTheDetails);
	req.setAttribute("list", dao.getAllStudents());
	RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
	dispatcher.forward(req, resp);
	
//	req.setAttribute("list", dao.getAllStudents());
//	req.setAttribute("studentwhologgedInandChangedthedetails", nameWhoChangedTheDetails);
//	RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
//	dispatcher.forward(req, resp);
//	
	
}
}
