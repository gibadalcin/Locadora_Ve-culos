package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.border.SoftBevelBorder;

import domain.Brand;
import domain.Vehicle;
import dtos.DTOComboBox;
import dtos.DTOInfoCars;
import persistence.BrandRepository;
import persistence.VehicleRepository;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFormattedTextField;

public class FormRental extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtModel;
	private JTextField txtOptional;
	private JTextField txtSearchModel;
	private Vehicle carsInEditing;
	private JFormattedTextField txtYear = new JFormattedTextField();
	private JFormattedTextField txtBoard = new JFormattedTextField();
	private VehicleRepository carRepository = new VehicleRepository();
	private BrandRepository brandRepository = new BrandRepository();
	private JTable carsToList;
	private JScrollPane ScrollPane;
	private Vector<DTOComboBox> vectorBrands;
	private JComboBox<DTOComboBox> cbBrands;
	private FormBrand formBrand = new FormBrand(this);
	private JLabel lblDisplayBrand;
	private JLabel lblLogo;
	private JButton btnRefreshBrands;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormRental frame = new FormRental();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 * 
	 */
	/**
	 * Create the frame.
	 */
	public FormRental() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setResizable(false);
		setTitle(" Ve\u00EDculos");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 607, 379);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocation(-10, 355);

		txtOptional = new JTextField();

		txtSearchModel = new JTextField();
		txtSearchModel.setFocusable(true);
		txtSearchModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearchModel.setColumns(10);
		txtSearchModel.setBounds(105, 149, 214, 20);
		contentPane.add(txtSearchModel);

		txtOptional.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtOptional.setColumns(10);
		txtOptional.setBounds(75, 112, 244, 20);
		contentPane.add(txtOptional);

		JLabel lblId = new JLabel("Id:");
		lblId.setFocusable(false);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(7, 9, 46, 14);
		contentPane.add(lblId);

		JLabel lblModel = new JLabel("Modelo:");
		lblModel.setFocusable(false);
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModel.setBounds(113, 8, 54, 14);
		contentPane.add(lblModel);

		JLabel lblBrand = new JLabel("Marca:");
		lblBrand.setFocusable(false);
		lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBrand.setBounds(6, 34, 46, 14);
		contentPane.add(lblBrand);

		JLabel lblYear = new JLabel("Ano:");
		lblYear.setFocusable(false);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYear.setBounds(7, 61, 46, 14);
		contentPane.add(lblYear);

		JLabel lblBoard = new JLabel("Placa:");
		lblBoard.setFocusable(false);
		lblBoard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBoard.setBounds(7, 88, 46, 14);
		contentPane.add(lblBoard);

		JLabel lblOptional = new JLabel("Opcionais:");
		lblOptional.setFocusable(false);
		lblOptional.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOptional.setBounds(7, 113, 68, 14);
		contentPane.add(lblOptional);

		JLabel lblSearchModel = new JLabel("Busca/Modelo:");
		lblSearchModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchModel.setBounds(7, 151, 95, 14);
		contentPane.add(lblSearchModel);

		txtId = new JTextField();
		txtId.setFocusable(false);
		txtId.setEnabled(false);
		txtId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setBounds(53, 7, 43, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtModel = new JTextField();
		txtModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtModel.setColumns(10);
		txtModel.setBounds(165, 6, 154, 20);
		contentPane.add(txtModel);

		JButton btnNew = new JButton("Novo");
		btnNew.setFocusable(false);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCars();
				cleanForm();
			}
		});
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\car2_32.png"));
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNew.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNew.setBounds(332, 4, 89, 40);
		contentPane.add(btnNew);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.setFocusable(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCarsByModel();
			}
		});
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\search.png"));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearch.setBounds(334, 130, 89, 40);
		contentPane.add(btnSearch);

		JLabel lblFieldLabelSearch = new JLabel("");
		lblFieldLabelSearch.setFocusable(false);
		lblFieldLabelSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFieldLabelSearch.setBounds(1, 141, 327, 34);
		contentPane.add(lblFieldLabelSearch);

		ScrollPane = new JScrollPane();
		ScrollPane.setFocusable(false);
		ScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ScrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ScrollPane.setBounds(2, 176, 592, 164);
		contentPane.add(ScrollPane);

		JButton btnSave = new JButton("Salvar");
		btnSave.setSelected(true);
		btnSave.setFocusCycleRoot(true);
		btnSave.setFocusTraversalPolicyProvider(true);
		btnSave.setFocusable(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSave();
			}
		});
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\check32.png"));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSave.setBounds(333, 46, 89, 40);
		contentPane.add(btnSave);

		JButton btnDelete = new JButton("Excluir");
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				searchCars();
				cleanForm();
			}
		});
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\clean32.png"));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDelete.setBounds(333, 88, 89, 40);
		contentPane.add(btnDelete);

		lblLogo = new JLabel("");
		lblLogo.setIcon(
				new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\lv_azul_desktop.png"));
		lblLogo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblLogo.setFocusable(false);
		lblLogo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblLogo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblLogo.setBounds(330, 1, 262, 173);
		contentPane.add(lblLogo);

		cbBrands = new JComboBox<DTOComboBox>();
		cbBrands.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MyItemListener itemListener = new MyItemListener();
		cbBrands.addItemListener(itemListener);
		cbBrands.setToolTipText("");
		cbBrands.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbBrands.setName("");
		cbBrands.setFocusable(false);
		cbBrands.setBounds(52, 32, 98, 22);
		contentPane.add(cbBrands);

		lblDisplayBrand = new JLabel();
		lblDisplayBrand.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblDisplayBrand.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\images\\chevrolet.png"));
		lblDisplayBrand.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayBrand.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDisplayBrand.setFocusable(false);
		lblDisplayBrand.setBounds(226, 30, 93, 77);
		contentPane.add(lblDisplayBrand);

		btnRefreshBrands = new JButton("");
		btnRefreshBrands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCbBrands();
				showBrandsForm();
			}
		});
		btnRefreshBrands.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefreshBrands.setToolTipText("Atualizar marcas!");
		btnRefreshBrands.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRefreshBrands.setFocusable(false);
		btnRefreshBrands
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\REFRESH.png"));
		btnRefreshBrands.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefreshBrands.setBounds(155, 30, 61, 51);
		contentPane.add(btnRefreshBrands);

		txtYear = new JFormattedTextField();
		txtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("####");
			txtYear = new javax.swing.JFormattedTextField(data);
			txtYear.setBackground(new Color(255, 255, 255));
			txtYear.setHorizontalAlignment(SwingConstants.LEFT);
			txtYear.setBounds(53, 59, 97, 22);
		} catch (Exception e) {
		}
		contentPane.add(txtYear);

		txtBoard = new JFormattedTextField();
		txtBoard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("*******");
			txtBoard = new javax.swing.JFormattedTextField(data);
			txtBoard.setBounds(52, 86, 99, 20);
			txtBoard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		} catch (Exception e) {
		}
		contentPane.add(txtBoard);

		JLabel lblFormFields = new JLabel("");
		lblFormFields.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormFields.setFocusable(false);
		lblFormFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFormFields.setBounds(1, 1, 327, 139);
		contentPane.add(lblFormFields);

		searchCars();
		fieldCbBrands();
	}

	private void showBrandsForm() {
		formBrand.setVisible(true);
		cleanForm();
		dispose();
		lblDisplayBrand.setIcon(new ImageIcon(""));
	}

	public void fieldCbBrands() {
		vectorBrands = brandRepository.getMarkForComboBox();
		cbBrands.setModel(new DefaultComboBoxModel<DTOComboBox>(vectorBrands));
	}

	private boolean validation() {
		if (txtModel.getText().isEmpty() || txtModel.getText().length() > 20) {
			JOptionPane.showMessageDialog(this, "Por favor, \n" + " digite o modelo do veículo!\n");
			txtModel.requestFocus();
			txtModel.setText("");
			return false;
		} else {
			cbBrands.requestFocus();
			if (cbBrands.getSelectedIndex() == 0) {
				cbBrands.setBackground(Color.PINK);
				return false;
			} else {
				cbBrands.setBackground(null);
				txtYear.requestFocus();
				if (txtYear.getText().isEmpty() || Integer.parseInt(txtYear.getText()) > 2100) {
					JOptionPane.showMessageDialog(this, "Por favor, \n" + " digite o ano do veículo!\n");
					txtYear.requestFocus();
					txtYear.setText("");
					return false;
				} else {
					if (txtBoard.getText().isEmpty() || txtBoard.getText().length() != 7) {
						JOptionPane.showMessageDialog(this, "Por favor, \n" + " digite a placa do veículo!\n");
						txtBoard.requestFocus();
						txtBoard.setText("");
						return false;
					} else {
						if (txtOptional.getText().isEmpty() || txtOptional.getText().length() > 100) {
							JOptionPane.showMessageDialog(this, "Por favor, \n" + " digite os opcionais do veículo!\n");
							txtOptional.requestFocus();
							txtOptional.setText("");
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void cleanForm() {
		txtId.setText("");
		txtModel.setText("");
		txtYear.setText("");
		txtBoard.setText("");
		txtOptional.setText("");
		fieldCbBrands();
		txtModel.requestFocus();
		lblDisplayBrand.setIcon(null);
	}

	private void delete() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Carregue um veículo antes de excluir!");
			return;
		}
		int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o veículo?", "Confirme essa ação!",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			if (!carRepository.delete(Integer.parseInt(txtId.getText()))) {
				JOptionPane.showMessageDialog(this, "Erro ao excluir");
				return;
			}
			JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso!");
			cleanForm();
			searchCars();
		}
	}

	private void toSave() {
		if (txtId.getText().equals("")) {
			CreateNewCar();
			searchCars();
		} else {
			editCar();
		}
		
	}

	private DTOComboBox getSelectedBrand() {
		DTOComboBox selectedBrand = (DTOComboBox) cbBrands.getSelectedItem();
		return selectedBrand;
	}

	private void CreateNewCar() {
		if (validation()) {
			Vehicle newCar;
			int year;
			try {
				year = Integer.parseInt(txtYear.getText());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			}
			DTOComboBox selectedBrand = getSelectedBrand();
			newCar = new Vehicle(txtModel.getText(), selectedBrand.getId(), year, txtBoard.getText(),
					txtOptional.getText());

			carRepository.toSave(newCar);
			cleanForm();

			if (newCar.getId() == -1) {
				JOptionPane.showMessageDialog(this, "Erro interno de sistema" + "!!Acionar suporte!!");
				searchCars();
				lblDisplayBrand.setIcon(null);

			} else {
				JOptionPane.showMessageDialog(this, "Veículo Cadastrado");
				cleanForm();
				searchCars();
			}
		}
	}

	private void editCar() {
		int year;
		try {
			year = Integer.parseInt(txtYear.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			validation();
			JOptionPane.showMessageDialog(this, "Preencha corretamente os campos");
			return;
		}
		DTOComboBox selectedBrand = getSelectedBrand();
		carsInEditing = new Vehicle(Integer.parseInt(txtId.getText()), txtModel.getText(), selectedBrand.getId(), year,
				txtBoard.getText(), txtOptional.getText());
		if (!carRepository.refreshCars(carsInEditing))
			JOptionPane.showMessageDialog(this, "Erro interno de sistema - acionar suporte!!");
		else {
			JOptionPane.showMessageDialog(this, "Veículo Atualizado!");
			cleanForm();
			searchCars();
			txtModel.requestFocus();
		}
	}

	public void searchCars() {
		ArrayList<DTOInfoCars> carsList = carRepository.getAllInfoCarsByModel("");
		loadJTable(carsList);
	}

	private void searchCarsByModel() {
		if (txtSearchModel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, \n" + " digite o modelo do veículo!\n");
			searchCars();
			txtSearchModel.requestFocus();
			txtSearchModel.setText("");
			return;
		}
		ArrayList<DTOInfoCars> carsList = carRepository.getAllInfoCarsByModel(txtSearchModel.getText());
		if (carsList == null) {
			JOptionPane.showMessageDialog(this, "Nenhum modelo corresponde \n" + "    à sua pesquisa!\n");
			txtSearchModel.requestFocus();
			txtSearchModel.setText("");
		}
		loadJTable(carsList);
		txtSearchModel.requestFocus();
		txtSearchModel.setText("");
	}

	private void loadJTable(ArrayList<DTOInfoCars> carsList) {
		try {
			carsToList = Util.carsListToJTable(carsList);
			carsToList.setFocusable(false);

			carsToList.setDefaultEditor(Object.class, null);

			carsToList.getColumnModel().getColumn(0).setMinWidth(0);
			carsToList.getColumnModel().getColumn(0).setMaxWidth(0);
			carsToList.getColumnModel().getColumn(0).setWidth(0);

			carsToList.getColumnModel().getColumn(2).setMinWidth(0);
			carsToList.getColumnModel().getColumn(2).setMaxWidth(0);
			carsToList.getColumnModel().getColumn(2).setWidth(0);

			carsToList.getColumnModel().getColumn(7).setMinWidth(0);
			carsToList.getColumnModel().getColumn(7).setMaxWidth(0);
			carsToList.getColumnModel().getColumn(7).setWidth(0);

			ScrollPane.setViewportView(carsToList);

			carsToList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						carsForEditing((carsToList).getSelectedRow());
						formBrand.refreshBrandLogo();
					}
				}
			});
		} catch (Exception e) {
		}
	}

	private void carsForEditing(int indexJTable) {

		int CarId = (int) this.carsToList.getModel().getValueAt(indexJTable, 0);
		int marcaId = (int) this.carsToList.getModel().getValueAt(indexJTable, 2);

		SetImageLabel(marcaId);

		carsInEditing = carRepository.getCarById(CarId);
		for (int i = 0; i < vectorBrands.size(); i++) {
			if (marcaId == vectorBrands.get(i).getId()) {
				cbBrands.setSelectedIndex(i);
				break;
			}
		}
		
		if (carsInEditing == null) {
			JOptionPane.showMessageDialog(this, "Veículo não Encontrado!");
			return;
		}
		fillInFields();
	}

	public void SetImageLabel(int marcaId) {
		Brand brandsInEditing = brandRepository.getBrandById(marcaId);
		File selectedFile = new File(brandsInEditing.getPath());
		lblDisplayBrand.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	}

	private void fillInFields() {
		txtId.setText(String.valueOf(carsInEditing.getId()));
		txtModel.setText(carsInEditing.getModelo());
		txtYear.setText(String.valueOf(carsInEditing.getAno()));
		txtBoard.setText(carsInEditing.getPlaca());
		txtOptional.setText(carsInEditing.getOpcionais());
	}

	class MyItemListener implements ItemListener {
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) {

			DTOComboBox item = (DTOComboBox) evt.getItem();

			if (evt.getStateChange() == ItemEvent.SELECTED) {
				SetImageLabel(item.getId());
			} else if (evt.getStateChange() == ItemEvent.ITEM_FIRST) {
				
			}
		}
	}
}
