package com.Practice.StudentManagementStudents;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "student_details")
public class Student {
	
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private double cgpa;
	@Column
	private String email;
	@Column
	private String branch;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(int id, String name, double cgpa, String email, String branch) {
		super();
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
		this.email = email;
		this.branch = branch;
	}

	public Student(String name, double cgpa, String email, String branch) {
		super();
		this.name = name;
		this.cgpa = cgpa;
		this.email = email;
		this.branch = branch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cgpa=" + cgpa + ", email=" + email + ", branch=" + branch
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(branch, cgpa, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(branch, other.branch)
				&& Double.doubleToLongBits(cgpa) == Double.doubleToLongBits(other.cgpa)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name);
	}
	
	

}
