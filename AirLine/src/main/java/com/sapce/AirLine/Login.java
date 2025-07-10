package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 438);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JLabel lblNewLabel_1 = new JLabel("Welcome In Space AirLine");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 26));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(148, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE).addGap(116))
				.addGroup(Alignment.LEADING,
						gl_contentPane
								.createSequentialGroup().addGap(311).addComponent(lblNewLabel_1,
										GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(322, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(26).addComponent(lblNewLabel_1).addGap(26)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(90, Short.MAX_VALUE)));

		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 26));

		username = new JTextField();
		username.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 26));

		password = new JTextField();
		password.setColumns(10);

		JButton btnlogin = new JButton("Log In");
		btnlogin.addActionListener(new ActionListener() {
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
					PreparedStatement pre = con.prepareStatement("select * from Admin where Username='"
							+ username.getText() + "' and Password='" + password.getText() + "'");
					ResultSet rs = pre.executeQuery();
					if (rs.next() != false) {
						Main main = new Main();
						main.getFrame().setVisible(true);
						Login.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Record not match");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnlogin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 20));

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(123)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(btnlogin, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE).addGap(158)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 153,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 153,
												GroupLayout.PREFERRED_SIZE))
								.addGap(60)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(password, GroupLayout.PREFERRED_SIZE, 225,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(username, GroupLayout.PREFERRED_SIZE, 225,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(187, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(42)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(username, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(31)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnlogin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(26, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
