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

public class UpdateStudentDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame parent;
	private JFrame frame;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentDetails window = new UpdateStudentDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateStudentDetails() {
		this(null);
	}

	public UpdateStudentDetails(JFrame parent) {
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
		gbl.rowHeights = new int[]{0, 0, 0, 0};
		gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl);

		JLabel lblId = new JLabel("Enter Student ID to update:");
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
		idField.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(5, 10, 5, 10);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 1;
		panel.add(btnUpdate, gbc_btnUpdate);
		
				JButton btnBack = new JButton("Back");
				GridBagConstraints gbc_btnBack = new GridBagConstraints();
				gbc_btnBack.insets = new Insets(5, 10, 0, 10);
				gbc_btnBack.gridx = 1;
				gbc_btnBack.gridy = 2;
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

		btnUpdate.addActionListener(new ActionListener() {
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

				CRUDoperation crud = new CRUDoperation();
				Student student = crud.getStudentById(id);
				if (student == null) {
					JOptionPane.showMessageDialog(frame, "Student with ID " + id + " not found.", "Not found", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// Ask which field to update
				String[] options = {"Name", "CGPA", "Email", "Branch"};
				int choice = JOptionPane.showOptionDialog(frame, "Select field to update:", "Choose Field", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (choice < 0) return; // user closed dialog

				String newValue = JOptionPane.showInputDialog(frame, "Enter new value for " + options[choice] + ":", "Update Field", JOptionPane.PLAIN_MESSAGE);
				if (newValue == null) return; // cancelled
				newValue = newValue.trim();
				if (newValue.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Value cannot be empty.", "Invalid input", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Session session = null;
				Transaction tx = null;
				try {
					session = CRUDoperation.factory.openSession();
					tx = session.beginTransaction();
					Student s = session.get(Student.class, id);
					if (s == null) {
						if (tx != null) tx.rollback();
						JOptionPane.showMessageDialog(frame, "Student not found during update (might have been removed).", "Not found", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					switch (choice) {
					case 0: // Name
						s.setName(newValue);
						break;
					case 1: // CGPA
						double cgpa;
						try {
							cgpa = Double.parseDouble(newValue);
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(frame, "CGPA must be a number.", "Invalid input", JOptionPane.ERROR_MESSAGE);
							if (tx != null) tx.rollback();
							return;
						}
						s.setCgpa(cgpa);
						break;
					case 2: // Email
						s.setEmail(newValue);
						break;
					case 3: // Branch
						s.setBranch(newValue);
						break;
					default:
						break;
					}
					session.merge(s);
					tx.commit();
					JOptionPane.showMessageDialog(frame, "Student with ID " + id + " updated successfully.", "Updated", JOptionPane.INFORMATION_MESSAGE);
					idField.setText("");
				} catch (Exception ex) {
					if (tx != null) tx.rollback();
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Error updating student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					if (session != null) session.close();
				}
			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}

}