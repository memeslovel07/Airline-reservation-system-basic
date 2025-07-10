package com.sapce.AirLine;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTicket<E> extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField customerid;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField contact;
	private JTextField Gender;
	private JTextField fare;
	private JTextField totalticket;
	private JTextField totalfare;
	private JLabel lblId;
	private JComboBox<String> arrival;
	private JComboBox<String> departure;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("rawtypes")
					BookTicket frame = new BookTicket();
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
					PreparedStatement pre = con.prepareStatement("SELECT MAX(Ticketid) AS MAXticketid FROM Ticket");
					ResultSet rs = pre.executeQuery()) {

				rs.next(); // move to first (only) row
				String lastId = rs.getString("MAXticketid");

				if (lastId == null) { // no rows in table yet
					lblId.setText("Tk001");
				} else {
					long id = Long.parseLong(lastId.substring(2).trim()); // strip "CS"
					id++;
					lblId.setText("Tk" + String.format("%03d", id));
				}
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public BookTicket() {
		setBackground(Color.BLUE);
		setClosable(true);
		setBounds(100, 100, 1377, 792);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));
		panel.setForeground(Color.BLACK);

		JLabel lblBookToYour = new JLabel("Book Your Flight! And Happy Journey");
		lblBookToYour.setForeground(Color.BLACK);
		lblBookToYour.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblTicketId = new JLabel("Ticket Id");
		lblTicketId.setForeground(Color.WHITE);
		lblTicketId.setFont(new Font("Tahoma", Font.BOLD, 21));

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = table.getSelectedRow();
				String flightid = table.getModel().getValueAt(row, col).toString();
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
							.prepareStatement("select fare from Flight where Flightid='" + flightid + "'");
					ResultSet rs = pre.executeQuery();
					rs.next();
					fare.setText(rs.getString("Fare"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
				new Object[][] { { "Flight ID", "Flight Name", "Arrival", "Departure", "Duration", "Date" }, },
				new String[] { "Flight ID", "Flight Name", "Arrival", "Departure", "Duration", "Date" }));
		table.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblId = new JLabel("ID");
		lblId.setForeground(new Color(255, 0, 0));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 21));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 102));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(59).addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(table,
										GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(496).addComponent(lblBookToYour,
								GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(550)
								.addComponent(lblTicketId, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addGap(73).addComponent(lblId)))
				.addContainerGap(63, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(20)
						.addComponent(lblBookToYour, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTicketId, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 195,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(table, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		JLabel lblNewLabel_1_1_1 = new JLabel("Customer Id");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_2 = new JLabel("First Name");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_3 = new JLabel("Last Name");
		lblNewLabel_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_4 = new JLabel("Contact");
		lblNewLabel_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_5 = new JLabel("Gender");
		lblNewLabel_1_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_6 = new JLabel("Fare");
		lblNewLabel_1_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_1_6.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_7 = new JLabel("Total Ticket");
		lblNewLabel_1_1_7.setForeground(Color.WHITE);
		lblNewLabel_1_1_7.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_8 = new JLabel("Total Fare");
		lblNewLabel_1_1_8.setForeground(Color.WHITE);
		lblNewLabel_1_1_8.setFont(new Font("Tahoma", Font.BOLD, 18));

		customerid = new JTextField();
		customerid.setColumns(10);

		firstname = new JTextField();
		firstname.setColumns(10);

		lastname = new JTextField();
		lastname.setColumns(10);

		contact = new JTextField();
		contact.setColumns(10);

		Gender = new JTextField();
		Gender.setColumns(10);

		fare = new JTextField();
		fare.setColumns(10);

		totalticket = new JTextField();
		totalticket.setColumns(10);

		totalfare = new JTextField();
		totalfare.setColumns(10);

		JButton idsearch = new JButton("Search");
		idsearch.addActionListener(new ActionListener() {
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
						String gender = rs.getString("Gender");
						if (gender != null && gender.trim().equalsIgnoreCase("Male")) {
							Gender.setText("Male");

						} else {
							Gender.setText("Female");

						}
						contact.setText(rs.getString("Contact"));

					} else {
						JOptionPane.showMessageDialog(null, "Customer not Exist");
					}
				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		idsearch.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnfare = new JButton("Cal. Fare");
		btnfare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Fare = Integer.parseInt(fare.getText().trim());
				int noofticket = Integer.parseInt(totalticket.getText().trim());
				int ans = Fare * noofticket;

				totalfare.setText(String.valueOf(ans));

			}
		});
		btnfare.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton book = new JButton("Book");
		book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = firstname.getText() + lastname.getText();

				String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=Airline_project;"
						+ "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;";

				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {

					int col = 0;
					int row = table.getSelectedRow();

					String flightid = table.getModel().getValueAt(row, col).toString();
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pre = con.prepareStatement(
							"insert into ticket(Ticketid,Flightid,Customerid,Arrival,Departure,Name,Gender,Contact,Fare) values('"
									+ lblId.getText() + "','" + flightid + "','" + customerid.getText() + "','"
									+ arrival.getSelectedItem().toString() + "','"
									+ departure.getSelectedItem().toString() + "','" + name + "','" + Gender.getText()
									+ "','" + contact.getText() + "','" + fare.getText() + "')");
					pre.executeUpdate();
					JOptionPane.showMessageDialog(null, "Ticket Booked successfully");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		book.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(30)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
								.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 117,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNewLabel_1_1_2, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1_1_3, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 94,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_5, GroupLayout.PREFERRED_SIZE, 94,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_6, GroupLayout.PREFERRED_SIZE, 94,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_7, GroupLayout.PREFERRED_SIZE, 117,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_8, GroupLayout.PREFERRED_SIZE, 94,
												GroupLayout.PREFERRED_SIZE))
								.addGap(67)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(totalfare, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(totalticket, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(btnfare, GroupLayout.PREFERRED_SIZE, 144,
														Short.MAX_VALUE))
										.addComponent(fare, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addComponent(Gender, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(contact, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(customerid, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(idsearch, GroupLayout.PREFERRED_SIZE, 114,
														GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(book, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
										.addComponent(cancel, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(78)))));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(51)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(customerid, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(idsearch, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(28)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(contact, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Gender, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(24)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1_6, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(fare, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_7, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalticket, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnfare, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_8, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalfare, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(book, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(cancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(34)));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("Search Your Flight");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setForeground(Color.WHITE);

		JLabel lblNewLabel_1 = new JLabel("Arrival");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1 = new JLabel("Departure");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		arrival = new JComboBox<String>();
		arrival.setFont(new Font("Tahoma", Font.PLAIN, 16));
		arrival.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Kanpur", "Lucknow", "Goa", "Mumbai", "Agra", "Dehli" }));
		arrival.setForeground(Color.BLACK);
		arrival.setToolTipText("");

		departure = new JComboBox<String>();
		departure.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		departure.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Kanpur", "Lucknow", "Goa", "Mumbai", "Agra", "Dehli" }));
		departure.setToolTipText("Kanpur\r\nLucknow\r\nGoa\r\nAgra \r\nDehli\r\nMubai");

		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String Arrival = arrival.getSelectedItem().toString();
				String Departure = departure.getSelectedItem().toString();

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
					PreparedStatement pre = con.prepareStatement(
							"select * from Flight where Arrival='" + Arrival + "' and Departure='" + Departure + "'");

					ResultSet rs = pre.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int c = rsmd.getColumnCount();
					DefaultTableModel dftm = (DefaultTableModel) table.getModel();
					while (rs.next()) {
						Vector<String> v = new Vector<>();
						for (int i = 0; i <= c; i++) {

							v.add(rs.getString("Flightid"));
							v.add(rs.getString("Flightname"));
							v.add(rs.getString("Arrival"));
							v.add(rs.getString("Departure"));
							v.add(rs.getString("Duration"));
							v.add(rs.getString("Date"));
						}
						dftm.addRow(v);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		search.setFont(new Font("Tahoma", Font.BOLD, 16));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(177)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(190, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addGap(48).addGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(arrival, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
								.addComponent(departure, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1)))
						.addGap(66))
				.addGroup(gl_panel.createSequentialGroup().addGap(234).addComponent(search).addContainerGap(252,
						Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(29).addComponent(lblNewLabel).addGap(28)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(arrival, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(departure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(search)
				.addContainerGap(19, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		getContentPane().setLayout(groupLayout);
		AutoID();

	}
}
