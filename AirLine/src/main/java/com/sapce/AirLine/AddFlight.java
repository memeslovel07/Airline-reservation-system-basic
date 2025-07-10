package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class AddFlight extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField flightid;
	private JTextField flightname;
	private JTextField arrival;
	private JTextField departure;
	private JTextField duration;
	private JTextField seats;
	private JTextField fare;
	private JDateChooser dof;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFlight frame = new AddFlight();
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
					PreparedStatement pre = con.prepareStatement("SELECT MAX(Flightid) AS MAXflitid FROM Flight");
					ResultSet rs = pre.executeQuery()) {

				rs.next(); // move to first (only) row
				String lastId = rs.getString("MAXflitid");

				if (lastId == null) { // no rows in table yet
					flightid.setText("Fl001");
				} else {
					long id = Long.parseLong(lastId.substring(2).trim()); // strip "CS"
					id++;
					flightid.setText("Fl" + String.format("%03d", id));
				}
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public AddFlight() {
		getContentPane().setBackground(Color.BLUE);
		setTitle("Customer Panel\r\n\r\n");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 936, 598);

		JLabel lblNewLabel = new JLabel("Welcome to Flight Panel");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 36));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 153));

		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd");
				String Date = da.format(dof.getDate());
				String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=Airline_project;"
						+ "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;";
				try {
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pre = con.prepareStatement(
							"insert into Flight(Flightid,flightname,Arrival,Departure,Duration,Seats,Fare,Date) Values('"
									+ flightid.getText() + "','" + flightname.getText() + "','" + arrival.getText()
									+ "','" + departure.getText() + "','" + duration.getText() + "','" + seats.getText()
									+ "','" + fare.getText() + "','" + Date + "')");
					pre.executeUpdate();
					JOptionPane.showMessageDialog(null, "Flight  Added Successfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
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
				.createSequentialGroup().addGap(37)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 497, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup().addGap(61).addComponent(panel_1,
												GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addGap(73).addComponent(btnadd)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 113,
														GroupLayout.PREFERRED_SIZE)))))
				.addGap(87)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(35).addComponent(lblNewLabel).addGap(36)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnadd)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGap(105))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));

		JLabel lblNewLabel_1_5 = new JLabel("Seats");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));

		seats = new JTextField();
		seats.setColumns(10);

		JLabel lblNewLabel_1_5_1 = new JLabel("Fare");
		lblNewLabel_1_5_1.setForeground(Color.WHITE);
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_5_2 = new JLabel("Date Of Flight");
		lblNewLabel_1_5_2.setForeground(Color.WHITE);
		lblNewLabel_1_5_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		dof = new JDateChooser();

		fare = new JTextField();
		fare.setColumns(10);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(20)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(lblNewLabel_1_5_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(dof, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 74,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(fare, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
										.addComponent(seats, GroupLayout.PREFERRED_SIZE, 152,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(26)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(29)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(seats, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(23)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(fare, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_5_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dof, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(69, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel_1 = new JLabel("Flight Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));

		flightid = new JTextField();
		flightid.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Flight Name");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_2 = new JLabel("Arrival");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_3 = new JLabel("Departure");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1_4 = new JLabel("Duration");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));

		flightname = new JTextField();
		flightname.setColumns(10);

		arrival = new JTextField();
		arrival.setColumns(10);

		departure = new JTextField();
		departure.setColumns(10);

		duration = new JTextField();
		duration.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(30)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(duration, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(departure, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(arrival, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(flightname, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(flightid, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(18, GroupLayout.PREFERRED_SIZE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(35)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(flightid, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(flightname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(arrival, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(departure, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(duration, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(190, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		AutoID();

	}
}
