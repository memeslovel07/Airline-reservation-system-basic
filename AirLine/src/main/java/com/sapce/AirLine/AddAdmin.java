package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAdmin extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField adminid;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAdmin frame = new AddAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void AutoID() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=Airline_project;"
					+ "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			try (Connection con = DriverManager.getConnection(url);
					PreparedStatement pre = con.prepareStatement("SELECT MAX(AdminId) AS MaxAdminid FROM Admin");
					ResultSet rs = pre.executeQuery()) {

				rs.next(); // move to first (only) row
				String lastId = rs.getString("MaXAdminid");

				if (lastId == null) { // no rows in table yet
					adminid.setText("AD001");
				} else {
					long id = Long.parseLong(lastId.substring(2).trim()); // strip "CS"
					id++;
					adminid.setText("AD" + String.format("%03d", id));
				}
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public AddAdmin() {
		setClosable(true);
		getContentPane().setBackground(new Color(0, 102, 153));
		getContentPane().setForeground(new Color(0, 0, 153));
		setBounds(100, 100, 827, 554);

		JLabel lblNewLabel = new JLabel("Welcome to the Admin panel");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 28));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 255));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(198)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(33, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE).addGap(29)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(37)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGap(47).addComponent(panel, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(64, Short.MAX_VALUE)));

		JLabel lblNewLabel_1 = new JLabel("Admin Id");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));

		adminid = new JTextField();
		adminid.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel_1_2 = new JLabel("Last Name");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel_1_3 = new JLabel("UserName");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel_1_4 = new JLabel("Password");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 20));

		firstname = new JTextField();
		firstname.setColumns(10);

		lastname = new JTextField();
		lastname.setColumns(10);

		username = new JTextField();
		username.setColumns(10);

		password = new JTextField();
		password.setColumns(10);

		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=Airline_project;"
						+ "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;";
				try {
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pre = con
							.prepareStatement("insert into Admin(AdminId,Firstname,Lastname,Username,Password) Values('"
									+ adminid.getText() + "','" + firstname.getText() + "','" + lastname.getText()
									+ "','" + username.getText() + "','" + password.getText() + "')");
					pre.executeUpdate();
					JOptionPane.showMessageDialog(null, "Admin  Added Successfully");
				} catch (SQLException e1) {
				}

			}
		});
		btnadd.setFont(new Font("Dialog", Font.BOLD, 14));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel
								.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 104,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
												.addComponent(adminid, GroupLayout.PREFERRED_SIZE, 181,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 110,
														Short.MAX_VALUE)
												.addGap(18).addComponent(firstname, GroupLayout.PREFERRED_SIZE, 181,
														GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.TRAILING,
										gl_panel.createSequentialGroup().addComponent(lblNewLabel_1_2)
												.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
												.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 181,
														GroupLayout.PREFERRED_SIZE)))
						.addGap(80)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 104,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(username,
												GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 104,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(password,
												GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING,
										gl_panel.createSequentialGroup()
												.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 110,
														GroupLayout.PREFERRED_SIZE)
												.addGap(155))))
						.addGroup(gl_panel.createSequentialGroup().addGap(179).addComponent(btnadd,
								GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(48)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(adminid, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addGap(40).addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup().addGap(31)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
				.addGap(33)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnadd, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE).addGap(22))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		AutoID();

	}
}
