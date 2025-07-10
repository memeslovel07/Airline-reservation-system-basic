package com.sapce.AirLine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.*;

@SuppressWarnings("unused")
public class Main {
	private JDesktopPane desktopPane;
	private JFrame frmMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setLocationByPlatform(true);
		frmMain.setTitle("Main\r\n");
		frmMain.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		frmMain.setBounds(100, 100, 731, 716);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktopPane = new JDesktopPane();
		frmMain.setContentPane(desktopPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		frmMain.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Customer");
		mnNewMenu.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Add Customer");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddCustomer cust = new AddCustomer();
				desktopPane.add(cust);
				cust.setVisible(true);
				try {
					cust.setSelected(true);
				} catch (java.beans.PropertyVetoException ex) {
					ex.printStackTrace();
				}

			}

		});
		mntmNewMenuItem.setIcon(null);
		mntmNewMenuItem.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmSearchCustoMer = new JMenuItem("Search Customer");
		mntmSearchCustoMer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchCustomer customer = new SearchCustomer();
				desktopPane.add(customer);
				customer.setVisible(true);
			}
		});
		mntmSearchCustoMer.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu.add(mntmSearchCustoMer);

		JMenu mnNewMenu_1 = new JMenu("Flight");
		mnNewMenu_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmAddFlight = new JMenuItem("Add Flight");
		mntmAddFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFlight flight = new AddFlight();
				desktopPane.add(flight);
				flight.setVisible(true);
				try {
					flight.setSelected(true);
				} catch (java.beans.PropertyVetoException ex) {
					ex.printStackTrace();

				}

			}
		});
		mntmAddFlight.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1.add(mntmAddFlight);

		JMenuItem mntmBookFlight = new JMenuItem("Book Flight");
		mntmBookFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("rawtypes")
				BookTicket newTicket = new BookTicket();
				desktopPane.add(newTicket);
				newTicket.setVisible(true);
			}
		});
		mntmBookFlight.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1.add(mntmBookFlight);

		JMenu mnNewMenu_1_1 = new JMenu("Ticket");
		mnNewMenu_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1_1.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_1_1);

		JMenuItem mntmViewTicket = new JMenuItem("View Ticket");
		mntmViewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetTicket ticket = new GetTicket();
				desktopPane.add(ticket);
				ticket.setVisible(true);
			}
		});
		mntmViewTicket.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1_1.add(mntmViewTicket);

		JMenu mnNewMenu_1_1_1 = new JMenu("Admin");
		mnNewMenu_1_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1_1_1.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_1_1_1);

		JMenuItem mntmAddAdmin = new JMenuItem("Add Admin");
		mntmAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAdmin admin = new AddAdmin();
				desktopPane.add(admin);
				admin.setVisible(true);
			}
		});
		mntmAddAdmin.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		mnNewMenu_1_1_1.add(mntmAddAdmin);

		JDesktopPane desktopPane = new JDesktopPane();
		frmMain.getContentPane().add(desktopPane, BorderLayout.CENTER);
	}

	public JFrame getFrame() {
		return frmMain;
	}

}
