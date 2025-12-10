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

public class DeleteStudent {

	private JFrame frame;
	private JFrame parent;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent window = new DeleteStudent();
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
	public DeleteStudent() {
		this(null);
	}

	/**
	 * Create the application with parent frame (so Back can restore it).
	 */
	public DeleteStudent(JFrame parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{0, 0, 0};
		gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl);
		
				JLabel lblId = new JLabel("Enter Student ID to delete:");
				GridBagConstraints gbc_lblId = new GridBagConstraints();
				gbc_lblId.anchor = GridBagConstraints.EAST;
				gbc_lblId.insets = new Insets(10, 10, 5, 5);
				gbc_lblId.gridx = 0;
				gbc_lblId.gridy = 1;
				panel.add(lblId, gbc_lblId);
								
										idField = new JTextField();
										GridBagConstraints gbc_idField = new GridBagConstraints();
										gbc_idField.insets = new Insets(10, 0, 5, 10);
										gbc_idField.fill = GridBagConstraints.HORIZONTAL;
										gbc_idField.gridx = 1;
										gbc_idField.gridy = 1;
										panel.add(idField, gbc_idField);
										idField.setColumns(10);
						
								JButton btnDelete = new JButton("Delete");
								GridBagConstraints gbc_btnDelete = new GridBagConstraints();
								gbc_btnDelete.insets = new Insets(5, 10, 5, 10);
								gbc_btnDelete.gridx = 1;
								gbc_btnDelete.gridy = 2;
								panel.add(btnDelete, gbc_btnDelete);
								
										btnDelete.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												String idText = idField.getText().trim();
												if (idText.isEmpty()) {
													JOptionPane.showMessageDialog(frame, "Please enter an ID.", "Input required", JOptionPane.WARNING_MESSAGE);
													return;
												}
												int id;
												try {
													id = Integer.parseInt(idText);
												} catch (NumberFormatException ex) {
													JOptionPane.showMessageDialog(frame, "ID must be a number.", "Invalid input", JOptionPane.ERROR_MESSAGE);
													return;
												}
								
												// Check presence
												CRUDoperation crud = new CRUDoperation();
												Student student = crud.getStudentById(id);
												if (student == null) {
													JOptionPane.showMessageDialog(frame, "Student with ID " + id + " not found.", "Not found", JOptionPane.INFORMATION_MESSAGE);
													return;
												}
								
												// Confirm deletion
												int choice = JOptionPane.showConfirmDialog(frame, "Do you want to delete student:\n" + student.toString(), "Confirm Delete", JOptionPane.YES_NO_OPTION);
												if (choice != JOptionPane.YES_OPTION) {
													return;
												}
								
												// Perform deletion using a fresh session
												Session session = null;
												Transaction tx = null;
												try {
													session = CRUDoperation.factory.openSession();
													tx = session.beginTransaction();
													Student s = session.get(Student.class, id);
													if (s != null) {
														session.remove(s);
														tx.commit();
														JOptionPane.showMessageDialog(frame, "Student with ID " + id + " deleted successfully.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
														idField.setText("");
													} else {
														if (tx != null) tx.rollback();
														JOptionPane.showMessageDialog(frame, "Student not found during deletion (might have been removed).", "Not found", JOptionPane.INFORMATION_MESSAGE);
													}
												} catch (Exception ex) {
													if (tx != null) tx.rollback();
													ex.printStackTrace();
													JOptionPane.showMessageDialog(frame, "Error deleting student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
												} finally {
													if (session != null) session.close();
												}
											}
										});
				
						JButton btnBack = new JButton("Back");
						GridBagConstraints gbc_btnBack = new GridBagConstraints();
						gbc_btnBack.insets = new Insets(5, 10, 5, 10);
						gbc_btnBack.gridx = 1;
						gbc_btnBack.gridy = 3;
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

	}

	public JFrame getFrame() {
		return frame;
	}

}