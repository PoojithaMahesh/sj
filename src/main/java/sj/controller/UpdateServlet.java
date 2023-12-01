package sj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sj.dao.StudentDao;
import sj.dto.Student;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int id=Integer.parseInt(req.getParameter("id"));
StudentDao studentDao=new StudentDao();
Student student=studentDao.getStudentById(id);
HttpSession httpSession=req.getSession();
String name=(String) httpSession.getAttribute("name");
if(name!=null) {
//	he is coming from the login page
	req.setAttribute("student", student);
	RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
	dispatcher.forward(req, resp);
}else {
//	he is a scammer
	req.setAttribute("message", "Hey scammer first login idiot fellow");
	RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
	dispatcher.forward(req, resp);
}

}
}
