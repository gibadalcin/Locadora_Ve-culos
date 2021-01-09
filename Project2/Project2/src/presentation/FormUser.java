package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import domain.User;
import persistence.UserRepository;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FormUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserId;
	private JTextField txtUserName;
	private JTextField txtUserEmail;
	private JPasswordField pxtUserPassword;
	private JPasswordField pxtUserPasswordConfirm;
	private JCheckBox ckbUserAdmin;
	private JScrollPane scrollPane;
	private UserRepository userRepository = new UserRepository();
	private JTable usersListJTable;
	private User editingUser;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormUser frame = new FormUser();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 * 
	 * /** Create the frame.
	 */
	public FormUser() {
		setRootPaneCheckingEnabled(false);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle(" Usu\u00E1rios");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 607, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocation(-10, 1);

		JButton btnSearchUser = new JButton("Consulta Admin");
		btnSearchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedAdmin();
			}
		});
		btnSearchUser
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\search.png"));
		btnSearchUser.setFocusable(false);
		btnSearchUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearchUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearchUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearchUser.setBounds(234, 146, 186, 45);
		contentPane.add(btnSearchUser);

		scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(2, 2, 589, 142);
		contentPane.add(scrollPane);

		JLabel lblIdUser = new JLabel("Id:");
		lblIdUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdUser.setBounds(8, 169, 38, 14);
		contentPane.add(lblIdUser);

		txtUserId = new JTextField();
		txtUserId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtUserId.setEnabled(false);
		txtUserId.setBounds(55, 167, 55, 20);
		contentPane.add(txtUserId);
		txtUserId.setColumns(10);

		JLabel lblUserName = new JLabel("Nome:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(8, 199, 46, 14);
		contentPane.add(lblUserName);

		JLabel lblUserEmail = new JLabel("Email:");
		lblUserEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserEmail.setBounds(8, 231, 46, 14);
		contentPane.add(lblUserEmail);

		JLabel lblUserPassword = new JLabel("Senha:");
		lblUserPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserPassword.setBounds(8, 261, 46, 14);
		contentPane.add(lblUserPassword);

		JLabel lblUserPasswordConfirm = new JLabel("Confirmar Senha:");
		lblUserPasswordConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserPasswordConfirm.setBounds(8, 291, 109, 14);
		contentPane.add(lblUserPasswordConfirm);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserName.setBounds(56, 197, 260, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		txtUserEmail = new JTextField();
		txtUserEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserEmail.setColumns(10);
		txtUserEmail.setBounds(55, 228, 260, 20);
		contentPane.add(txtUserEmail);

		pxtUserPassword = new JPasswordField();
		pxtUserPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pxtUserPassword.setBounds(55, 259, 260, 20);
		contentPane.add(pxtUserPassword);

		pxtUserPasswordConfirm = new JPasswordField();
		pxtUserPasswordConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pxtUserPasswordConfirm.setBounds(122, 288, 194, 20);
		contentPane.add(pxtUserPasswordConfirm);

		JButton btnNewUser = new JButton("Novo");
		btnNewUser
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\newUser32.png"));
		btnNewUser.setFocusable(false);
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				searchUser();
			}
		});
		btnNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewUser.setBounds(332, 193, 89, 40);
		contentPane.add(btnNewUser);

		JButton btnSaveUser = new JButton("Salvar");
		btnSaveUser.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\check32.png"));
		btnSaveUser.setFocusable(false);
		btnSaveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSave();
			}
		});
		btnSaveUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSaveUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveUser.setBounds(332, 235, 89, 40);
		contentPane.add(btnSaveUser);

		JButton btnDeleteUser = new JButton("Excluir");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDeleteUser
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\clean32.png"));
		btnDeleteUser.setFocusable(false);
		btnDeleteUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteUser.setBounds(332, 277, 89, 40);
		contentPane.add(btnDeleteUser);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setBounds(234, 147, 185, 45);
		contentPane.add(lblNewLabel);

		ckbUserAdmin = new JCheckBox("Administrador");
		ckbUserAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ckbUserAdmin.setBounds(112, 165, 110, 23);
		contentPane.add(ckbUserAdmin);

		JLabel lblButtons = new JLabel("");
		lblButtons.setIcon(
				new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\lv_azul_desktop.png"));
		lblButtons.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblButtons.setBounds(329, 146, 262, 177);
		contentPane.add(lblButtons);

		JLabel lblFields = new JLabel("");
		lblFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFields.setBounds(2, 146, 325, 177);
		contentPane.add(lblFields);

		searchUser();
	}

	private boolean validation() {
		if (txtUserName.getText().isEmpty() || txtUserName.getText().length() > 20) {
			txtUserName.requestFocus();
			txtUserName.setText("");
			JOptionPane.showMessageDialog(this, "Digite o nome!");
			return false;
		} else if (txtUserEmail.getText().isEmpty() || txtUserName.getText().length() > 50) {
			txtUserEmail.requestFocus();
			txtUserEmail.setText("");
			JOptionPane.showMessageDialog(this, "Digite o endereço de email!");
			return false;
		} else {
			if (pxtUserPassword.getPassword().length < 6 || pxtUserPassword.getPassword().length > 10) {
				pxtUserPassword.requestFocus();
				pxtUserPassword.setText("");
				JOptionPane.showMessageDialog(this, "Digite a senha! \n" + "Deve ter entre 6 e 10 caracteres!");
				return false;
			} else {
				if (!String.valueOf(pxtUserPasswordConfirm.getPassword())
						.equals(String.valueOf(pxtUserPassword.getPassword()))) {
					pxtUserPasswordConfirm.requestFocus();
					pxtUserPasswordConfirm.setText("");
					JOptionPane.showMessageDialog(this, "Senha não confere!");
					return false;
				}
			}
		}
		return true;
	}

	private void clean() {
		txtUserId.setText("");
		txtUserName.setText("");
		txtUserEmail.setText("");
		pxtUserPassword.setText("");
		pxtUserPasswordConfirm.setText("");
		ckbUserAdmin.setSelected(false);
	}

	private void toSave() {
		if (txtUserId.getText().equals(""))
			createNewUser();
		else
			editUser();
	}

	@SuppressWarnings("deprecation")
	private void createNewUser() {
		if (validation()) {
			User newUser;
			newUser = new User(txtUserName.getText(), txtUserEmail.getText(), pxtUserPassword.getText(),
					ckbUserAdmin.isSelected());
			userRepository.toSave(newUser);
			if (newUser.getId() == -1) {
				JOptionPane.showMessageDialog(this, "Errro no sistema! \n" + "Contate o suporte!");
			} else {
				JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
				clean();
				searchUser();
			}
		}
	}

	private void searchUser() {
		ArrayList<User> usersList = userRepository.getAllUsers();
		LoadJTable(usersList);
	}

	private void LoadJTable(ArrayList<User> usersList) {
		usersListJTable = usersListToJTable(usersList);

		usersListJTable.setDefaultEditor(Object.class, null);

		usersListJTable.getColumnModel().getColumn(0).setMinWidth(0);
		usersListJTable.getColumnModel().getColumn(0).setMaxWidth(0);
		usersListJTable.getColumnModel().getColumn(0).setWidth(0);

		scrollPane.setViewportView(usersListJTable);

		usersListJTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					putUserInEdit(usersListJTable.getSelectedRow());
				}
			}
		});
	}

	private JTable usersListToJTable(ArrayList<User> usersList) {
		String[] columns = new String[] { "Id", "Nome", "Email", "Admin" };

		Object[][] data = new Object[usersList.size()][columns.length];

		for (int i = 0; i < usersList.size(); i++) {
			data[i][0] = usersList.get(i).getId();
			data[i][1] = usersList.get(i).getNome();
			data[i][2] = usersList.get(i).getEmail();
			data[i][3] = usersList.get(i).getAdmin();
		}
		return new JTable(data, columns);
	}

	private void putUserInEdit(int indexJTable) {
		int userId = (int) this.usersListJTable.getModel().getValueAt(indexJTable, 0);
		editingUser = userRepository.getUserById(userId);
		if (editingUser == null) {
			JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
			return;
		}
		fillInFields();
	}

	private void selectedAdmin() {
		ArrayList<User> adminList = userRepository.getAllUsersAdmin();
		LoadJTable(adminList);
	}

	private void fillInFields() {
		txtUserId.setText(String.valueOf(editingUser.getId()));
		txtUserName.setText(editingUser.getNome());
		txtUserEmail.setText(editingUser.getEmail());
		pxtUserPassword.setText(editingUser.getSenha());
		pxtUserPasswordConfirm.setText(editingUser.getSenha());
		ckbUserAdmin.setSelected(editingUser.getAdmin());
		if (ckbUserAdmin.isSelected() == true) {

		}
	}

	@SuppressWarnings("deprecation")
	private void editUser() {
		editingUser = new User(Integer.parseInt(txtUserId.getText()), txtUserName.getText(), txtUserEmail.getText(),
				pxtUserPassword.getText(), ckbUserAdmin.isSelected());
		if (!validation()) {
			JOptionPane.showMessageDialog(this, "Usuário inválido!");
			return;
		}

		if (!userRepository.updateUser(editingUser))
			JOptionPane.showMessageDialog(this, "Erro no Sistema\n" + "Acionar suporte!");

		else {
			JOptionPane.showMessageDialog(this, "Usuário atualizado!");
			searchUser();
			clean();
		}
	}

	private void delete() {
		if (txtUserId.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Selecione um usuário \n" + "antes de excluir!");
			return;
		}

		int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?\n", "Confirme essa ação!",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			if (!userRepository.delete(Integer.parseInt(txtUserId.getText()))) {
				JOptionPane.showMessageDialog(this, "Erro ao excluir!");
				return;
			}
			JOptionPane.showMessageDialog(this, "Usuário excluido com sucesso!");
			clean();
			searchUser();
		}
	}
}
