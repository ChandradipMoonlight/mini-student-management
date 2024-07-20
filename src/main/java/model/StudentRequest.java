package model;

public class StudentRequest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private int standard;
	private int age;

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", standard=" + standard +
				", age=" + age +
				'}';
	}
}
