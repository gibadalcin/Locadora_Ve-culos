package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import domain.Client;
import domain.Location;
import domain.User;
import domain.Vehicle;
import dtos.DTOInfoCars;
import dtos.DTORentedCar;
import persistence.ClientRepository;
import persistence.LocationRepository;
import persistence.VehicleRepository;

import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormControl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpanelLocation;
	private JPanel jpanelDevolution;
	private JTextField txtClientSelected;
	private JTextField txtCarSelected;
	private UtilDateModel model;
	private JDatePickerImpl datePicker;
	private JButton btnNewLocation;
	private JButton btnClean;
	private JButton btnUpdate;
	private User loggedInUser;
	private JLabel lblLoggedInUser;
	private JTextField txtLoggedInUser;
	private JTextField txtCarId;
	private JTextField txtClientId;
	private VehicleRepository carRepository = new VehicleRepository();
	private ClientRepository clientRepository = new ClientRepository();
	private LocationRepository locationRepository = new LocationRepository();
	private JTable carsToList;
	private JTabbedPane tabbedPane;
	private JTable customersToList;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblCarDev;
	private JLabel text;
	private JLabel lblClientDev;
	private JTextField txtCarDev;
	private JTextField txtClientDev;
	private JTextField txtCarDevId;
	private JTextField txtDeliveryStatus;
	private JButton btnSaveDev;
	private JButton btnCleanDev;
	private JButton btnUpdateDev;
	private JLabel lblNewLabel_3;
	private JTable leasedToList;
	private JScrollPane scrollPaneDev;
	private JScrollPane scrollPaneDis;
	private JScrollPane scrollPaneClient;
	private JLabel lblLoggedInUserDev;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormControl frame = new FormControl();
	 * frame.setLoggedInUser(new User(3,"giba","gibadalcin@hotmail.com","111111",
	 * true)); frame.setVisible(true); } catch (Exception e) { e.printStackTrace();
	 * } } }); } /** Create the frame.
	 */

	public FormControl() {
		setRootPaneCheckingEnabled(false);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\menu.png"));
		setTitle("Controle");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 788, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBounds(0, 0, 782, 350);
		contentPane.add(tabbedPane);
		setLocation(582, 355);

		jpanelLocation = new javax.swing.JPanel();
		jpanelLocation.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		jpanelLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelLocation.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.add("Loca\u00E7\u00E3o", jpanelLocation);
		jpanelLocation.setLayout(null);

		scrollPaneDis = new JScrollPane();
		scrollPaneDis.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneDis.setBounds(94, 0, 675, 131);
		jpanelLocation.add(scrollPaneDis);

		scrollPaneClient = new JScrollPane();
		scrollPaneClient.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneClient.setBounds(2, 170, 769, 128);
		jpanelLocation.add(scrollPaneClient);

		JLabel lblCarSelected = new JLabel("Ve\u00EDculo/");
		lblCarSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCarSelected.setBounds(182, 146, 57, 14);
		jpanelLocation.add(lblCarSelected);

		JLabel lblClientSelected = new JLabel("Cliente/");
		lblClientSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientSelected.setBounds(379, 145, 52, 14);
		jpanelLocation.add(lblClientSelected);

		JLabel lblData = new JLabel("Retorno/");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(5, 145, 62, 14);
		jpanelLocation.add(lblData);

		txtCarSelected = new JTextField();
		txtCarSelected.setEditable(false);
		txtCarSelected.setHorizontalAlignment(SwingConstants.CENTER);
		txtCarSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCarSelected.setBounds(231, 142, 108, 20);
		jpanelLocation.add(txtCarSelected);
		txtCarSelected.setColumns(10);

		txtClientSelected = new JTextField();
		txtClientSelected.setEditable(false);
		txtClientSelected.setHorizontalAlignment(SwingConstants.CENTER);
		txtClientSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtClientSelected.setColumns(10);
		txtClientSelected.setBounds(425, 142, 141, 20);
		jpanelLocation.add(txtClientSelected);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		tabbedPane.setIconAt(0,
				new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\right.png"));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jpanelDevolution = new javax.swing.JPanel();
		jpanelDevolution.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		jpanelDevolution.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelDevolution.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.add("Devolu\u00E7\u00E3o", jpanelDevolution);
		tabbedPane.setEnabledAt(1, true);
		jpanelDevolution.setLayout(null);

		scrollPaneDev = new JScrollPane();
		scrollPaneDev.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneDev.setBounds(0, 168, 770, 131);
		jpanelDevolution.add(scrollPaneDev);
		
		lblCarDev = new JLabel("Ve\u00EDculo/");
		lblCarDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCarDev.setBounds(14, 143, 59, 14);
		jpanelDevolution.add(lblCarDev);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_1.setBounds(95, 0, 676, 131);
		jpanelDevolution.add(lblNewLabel_1);
		
		lblClientDev = new JLabel("Cliente/");
		lblClientDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientDev.setBounds(310, 143, 59, 14);
		jpanelDevolution.add(lblClientDev);
		
		txtCarDev = new JTextField();
		txtCarDev.setEditable(false);
		txtCarDev.setHorizontalAlignment(SwingConstants.CENTER);
		txtCarDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCarDev.setBounds(64, 140, 172, 20);
		jpanelDevolution.add(txtCarDev);
		txtCarDev.setColumns(10);
		
		txtClientDev = new JTextField();
		txtClientDev.setEditable(false);
		txtClientDev.setHorizontalAlignment(SwingConstants.CENTER);
		txtClientDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtClientDev.setColumns(10);
		txtClientDev.setBounds(358, 141, 216, 20);
		jpanelDevolution.add(txtClientDev);
		
		txtCarDevId = new JTextField();
		txtCarDevId.setHorizontalAlignment(SwingConstants.CENTER);
		txtCarDevId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCarDevId.setEditable(false);
		txtCarDevId.setBounds(238, 140, 65, 20);
		jpanelDevolution.add(txtCarDevId);
		txtCarDevId.setColumns(10);
		
		txtDeliveryStatus = new JTextField();
		txtDeliveryStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtDeliveryStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDeliveryStatus.setEditable(false);
		txtDeliveryStatus.setColumns(10);
		txtDeliveryStatus.setBounds(647, 141, 114, 20);
		jpanelDevolution.add(txtDeliveryStatus);
		
		btnSaveDev = new JButton("Salvar");
		btnSaveDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnCars();
			}
		});
		btnSaveDev.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\check32.png"));
		btnSaveDev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveDev.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSaveDev.setBounds(2, 3, 89, 40);
		jpanelDevolution.add(btnSaveDev);
		
		btnCleanDev = new JButton("Limpar");
		btnCleanDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanDev();
			}
		});
		btnCleanDev.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\clean32.png"));
		btnCleanDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCleanDev.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCleanDev.setBounds(3, 46, 89, 40);
		jpanelDevolution.add(btnCleanDev);
		
		btnUpdateDev = new JButton("");
		btnUpdateDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchRentedCars();
				searchCustomers();
				searchCars();
			}
		});
		btnUpdateDev.setToolTipText("Atualizar lista!");
		btnUpdateDev.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\update.png"));
		btnUpdateDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateDev.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUpdateDev.setBounds(3, 88, 89, 40);
		jpanelDevolution.add(btnUpdateDev);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_2.setBounds(-1, 2, 94, 125);
		jpanelDevolution.add(lblNewLabel_2);
		
		lblLoggedInUserDev = new JLabel("Operador/");
		lblLoggedInUserDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoggedInUserDev.setBounds(583, 144, 65, 14);
		jpanelDevolution.add(lblLoggedInUserDev);
		
		text = new JLabel("");
		text.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		text.setBounds(3, 133, 766, 34);
		jpanelDevolution.add(text);
		tabbedPane.setIconAt(1,
				new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\left.png"));
		tabbedPane.setForegroundAt(1, Color.BLACK);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));

		BuilderDatePeaker();

		btnNewLocation = new JButton("Salvar");
		btnNewLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leased();
			}
		});
		btnNewLocation
				.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\check32.png"));
		btnNewLocation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewLocation.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewLocation.setBounds(2, 4, 89, 40);
		jpanelLocation.add(btnNewLocation);

		btnClean = new JButton("Limpar");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		btnClean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClean.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClean.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\clean32.png"));
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClean.setBounds(2, 46, 89, 40);
		jpanelLocation.add(btnClean);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCustomers();
				searchCars();
				searchRentedCars();
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("Atualizar Lista");
		btnUpdate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUpdate.setIcon(new ImageIcon("C:\\Dev\\eclipse-workspace\\Project2\\src\\images\\allImages\\update.png"));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(2, 88, 89, 40);
		jpanelLocation.add(btnUpdate);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(1, 2, 91, 131);
		jpanelLocation.add(lblNewLabel);

		lblLoggedInUser = new JLabel("Operador/");
		lblLoggedInUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoggedInUser.setBounds(607, 145, 64, 14);
		jpanelLocation.add(lblLoggedInUser);

		txtLoggedInUser = new JTextField();
		txtLoggedInUser.setEditable(false);
		txtLoggedInUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoggedInUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLoggedInUser.setColumns(10);
		txtLoggedInUser.setBounds(672, 142, 97, 20);
		jpanelLocation.add(txtLoggedInUser);
		
		txtCarId = new JTextField();
		txtCarId.setEditable(false);
		txtCarId.setBounds(340, 142, 37, 20);
		jpanelLocation.add(txtCarId);
		txtCarId.setColumns(10);
		
		txtClientId = new JTextField();
		txtClientId.setEditable(false);
		txtClientId.setColumns(10);
		txtClientId.setBounds(567, 142, 38, 20);
		jpanelLocation.add(txtClientId);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_3.setBounds(3, 133, 765, 35);
		jpanelLocation.add(lblNewLabel_3);
		
		searchCars();
		searchCustomers();
		searchRentedCars();
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
		txtLoggedInUser.setText(this.loggedInUser.getNome());
		txtDeliveryStatus.setText(this.loggedInUser.getNome());
	}
   
	private void BuilderDatePeaker() {
		model = new UtilDateModel();
		// model.setDate(1990, 8, 24);
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 14));
		datePicker.getJFormattedTextField().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		@SuppressWarnings("unused")
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		datePicker.setSize(118, 20);
		datePicker.setLocation(62, 142);
		jpanelLocation.add(datePicker);
	}
	
	public void searchCars() {
		ArrayList<DTOInfoCars> carsList = carRepository.getAllInfoCarsAvailable("");
		loadJTable(carsList);
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

		scrollPaneDis.setViewportView(carsToList);

		carsToList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selectCars((carsToList).getSelectedRow());
				}
			}
		});
		}catch(Exception e) {}
	}
	
	private void selectCars(int indexJTable) {
		txtCarSelected.setText(this.carsToList.getModel().getValueAt(indexJTable, 1).toString());
		txtCarId.setText(this.carsToList.getModel().getValueAt(indexJTable, 0).toString());
	}
	
	public void searchCustomers() {
		ArrayList<Client> customersList = clientRepository.allCustomers();
		loadJTableClient(customersList);
	}
	
	private void loadJTableClient(ArrayList<Client> customersList) {

		customersToList = Util.customersListToJTable(customersList);
		customersToList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		customersToList.setFocusable(false);

		customersToList.setDefaultEditor(Object.class, null);

		customersToList.getColumnModel().getColumn(0).setMinWidth(0);
		customersToList.getColumnModel().getColumn(0).setMaxWidth(0);
		customersToList.getColumnModel().getColumn(0).setWidth(0);

		scrollPaneClient.setViewportView(customersToList);

		customersToList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selectCustomers((customersToList).getSelectedRow());
				}
			}
		});
	}
	
	private void selectCustomers(int indexJTable) {
		txtClientSelected.setText(this.customersToList.getModel().getValueAt(indexJTable, 1).toString());
		txtClientId.setText(this.customersToList.getModel().getValueAt(indexJTable, 0).toString());
	}
	
	private void leased() {
		if(txtCarId.getText().isEmpty() || txtClientId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione um carro e um cliente!");
			return;
		}
				int carId = Integer.parseInt(txtCarId.getText());
				int clientId = Integer.parseInt(txtClientId.getText());
				Date dataDevPrev = (Date) datePicker.getModel().getValue();
				Date dataLoc = new Date();
				Location newLoc = new Location(carId, clientId, dataDevPrev, loggedInUser.getId(),dataLoc);
				
				locationRepository.toSave(newLoc);
				
				if(newLoc.getId() == -1)
					JOptionPane.showMessageDialog(this, "Erro interno do sistema - acionar suporte!");
				else {
					setCarAsLeased(carId);
					JOptionPane.showMessageDialog(this, "Locação confirmada!");
					clean();
					searchCars();
					searchCustomers();
					searchRentedCars();		
				}		
	}
	
	private boolean setCarAsLeased(int carId) {
		Vehicle carSelected = carRepository.getCarById(carId);
		carSelected.leased();
		return carRepository.refreshCars(carSelected);		
	}

	private void clean() {
		txtClientId.setText("");
		txtClientSelected.setText("");
		txtCarId.setText("");
		txtCarSelected.setText("");	
	}
	
	private void searchRentedCars() {
		ArrayList<DTORentedCar> rentCarsList = locationRepository.getRentedCars();
		loadJTableLeased(rentCarsList);
	}

	private void loadJTableLeased(ArrayList<DTORentedCar> rentCarsList) {
		leasedToList = Util.carsLeasedListToJTable(rentCarsList);
		leasedToList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		leasedToList.setFocusable(false);

		leasedToList.setDefaultEditor(Object.class, null);

		leasedToList.getColumnModel().getColumn(0).setMinWidth(0);
		leasedToList.getColumnModel().getColumn(0).setMaxWidth(0);
		leasedToList.getColumnModel().getColumn(0).setWidth(0);

		scrollPaneDev.setViewportView(leasedToList);

		leasedToList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					devSelected((leasedToList).getSelectedRow());
				}
			}
		});
		
	}	
	
	private void devSelected(int selectedRow) {
		txtCarDevId.setText(this.leasedToList.getModel().getValueAt(selectedRow, 0).toString());
		txtClientDev.setText(this.leasedToList.getModel().getValueAt(selectedRow, 3).toString());
		txtCarDev.setText(this.leasedToList.getModel().getValueAt(selectedRow, 1).toString());
	}
	
	private void returnCars() {
		if(txtCarDevId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione uo veículo locado a ser devolvido!");
			return;
		}
				Location locSelected = locationRepository.getRentalById(Integer.parseInt(txtCarDevId.getText()));
				
				locSelected.finish();
				
				if(!locationRepository.update(locSelected)) {
					JOptionPane.showMessageDialog(this, "Erro interno do sistema - acionar suporte!");
				}
				
				Vehicle carSelected = carRepository.getCarById(locSelected.getCarId());
				
				carSelected.returned();
				
				if(!carRepository.refreshCars(carSelected)){
					JOptionPane.showMessageDialog(null, "Erro interno do sistema - acionar suporte!");
				}
					
					JOptionPane.showMessageDialog(this, "O veículo está novamente disponível!");
					cleanDev();
					searchCars();
					searchRentedCars();		
	}
	
	private void cleanDev() {
		txtClientDev.setText("");
		txtCarDevId.setText("");
		txtDeliveryStatus.setText("");
		txtCarDev.setText("");	
	}
}
