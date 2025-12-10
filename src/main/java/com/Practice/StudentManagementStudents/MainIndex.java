package com.Practice.StudentManagementStudents;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainIndex {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainIndex window = new MainIndex();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainIndex() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton studentDetailsbtn = new JButton("Get Student Details");
		studentDetailsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetStudentDetails getDetails = new GetStudentDetails(frame);
				frame.setVisible(false);
				getDetails.getFrame().setVisible(true);
			}
		});
		studentDetailsbtn.setBounds(68, 44, 263, 20);
		frame.getContentPane().add(studentDetailsbtn);
		
		JLabel lblNewLabel = new JLabel("Student Management System");
		lblNewLabel.setBounds(133, 10, 197, 12);
		frame.getContentPane().add(lblNewLabel);
		
		JButton addStudentbtn = new JButton("Add Student ");
		addStudentbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentDetails addDetails = new AddStudentDetails(frame);
				frame.setVisible(false);
				addDetails.getFrame().setVisible(true);
			}
		});
		addStudentbtn.setBounds(68, 74, 263, 20);
		frame.getContentPane().add(addStudentbtn);
		
		JButton updateStudentDetailsbtn = new JButton("Update Student Details");
		updateStudentDetailsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudentDetails updateDetails = new UpdateStudentDetails(frame);
				frame.setVisible(false);
				updateDetails.getFrame().setVisible(true);
			}
		});
		updateStudentDetailsbtn.setBounds(68, 104, 263, 20);
		frame.getContentPane().add(updateStudentDetailsbtn);
		
		JButton deleteStudentDetailsbtn = new JButton("Delete Student Details");
		deleteStudentDetailsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStudent deleteDetails = new DeleteStudent(frame);
				frame.setVisible(false);
				deleteDetails.getFrame().setVisible(true);
			}
		});
		deleteStudentDetailsbtn.setBounds(68, 134, 263, 20);
		frame.getContentPane().add(deleteStudentDetailsbtn);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(68, 164, 263, 20);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}