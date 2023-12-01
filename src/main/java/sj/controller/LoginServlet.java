package sj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sj.dao.StudentDao;
import sj.dto.Student;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		
		StudentDao studentDao=new StudentDao();
		List<Student> students=studentDao.getAllStudents();
		boolean value=false;
		String studentPassword=null;
		String studentWhoLoggedIn=null;
		
		for(Student student:students) {
			if(email.equals(student.getEmail())) {
//				email is present
				value=true;
				studentPassword=student.getPassword();
//				cookies concept
				studentWhoLoggedIn=student.getName();
				break;
			}
		}
		if(value) {
//		value=true  email is present then i can compare the passwords
			
			if(password.equals(studentPassword)) {
//				login success
				
				
				Cookie cookie=new Cookie("namewhologgein", studentWhoLoggedIn);
				resp.addCookie(cookie);
				
				
				HttpSession httpSession=req.getSession();
				httpSession.setAttribute("name", studentWhoLoggedIn);
				
				
				List<Student> list=studentDao.getAllStudents();
				req.setAttribute("list", list);
				RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
				dispatcher.forward(req, resp);
			}else {
//				password doesnot matches
				req.setAttribute("message", "Invalid Password");
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
				dispatcher.include(req, resp);

			}
			
		}else {
//	email is not present
			req.setAttribute("message", "Invalid EMail");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
