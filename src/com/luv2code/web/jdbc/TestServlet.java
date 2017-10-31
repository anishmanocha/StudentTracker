package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//We need this connection pool in our application
	//Inside of the dataSource object, we're going to inject information about the database we're attempting to connect to
	//After this injection process has occured once, we don't need to repeat it throughout classes
	@Resource(name= "jdbc/web_student_tracker")
	//Used to establish a connection with database from JAVA code
	DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Step 1: Set up the PrintWriter
		PrintWriter out= response.getWriter();
		//What type of data is going to be sent back to view
		response.setContentType("text/plain");
		//Step 2: Get connection to database
		Connection myConn= null;
		Statement myStmt= null;
		ResultSet myRs= null;
		
		try {
			
			myConn= dataSource.getConnection();
			
			//Step 3: Create SQL statement
			String sql= "select * from student";
			myStmt= myConn.createStatement();
			//Step 4: Execute SQL query
			myRs= myStmt.executeQuery(sql);
			//Step 5: Process the result set
			while (myRs.next()) {
				
				String email= myRs.getString("email");
				
				out.println(email);
			}

		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
