package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import domain.User;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class FormMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FormRental formRental = new FormRental();
	private FormClient formClient = new FormClient();
	private FormUser formUser = new FormUser();
	private FormControl formControl = new FormControl();
	private User loggedInUser;
	private JLabel lblUserLogin;
	private JButton btnUser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenu frame = new FormMenu();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public FormMenu() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setResizable(false);
		setBounds(976, 139, 173, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocation(420, 180);

		JButton btnCars = new JButton("Ve\u00EDculos");
		btnCars.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFormRental();
			}
		});
		btnCars.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\car1_32.png"));
		btnCars.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCars.setBounds(0, 88, 157, 56);
		contentPane.add(btnCars);

		JButton btnCustomers = new JButton("Clientes");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFormClient();
			}
		});
		btnCustomers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCustomers
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\client32.png"));
		btnCustomers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCustomers.setBounds(1, 146, 156, 56);
		contentPane.add(btnCustomers);

		JButton btnLocation = new JButton("Loca\u00E7\u00E3o");
		btnLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFormControl();
			}
		});
		btnLocation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLocation
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\location32.png"));
		btnLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLocation.setBounds(1, 204, 156, 56);
		contentPane.add(btnLocation);

		btnUser = new JButton("Usu\u00E1rio");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFormUser();
			}
		});
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\user32.png"));
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUser.setBounds(1, 31, 156, 56);
		contentPane.add(btnUser);

		JButton btnCloseMenu = new JButton("Sair     ");
		btnCloseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeMenu();
			}
		});
		btnCloseMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCloseMenu.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\sair.png"));
		btnCloseMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCloseMenu.setBounds(1, 262, 156, 56);
		contentPane.add(btnCloseMenu);

		lblUserLogin = new JLabel("");
		lblUserLogin.setForeground(SystemColor.windowBorder);
		lblUserLogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblUserLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserLogin.setBounds(1, 4, 156, 26);
		contentPane.add(lblUserLogin);

		JLabel lblAllField = new JLabel("");
		lblAllField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAllField.setBounds(0, 0, 170, 321);
		contentPane.add(lblAllField);

		JLabel lblNewLabel = new JLabel("Design by gibadalcin");
		lblNewLabel.setForeground(UIManager.getColor("activeCaption"));
		lblNewLabel.setFont(new Font("a Aha Wow", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(7, 321, 150, 21);
		contentPane.add(lblNewLabel);

		loadLogin();
	}

	private void openFormRental() {
		if (formRental.isShowing()) {
			this.formRental.setVisible(false);
		} else {
			this.formRental.setVisible(true);
			FormMenu.isDefaultLookAndFeelDecorated();
		}
	}

	private void openFormClient() {
		if (formClient.isShowing()) {
			this.formClient.setVisible(false);
		} else {
			this.formClient.setVisible(true);
			FormMenu.isDefaultLookAndFeelDecorated();
		}
	}

	private void openFormUser() {
		if (formUser.isShowing() && loggedInUser.getAdmin()) {
			this.formUser.setVisible(false);
		} else if (loggedInUser.getAdmin()) {
			this.formUser.setVisible(true);
			FormMenu.isDefaultLookAndFeelDecorated();
		}
	}

	private void openFormControl() {
		if (formControl.isShowing()) {
			this.formControl.setVisible(false);
		} else {
			this.formControl.setLoggedInUser(loggedInUser);
			this.formControl.setVisible(true);
			FormMenu.isDefaultLookAndFeelDecorated();
		}
	}

	private void loadLogin() {
		this.setVisible(false);
		FormLogin formLogin = new FormLogin(this);
		formLogin.setVisible(true);
	}

	private void closeMenu() {
		loadLogin();
		this.formControl.setVisible(false);
		this.formUser.setVisible(false);
		this.formRental.setVisible(false);
		this.formClient.setVisible(false);
	}

	public void setLoggedInUser(User user) {
		loggedInUser = user;
		lblUserLogin.setText(loggedInUser.getNome());
	}
}
