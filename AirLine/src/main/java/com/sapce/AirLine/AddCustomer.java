package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.sound.sampled.ReverbType;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
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
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class AddCustomer extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtcustid;
	private JTextField Fname;
	private JTextField Lname;
	private JTextField Passno;
	private JTextField nanid;
	private JTextField cont;
	private JTextArea address;
	private JRadioButton rdbtnMale;
	private JDateChooser dob;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer frame = new AddCustomer();
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
					PreparedStatement pre = con.prepareStatement("SELECT MAX(CustomerId) AS MaxCustId FROM Customer");
					ResultSet rs = pre.executeQuery()) {

				rs.next(); // move to first (only) row
				String lastId = rs.getString("MaxCustId");

				if (lastId == null) { // no rows in table yet
					txtcustid.setText("CS001");
				} else {
					long id = Long.parseLong(lastId.substring(2).trim()); // strip "CS"
					id++;
					txtcustid.setText("CS" + String.format("%03d", id));
				}
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public AddCustomer() {
		getContentPane().setBackground(Color.BLUE);

		setTitle("Customer Panel\r\n\r\n");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 936, 634);

		JLabel lblNewLabel = new JLabel("Welcome to Customer Panel");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 36));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 153));

		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					String CustomerId = txtcustid.getText();
					String Faname = Fname.getText();
					String lname = Lname.getText();
					String passport = Passno.getText();
					String Nan = nanid.getText();
					String Address = address.getText();
					String Gender;

					if (rdbtnMale.isSelected()) {
						Gender = "Male";

					} else {
						Gender = "Female";

					}
					SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd");
					String Date = da.format(dob.getDate());

					String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=Airline_project;"
							+ "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;";
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pre = con.prepareStatement(
							"insert into Customer(CustomerId,FirstName,LastName,Passport,Nationalid,Address,Contact,Gender,DOB) Values('"
									+ CustomerId + "','" + Faname + "','" + lname + "','" + passport + "','" + Nan
									+ "','" + Address + "','" + cont.getText() + "','" + Gender + "','" + Date + "')");
					pre.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Added Successfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnadd.setIcon(null);
		btnadd.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));

		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(null);
		btnDelete.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(37)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE).addGap(61)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnadd)
										.addPreferredGap(ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)))
						.addGroup(
								groupLayout.createSequentialGroup().addContainerGap(298, Short.MAX_VALUE).addComponent(
										lblNewLabel, GroupLayout.PREFERRED_SIZE, 497, GroupLayout.PREFERRED_SIZE)))
				.addGap(87)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(37).addComponent(lblNewLabel).addGap(34)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout
						.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(btnadd)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(28, Short.MAX_VALUE)));

		JLabel lblNewLabel_1_5 = new JLabel("Contact No.");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));

		cont = new JTextField();
		cont.setColumns(10);

		JLabel lblNewLabel_1_5_1 = new JLabel("Gender");
		lblNewLabel_1_5_1.setForeground(Color.WHITE);
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(new Color(255, 255, 255));
		rdbtnMale.setBackground(new Color(0, 0, 153));
		rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 16));

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFemale.setBackground(new Color(0, 0, 153));

		JLabel lblNewLabel_1_5_2 = new JLabel("Date Of Birth");
		lblNewLabel_1_5_2.setForeground(Color.WHITE);
		lblNewLabel_1_5_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		dob = new JDateChooser();

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1
						.createSequentialGroup().addGap(20).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblNewLabel_1_5_2, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(dob, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_panel_1.createSequentialGroup()
												.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 74,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(rdbtnMale, GroupLayout.PREFERRED_SIZE, 76,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnFemale,
														GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(cont,
												GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
						.addGap(26)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(29)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(cont, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_5_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(69, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel_1 = new JLabel("Customer Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));

		txtcustid = new JTextField();
		txtcustid.setColumns(10);

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

		Fname = new JTextField();
		Fname.setColumns(10);

		Lname = new JTextField();
		Lname.setColumns(10);

		Passno = new JTextField();
		Passno.setColumns(10);

		nanid = new JTextField();
		nanid.setColumns(10);

		JLabel lblNewLabel_1_4_1 = new JLabel("Address");
		lblNewLabel_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		address = new JTextArea();
		address.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(30)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(nanid, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(Passno, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(Lname, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(Fname, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtcustid, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1_4_1, GroupLayout.PREFERRED_SIZE, 84,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(address, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
				.addContainerGap(18, GroupLayout.PREFERRED_SIZE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(35)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(txtcustid, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(Fname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(Lname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(Passno, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(nanid, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(48).addComponent(lblNewLabel_1_4_1,
										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addGap(34).addComponent(address,
										GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(34, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		AutoID();

	}
}
