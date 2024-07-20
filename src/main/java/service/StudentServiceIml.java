package service;

import dao.DatabaseConnection;
import dao.Repository;
import dao.StudentException;
import model.Student;
import model.StudentRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceIml implements StudentService{

	private final Repository repository;

	public StudentServiceIml(Repository repository) {
		this.repository = repository;
	}
	@Override
	public boolean addStudent(StudentRequest request) {
		String sqlQuery = "INSERT INTO student_details(name, age, standard) VALUES (?, ?, ?)";
		boolean executed = false;
		Connection connection = DatabaseConnection.getConnection();
		try (
				// Get the connection and prepare the statement within the try-with-resources block
				PreparedStatement statement = connection.prepareStatement(sqlQuery)
		) {
			// Set the parameters for the PreparedStatement
			statement.setString(1, request.getName());
			statement.setInt(2, request.getAge());
			statement.setInt(3, request.getStandard());
			// Execute the statement
			executed = statement.executeUpdate() > 0; // Use executeUpdate for insert statements
		} catch (Exception e) {
			e.printStackTrace();
		}

		return executed;
	}


	@Override
	public Student fetchStudent(int id) {
		String query = "SELECT * FROM student_details WHERE id = ?";
		Student student = null;
		Connection connection = DatabaseConnection.getConnection();
		try(
				PreparedStatement statement =connection.prepareStatement(query)
		){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			student = new Student();
			if (resultSet.next()) {
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setAge(resultSet.getInt(3));
				student.setStandard(4);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return student;
	}

	@Override
	public List<Student> fetchAllStudent() {
		String query = "SELECT * FROM student_details";
		List<Student> students = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();

		try (
				PreparedStatement statement =connection.prepareStatement(query);
		){
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setAge(resultSet.getInt(3));
				student.setStandard(4);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public boolean updateStudent(int id, StudentRequest request) {
		boolean isUpdated = false;
		try {
			Student student = repository.getStudent(id);
			if (student==null) {
				throw new StudentException("Invalid Student Id");
			}
			if (request.getName()!=null) {
				student.setName(request.getName());
			}
			if (request.getAge()>0) {
				student.setAge(request.getAge());
			}
			if (request.getStandard()>0) {
				student.setStandard(request.getStandard());
			}
			repository.saveStudent(student);
			isUpdated = true;
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
		return isUpdated;
	}

	public Student mapToStudent(StudentRequest request) {
		Student student = new Student();
		student.setAge(request.getAge());
		student.setName(request.getName());
		student.setStandard(request.getStandard());
		return student;
	}
}
