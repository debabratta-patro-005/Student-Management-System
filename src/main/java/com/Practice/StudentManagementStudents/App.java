package com.Practice.StudentManagementStudents;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	while (true) {
    		Scanner sc = new Scanner(System.in);
    		CRUDoperation crud = new CRUDoperation();
    		System.out.println("Welcome to Student Management System");
    		System.out.println("Choose an option:");
    		System.out.println("1 -> View Student");
    		System.out.println("2 ->  Add Students");
    		System.out.println("3 -> Delete Student");
    		System.out.println("4 -> Update Student");
    		System.out.println("Other -> Exit");
    		int choice = sc.nextInt();
    		switch (choice) {
    			case 1:
    				crud.viewStudent();
					break;
				case 2:
					crud.addStudent();
					break;
				case 3:
					crud.deleteStudent();
					break;
				case 4:
					crud.updateStudent();
					break;
				default:
					System.out.println("Exiting...");
					sc.close();
					System.exit(0);
    		}
    		
    	}
    }
}
