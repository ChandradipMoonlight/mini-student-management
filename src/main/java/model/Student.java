package model;

public class Student extends StudentRequest{
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				"name='" + getName() + '\'' +
				", standard=" + getStandard() +
				", age=" + getAge() +
				'}';
	}
}
