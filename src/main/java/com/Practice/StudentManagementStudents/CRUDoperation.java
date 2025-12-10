package com.Practice.StudentManagementStudents;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CRUDoperation {
	
	 static Scanner sc = new Scanner(System.in);
	 
	 static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	 
	 
	public void viewStudent() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Student ID to get details: ");
		int id = sc.nextInt();
		Student student = session.get(Student.class, id);
		transaction.commit();
		if(student != null) {
			System.out.println("Student Details: ");
			System.out.println("ID: " + student.getId());
			System.out.println("Name: " + student.getName());
			System.out.println("Age: " + student.getCgpa());
			System.out.println("Course: " + student.getEmail());
			System.out.println("Branch: " + student.getBranch());
		} else {
			System.out.println("Student with ID " + id + " not found.");
		}
		session.close();
	}
	
	public void addStudent() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Student ID:");
		int id = sc.nextInt();
		System.out.println("Enter Student Name: ");
		String name = sc.next();
		System.out.println("Enter Student CGPA: ");
		double cgpa = sc.nextDouble();
		System.out.println("Enter Student Email: ");
		String email = sc.next();
		System.out.println("Enter Student Branch: ");
		String branch = sc.next();
		
		Student student = new Student(id, name, cgpa, email, branch);
		session.persist(student);
		transaction.commit();
		session.close();
		System.out.println("Student added successfully with ID: " + student.getId());
	}
	
	public void deleteStudent() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Student ID to delete: ");
		int id = sc.nextInt();
		Student student = session.get(Student.class, id);
		if(student != null) {
			session.remove(student);
			transaction.commit();
			System.out.println("Student with ID " + id + " deleted successfully.");
		} else {
			System.out.println("Student with ID " + id + " not found.");
		}
		session.close();
	}
	
	public void updateStudent() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Student ID to update: ");
		int id = sc.nextInt();
		Student student = session.get(Student.class, id);
		if(student != null) {
			System.out.println("Choose field to update: ");
			System.out.println("1. Name");
			System.out.println("2. CGPA");
			System.out.println("3. Email");
			System.out.println("4. Branch");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter new Name: ");
				String name = sc.next();
				student.setName(name);
				break;
			case 2:
				System.out.println("Enter new CGPA: ");
				double cgpa = sc.nextDouble();
				student.setCgpa(cgpa);
				break;
			case 3:
				System.out.println("Enter new Email: ");
				String email = sc.next();
				student.setEmail(email);
				break;
			case 4:
				System.out.println("Enter new Branch: ");
				String branch = sc.next();
				student.setBranch(branch);
				break;
			default:
				System.out.println("Invalid choice.");
				return;
			}
			session.merge(student);
			transaction.commit();
			System.out.println("Student with ID " + id + " updated successfully.");			
		} else {
			System.out.println("Student with ID " + id + " not found.");
		}
		session.close();
	}

    // Added helper to fetch a Student by id programmatically (used by GUI)
    public Student getStudentById(int id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = session.get(Student.class, id);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}