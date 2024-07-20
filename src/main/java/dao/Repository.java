package dao;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

	private Map<Integer, Student> studentDb = new HashMap<>();

	public int saveStudent(Student request) {
		studentDb.put(request.getId(), request);
		return request.getId();
	}

	public Student getStudent(int id) {
		return studentDb.get(id);
	}


	public int getUniqueId() {
		return studentDb.keySet().size() +1;
	}

	public List<Student> getAllStudents() {
		return new ArrayList<>(studentDb.values());
	}

	public void intiDb() {
		studentDb.put(1, generate(1, "Jhon", 2, 7));
		studentDb.put(2, generate(2, "Ram", 2, 6));
	}

	public Student generate(int id, String name, int clas, int age) {
		Student student = new Student();
		student.setId(id);
		student.setStandard(clas);
		student.setName(name);
		student.setAge(age);
		return student;
	}

}
