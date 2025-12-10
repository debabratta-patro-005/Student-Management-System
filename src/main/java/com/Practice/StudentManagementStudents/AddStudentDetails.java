package com.Practice.StudentManagementStudents;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddStudentDetails {

	private JFrame frame;
	private JFrame parent; // parent frame so Back can restore it
	private JTextField idField;
	private JTextField nameField;
	private JTextField cgpaField;
	private JTextField emailField;
	private JTextField branchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentDetails window = new AddStudentDetails();
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
	public AddStudentDetails() {
		this(null);
	}

	/**
	 * Create the application with parent (so Back restores it)
	 */
	public AddStudentDetails(JFrame parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // don't exit whole app

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{0, 0, 0};
		gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl);

		JLabel lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(10, 10, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		panel.add(lblId, gbc_lblId);

		idField = new JTextField();
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.insets = new Insets(10, 0, 5, 10);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 1;
		gbc_idField.gridy = 0;
		panel.add(idField, gbc_idField);
		idField.setColumns(20);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(5, 10, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);

		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(5, 0, 5, 10);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		panel.add(nameField, gbc_nameField);
		nameField.setColumns(20);

		JLabel lblCgpa = new JLabel("CGPA:");
		GridBagConstraints gbc_lblCgpa = new GridBagConstraints();
		gbc_lblCgpa.anchor = GridBagConstraints.EAST;
		gbc_lblCgpa.insets = new Insets(5, 10, 5, 5);
		gbc_lblCgpa.gridx = 0;
		gbc_lblCgpa.gridy = 2;
		panel.add(lblCgpa, gbc_lblCgpa);

		cgpaField = new JTextField();
		GridBagConstraints gbc_cgpaField = new GridBagConstraints();
		gbc_cgpaField.insets = new Insets(5, 0, 5, 10);
		gbc_cgpaField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cgpaField.gridx = 1;
		gbc_cgpaField.gridy = 2;
		panel.add(cgpaField, gbc_cgpaField);
		cgpaField.setColumns(20);

		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(5, 10, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		panel.add(lblEmail, gbc_lblEmail);

		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(5, 0, 5, 10);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		panel.add(emailField, gbc_emailField);
		emailField.setColumns(20);

		JLabel lblBranch = new JLabel("Branch:");
		GridBagConstraints gbc_lblBranch = new GridBagConstraints();
		gbc_lblBranch.anchor = GridBagConstraints.EAST;
		gbc_lblBranch.insets = new Insets(5, 10, 5, 5);
		gbc_lblBranch.gridx = 0;
		gbc_lblBranch.gridy = 4;
		panel.add(lblBranch, gbc_lblBranch);

		branchField = new JTextField();
		GridBagConstraints gbc_branchField = new GridBagConstraints();
		gbc_branchField.insets = new Insets(5, 0, 5, 10);
		gbc_branchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_branchField.gridx = 1;
		gbc_branchField.gridy = 4;
		panel.add(branchField, gbc_branchField);
		branchField.setColumns(20);

		JButton btnAdd = new JButton("Add Student");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(10, 10, 10, 10);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 5;
		panel.add(btnAdd, gbc_btnAdd);
				
						// Back button (restore parent)
						JButton btnBack = new JButton("Back");
						GridBagConstraints gbc_btnBack = new GridBagConstraints();
						gbc_btnBack.insets = new Insets(10, 10, 10, 10);
						gbc_btnBack.gridx = 1;
						gbc_btnBack.gridy = 6;
						panel.add(btnBack, gbc_btnBack);
				
						btnBack.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (parent != null) {
									parent.setVisible(true);
								}
								frame.dispose();
							}
						});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validate inputs
				String idText = idField.getText().trim();
				String name = nameField.getText().trim();
				String cgpaText = cgpaField.getText().trim();
				String email = emailField.getText().trim();
				String branch = branchField.getText().trim();

				if (idText.isEmpty() || name.isEmpty() || cgpaText.isEmpty() || email.isEmpty() || branch.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input required", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int id;
				double cgpa;
				try {
					id = Integer.parseInt(idText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "ID must be a valid integer.", "Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					cgpa = Double.parseDouble(cgpaText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "CGPA must be a valid number.", "Invalid input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Create Student and persist using existing SessionFactory from CRUDoperation
				Student student = new Student(id, name, cgpa, email, branch);
				Session session = null;
				Transaction tx = null;
				try {
					session = CRUDoperation.factory.openSession();
					tx = session.beginTransaction();
					session.persist(student);
					tx.commit();
					JOptionPane.showMessageDialog(frame, "Student added successfully with ID: " + id, "Success", JOptionPane.INFORMATION_MESSAGE);
					// clear fields
					idField.setText("");
					nameField.setText("");
					cgpaField.setText("");
					emailField.setText("");
					branchField.setText("");
				} catch (Exception ex) {
					if (tx != null) tx.rollback();
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Failed to add student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					if (session != null) session.close();
				}
			}
		});

	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

}