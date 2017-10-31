package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource dataSource) {
		
	this.dataSource= dataSource;
		
	}
	
	public List<Student> getListOfStudents() throws Exception {
		
		
		List<Student> listOfStudents= new ArrayList<Student>();
		
		Connection connection= null;
		Statement statement= null;
		ResultSet resultSet= null;
		try {
		//Kind of interesting to me we only had to inject the dataSource once in another class
			
		connection= this.dataSource.getConnection();
		
		String sql= "Select * from student order by last_name";
		
		statement= connection.createStatement();
		
		resultSet= statement.executeQuery(sql);
		
		while (resultSet.next()) {
			
			Student student= new Student(resultSet.getInt("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getString("email"));
			
			listOfStudents.add(student);
			
			System.out.println(student);
			
			
		}
		
		return listOfStudents;
		
		}
		
		finally {
			close(connection, statement, resultSet);
			
		}
		
	}
	
	public void addStudent(String firstName, String lastName, String email) throws SQLException {
				
		Connection connection= null;
		PreparedStatement statement = null;
		try {
		//Kind of interesting to me we only had to inject the dataSource once in another class
			
		connection= this.dataSource.getConnection();
		
	
		String sql= "insert into student " + "(first_name, last_name, email) " + "values (?, ?, ?)";
		
		System.out.println(sql);
		
		statement= connection.prepareStatement(sql);
		
		statement.setString(1, firstName);
		
		statement.setString(2, lastName);
		
		statement.setString(3, email);
		
		statement.execute();
		
		}
		
		finally {
			close(connection, statement, null);
			
		}
		
	}
	
	public Student loadStudent(String id) throws Exception {
		
		Connection connection= null;
		PreparedStatement statement = null;
		ResultSet resultSet= null;
		try {
		
			
		connection= this.dataSource.getConnection();
		
		
		String sql= "select * from student where id= ?";
		
		System.out.println(sql);
		
		statement= connection.prepareStatement(sql);
		
		int studentId= Integer.parseInt(id);
		
		statement.setInt(1, studentId);
			
		resultSet= statement.executeQuery();
		
		if (resultSet.next()) {
			
		return new Student(studentId, resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getString("email"));
		
		
		}
		
		else {
			
			throw new Exception("Could not find student id: " + studentId);
		}
		
		
		
		}
		
		finally {
			close(connection, statement, resultSet);
			
		}
		
	}
	
	public void updateStudent(String id, String firstName, String lastName, String email) throws Exception {
		
		Connection connection= null;
		PreparedStatement statement = null;
		ResultSet resultSet= null;
		
		try {
		
		connection= this.dataSource.getConnection();
		
		String sql= "Update student set first_name= ?, last_name= ?, email= ? where id= ?";
		
		System.out.println(sql);
		
		statement= connection.prepareStatement(sql);
		
		int studentId= Integer.parseInt(id);
		
		statement.setString(1, firstName);
		
		statement.setString(2, lastName);
		
		statement.setString(3, email);
		
		statement.setInt(4, studentId);
			
		statement.execute();
		
		
		}
		
		finally {
			close(connection, statement, resultSet);
			
		}
		
		
	}
	
	public void deleteStudent(int id) throws SQLException {
		
		Connection connection= null;
		PreparedStatement statement = null;
		ResultSet resultSet= null;
		
		try {
		
		connection= this.dataSource.getConnection();
		
		String sql= "Delete from student where id= ?";
		
		statement= connection.prepareStatement(sql);
		
		
		
		statement.setInt(1, id);
		
			
		statement.execute();
		
		
		}
		
		finally {
			close(connection, statement, resultSet);
			
		}
	}
	
	

	private void close(Connection connection, Statement statement, ResultSet resultSet) {
		
		try {
		
		if (resultSet != null) {
			
			resultSet.close();
		}
		
		if (statement != null) {
			
			statement.close();
		}
		
		if (connection != null) {
			
			connection.close();
		}
		
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
