import dao.DatabaseConnection;
import dao.Repository;
import model.StudentRequest;
import service.StudentService;
import service.StudentServiceIml;

import java.util.Scanner;

public class Main {
	StudentService service;
	Repository repository;

	public Main() {
		repository = new Repository();
		service = new StudentServiceIml(repository);
		repository.intiDb();
	}
	public void run(Scanner sc) {
		System.out.println("\t\t\t\tStudent Management Application");
		boolean start = true;
		while (start) {
			System.out.println("Please select Below Option");
			System.out.println("1 : Add Student");
			System.out.println("2 : Student Details");
			System.out.println("3 : Get All Students");
			System.out.println("4 : Update Student Details");
			System.out.println("5 : Exit");
			int choice = sc.nextInt();
			switch (choice) {
				case 1 :
					addStudent(sc);
					break;
				case 2 :
					System.out.print("Please provide student Id ");
					int id = sc.nextInt();
					System.out.println(service.fetchStudent(id));
					break;
				case 3 :
					service.fetchAllStudent().forEach(System.out::println);
					break;
				case 4 :
					update(sc);
					break;
				case 5 :
					DatabaseConnection.stopDbInstance();
					start = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		DatabaseConnection.initConnection();
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		main.run(sc);
	}

	public void update(Scanner sc) {
		StudentRequest request = new StudentRequest();
		System.out.println("Please enter Id of student to update his details");
		int id = sc.nextInt();
		System.out.println("if you want to update name please enter name : or else enter NO ");
		String name = sc.next();
		System.out.println("if you want to update Age please enter age : else enter : 0 ");
		int age = sc.nextInt();
		if (!name.equalsIgnoreCase("NO")) {
			request.setName(name);
		}
		if (age!=0) {
			request.setAge(age);
		}
		service.updateStudent(id, request);
	}

	public void addStudent(Scanner sc) {
		StudentRequest request = new StudentRequest();
		System.out.println("Please enter name");
		request.setName(sc.next());
		System.out.println("Please enter age");
		request.setAge(sc.nextInt());
		System.out.println("Please enter standard");
		request.setStandard(sc.nextInt());
		service.addStudent(request);
	}

}
