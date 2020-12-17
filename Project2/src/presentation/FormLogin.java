package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.SoftBevelBorder;
import domain.User;
import persistence.UserRepository;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField pxtPassword;
	private UserRepository userRepository = new UserRepository();
	private User loggedInUser;
	private FormMenu formMenu;

	/**
	 * Launch the application.
	 *//**
		 * public static void main(String[] args) { EventQueue.invokeLater(new
		 * Runnable() { public void run() { try { FormLogin frame = new FormLogin();
		 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
		 * }
		 */
	/**
	 * Create the frame.
	 */
	public FormLogin(FormMenu menu) {
		setRootPaneCheckingEnabled(false);
		setResizable(false);
		formMenu = menu;
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		lblEmail.setBounds(15, 16, 40, 14);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Senha:");
		lblPassword.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		lblPassword.setBounds(15, 43, 48, 14);
		contentPane.add(lblPassword);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
				public void keyPressed(KeyEvent e) {
				    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				    	pxtPassword.requestFocus();
				    }
			}
		});
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setBounds(65, 11, 227, 24);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		pxtPassword = new JPasswordField();
		pxtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	login();
			    }
			}
		});
		pxtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pxtPassword.setBounds(65, 38, 227, 24);
		contentPane.add(pxtPassword);

		JButton btnEnter = new JButton("  Entrar");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnEnter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnter.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEnter.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\user.png"));
		btnEnter.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		btnEnter.setBounds(65, 65, 227, 30);
		contentPane.add(btnEnter);

		JLabel lblNewLabel_2 = new JLabel("Design By Gibadalcin");
		lblNewLabel_2.setRequestFocusEnabled(false);
		lblNewLabel_2.setForeground(SystemColor.activeCaption);
		lblNewLabel_2.setFont(new Font("a Aha Wow", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 101, 290, 18);
		contentPane.add(lblNewLabel_2);

		JButton btnClose = new JButton("");
		btnClose.setToolTipText("Fechar Programa");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnClose.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\close1.png"));
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setBounds(11, 66, 52, 29);
		contentPane.add(btnClose);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setRequestFocusEnabled(false);
		lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setBounds(7, 6, 292, 93);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel((String) null);
		lblNewLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setBounds(4, 3, 298, 117);
		contentPane.add(lblNewLabel);
	}

	@SuppressWarnings("deprecation")
	private void login() {
		loggedInUser = userRepository.getUserByEmailOrPassword(txtEmail.getText(), pxtPassword.getText());

		if (loggedInUser == null) {
			JOptionPane.showMessageDialog(this, "Usuário ou senha inválido!");
			txtEmail.setText("");
			pxtPassword.setText("");
			txtEmail.requestFocus();
			return;
		}
		formMenu.setLoggedInUser(loggedInUser);
		this.setVisible(false);
		formMenu.setVisible(true);
	}

	private void close() {
		System.exit(0);
	}
}
