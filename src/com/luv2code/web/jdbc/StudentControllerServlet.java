package com.luv2code.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentDbUtil;
	
	@Resource(name= "jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			studentDbUtil= new StudentDBUtil(dataSource);
		}
		catch(Exception e) {
			
			throw new ServletException(e);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String theCommand = request.getParameter("command");
		
		if (theCommand== null) {
			
			theCommand= "List";
		}
		
		
		try {
			
			switch(theCommand) {
			
				case "LIST": 
					listStudents(request, response);
					break;
					
				case "Add": 
					addStudent(request, response);
					break;
					
				case "Load":
					loadStudent(request, response);
					break;
					
				case "Update":
					updateStudent(request,response);
					break;
					
				case "Delete":
					deleteStudent(request,response);
					break;
					
				default:
					listStudents(request, response);
					
				
			}
		}
		catch (Exception e) {
			
			throw new ServletException(e);
		}
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id= request.getParameter("studentId");
		
		int parsedId= Integer.parseInt(id);
		
		try {
			this.studentDbUtil.deleteStudent(parsedId);
			}
			
			catch(Exception e) {
				
				e.printStackTrace();
			}
		
		listStudents(request, response);
		
		try {
			request.setAttribute("listOfStudents", this.studentDbUtil.getListOfStudents());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String firstName= request.getParameter("firstName");
		
		String lastName= request.getParameter("lastName");
		
		String email= request.getParameter("email");
		
		String id= request.getParameter("id");
		
		try {
		this.studentDbUtil.updateStudent(id, firstName, lastName, email);
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		listStudents(request, response);
		
		try {
			request.setAttribute("listOfStudents", this.studentDbUtil.getListOfStudents());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String id= request.getParameter("studentId");
		
		try {
			request.setAttribute("loadedStudent", this.studentDbUtil.loadStudent(id));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName= request.getParameter("firstName");
		
		
		String lastName= request.getParameter("lastName");
		
		String email= request.getParameter("email");
		
		this.studentDbUtil.addStudent(firstName, lastName, email);
		
		listStudents(request, response);
		
		try {
			request.setAttribute("listOfStudents", this.studentDbUtil.getListOfStudents());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
				
				try {
					request.setAttribute("listOfStudents", this.studentDbUtil.getListOfStudents());
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher= request.getRequestDispatcher("/list-students.jsp");
				dispatcher.forward(request, response);
	}

}
