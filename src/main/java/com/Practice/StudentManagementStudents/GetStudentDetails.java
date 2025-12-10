package com.Practice.StudentManagementStudents;

import java.awt.EventQueue;
import java.awt.Font;
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

public class GetStudentDetails {

    private JFrame frame;
    private JFrame parent;
    private JTextField idField;
    private JLabel nameValue;
    private JLabel cgpaValue;
    private JLabel emailValue;
    private JLabel branchValue;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GetStudentDetails window = new GetStudentDetails();
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
    public GetStudentDetails() {
        this(null);
    }

    public GetStudentDetails(JFrame parent) {
        this.parent = parent;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 320);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        GridBagLayout gbl = new GridBagLayout();
        // Use 7 logical rows: 0=ID,1=Fetch,2=Name,3=CGPA,4=Email,5=Branch,6=Back
        gbl.columnWidths = new int[]{0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl);

        JLabel lblId = new JLabel("Enter Student ID:");
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
        gbc_idField.weightx = 1.0; // allow value column to expand
        panel.add(idField, gbc_idField);
        idField.setColumns(10);

        JButton btnFetch = new JButton("Fetch");
        GridBagConstraints gbc_btnFetch = new GridBagConstraints();
        gbc_btnFetch.insets = new Insets(0, 10, 5, 10);
        gbc_btnFetch.anchor = GridBagConstraints.WEST;
        gbc_btnFetch.gridx = 1;
        gbc_btnFetch.gridy = 1;
        panel.add(btnFetch, gbc_btnFetch);
                
        // Action for Fetch button
        btnFetch.addActionListener(new ActionListener() {
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
                if (student != null) {
                    nameValue.setText(student.getName());
                    cgpaValue.setText(Double.toString(student.getCgpa()));
                    emailValue.setText(student.getEmail());
                    branchValue.setText(student.getBranch());
                } else {
                    nameValue.setText("");
                    cgpaValue.setText("");
                    emailValue.setText("");
                    branchValue.setText("");
                    JOptionPane.showMessageDialog(frame, "Student with ID " + id + " not found.", "Not found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JLabel lblName = new JLabel("Name:");
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(5, 10, 5, 5);
        gbc_lblName.gridx = 0;
        gbc_lblName.gridy = 2; // aligned with nameValue
        panel.add(lblName, gbc_lblName);

        nameValue = new JLabel("");
        nameValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_nameValue = new GridBagConstraints();
        gbc_nameValue.anchor = GridBagConstraints.WEST;
        gbc_nameValue.insets = new Insets(5, 0, 5, 10);
        gbc_nameValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_nameValue.weightx = 1.0;
        gbc_nameValue.gridx = 1;
        gbc_nameValue.gridy = 2;
        panel.add(nameValue, gbc_nameValue);

        JLabel lblCgpa = new JLabel("CGPA:");
        GridBagConstraints gbc_lblCgpa = new GridBagConstraints();
        gbc_lblCgpa.anchor = GridBagConstraints.EAST;
        gbc_lblCgpa.insets = new Insets(5, 10, 5, 5);
        gbc_lblCgpa.gridx = 0;
        gbc_lblCgpa.gridy = 3;
        panel.add(lblCgpa, gbc_lblCgpa);

        cgpaValue = new JLabel("");
        cgpaValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_cgpaValue = new GridBagConstraints();
        gbc_cgpaValue.anchor = GridBagConstraints.WEST;
        gbc_cgpaValue.insets = new Insets(5, 0, 5, 10);
        gbc_cgpaValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_cgpaValue.weightx = 1.0;
        gbc_cgpaValue.gridx = 1;
        gbc_cgpaValue.gridy = 3;
        panel.add(cgpaValue, gbc_cgpaValue);

        JLabel lblEmail = new JLabel("Email:");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.EAST;
        gbc_lblEmail.insets = new Insets(5, 10, 5, 5);
        gbc_lblEmail.gridx = 0;
        gbc_lblEmail.gridy = 4;
        panel.add(lblEmail, gbc_lblEmail);

        emailValue = new JLabel("");
        emailValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_emailValue = new GridBagConstraints();
        gbc_emailValue.anchor = GridBagConstraints.WEST;
        gbc_emailValue.insets = new Insets(5, 0, 5, 10);
        gbc_emailValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailValue.weightx = 1.0;
        gbc_emailValue.gridx = 1;
        gbc_emailValue.gridy = 4;
        panel.add(emailValue, gbc_emailValue);

        JLabel lblBranch = new JLabel("Branch:");
        GridBagConstraints gbc_lblBranch = new GridBagConstraints();
        gbc_lblBranch.anchor = GridBagConstraints.EAST;
        gbc_lblBranch.insets = new Insets(5, 10, 5, 5);
        gbc_lblBranch.gridx = 0;
        gbc_lblBranch.gridy = 5;
        panel.add(lblBranch, gbc_lblBranch);

        branchValue = new JLabel("");
        branchValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_branchValue = new GridBagConstraints();
        gbc_branchValue.anchor = GridBagConstraints.WEST;
        gbc_branchValue.insets = new Insets(5, 0, 5, 10);
        gbc_branchValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_branchValue.weightx = 1.0;
        gbc_branchValue.gridx = 1;
        gbc_branchValue.gridy = 5;
        panel.add(branchValue, gbc_branchValue);
                                                
        // Back button
        JButton btnBack = new JButton("Back");
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.insets = new Insets(10, 10, 5, 10);
        gbc_btnBack.anchor = GridBagConstraints.WEST;
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

    }

    public JFrame getFrame() {
        return frame;
    }

}