package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import domain.Client;
import persistence.ClientRepository;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class FormClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdClient;
	private JTextField txtNameClient;
	private JTextField txtEmailClient;
	private JTextField txtAddressClient;
	JFormattedTextField txtCpfClient = new JFormattedTextField();
	JFormattedTextField txtDateBirthClient = new JFormattedTextField();
	JFormattedTextField txtCelClient = new JFormattedTextField();
	JFormattedTextField txtCnhClient = new JFormattedTextField();
	JFormattedTextField txtRgClient = new JFormattedTextField();
	JFormattedTextField txtCustomersSearch = new JFormattedTextField();
	ClientRepository clientRepository = new ClientRepository();
	FormControl formControl = new FormControl();

	private Client customersInEditing;
	private JTable customersToList;
	private JScrollPane ScrollPaneClient;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormClient frame = new FormClient();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 * 
	 * /* Create the frame.
	 **/
	public FormClient() {
		setForeground(Color.BLACK);
		setResizable(false);
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 788, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocation(582, 1);
		contentPane.setLayout(null);

		JButton btnNewClient = new JButton("Novo");
		btnNewClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewClient.setBounds(4, 151, 89, 40);
		btnNewClient
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\newUser32.png"));
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCustomers();
				cleanFormClient();
			}
		});
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewClient.setFocusable(false);
		btnNewClient.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnNewClient);

		JButton btnSavedClient = new JButton("Salvar");
		btnSavedClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSavedClient.setBounds(4, 192, 89, 40);
		btnSavedClient
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\check32.png"));
		btnSavedClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSaveClient();
			}
		});
		btnSavedClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSavedClient.setFocusable(false);
		btnSavedClient.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnSavedClient);

		JButton btnDeleteClient = new JButton("Excluir");
		btnDeleteClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteClient.setBounds(4, 234, 89, 40);
		btnDeleteClient
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\clean32.png"));
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				searchCustomers();
				cleanFormClient();
			}
		});
		btnDeleteClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteClient.setFocusable(false);
		btnDeleteClient.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnDeleteClient);

		JButton btnBrandSearchClient = new JButton("Buscar");
		btnBrandSearchClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBrandSearchClient.setBounds(4, 277, 89, 40);
		btnBrandSearchClient
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\search.png"));
		btnBrandSearchClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCustomersByCpf();
			}
		});
		btnBrandSearchClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBrandSearchClient.setFocusable(false);
		btnBrandSearchClient.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnBrandSearchClient);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogo.setBounds(605, 146, 171, 177);
		lblLogo.setIcon(
				new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\lv_azul_desktop.png"));
		lblLogo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lblLogo);

		JLabel lblBrandSearchClient = new JLabel("Busca/Cpf:");
		lblBrandSearchClient.setBounds(103, 296, 73, 14);
		lblBrandSearchClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBrandSearchClient.setFocusable(false);
		contentPane.add(lblBrandSearchClient);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(104, 154, 46, 14);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setFocusable(false);
		contentPane.add(lblId);

		txtIdClient = new JTextField();
		txtIdClient.setBounds(145, 150, 66, 20);
		txtIdClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdClient.setFocusable(false);
		txtIdClient.setEnabled(false);
		txtIdClient.setColumns(10);
		txtIdClient.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(txtIdClient);

		JLabel lblNameClient = new JLabel("Nome:");
		lblNameClient.setBounds(241, 152, 54, 14);
		lblNameClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNameClient.setFocusable(false);
		contentPane.add(lblNameClient);

		txtNameClient = new JTextField();
		txtNameClient.setBounds(292, 150, 303, 21);
		txtNameClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNameClient.setColumns(10);
		contentPane.add(txtNameClient);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(103, 180, 46, 14);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setFocusable(false);
		contentPane.add(lblCpf);

		JLabel lblDataNascimento = new JLabel("Data Nasc.:");
		lblDataNascimento.setBounds(264, 180, 78, 14);
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataNascimento.setFocusable(false);
		contentPane.add(lblDataNascimento);

		JLabel lblEmailClient = new JLabel("Email");
		lblEmailClient.setBounds(103, 236, 37, 14);
		lblEmailClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailClient.setFocusable(false);
		contentPane.add(lblEmailClient);

		JLabel lblCel = new JLabel("Cel:");
		lblCel.setBounds(278, 210, 28, 14);
		lblCel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCel.setFocusable(false);
		contentPane.add(lblCel);

		txtEmailClient = new JTextField();
		txtEmailClient.setBounds(146, 231, 449, 22);
		txtEmailClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmailClient.setColumns(10);
		contentPane.add(txtEmailClient);

		JLabel lblAddressClient = new JLabel("Endere\u00E7o:");
		lblAddressClient.setBounds(103, 262, 69, 14);
		lblAddressClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddressClient.setFocusable(false);
		contentPane.add(lblAddressClient);

		txtAddressClient = new JTextField();
		txtAddressClient.setBounds(176, 259, 421, 21);
		txtAddressClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAddressClient.setColumns(10);
		contentPane.add(txtAddressClient);

		JLabel lblCnh = new JLabel("CNH:");
		lblCnh.setBounds(449, 181, 35, 14);
		lblCnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnh.setFocusable(false);
		contentPane.add(lblCnh);

		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(105, 210, 25, 14);
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRg.setFocusable(false);
		contentPane.add(lblRg);

		DateFormat format = new SimpleDateFormat("dd/MM/yyy");
		txtDateBirthClient = new JFormattedTextField(format);
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
			txtDateBirthClient = new javax.swing.JFormattedTextField(data);
			txtDateBirthClient.setBounds(343, 176, 94, 22);
			txtDateBirthClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (Exception e) {
		}
		contentPane.add(txtDateBirthClient);

		txtCpfClient = new JFormattedTextField();
		txtCpfClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("###.###.###-##");
			txtCpfClient = new javax.swing.JFormattedTextField(data);
			txtCpfClient.setBounds(146, 176, 106, 22);
			txtCpfClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (Exception e) {
		}
		contentPane.add(txtCpfClient);

		txtCelClient = new JFormattedTextField();
		txtCelClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("(##)#####-####");
			txtCelClient = new javax.swing.JFormattedTextField(data);
			txtCelClient.setBounds(309, 205, 128, 22);
			txtCelClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (Exception e) {
		}
		contentPane.add(txtCelClient);

		txtRgClient = new JFormattedTextField();
		txtRgClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##########");
			txtRgClient = new javax.swing.JFormattedTextField(data);
			txtRgClient.setBounds(146, 204, 106, 22);
			txtRgClient.setFont(new Font("Tahoma", Font.PLAIN, 14));

		} catch (Exception e1) {
		}
		contentPane.add(txtRgClient);

		txtCnhClient = new JFormattedTextField();
		txtCnhClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("###########");
			txtCnhClient = new javax.swing.JFormattedTextField(data);
			txtCnhClient.setBounds(483, 177, 112, 20);
			txtCnhClient.setFont(new Font("Tahoma", Font.PLAIN, 14));

		} catch (Exception e1) {

		}
		contentPane.add(txtCnhClient);

		JLabel lblFieldRegister = new JLabel("");
		lblFieldRegister.setBounds(98, 146, 506, 140);
		lblFieldRegister.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lblFieldRegister);

		txtCustomersSearch = new JFormattedTextField();
		txtCustomersSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("###.###.###-##");
			txtCustomersSearch = new javax.swing.JFormattedTextField(data);
			txtCustomersSearch.setBounds(176, 293, 130, 24);
			txtCustomersSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (Exception e) {
		}
		contentPane.add(txtCustomersSearch);

		ScrollPaneClient = new JScrollPane();
		ScrollPaneClient.setBounds(0, 2, 776, 142);
		ScrollPaneClient.setFocusable(false);
		ScrollPaneClient.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ScrollPaneClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(ScrollPaneClient);

		JLabel lblFieldSearch = new JLabel("");
		lblFieldSearch.setBounds(98, 286, 507, 37);
		lblFieldSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lblFieldSearch);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setBounds(0, 146, 96, 177);
		contentPane.add(lblNewLabel);

		searchCustomers();

	}

	private boolean validation() {
		if (txtNameClient.getText().isEmpty() || txtNameClient.getText().length() > 20) {
			JOptionPane.showMessageDialog(this, "Digite o nome do cliente!");
			txtNameClient.requestFocus();
			txtNameClient.setText("");
			return false;
		} else {
			if (txtCpfClient.getText().equals("   .   .   -  ")) {
				JOptionPane.showMessageDialog(this, "Digite o cpf do cliente!");
				txtCpfClient.requestFocus();
				txtCpfClient.setText("");
				return false;
			} else {
				if (txtDateBirthClient.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(this, "Digite a data de nascimento do cliente!");
					txtDateBirthClient.requestFocus();
					txtDateBirthClient.setText("");
					return false;
				} else {
					if (txtCnhClient.getText().equals("           ")) {
						JOptionPane.showMessageDialog(this, "Digite o número da CNH do cliente!");
						txtCnhClient.requestFocus();
						txtCnhClient.setText("");
						return false;
					} else {
						if (txtRgClient.getText().equals("          ")) {
							JOptionPane.showMessageDialog(this, "Digite o RG do cliente!");
							txtRgClient.requestFocus();
							txtRgClient.setText("");
							return false;
						} else {
							if (txtCelClient.getText().equals("(  )     -    ")) {
								JOptionPane.showMessageDialog(this, "Digite o número do celular do cliente!");
								txtCelClient.requestFocus();
								txtCelClient.setText("");
								return false;
							} else {
								if (txtEmailClient.getText().isEmpty()) {
									JOptionPane.showMessageDialog(this, "Digite o endereço de email do cliente!");
									txtEmailClient.requestFocus();
									txtEmailClient.setText("");
									return false;
								} else {
									if (txtAddressClient.getText().isEmpty()) {
										JOptionPane.showMessageDialog(this, "Digite o endereço do cliente!");
										txtAddressClient.requestFocus();
										txtAddressClient.setText("");
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	private void cleanFormClient() {
		txtIdClient.setText("");
		txtNameClient.setText("");
		txtCpfClient.setValue(null);
		txtDateBirthClient.setValue(null);
		txtCnhClient.setText("");
		txtRgClient.setText("");
		txtCelClient.setValue(null);
		;
		txtEmailClient.setText("");
		txtAddressClient.setText("");
		txtNameClient.requestFocus();
		;
	}

	private void searchCustomers() {
		ArrayList<Client> customersList = clientRepository.allCustomers();
		loadJTable(customersList);
	}

	private void searchCustomersByCpf() {
		if (clientRepository.empty == 1) {
			JOptionPane.showMessageDialog(this, "Nenhum CPF corresponde à pesquisa!");
			searchCustomers();
			txtCustomersSearch.requestFocus();
			txtCustomersSearch.setText("");
			return;
		} else {
			ArrayList<Client> customersList = clientRepository.allCustomersByCpf(txtCustomersSearch.getText());
			loadJTable(customersList);
			txtCustomersSearch.requestFocus();
			txtCustomersSearch.setText("");
		}
	}

	private void toSaveClient() {
		if (txtIdClient.getText().equals("")) {
			CreateNewClient();
			searchCustomers();
			formControl.repaint();
		
			
		} else {
			editClient();
		}
	}

	private void editClient() {
		customersInEditing = new Client(Integer.parseInt(txtIdClient.getText()), txtNameClient.getText(),
				txtCpfClient.getText(), txtDateBirthClient.getText(), txtCnhClient.getText(), txtRgClient.getText(),
				txtCelClient.getText(), txtEmailClient.getText(), txtAddressClient.getText());
		if (!clientRepository.refreshCustomers(customersInEditing))
			JOptionPane.showMessageDialog(this, "Erro interno do sistema - Acionar suporte!");
		else {
			JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!");
			editedClient();
			searchCustomers();
			cleanFormClient();
			txtNameClient.requestFocus();
		}
	}

	private void editedClient() {
		if (txtIdClient.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nenhuma edição realizada!");
			searchCustomers();
			txtCustomersSearch.requestFocus();
			txtCustomersSearch.setText("");
			return;
		}
		ArrayList<Client> customersList = clientRepository.getEditedClientId(Integer.parseInt(txtIdClient.getText()));
		loadJTable(customersList);
		txtCustomersSearch.requestFocus();
		txtCustomersSearch.setText("");
	}

	private void CreateNewClient() {
		if (validation()) {
			Client newClient;
			newClient = new Client(txtNameClient.getText(), txtCpfClient.getText(), txtDateBirthClient.getText(),
					txtCnhClient.getText(), txtRgClient.getText(), txtCelClient.getText(), txtEmailClient.getText(),
					txtAddressClient.getText());
			clientRepository.toSave(newClient);
			if (newClient.getId() == -1) {
				JOptionPane.showMessageDialog(this, "Erro interno de sistema - acionar suporte!");
				searchCustomers();
			} else {
				JOptionPane.showMessageDialog(this, "Cliente Cadastrado");
				cleanFormClient();
				searchCustomers();
			}
		}
	}

	private void delete() {
		if (txtIdClient.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Carregue um cadastro antes de excluir!");
			return;
		}
		int question = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?",
				"Confirme essa ação!", JOptionPane.YES_NO_OPTION);
		if (question == JOptionPane.YES_OPTION) {
			if (!clientRepository.delete(Integer.parseInt(txtIdClient.getText()))) {
				JOptionPane.showMessageDialog(this, "Erro ao excluir");
				return;
			}
			JOptionPane.showMessageDialog(this, "Cadastro excluído co sucesso!");
			cleanFormClient();
			searchCustomers();
		}
	}

	private void loadJTable(ArrayList<Client> customersList) {

		customersToList = Util.customersListToJTable(customersList);
		customersToList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		customersToList.setFocusable(false);

		customersToList.setDefaultEditor(Object.class, null);

		customersToList.getColumnModel().getColumn(0).setMinWidth(0);
		customersToList.getColumnModel().getColumn(0).setMaxWidth(0);
		customersToList.getColumnModel().getColumn(0).setWidth(0);

		ScrollPaneClient.setViewportView(customersToList);

		customersToList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					customersForEditing((customersToList).getSelectedRow());
				}
			}
		});
	}

	private void customersForEditing(int indexJTable) {
		int clientId = (int) this.customersToList.getModel().getValueAt(indexJTable, 0);
		customersInEditing = clientRepository.getClientById(clientId);
		if (customersInEditing == null) {
			JOptionPane.showMessageDialog(this, "Cadastro não encontrado!");
			return;
		}
		fillInFieldsCustomers();
	}

	private void fillInFieldsCustomers() {
		txtIdClient.setText(String.valueOf(customersInEditing.getId()));
		txtNameClient.setText(customersInEditing.getNome());
		txtCpfClient.setText(customersInEditing.getCpf());
		txtDateBirthClient.setText(customersInEditing.getNascimento());
		txtCnhClient.setText(customersInEditing.getCnh());
		txtRgClient.setText(customersInEditing.getRg());
		txtCelClient.setText(customersInEditing.getCelular());
		txtEmailClient.setText(customersInEditing.getEmail());
		txtAddressClient.setText(customersInEditing.getEndereco());
	}
}
