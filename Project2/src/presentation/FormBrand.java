package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import domain.Brand;
import persistence.BrandRepository;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class FormBrand extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSite;
	private JTextField txtContry;
	private JTextField txtSearchLogoBrand;
	private JTextField txtBrandSearch;
	private BrandRepository brandRepository = new BrandRepository();
	private JTable brandsToList;
	private Brand brandsInEditing;
	private JScrollPane ScrollPaneBrand;
	private JLabel lblBrandIcon;
	private JLabel lblFormField;
	private JLabel lblFieldLogo;
	private JButton btnReturn;
	private FormRental formRental;

	/**
	 * Launch the application.
	 *//**
		 * public static void main(String[] args) { EventQueue.invokeLater(new
		 * Runnable() { public void run() { try { FormBrand frame = new FormBrand();
		 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
		 * }
		 */
	/**
	 * Create the frame.
	 */
	public FormBrand(FormRental formRentalParameter) {
		this.formRental = formRentalParameter;
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setTitle(" Marcas");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 607, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocation(-10, 355);
		contentPane.setLayout(null);

		JButton btnSearchLogoBrand = new JButton("Selecionar/Imagem");
		btnSearchLogoBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearchLogoBrand.setBounds(61, 106, 155, 34);
		btnSearchLogoBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFindPath();
			}
		});
		contentPane.add(btnSearchLogoBrand);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(5, 10, 46, 14);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setFocusable(false);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(6, 34, 54, 14);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setFocusable(false);
		contentPane.add(lblName);

		JLabel lblAddress = new JLabel("Site:\r\n");
		lblAddress.setBounds(6, 58, 54, 14);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setFocusable(false);
		contentPane.add(lblAddress);

		JLabel lblContry = new JLabel("Pa\u00EDs:");
		lblContry.setBounds(6, 83, 46, 14);
		lblContry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContry.setFocusable(false);
		contentPane.add(lblContry);

		JLabel lblBrandSearch = new JLabel("Busca/Marca:");
		lblBrandSearch.setBounds(6, 152, 95, 14);
		lblBrandSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBrandSearch.setFocusable(false);
		contentPane.add(lblBrandSearch);

		txtId = new JTextField();
		txtId.setBounds(62, 9, 46, 20);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setEnabled(false);
		txtId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtId.setFocusable(false);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(62, 32, 255, 22);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtSite = new JTextField();
		txtSite.setBounds(62, 57, 153, 22);
		txtSite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSite.setColumns(10);
		contentPane.add(txtSite);

		txtContry = new JTextField();
		txtContry.setBounds(62, 82, 153, 22);
		txtContry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtContry.setColumns(10);
		contentPane.add(txtContry);

		txtSearchLogoBrand = new JTextField();
		txtSearchLogoBrand.setBounds(111, 9, 206, 20);
		txtSearchLogoBrand.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSearchLogoBrand.setEnabled(false);
		txtSearchLogoBrand.setFocusable(false);
		txtSearchLogoBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearchLogoBrand.setColumns(10);
		contentPane.add(txtSearchLogoBrand);

		txtBrandSearch = new JTextField();
		txtBrandSearch.setBounds(97, 149, 221, 20);
		txtBrandSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBrandSearch.setColumns(10);
		contentPane.add(txtBrandSearch);

		JLabel lblFieldSearch = new JLabel("");
		lblFieldSearch.setBounds(1, 144, 325, 29);
		lblFieldSearch.setFocusable(false);
		lblFieldSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lblFieldSearch);

		JButton btnNewBrand = new JButton("Novo");
		btnNewBrand.setBounds(331, 3, 89, 40);
		btnNewBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBrands();
				cleanFormBrand();
			}
		});
		btnNewBrand
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\rebrand32.png"));
		btnNewBrand.setFocusable(false);
		btnNewBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewBrand.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnNewBrand);

		JButton btnSaved = new JButton("Salvar");
		btnSaved.setBounds(331, 44, 89, 40);
		btnSaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSaveBrands();
			}
		});
		btnSaved.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\check32.png"));
		btnSaved.setFocusable(false);
		btnSaved.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaved.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnSaved);

		JButton btnDelete = new JButton("Excluir");
		btnDelete.setBounds(331, 86, 89, 40);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\clean32.png"));
		btnDelete.setFocusable(false);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnDelete);

		JButton btnBrandSearch = new JButton("Buscar");
		btnBrandSearch.setBounds(332, 128, 89, 40);
		btnBrandSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBrandsByName();
				lblBrandIcon.repaint();
			}
		});
		btnBrandSearch
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\search.png"));
		btnBrandSearch.setFocusable(false);
		btnBrandSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBrandSearch.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(btnBrandSearch);

		lblFieldLogo = new JLabel("");
		lblFieldLogo.setBounds(328, 1, 263, 172);
		lblFieldLogo.setIcon(
				new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\lv_azul_desktop.png"));
		lblFieldLogo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFieldLogo.setFocusable(false);
		contentPane.add(lblFieldLogo);

		lblBrandIcon = new JLabel();
		lblBrandIcon.setBounds(222, 57, 95, 82);
		lblBrandIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBrandIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrandIcon.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblBrandIcon.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\imgMarcas\\chevrolet.png"));
		lblBrandIcon.setFocusable(false);
		contentPane.add(lblBrandIcon);

		ScrollPaneBrand = new JScrollPane();
		ScrollPaneBrand.setBounds(0, 174, 591, 163);
		ScrollPaneBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ScrollPaneBrand.setFocusable(false);
		ScrollPaneBrand.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(ScrollPaneBrand);

		btnReturn = new JButton("");
		btnReturn.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReturn.setBounds(7, 99, 50, 42);
		btnReturn.setToolTipText("P\u00E1gina principal!\r\n");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openRentalForm();
			}
		});
		btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturn.setFocusable(false);
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturn.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\return.png"));
		contentPane.add(btnReturn);

		lblFormField = new JLabel("");
		lblFormField.setBounds(0, 1, 327, 141);
		lblFormField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFormField.setFocusable(false);
		contentPane.add(lblFormField);

		searchBrands();
	}

	private void openRentalForm() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.formRental.setVisible(true);
		cleanFormBrand();
		dispose();
	}

	private void openFindPath() {

		JFileChooser fileChooser = new JFileChooser();
		setAlwaysOnTop(true);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			lblBrandIcon.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
			txtSearchLogoBrand.setText(String.valueOf(selectedFile.getAbsolutePath()));
		} else {
			setAlwaysOnTop(false);
		}
	}

	public void refreshBrandLogo() {
		lblBrandIcon.setIcon(new ImageIcon(txtSearchLogoBrand.getText()));
	}

	private boolean validation() {
		if (txtName.getText().isEmpty() || txtName.getText().length() > 20) {
			JOptionPane.showMessageDialog(this, "Digite o nome da marca!");
			txtName.requestFocus();
			txtName.setText("");
			return false;
		} else {
			if (txtSite.getText().isEmpty() || txtSite.getText().length() > 50) {
				JOptionPane.showMessageDialog(this, "Digite o site da marca!");
				txtSite.requestFocus();
				txtSite.setText("");
				return false;
			} else {
				if (txtContry.getText().isEmpty() || txtContry.getText().length() > 20) {
					JOptionPane.showMessageDialog(this, "Digite o País da marca!");
					txtContry.requestFocus();
					txtContry.setText("");
					return false;
				}
			}
		}
		return true;
	}

	private void cleanFormBrand() {
		txtId.setText("");
		txtName.setText("");
		txtSite.setText("");
		txtContry.setText("");
		txtSearchLogoBrand.setText("");
		lblBrandIcon.setIcon(null);
		txtName.requestFocus();
	}

	private void refreshCbBrandsInFormRental() {
		this.formRental.fieldCbBrands();
		this.formRental.searchCars();
	}

	private void delete() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Carregue uma marca \n" + "   antes de excluir!");
			return;
		}
		int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a marca?", "Confirme essa ação!",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			if (!brandRepository.delete(Integer.parseInt(txtId.getText()))) {
				JOptionPane.showMessageDialog(this,
						"Não foi possível concluir a exclusão!!\n" + "Exclua primeiro os veículos da Marca!");
				return;
			}
			JOptionPane.showMessageDialog(this, "Marca excluída com sucesso!");
			refreshCbBrandsInFormRental();
			cleanFormBrand();
			searchBrands();
		}
	}

	private void toSaveBrands() {
		if (txtId.getText().equals("")) {
			CreateNewBrand();
			searchBrands();
			refreshCbBrandsInFormRental();
		} else {
			editBrand();
		}
	}

	private void CreateNewBrand() {
		if (validation()) {
			Brand newBrand;
			newBrand = new Brand(txtName.getText(), txtSite.getText(), txtContry.getText(),
					txtSearchLogoBrand.getText());
			brandRepository.toSave(newBrand);
			if (newBrand.getId() == -1) {
				JOptionPane.showMessageDialog(this, "Erro interno de sistema\n" + "!!Acionar suporte!");
				searchBrands();
			} else {
				JOptionPane.showMessageDialog(this, "Marca Cadastrada");
				cleanFormBrand();
				searchBrands();
				refreshCbBrandsInFormRental();
			}
		}
	}

	private void editBrand() {

		brandsInEditing = new Brand(Integer.parseInt(txtId.getText()), txtName.getText(), txtSite.getText(),
				txtContry.getText(), txtSearchLogoBrand.getText());

		if (!brandRepository.refreshBrands(brandsInEditing))
			JOptionPane.showMessageDialog(this, "Erro interno de sistema\n" + "!!Acionar suporte!!");
		else {
			JOptionPane.showMessageDialog(this, "Marca Atualizada!");
			editedBrand();
			refreshCbBrandsInFormRental();
			searchBrands();
			cleanFormBrand();
			txtName.requestFocus();
		}
	}

	private void editedBrand() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nenhum cadastro editado!\n");
			searchBrands();
			txtBrandSearch.requestFocus();
			txtBrandSearch.setText("");
			return;
		}
		ArrayList<Brand> brandsList = brandRepository.getEditedBrandId(Integer.parseInt(txtId.getText()));
		loadJTable(brandsList);
		txtBrandSearch.requestFocus();
		txtBrandSearch.setText("");
	}

	private void searchBrands() {
		ArrayList<Brand> brandsList = brandRepository.allBrands();
		loadJTable(brandsList);
	}

	private void searchBrandsByName() {
		if (txtBrandSearch.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nenhuma marca corresponde \n" + "  à sua pesquisa!\n");
			searchBrands();
			txtBrandSearch.requestFocus();
			txtBrandSearch.setText("");
			return;
		}
		ArrayList<Brand> brandsList = brandRepository.allBrandsByName(txtBrandSearch.getText());
		loadJTable(brandsList);
		txtBrandSearch.requestFocus();
		txtBrandSearch.setText("");
	}

	private void loadJTable(ArrayList<Brand> brandsList) {

		brandsToList = brandsListToJTable(brandsList);
		brandsToList.setFocusable(false);

		brandsToList.setDefaultEditor(Object.class, null);

		brandsToList.getColumnModel().getColumn(0).setMinWidth(0);
		brandsToList.getColumnModel().getColumn(0).setMaxWidth(0);
		brandsToList.getColumnModel().getColumn(0).setWidth(0);

		brandsToList.getColumnModel().getColumn(4).setMinWidth(0);
		brandsToList.getColumnModel().getColumn(4).setMaxWidth(0);
		brandsToList.getColumnModel().getColumn(4).setWidth(0);

		ScrollPaneBrand.setViewportView(brandsToList);

		brandsToList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					brandsForEditing((brandsToList).getSelectedRow());
					refreshBrandLogo();
				}
			}
		});
	}

	private void brandsForEditing(int indexJTable) {
		int brandId = (int) this.brandsToList.getModel().getValueAt(indexJTable, 0);
		brandsInEditing = brandRepository.getBrandById(brandId);
		if (brandsInEditing == null) {
			JOptionPane.showMessageDialog(this, "Marca não Encontrada!");
			return;
		}
		fillInFieldsBrands();
	}

	private void fillInFieldsBrands() {
		txtId.setText(String.valueOf(brandsInEditing.getId()));
		txtName.setText(brandsInEditing.getNome());
		txtSite.setText(brandsInEditing.getSite());
		txtContry.setText(brandsInEditing.getPais());
		txtSearchLogoBrand.setText(brandsInEditing.getPath());
	}

	private JTable brandsListToJTable(ArrayList<Brand> brandsList) {
		String[] columns = new String[] { "Id", "Nome", "Site", "Pais", "Path" };
		Object[][] data = new Object[brandsList.size()][columns.length];

		for (int i = 0; i < brandsList.size(); i++) {
			data[i][0] = brandsList.get(i).getId();
			data[i][1] = brandsList.get(i).getNome();
			data[i][2] = brandsList.get(i).getSite();
			data[i][3] = brandsList.get(i).getPais();
			data[i][4] = brandsList.get(i).getPath();
		}
		return new JTable(data, columns);
	}
}
