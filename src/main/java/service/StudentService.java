package service;

import model.Student;
import model.StudentRequest;

import java.util.List;

public interface StudentService {
	boolean addStudent(StudentRequest student);

	Student fetchStudent(int id);

	List<Student> fetchAllStudent();

	boolean updateStudent(int id, StudentRequest student);
}
