package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GetTicket extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField ticketid;
	private JLabel name;
	private JLabel arrival;
	private JLabel gender;
	private JLabel departure;
	private JLabel contact;
	private JLabel flightname;
	private JLabel fare;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetTicket frame = new GetTicket();
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
	public GetTicket() {
		setResizable(true);
		getContentPane().setBackground(new Color(0, 0, 102));
		setBackground(new Color(0, 0, 204));
		setClosable(true);
		setBounds(100, 100, 979, 521);

		JLabel lblNewLabel = new JLabel("Ticket ID");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		ticketid = new JTextField();
		ticketid.setColumns(10);

		JButton getticket = new JButton("Get Ticket");
		getticket.addActionListener(new ActionListener() {

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
					PreparedStatement pre;
					ResultSet rs, rs1;
					pre = con.prepareStatement("select * from Ticket where Ticketid='" + ticketid.getText() + "'");

					rs = pre.executeQuery();

					if (rs.next() != false) {

						name.setText(rs.getString("Name"));
						contact.setText(rs.getString("contact"));
						gender.setText(rs.getString("gender"));
						arrival.setText(rs.getString("arrival"));
						departure.setText(rs.getString("departure"));
						fare.setText(rs.getString("Fare"));
						String fn = rs.getString("Flightid");

						pre = con.prepareStatement("select * from Flight where Flightid='" + fn + "'");

						rs1 = pre.executeQuery();
						rs1.next();
						flightname.setText(rs1.getString("Flightname"));

					}

					else {
						JOptionPane.showMessageDialog(null, "Enter valid ticket id");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getticket.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 204));

		JLabel lblHappyJourneythanks = new JLabel("Happy Journey !thanks you for choosing Our Airline");
		lblHappyJourneythanks.setForeground(Color.WHITE);
		lblHappyJourneythanks.setFont(new Font("Arial", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(346)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(41)
								.addComponent(ticketid, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(406).addComponent(getticket)))
				.addContainerGap(339, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(115, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 757, GroupLayout.PREFERRED_SIZE).addGap(91))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(277, Short.MAX_VALUE)
						.addComponent(lblHappyJourneythanks).addGap(224)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(21)
						.addComponent(lblHappyJourneythanks, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(ticketid, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(getticket, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGap(30).addComponent(panel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(26, Short.MAX_VALUE)));

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblArrival = new JLabel("Arrival");
		lblArrival.setForeground(Color.WHITE);
		lblArrival.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblDeparture = new JLabel("Departure");
		lblDeparture.setForeground(Color.WHITE);
		lblDeparture.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setForeground(Color.WHITE);
		lblContactNo.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblFare = new JLabel("Fare");
		lblFare.setForeground(Color.WHITE);
		lblFare.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblGender_1 = new JLabel("");
		lblGender_1.setForeground(Color.WHITE);
		lblGender_1.setFont(new Font("Arial", Font.BOLD, 22));

		JLabel lblFlightName = new JLabel("Flight Name");
		lblFlightName.setForeground(Color.WHITE);
		lblFlightName.setFont(new Font("Arial", Font.BOLD, 22));

		name = new JLabel("");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Arial", Font.BOLD, 22));

		gender = new JLabel("");
		gender.setForeground(Color.WHITE);
		gender.setFont(new Font("Arial", Font.BOLD, 22));

		arrival = new JLabel("");
		arrival.setForeground(Color.WHITE);
		arrival.setFont(new Font("Arial", Font.BOLD, 22));

		departure = new JLabel("");
		departure.setForeground(Color.WHITE);
		departure.setFont(new Font("Arial", Font.BOLD, 22));

		contact = new JLabel("");
		contact.setForeground(Color.WHITE);
		contact.setFont(new Font("Arial", Font.BOLD, 22));

		fare = new JLabel("");
		fare.setForeground(Color.WHITE);
		fare.setFont(new Font("Arial", Font.BOLD, 22));

		flightname = new JLabel("");
		flightname.setForeground(Color.WHITE);
		flightname.setFont(new Font("Arial", Font.BOLD, 22));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addGroup(gl_panel
								.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 62,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblArrival, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE))
								.addGap(33)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(name, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(gender, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
										.addComponent(arrival, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblDeparture, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(departure, GroupLayout.PREFERRED_SIZE, 120,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(170)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGender_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblContactNo, GroupLayout.PREFERRED_SIZE, 131,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblFare, GroupLayout.PREFERRED_SIZE, 84,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(fare, GroupLayout.PREFERRED_SIZE, 120,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(contact, GroupLayout.PREFERRED_SIZE, 120,
														GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel.createSequentialGroup().addGap(305)
								.addComponent(lblFlightName, GroupLayout.PREFERRED_SIZE, 133,
										GroupLayout.PREFERRED_SIZE)
								.addGap(44)
								.addComponent(flightname, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(67, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(23)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContactNo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(name, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(contact, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFare, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(gender, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(fare, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(28)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblArrival, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGender_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(arrival, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(26)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(departure, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblDeparture, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addGap(28)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(flightname, GroupLayout.PREFERRED_SIZE, 22,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblFlightName, GroupLayout.PREFERRED_SIZE, 22,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(30, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

}
