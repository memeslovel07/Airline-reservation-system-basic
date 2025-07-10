package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class SearchCustomer extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField customerid;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField passport;
	private JTextField nationalid;
	private JTextField contact;
	private JTextField dob;
	private JRadioButton rdbtnFemale;
	private JRadioButton rbtnMale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCustomer frame = new SearchCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchCustomer() {
		getContentPane().setBackground(Color.BLUE);
		setTitle("Customer Panel\r\n\r\n");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 936, 634);

		JLabel lblNewLabel = new JLabel("Welcome to Search Panel");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 36));

		JPanel pane = new JPanel();
		pane.setBackground(new Color(0, 0, 153));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 153));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(37)
						.addComponent(pane, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE).addGap(39))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(254, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addGap(248)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(38).addComponent(lblNewLabel).addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addComponent(pane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));

		JLabel lblNewLabel_1_5 = new JLabel("Contact No.");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));

		contact = new JTextField();
		contact.setColumns(10);

		JLabel lblNewLabel_1_5_1 = new JLabel("Gender");
		lblNewLabel_1_5_1.setForeground(Color.WHITE);
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		final JRadioButton rbtnMale = new JRadioButton("Male");
		rbtnMale.setForeground(new Color(255, 255, 255));
		rbtnMale.setBackground(new Color(0, 0, 153));
		rbtnMale.setFont(new Font("Tahoma", Font.BOLD, 16));

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFemale.setBackground(new Color(0, 0, 153));

		JLabel lblNewLabel_1_5_2 = new JLabel("Date Of Birth");
		lblNewLabel_1_5_2.setForeground(Color.WHITE);
		lblNewLabel_1_5_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		dob = new JTextField();
		dob.setColumns(10);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(20)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_5_2, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dob, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 74,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rbtnMale, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(contact, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
				.addGap(34)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(29)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(contact, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(rbtnMale)
						.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(23)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_5_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dob, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(69, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel_1 = new JLabel("Customer Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));

		customerid = new JTextField();
		customerid.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_2 = new JLabel("Last Name");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_3 = new JLabel("Passport No.");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_4 = new JLabel("National Id");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));

		firstname = new JTextField();
		firstname.setColumns(10);

		lastname = new JTextField();
		lastname.setColumns(10);

		passport = new JTextField();
		passport.setColumns(10);

		nationalid = new JTextField();
		nationalid.setColumns(10);

		JLabel lblNewLabel_1_4_1 = new JLabel("Address");
		lblNewLabel_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JTextArea address = new JTextArea();

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			private JInternalFrame rbtnfemale;

			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=Airline_project;"
						+ "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;";

				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pre = con
							.prepareStatement("select * from Customer where CustomerId='" + customerid.getText() + "'");
					ResultSet rs = pre.executeQuery();
					if (rs.next() != false) {

						firstname.setText(rs.getString("FirstName"));
						lastname.setText(rs.getString("LastName"));
						passport.setText(rs.getString("Passport"));
						nationalid.setText(rs.getString("Nationalid"));
						address.setText(rs.getString("Address"));
						contact.setText(rs.getString("Contact"));
						dob.setText(rs.getString("DOB"));
						String gender = rs.getString("Gender");

						if (gender != null && gender.trim().equalsIgnoreCase("Male")) {
							rbtnMale.setSelected(true);
						} else {
							rdbtnFemale.setSelected(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Customer not Exist");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		GroupLayout gl_pane = new GroupLayout(pane);
		gl_pane.setHorizontalGroup(gl_pane.createParallelGroup(Alignment.LEADING).addGroup(gl_pane
				.createSequentialGroup().addGap(30)
				.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pane.createSequentialGroup()
								.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(nationalid, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(passport, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pane.createSequentialGroup()
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pane.createSequentialGroup()
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pane.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(customerid, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton))
						.addGroup(gl_pane.createSequentialGroup()
								.addComponent(lblNewLabel_1_4_1, GroupLayout.PREFERRED_SIZE, 84,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(address, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_pane.setVerticalGroup(gl_pane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pane.createSequentialGroup().addGap(35)
						.addGroup(gl_pane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(customerid, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_pane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(gl_pane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addGroup(
								gl_pane.createParallelGroup(Alignment.BASELINE)
										.addComponent(passport, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addGroup(gl_pane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(nationalid, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pane.createSequentialGroup().addGap(48).addComponent(lblNewLabel_1_4_1,
										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pane.createSequentialGroup().addGap(34).addComponent(address,
										GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(22, Short.MAX_VALUE)));
		pane.setLayout(gl_pane);
		getContentPane().setLayout(groupLayout);

	}
}
