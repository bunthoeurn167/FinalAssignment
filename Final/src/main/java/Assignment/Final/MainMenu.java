package Assignment.Final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.List;

import javax.sql.PooledConnection;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;



import coding.Airline_Aircraft;

import coding.flightDepArr;

public class MainMenu extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenu airline, flight, flightSchedule, help;
	JMenuItem itemAddAirline, itemAddFlight, itemUpdateFlight, itemShowFlightInfo, itemShowDeparture, itemShowArrival, itemClear, itemAbout, itemWelcome;
	JTabbedPane jTab;
	JTree treeAirline;
	JTextField txtName, txtCode, txtModel, txtFirst, txtBusiness, txtEconomy;
	JButton save, clear;
	JTextField txtFlightNum, txtDepCode, txtDepGate, txtDepDay, txtDepTime, txtArrCode, txtArrGate, txtArrDay, txtArrTime;
	JComboBox cbType, cboCodeAir;
	JButton add, clear1;
	JTextField code, code1, num; //for searching
	JButton search, clear2;
	DefaultTableModel tbModel, tbModelFlight, tbModelDep, tbModelArr;
	JTable tbAirline, tbDep, tbArr, tbFlight;
	JButton search1, clear3;
	JLabel lFlight, lStatus, lType, lModel, lFirst, lBusiness, lEconomy, lDepDay, lDepTime, lDepCode, lDepGate, lArrDay, lArrTime, lArrCode, lArrGate;
	JButton search2, clear5;
	JButton search3, clear6;
	JButton search5, search6;
	JButton clear7, clear8;
	JComboBox cboUpdate;
	JButton update, cancel;
	
	//////////////////////////////////////
	String name;
	String codde;
	String model;
	int first;
	int business;
	int economy;
	
	//////////////////////////////////////
	String airCode, type, status, depCode, depGate, depDay, depTime, arrCode, arrGate, arrDay, arrTime;
	int flightNum, b;
	List<flightDepArr> fli;
	List<Airline_Aircraft> air;
	
	public MainMenu()
	{
		itemAddAirline = new JMenuItem("Add Airline");
		airline = new JMenu("Airline");
		airline.add(itemAddAirline);
		itemAddAirline.addActionListener(this);
		
		itemAddFlight = new JMenuItem("Add Flight");
		itemUpdateFlight = new JMenuItem("Update Flight");
		itemShowFlightInfo = new JMenuItem("Show Flight Info");
		flight = new JMenu("Flight");
		flight.add(itemAddFlight);
		flight.add(itemUpdateFlight);
		flight.add(itemShowFlightInfo);
		itemAddFlight.addActionListener(this);
		itemUpdateFlight.addActionListener(this);
		itemShowFlightInfo.addActionListener(this);
		
		itemShowDeparture = new JMenuItem("Show Departures");
		itemShowArrival = new JMenuItem("Show Arrivals");
		itemClear = new JMenuItem("Clear Schedule");
		flightSchedule = new JMenu("Flight Schedule");
		flightSchedule.add(itemShowDeparture);
		flightSchedule.add(itemShowArrival);
		flightSchedule.add(itemClear);
		itemShowDeparture.addActionListener(this);
		itemShowArrival.addActionListener(this);
		itemClear.addActionListener(this);
		
		itemAbout = new JMenuItem("About");
		itemWelcome = new JMenuItem("Welcome");
		help = new JMenu("Help");
		help.add(itemWelcome);
		help.add(itemAbout);
		itemWelcome.addActionListener(this);
		itemAbout.addActionListener(this);
		
		menuBar = new JMenuBar();
		menuBar.add(airline);
		menuBar.add(flight);
		menuBar.add(flightSchedule);
		menuBar.add(help);
		
		// Add JTree 
		JTree leftjTree = LeftJPane();
		//leftPanel.add(new JLabel(""))
		jTab = new JTabbedPane();
		//rightPanel.add(new JLabel("RIGHT"));
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,leftjTree,jTab);
		jsp.setDividerLocation(200);
		
		jTab.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Component selected = jTab.getSelectedComponent();
				if(e.getClickCount()==2) {
					jTab.remove(selected);
				}
			}
		});
		
		add(menuBar);
		add(jsp);
		setTitle("Airline Program v1.0");
		setJMenuBar(menuBar);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	
	public JTree LeftJPane()
	{
		DefaultMutableTreeNode nodeAirline = new DefaultMutableTreeNode("Airline");
		DefaultMutableTreeNode nodeAddAirline = new DefaultMutableTreeNode("Add Airline");
		nodeAirline.add(nodeAddAirline);
		
		DefaultMutableTreeNode nodeFlight = new DefaultMutableTreeNode("Flight");
		DefaultMutableTreeNode nodeAddFlight = new DefaultMutableTreeNode("Add Flight");
		DefaultMutableTreeNode nodeUpdateFlight = new DefaultMutableTreeNode("Update Flight");
		DefaultMutableTreeNode nodeShowFlightInfo = new DefaultMutableTreeNode("Show Flight Info");
		nodeFlight.add(nodeAddFlight);
		nodeFlight.add(nodeUpdateFlight);
		nodeFlight.add(nodeShowFlightInfo);
		
		DefaultMutableTreeNode nodeFlightSchedule = new DefaultMutableTreeNode("Flight Schedule");
		DefaultMutableTreeNode nodeShowDeparture = new DefaultMutableTreeNode("Show Departures");
		DefaultMutableTreeNode nodeShowArrival = new DefaultMutableTreeNode("Show Arrivals");
		DefaultMutableTreeNode nodeClear = new DefaultMutableTreeNode("Clear Schedule");
		nodeFlightSchedule.add(nodeShowDeparture);
		nodeFlightSchedule.add(nodeShowArrival);
		nodeFlightSchedule.add(nodeClear);
		
		DefaultMutableTreeNode nodeHelp = new DefaultMutableTreeNode("Help");
		DefaultMutableTreeNode nodeWelcome = new DefaultMutableTreeNode("Welcome");
		DefaultMutableTreeNode nodeAbout = new DefaultMutableTreeNode("About");
		nodeHelp.add(nodeWelcome);
		nodeHelp.add(nodeAbout);
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root Node");
		rootNode.add(nodeAirline);
		rootNode.add(nodeFlight);
		rootNode.add(nodeFlightSchedule);
		rootNode.add(nodeHelp);
		
		 treeAirline = new JTree(rootNode);
		 treeAirline.setRowHeight(30);
		 treeAirline.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		 treeAirline.setRootVisible(false);
		    // Add mouse Listener
		 treeAirline.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mousePressed(MouseEvent e) {
		    		 // Find sellected node of tree
					int selectedNode = treeAirline.getRowForLocation(e.getX(), e.getY());
		    		// Condition when mouse pressed on specific node
				if(selectedNode !=-1) {
		    		if(e.getClickCount() == 2) {
		    			// Get for whole tree path
		    			TreePath treePath = treeAirline.getPathForLocation(e.getX(), e.getY());
		    			// Get Last selected tree path
		    			String lastSelectedPath = treePath.getLastPathComponent().toString();
		    			if(lastSelectedPath.equals("Add Airline")) {
		    				 addAirline();
		    				 air = read_Airline(air);
		    				 for(int i=0; i<air.size(); i++)
		    				 {
		    					 String[] output = air.get(i).toStringAirline_Aircraft();
		    					 tbModel.addRow(output);
		    				 }
		    			}
		    			else if(lastSelectedPath.equals("Add Flight")) {
		    				addFlight();
		    				fli = read_Flight(fli);
		    				for(int i=0; i<fli.size(); i++)
		    				{
		    					String[] output = fli.get(i).toStringFlight();
		    					tbModelFlight.addRow(output);
		    				}

		    			}
		    			else if(lastSelectedPath.equals("Update Flight")) {
		    				final_Update();
		    			}
		    			else if(lastSelectedPath.equals("Show Flight Info")) {
		    				final_Search();
		    			}
		    			else if(lastSelectedPath.equals("Show Departures")) {
		    				final_SearchDep();
		    			}
		    			else if(lastSelectedPath.equals("Show Arrivals")) {
		    				final_SearchArr();
		    			}
		    			else if(lastSelectedPath.equals("Clear Schedule")) {
		    				message();
		    			}
		    			else if(lastSelectedPath.equals("Welcome")) {
		    				welcome();
		    			}
		    			else if(lastSelectedPath.equals("About")) {
		    				about();
		    			}
		    		}
		    	}
		    	}
			});   

		for(int i = 0 ; i <=  treeAirline.getRowCount();i++) {
			   treeAirline.expandRow(i);
		}
		return  treeAirline;
	}
	
	private JPanel perform_ListAirline(JPanel airList) {
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("List of Airline");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		airList.setBorder(tBorderNew);
		

		search5 = new JButton("Search");
		search5.setPreferredSize(new Dimension(100,30));
		clear7 = new JButton("Clear");
		clear7.setPreferredSize(new Dimension(100,30));
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel("Airline Code:"));
		panel.add(new JLabel("                    "));
		panel.add(code = new JTextField());
		code.setPreferredSize(new Dimension(300,35));
		panel.add(new JLabel("      "));
		panel.add(search5);
		panel.add(clear7);
		search5.addActionListener(this);
		clear7.addActionListener(this);
		
		
		tbModel = new DefaultTableModel();
		tbModel.addColumn("Airline Name");
		tbModel.addColumn("Airline Code");
		tbModel.addColumn("Airline Model");
		tbModel.addColumn("FirstClassCapacity");
		tbModel.addColumn("BusinessClassCapacity");
		tbModel.addColumn("EconomyClassCapacity");
		
		tbAirline = new JTable(tbModel);
		JPanel searchAndListPanel = new JPanel(new BorderLayout(10,10));
		searchAndListPanel.add(panel,BorderLayout.NORTH);
		searchAndListPanel.add(new JScrollPane(tbAirline),BorderLayout.CENTER);
		
		JLabel header = new JLabel("List of Airline");
		header.setFont(new Font("Georgia", Font.BOLD ,16));
		header.setPreferredSize(new Dimension(200,25));
		JPanel blockList_FINAL = new JPanel(new BorderLayout(10,10));
		blockList_FINAL.add(header,BorderLayout.NORTH);
		blockList_FINAL.add(new JSeparator(),BorderLayout.CENTER);
		blockList_FINAL.add(searchAndListPanel,BorderLayout.SOUTH);
		
		return blockList_FINAL;
	}
	
	
	private JPanel perform_AddInfo()
	{			
		JPanel info = new JPanel(new GridLayout(7,2,10,10));
		info.add(new JLabel("Airline Name: "));
		info.add(txtName = new JTextField());
		txtName.setPreferredSize(new Dimension(200,35));
		info.add(new JLabel("Airline Code: "));
		info.add(txtCode = new JTextField());
		info.add(new JLabel("Airline Model: "));
		info.add(txtModel = new JTextField());
		info.add(new JLabel("First Class Capacity: "));
		info.add(txtFirst = new JTextField());
		info.add(new JLabel("Business Class Capacity: "));
		info.add(txtBusiness = new JTextField());
		info.add(new JLabel("Economy Class Capacity: "));
		info.add(txtEconomy = new JTextField());
		
		JLabel header1 = new JLabel("Airline Information");
		header1.setFont(new Font("Georgia", Font.BOLD ,16));
		header1.setPreferredSize(new Dimension(200,25));
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(header1, BorderLayout.NORTH);
		header.add(new JSeparator(), BorderLayout.CENTER);
		header.add(info, BorderLayout.SOUTH);

		return header;
	}
	
	private JPanel perform_button()
	{
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(save = new JButton("Save"));
		actionBtnPanel.add(clear = new JButton("Clear"));
		save.setPreferredSize(new Dimension(100,30));
		clear.setPreferredSize(new Dimension(100,30));
		save.addActionListener(this);
		clear.addActionListener(this);
		
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(new JSeparator(), BorderLayout.NORTH);
		final_Panel.add(actionBtnPanel, BorderLayout.CENTER);
		
		return final_Panel;
	}
	
	private JPanel rightPanel(JPanel right)
	{
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Create New Airline");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.add(perform_AddInfo(), BorderLayout.NORTH);
		panel.add(perform_button(), BorderLayout.CENTER);
		right.setBorder(tBorderNew);
		return panel;
	}
	
	private void addAirline()
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.add(rightPanel(panel));
		panel1.add(perform_ListAirline(panel1));
		final_panel.add(panel, BorderLayout.EAST);
		final_panel.add(panel1, BorderLayout.CENTER);
		
		jTab.addTab("New Airline",final_panel);
		jTab.setSelectedComponent(final_panel);
	}
	
	////////////////////////////////End Add Ariline/////////////////////////////////////////////
	
	private JPanel perform_AddFlight()
	{
		air = read_Airline(air);
		JPanel info = new JPanel(new GridLayout(3,2,10,10));
		info.add(new JLabel("Airline Code: "));
		cboCodeAir = new JComboBox();
		cboCodeAir.addItem("");
		for(int i=0; i<air.size(); i++)
		{
			cboCodeAir.addItem(air.get(i).getAirlineCode());
		}
		info.add(cboCodeAir);
		info.add(new JLabel("Flgiht Number: "));
		info.add(txtFlightNum = new JTextField());
		txtFlightNum.setPreferredSize(new Dimension(200,35));
		info.add(new JLabel("Type: "));
		cbType = new JComboBox();
		cbType.addItem("");
		cbType.addItem("Domestic");
		cbType.addItem("International");
		info.add(cbType);
		
		JLabel header1 = new JLabel("Flight Information");
		header1.setFont(new Font("Georgia", Font.BOLD ,16));
		header1.setPreferredSize(new Dimension(200,25));
		JPanel flight = new JPanel(new BorderLayout(10,10));
		flight.add(header1, BorderLayout.NORTH);
		flight.add(new JSeparator(), BorderLayout.CENTER);
		flight.add(info, BorderLayout.SOUTH);
		
		JPanel info1 = new JPanel(new GridLayout(5,2,10,10));
		info1.add(new JLabel("Departure Airport Code: "));
		info1.add(txtDepCode = new JTextField());
		txtDepCode.setPreferredSize(new Dimension(200,35));
		info1.add(new JLabel("Departure Gate: "));
		info1.add(txtDepGate = new JTextField());
		info1.add(new JLabel("Departure Day: "));
		info1.add(txtDepDay = new JTextField());
		info1.add(new JLabel("Departure Time: "));
		info1.add(txtDepTime = new JTextField());
		
		
		JLabel header2 = new JLabel("Departure Information");
		header2.setFont(new Font("Georgia", Font.BOLD ,16));
		header2.setPreferredSize(new Dimension(200,25));
		JPanel dep = new JPanel(new BorderLayout(10,10));
		dep.add(header2, BorderLayout.NORTH);
		dep.add(new JSeparator(), BorderLayout.CENTER);
		dep.add(info1, BorderLayout.SOUTH);
		
		JPanel info2 = new JPanel(new GridLayout(5,2,10,10));
		info2.add(new JLabel("Arrival Airport Code: "));
		info2.add(txtArrCode = new JTextField());
		txtArrCode.setPreferredSize(new Dimension(200,35));
		info2.add(new JLabel("Arrival Gate: "));
		info2.add(txtArrGate = new JTextField());
		info2.add(new JLabel("Arrival Day: "));
		info2.add(txtArrDay = new JTextField());
		info2.add(new JLabel("Arrival Time: "));
		info2.add(txtArrTime = new JTextField());
		
		JLabel header3 = new JLabel("Arrival Information");
		header3.setFont(new Font("Georgia", Font.BOLD ,16));
		header3.setPreferredSize(new Dimension(200,25));
		JPanel arr = new JPanel(new BorderLayout(10,10));
		arr.add(header3, BorderLayout.NORTH);
		arr.add(new JSeparator(), BorderLayout.CENTER);
		arr.add(info2, BorderLayout.SOUTH);
		
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(flight, BorderLayout.NORTH);
		header.add(dep, BorderLayout.CENTER);
		header.add(arr, BorderLayout.SOUTH);

		return header;
	}
	
	private JPanel perform_button1()
	{
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(add = new JButton("Add"));
		actionBtnPanel.add(clear1 = new JButton("Clear"));
		add.setPreferredSize(new Dimension(100,30));
		clear1.setPreferredSize(new Dimension(100,30));
		add.addActionListener(this);
		clear1.addActionListener(this);
		
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(new JSeparator(), BorderLayout.NORTH);
		final_Panel.add(actionBtnPanel, BorderLayout.CENTER);
		
		return final_Panel;
	}
	
	private JPanel perform_ListFlight(JPanel fliList) {
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("List of Flight");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		fliList.setBorder(tBorderNew);
		
		
		search6 = new JButton("Search");
		search6.setPreferredSize(new Dimension(100,30));
		clear8 = new JButton("Clear");
		clear8.setPreferredSize(new Dimension(100,30));
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel("Flight Number:"));
		panel.add(new JLabel("                           "));
		panel.add(code1 = new JTextField());
		code1.setPreferredSize(new Dimension(300,35));
		panel.add(new JLabel("      "));
		panel.add(search6);
		panel.add(clear8);
		search6.addActionListener(this);
		clear8.addActionListener(this);
		
		tbModelFlight = new DefaultTableModel();
		tbModelFlight.addColumn("Flight");
		tbModelFlight.addColumn("Flight Number");
		tbModelFlight.addColumn("Type");
		tbModelFlight.addColumn("Departure Airport Code");
		tbModelFlight.addColumn("Departure Gate");
		tbModelFlight.addColumn("Departure Day");
		tbModelFlight.addColumn("Departure Time");
		tbModelFlight.addColumn("Arrival Airport Code");
		tbModelFlight.addColumn("Arrival Gate");
		tbModelFlight.addColumn("Arrvial Day");
		tbModelFlight.addColumn("Arrival Time");
		
		tbFlight = new JTable(tbModelFlight);
		JPanel searchAndListPanel = new JPanel(new BorderLayout(10,10));
		searchAndListPanel.add(panel,BorderLayout.NORTH);
		searchAndListPanel.add(new JScrollPane(tbFlight),BorderLayout.CENTER);
		
		JLabel header = new JLabel("List of Flight");
		header.setFont(new Font("Georgia", Font.BOLD ,16));
		header.setPreferredSize(new Dimension(200,25));
		JPanel blockList_FINAL = new JPanel(new BorderLayout(10,10));
		blockList_FINAL.add(header,BorderLayout.NORTH);
		blockList_FINAL.add(new JSeparator(),BorderLayout.CENTER);
		blockList_FINAL.add(searchAndListPanel,BorderLayout.SOUTH);
		
		return blockList_FINAL;
	}
	
	private JPanel rightPanelFlight(JPanel right)
	{

		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Create New Flight");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.add(perform_AddFlight(), BorderLayout.NORTH);
		panel.add(perform_button1(), BorderLayout.CENTER);
		right.setBorder(tBorderNew);
		return panel;
	}
	
	
	private void addFlight()
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.add(rightPanelFlight(panel));
		panel1.add(perform_ListFlight(panel1));
		final_panel.add(new JScrollPane(panel), BorderLayout.EAST);
		final_panel.add(panel1, BorderLayout.CENTER);
		
		jTab.addTab("New Flight",final_panel);
		jTab.setSelectedComponent(final_panel);
	}
	
	/////////////////////////////////End Add Flight//////////////////////////////////////////////
	
	private JPanel perform_UpdateFlight()
	{			
		JPanel info = new JPanel(new GridLayout(3,2,10,10));
		info.add(new JLabel("Airline Code: "));
		info.add(code = new JTextField());
		code.setPreferredSize(new Dimension(200,35));
		info.add(new JLabel("Flight Number: "));
		info.add(num = new JTextField());
	
		JLabel a = new JLabel("Update Flight Status");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(a, BorderLayout.NORTH);
		header.add(new JSeparator(), BorderLayout.CENTER);
		header.add(info, BorderLayout.SOUTH);

		return header;
	}
	
	private JPanel perform_button2()
	{
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(search = new JButton("Search"));
		actionBtnPanel.add(clear2 = new JButton("Clear"));
		search.setPreferredSize(new Dimension(100,30));
		clear2.setPreferredSize(new Dimension(100,30));
		search.addActionListener(this);
		clear2.addActionListener(this);
		
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(new JSeparator(), BorderLayout.NORTH);
		final_Panel.add(actionBtnPanel, BorderLayout.CENTER);
		
		return final_Panel;
	}
	
	private JPanel perform_newValue()
	{
		String[] status = {"","S", "D", "A", "C"};
		JPanel info = new JPanel(new GridLayout(1,2,10,10));
		info.add(new JLabel("New Status Value: "));
		cboUpdate = new JComboBox(status);
		info.add(cboUpdate);
		cboUpdate.setPreferredSize(new Dimension(200,35));
		
		JLabel a = new JLabel("Update Value");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(a, BorderLayout.NORTH);
		header.add(new JSeparator(), BorderLayout.CENTER);
		header.add(info, BorderLayout.SOUTH);
		
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(update = new JButton("Update"));
		actionBtnPanel.add(cancel = new JButton("Cancel"));
		update.setPreferredSize(new Dimension(100,30));
		cancel.setPreferredSize(new Dimension(100,30));
		update.addActionListener(this);
		cancel.addActionListener(this);
		update.setEnabled(false);
		
		JLabel b = new JLabel("");
		b.setPreferredSize(new Dimension(100,35));
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(header, BorderLayout.NORTH);
		final_Panel.add(b, BorderLayout.CENTER);
		final_Panel.add(actionBtnPanel, BorderLayout.SOUTH);
		
		return final_Panel;
	}
	
	private JPanel updateFlight(JPanel panel)
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Update Flight Info");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tBorderNew);
		
		final_panel.add(perform_UpdateFlight(), BorderLayout.NORTH);
		final_panel.add(perform_button2(), BorderLayout.CENTER);
		final_panel.add(perform_newValue(), BorderLayout.SOUTH);
		
		return final_panel;
	}
	
	private void final_Update()
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.add(updateFlight(panel));
		panel1.add(perform_SearchResult(panel1));
		final_panel.add(panel, BorderLayout.CENTER);
		final_panel.add(panel1, BorderLayout.EAST);
		
		jTab.addTab("Update Flight Info",final_panel);
		jTab.setSelectedComponent(final_panel);
	}
	
	/////////////////////////////////End Update Flight//////////////////////////////////////////////
	
	private JPanel perform_SearchFlight()
	{			
		JPanel info = new JPanel(new GridLayout(3,2,10,10));
		info.add(new JLabel("Airline Code: "));
		info.add(code = new JTextField());
		code.setPreferredSize(new Dimension(200,35));
		info.add(new JLabel("Flight Number: "));
		info.add(num = new JTextField());
		
		JLabel a = new JLabel("Search Flight Info");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(a, BorderLayout.NORTH);
		header.add(new JSeparator(), BorderLayout.CENTER);
		header.add(info, BorderLayout.SOUTH);

		return header;
	}
	
	private JPanel perform_button3()
	{
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(search1 = new JButton("Search"));
		actionBtnPanel.add(clear3 = new JButton("Clear"));
		search1.setPreferredSize(new Dimension(100,30));
		clear3.setPreferredSize(new Dimension(100,30));
		search1.addActionListener(this);
		clear3.addActionListener(this);
		
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(new JSeparator(), BorderLayout.NORTH);
		final_Panel.add(actionBtnPanel, BorderLayout.CENTER);
		
		return final_Panel;
	}
	
	private JPanel searchFlight(JPanel panel)
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Search Flight Info");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tBorderNew);
		
		final_panel.add(perform_SearchFlight(), BorderLayout.NORTH);
		final_panel.add(perform_button3(), BorderLayout.CENTER);
		
		return final_panel;
	}
	
	private JPanel perform_SearchResult(JPanel panel)
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Search Result");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tBorderNew);
		
		JPanel result = new JPanel(new GridLayout(16,2,10,10));
		result.add(new JLabel("Flight:"));
		result.add(lFlight = new JLabel(""));
		lFlight.setPreferredSize(new Dimension(200,35));
		result.add(new JLabel("Status:"));
		result.add(lStatus = new JLabel(""));
		result.add(new JLabel("Type:"));
		result.add(lType = new JLabel(""));
		result.add(new JLabel("Model:"));
		result.add(lModel = new JLabel(""));
		result.add(new JLabel("First Class Capacity:"));
		result.add(lFirst = new JLabel(""));
		result.add(new JLabel("Business Class Capacity:"));
		result.add(lBusiness = new JLabel(""));
		result.add(new JLabel("Economy Class Capacity:"));
		result.add(lEconomy = new JLabel(""));
		result.add(new JLabel("Departure Day:"));
		result.add(lDepDay = new JLabel(""));
		result.add(new JLabel("Departure Time:"));
		result.add(lDepTime = new JLabel(""));
		result.add(new JLabel("Departure Code:"));
		result.add(lDepCode = new JLabel(""));
		result.add(new JLabel("Departure Gate:"));
		result.add(lDepGate = new JLabel(""));
		result.add(new JLabel("Arrival Day:"));
		result.add(lArrDay = new JLabel(""));
		result.add(new JLabel("Arrival Time:"));
		result.add(lArrTime = new JLabel(""));
		result.add(new JLabel("Arrival Code:"));
		result.add(lArrCode = new JLabel(""));
		result.add(new JLabel("Arrival Gate:"));
		result.add(lArrGate = new JLabel(""));
		
		JLabel header = new JLabel("Flight Information");
		header.setFont(new Font("Georgia", Font.BOLD ,16));
		header.setPreferredSize(new Dimension(200,25));
		JPanel result1 = new JPanel(new  BorderLayout(10,10));
		result1.add(header, BorderLayout.NORTH);
		result1.add(new JSeparator(), BorderLayout.CENTER);
		result1.add(result, BorderLayout.SOUTH);
		
		return result1;
	}
	
	private void final_Search()
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.add(searchFlight(panel));
		panel1.add(perform_SearchResult(panel1));
		final_panel.add(panel, BorderLayout.CENTER);
		final_panel.add(panel1, BorderLayout.EAST);
		
		jTab.addTab("Show Flight Info",final_panel);
		jTab.setSelectedComponent(final_panel);
	}
	
	//////////////////////////////////End Search Flight/////////////////////////////////////////////
	
	private JPanel perform_SearchDep()
	{			
		JPanel info = new JPanel(new GridLayout(3,2,10,10));
		info.add(new JLabel("Airport Code: "));
		info.add(code = new JTextField());
		code.setPreferredSize(new Dimension(200,35));
		info.add(new JLabel("Departure Day: "));
		info.add(num = new JTextField());
		
		JLabel a = new JLabel("Search Departure Info");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(a, BorderLayout.NORTH);
		header.add(new JSeparator(), BorderLayout.CENTER);
		header.add(info, BorderLayout.SOUTH);

		return header;
	}
	
	private JPanel perform_button5()
	{
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(search2 = new JButton("Search"));
		actionBtnPanel.add(clear5 = new JButton("Clear"));
		search2.setPreferredSize(new Dimension(100,30));
		clear5.setPreferredSize(new Dimension(100,30));
		search2.addActionListener(this);
		clear5.addActionListener(this);
		
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(new JSeparator(), BorderLayout.NORTH);
		final_Panel.add(actionBtnPanel, BorderLayout.CENTER);
		
		return final_Panel;
	}
	
	private JPanel searchDep(JPanel panel)
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Search Departure Info");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tBorderNew);
		
		final_panel.add(perform_SearchDep(), BorderLayout.NORTH);
		final_panel.add(perform_button5(), BorderLayout.CENTER);
		
		return final_panel;
	}
	
	private JPanel perform_ListDep(JPanel depList) {
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Departure Search Result");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		depList.setBorder(tBorderNew);

		// table Employee
		tbModelDep = new DefaultTableModel();
		tbModelDep.addColumn("Flight");
		tbModelDep.addColumn("Status");
		tbModelDep.addColumn("Time");
		tbModelDep.addColumn("Destination");
		tbModelDep.addColumn("Gate");
		
		tbDep = new JTable(tbModelDep);

		JPanel blockList_FINAL = new JPanel(new BorderLayout(10,10));
		JLabel a = new JLabel("Departure Information");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		blockList_FINAL.add(a,BorderLayout.NORTH);
		blockList_FINAL.add(new JSeparator(),BorderLayout.CENTER);
		blockList_FINAL.add(new JScrollPane(tbDep),BorderLayout.SOUTH);
		
		return blockList_FINAL;
	}
	
	private void final_SearchDep()
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.add(searchDep(panel));
		panel1.add(perform_ListDep(panel1));
		final_panel.add(panel, BorderLayout.EAST);
		final_panel.add(panel1, BorderLayout.CENTER);
		
		jTab.addTab("Show Departure Info",final_panel);
		jTab.setSelectedComponent(final_panel);
	}
	
	/////////////////////////////////////End Show Departure//////////////////////////////////////////
	
	private JPanel perform_SearchArr()
	{			
		JPanel info = new JPanel(new GridLayout(3,2,10,10));
		info.add(new JLabel("Airport Code: "));
		info.add(code = new JTextField());
		code.setPreferredSize(new Dimension(200,35));
		info.add(new JLabel("Arrival Day: "));
		info.add(num = new JTextField());
		
		JLabel a = new JLabel("Search Arrival Info");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		JPanel header = new JPanel(new BorderLayout(10,10));
		header.add(a, BorderLayout.NORTH);
		header.add(new JSeparator(), BorderLayout.CENTER);
		header.add(info, BorderLayout.SOUTH);

		return header;
	}
	
	private JPanel perform_button6()
	{
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(search3 = new JButton("Search"));
		actionBtnPanel.add(clear6 = new JButton("Clear"));
		search3.setPreferredSize(new Dimension(100,30));
		clear6.setPreferredSize(new Dimension(100,30));
		search3.addActionListener(this);
		clear6.addActionListener(this);
		
		JPanel final_Panel = new JPanel(new BorderLayout());
		final_Panel.add(new JSeparator(), BorderLayout.NORTH);
		final_Panel.add(actionBtnPanel, BorderLayout.CENTER);
		
		return final_Panel;
	}
	
	private JPanel searchArr(JPanel panel)
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Search Arrival Info");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tBorderNew);
		
		final_panel.add(perform_SearchArr(), BorderLayout.NORTH);
		final_panel.add(perform_button6(), BorderLayout.CENTER);
		
		return final_panel;
	}
	
	private JPanel perform_ListArr(JPanel arrList) {
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Arrival Search Result");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		arrList.setBorder(tBorderNew);

		// table Employee
		tbModelArr = new DefaultTableModel();
		tbModelArr.addColumn("Flight");
		tbModelArr.addColumn("Status");
		tbModelArr.addColumn("Time");
		tbModelArr.addColumn("Destination");
		tbModelArr.addColumn("Gate");
		
		tbArr = new JTable(tbModelArr);

		JPanel blockList_FINAL = new JPanel(new BorderLayout(10,10));
		JLabel a = new JLabel("Arrival Information");
		a.setFont(new Font("Georgia", Font.BOLD ,16));
		a.setPreferredSize(new Dimension(200,35));
		blockList_FINAL.add(a,BorderLayout.NORTH);
		blockList_FINAL.add(new JSeparator(),BorderLayout.CENTER);
		blockList_FINAL.add(new JScrollPane(tbArr),BorderLayout.SOUTH);
		
		return blockList_FINAL;
	}
	
	private void final_SearchArr()
	{
		JPanel final_panel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.add(searchArr(panel));
		panel1.add(perform_ListArr(panel1));
		final_panel.add(panel, BorderLayout.EAST);
		final_panel.add(panel1, BorderLayout.CENTER);
		
		jTab.addTab("Show Arrival Info",final_panel);
		jTab.setSelectedComponent(final_panel);
	}
	
	/////////////////////////////////////End Show Arrival//////////////////////////////////////////
	
	private void message()
	{
		int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the schedule?");
		if(a == JOptionPane.YES_OPTION)
		{
			int t = tbModelFlight.getRowCount();
			int t1 = tbModel.getRowCount();
				SessionFactory factory = new Configuration()
						.configure("mysql.cfg.xml")
						.addAnnotatedClass(Airline_Aircraft.class).buildSessionFactory();
				Session session = factory.getCurrentSession();
				
				try {
					session.beginTransaction();
					session.createQuery("delete from Airline_Aircraft").executeUpdate();
					session.getTransaction().commit();
				}finally {
					session.close();
					factory.close();
					for(int i=t1-1; i>=0; i--)
					{
						tbModel.removeRow(i);
					}
				}
			
				SessionFactory factory1 = new Configuration()
						.configure("mysql.cfg.xml")
						.addAnnotatedClass(flightDepArr.class).buildSessionFactory();
				Session session1 = factory1.getCurrentSession();
				try {
					session1.beginTransaction();
					session1.createQuery("delete from flightDepArr").executeUpdate();
					session1.getTransaction().commit();
				}finally {
					session1.close();
					factory1.close();
					for(int i=t-1; i>=0; i--)
					{
						tbModelFlight.removeRow(i);
					}
				}
				
				 int count = tbModelDep.getRowCount();
				 for(int j=count-1; j>=0; j--)
				 {
					 tbModelDep.removeRow(j);
				 }
				 int count1 = tbModelArr.getRowCount();
				 for(int j=count1-1; j>=0; j--)
				 {
					 tbModelArr.removeRow(j);
				 }
		}
	}
	
	/////////////////////////////////////End Clear Schedule//////////////////////////////////////////
	
	//////////////////////////////////Start coding///////////////////////////////////////
	private void perform_ButtonSaveAirline()
	{
		if(txtName.getText().equals("") || txtCode.getText().equals("") || txtModel.getText().equals("") || txtFirst.getText().equals("") || txtBusiness.getText().equals("") || txtEconomy.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "It require to put all of the information above");
		}
		else
		{
			name = txtName.getText();
			codde = txtCode.getText();
			model = txtModel.getText();
			first = Integer.parseInt(txtFirst.getText());
			business = Integer.parseInt(txtBusiness.getText());
			economy = Integer.parseInt(txtEconomy.getText());
			Airline_Aircraft air = new Airline_Aircraft(name,codde,model,first,business,economy);
			String[] input = { name, codde, model, first+"", business+"", economy+""};
			tbModel.addRow(input);
			
			SessionFactory factory = new Configuration()
					.configure("mysql.cfg.xml")
					.addAnnotatedClass(Airline_Aircraft.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			
			try {
				session.beginTransaction();
				session.save(air);
				session.getTransaction().commit();
			}finally {
				session.close();
				factory.close();
			}
		}
		
	}
	
	private List<Airline_Aircraft> read_Airline(List<Airline_Aircraft> air)
	{
		SessionFactory factory = new Configuration()
				.configure("mysql.cfg.xml")
				.addAnnotatedClass(Airline_Aircraft.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			air = session.createQuery("from Airline_Aircraft").getResultList();
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
		
		return air;
	}
	
	
	private void perfrom_buttonClearAirline()
	{
		txtName.setText("");
		txtCode.setText("");
		txtModel.setText("");
		txtFirst.setText("");
		txtBusiness.setText("");
		txtEconomy.setText("");
	}
	
	private void perfrom_SearchAirline()
	{
		
		int count =tbModel.getRowCount(),i;
		air = read_Airline(air);
		String c = code.getText();
		for(i=0; i<air.size(); i++)
		{
			if(air.get(i).getAirlineCode().equals(c))
			{
				for(int j=count-1; j>=0; j--)
				{
					tbModel.removeRow(j);
				}
				tbModel.addRow(air.get(i).toStringAirline_Aircraft());
			}
		}
	
	}
	
	private void clear_SearchAirline()
	{
		int count = tbModel.getRowCount();
		code.setText("");
		for(int j=count-1; j>=0; j--)
		{
			tbModel.removeRow(j);
		}
		air = read_Airline(air);
		for(int i=0; i<air.size(); i++)
		 {
			 String[] output = air.get(i).toStringAirline_Aircraft();
			 tbModel.addRow(output);
		 }
		
	}
	////////////////////////////////////////////////////////////////////////////////
	
	
	private void perform_ButtonSaveFlight()
	{

		if(cboCodeAir.getSelectedItem() == "" || txtFlightNum.getText().equals("") || cbType.getSelectedItem() == "" || txtDepCode.getText().equals("") || txtDepGate.getText().equals("") || txtDepDay.getText().equals("") || txtDepTime.getText().equals("") || txtArrCode.getText().equals("") || txtArrGate.getText().equals("") || txtArrDay.getText().equals("") || txtArrTime.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "It require to put all of the information above");
		}
		else
		{
			airCode = cboCodeAir.getSelectedItem().toString();
			flightNum = Integer.parseInt(txtFlightNum.getText());
			type = cbType.getSelectedItem().toString();
			status = "s";
			depCode = txtDepCode.getText();
			depGate = txtDepGate.getText();
			depDay = txtDepDay.getText();
			depTime = txtDepTime.getText();
			arrCode = txtArrCode.getText();
			arrGate = txtArrGate.getText();
			arrDay = txtArrDay.getText();
			arrTime = txtArrTime.getText();
			
			flightDepArr fli = new flightDepArr(airCode, flightNum,type,status,depCode,depGate,depDay,depTime,arrCode,arrGate,arrDay,arrTime);
			String[] input = {airCode+flightNum, flightNum+"", type, status, depCode, depGate, depDay, depTime, arrCode, arrGate, arrDay, arrTime };
			tbModelFlight.addRow(input);
			
			SessionFactory factory = new Configuration()
					.configure("mysql.cfg.xml")
					.addAnnotatedClass(flightDepArr.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			
			try {
				session.beginTransaction();
				session.save(fli);
				session.getTransaction().commit();
			}finally {
				session.close();
				factory.close();
			}
		}
		
	}
	
	private void perform_ButtonClearFlight()
	{
		cboCodeAir.setSelectedItem("");
		txtFlightNum.setText("");
		cbType.setSelectedItem("");
		txtDepCode.setText("");
		txtDepGate.setText("");
		txtDepDay.setText("");
		txtDepTime.setText("");
		txtArrCode.setText("");
		txtArrGate.setText("");
		txtArrDay.setText("");
		txtArrTime.setText("");
	}
	
	private List<flightDepArr> read_Flight(List<flightDepArr> fli)
	{
		SessionFactory factory = new Configuration()
				.configure("mysql.cfg.xml")
				.addAnnotatedClass(flightDepArr.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			fli = session.createQuery("from flightDepArr").getResultList();
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
		
		return fli;
	}
	
	private void perfrom_Search()
	{
		
		int count = tbModelFlight.getRowCount(),i;
		fli = read_Flight(fli);
		String c = code1.getText();
		for(i=0; i<fli.size(); i++)
		{
			String search = fli.get(i).getFlightNumber()+"";
			if(search.equals(c))
			{
				for(int j=count-1; j>=0; j--)
				{
					tbModelFlight.removeRow(j);
				}
				tbModelFlight.addRow(fli.get(i).toStringFlight());
			}
		}
	
	}
	
	private void clear_SearchFlight()
	{
		int count = tbModelFlight.getRowCount();
		code1.setText("");
		for(int j=count-1; j>=0; j--)
		{
			tbModelFlight.removeRow(j);
		}
		fli = read_Flight(fli);
		for(int i=0; i<fli.size(); i++)
		 {
			 String[] output = fli.get(i).toStringFlight();
			 tbModelFlight.addRow(output);
		 }
		
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	private void update_flightStatus(int count)
	{
		String airCode;
		int fliNum;
		fli = read_Flight(fli);
		air = read_Airline(air);
		airCode = code.getText();
		fliNum = Integer.parseInt(num.getText());
		int b=0;
		for(int i=0; i<fli.size(); i++)
		{
			if(fli.get(i).getCode().equals(airCode) && fli.get(i).getFlightNumber() == fliNum)
			{
				b=1;
				lFlight.setText(fli.get(i).getCode() + fli.get(i).getFlightNumber());
				lStatus.setText(fli.get(i).getStatus());
				lType.setText(fli.get(i).getType());
				lDepDay.setText(fli.get(i).getDepDay());
				lDepTime.setText(fli.get(i).getDepTime());
				lDepCode.setText(fli.get(i).getDepCode());
				lDepGate.setText(fli.get(i).getDepGate());
				lArrDay.setText(fli.get(i).getArrDay());
				lArrTime.setText(fli.get(i).getArrTime());
				lArrCode.setText(fli.get(i).getArrCode());
				lArrGate.setText(fli.get(i).getArrGate());
				if(count == 1)
					update.setEnabled(true);
			}
		}
		
		if(b == 1)
		{
			for(int i=0; i<air.size(); i++)
			{
				if(air.get(i).getAirlineCode().equals(airCode))
				{
					b=1;
					lModel.setText(air.get(i).getModel());
					lFirst.setText(air.get(i).getFirstClassCapacity() + "");
					lBusiness.setText(air.get(i).getBusinessClassCapacity()+"");
					lEconomy.setText(air.get(i).getEconomyClassCapacity() + "");
				}
			}
		}
		
		if(b==0)
		{
			clearUpdate(2);
			JOptionPane.showMessageDialog(this, "Flight not Found");
			if(count == 1)
			update.setEnabled(false);
		}
	}
	
	private void perform_btnUpdate()
	{
		if(cboUpdate.getSelectedItem() != "")
		{
		SessionFactory factory = new Configuration()
				.configure("mysql.cfg.xml")
				.addAnnotatedClass(flightDepArr.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
				session.beginTransaction();
				session.createQuery("update flightDepArr set type = '" + cboUpdate.getSelectedItem() + "' where code = '"+ code.getText() + "' ").executeUpdate();
				session.getTransaction().commit();
				
				lType.setText(cboUpdate.getSelectedItem().toString());
			}	
		finally {
				session.close();
				factory.close();
			}
		}
		else if(cboUpdate.getSelectedItem() == "")
		{
			JOptionPane.showMessageDialog(this, "You have to choose your new value");
		}
	}
	
	private void clearUpdate(int count)
	{
		if(count == 1) 
		{
			code.setText("");
			num.setText("");
		}
		cboUpdate.setSelectedItem("");
		lFlight.setText("");
		lStatus.setText("");
		lType.setText("");
		lDepDay.setText("");
		lDepTime.setText("");
		lDepCode.setText("");
		lDepGate.setText("");
		lArrDay.setText("");
		lArrTime.setText("");
		lArrCode.setText("");
		lArrGate.setText("");
		lModel.setText("");
		lFirst.setText("");
		lBusiness.setText("");
		lEconomy.setText("");
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	private void showDeparture()
	{
		String airCode, depDay;
		airCode = code.getText();
		depDay = num.getText();
		fli = read_Flight(fli);
	//	air = read_Airline(air);
		
		int b=0;
		for(int i=0; i<fli.size(); i++)
		{
			if(fli.get(i).getCode().equals(airCode) && fli.get(i).getDepDay().equals(depDay))
			{
				b=1;
				String[] output = { fli.get(i).getCode() + fli.get(i).getFlightNumber() , fli.get(i).getStatus(), fli.get(i).getDepTime(), fli.get(i).getDepCode(), fli.get(i).getDepGate()};
				tbModelDep.addRow(output);
			}
		}
		
		if(b==0)
		{
			 int count = tbModelDep.getRowCount();
			 for(int j=count-1; j>=0; j--)
			 {
				 tbModelDep.removeRow(j);
			 }
			JOptionPane.showMessageDialog(this, "There is no flight departure from Airport " + airCode);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
		
	private void showArrival()
	{
		String airCode, arrDay;
		airCode = code.getText();
		arrDay = num.getText();
		fli = read_Flight(fli);
	//	air = read_Airline(air);
		
		int b=0;
		for(int i=0; i<fli.size(); i++)
		{
			if(fli.get(i).getCode().equals(airCode) && fli.get(i).getArrDay().equals(arrDay))
			{
				b=1;
				String[] output = { fli.get(i).getCode() + fli.get(i).getFlightNumber() , fli.get(i).getStatus(), fli.get(i).getArrTime(), fli.get(i).getArrCode(), fli.get(i).getArrGate()};
				tbModelArr.addRow(output);
			}
		}
	
		if(b==0)
		{
			 int count = tbModelArr.getRowCount();
			 for(int j=count-1; j>=0; j--)
			 {
				 tbModelArr.removeRow(j);
			 }
			JOptionPane.showMessageDialog(this, "There is no flight arrival from Airport " + airCode);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	private void about()
	{
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("About");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		
		JPanel p = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JLabel header = new JLabel("Cambodia-Korea Cooperation Center");
		header.setFont(new Font("Serif", Font.BOLD, 35));
		header.setPreferredSize(new Dimension(575,50));
		
		JLabel header1 = new JLabel("                         Course : App Development With Java");
		header1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		header1.setPreferredSize(new Dimension(545,35));
		
		p.add(new JLabel("                    "), BorderLayout.NORTH);
		p.add(header, BorderLayout.CENTER);
		p.add(header1, BorderLayout.SOUTH);
		
		JPanel p1 = new JPanel(new BorderLayout(10,10));
		JLabel header2 = new JLabel("                                       Lecturer : Thay Setha");
		header2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		header2.setPreferredSize(new Dimension(545,35));
		
		JLabel header3 = new JLabel("           Created By : ");
		header3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		header3.setPreferredSize(new Dimension(200,50));
		
		JLabel header5 = new JLabel("Taing Kheng Leang");
		header5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel header6 = new JLabel("Prum Bunthoeurn");
		header6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel header7 = new JLabel("Lengry Sophakneath");
		header7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel header10 = new JLabel("                              ");
		header10.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		header10.setPreferredSize(new Dimension(200,50));
		
		JLabel header8 = new JLabel("                                         Airline Management System V1.0");
		header8.setFont(new Font("Symbol", Font.PLAIN, 16));
		header8.setPreferredSize(new Dimension(200,30));
		
		JLabel header9 = new JLabel("                                       2018-2019");
		header9.setFont(new Font("Sarif", Font.PLAIN, 20));
		header9.setPreferredSize(new Dimension(200,30));
		
		JPanel p2 = new JPanel(new GridLayout(3,2,10,10));
		p2.add(header3);
		p2.add(header5);
		p2.add(new JLabel(""));
		p2.add(header6);
		p2.add(new JLabel(""));
		p2.add(header7);
		
		p1.add(p, BorderLayout.NORTH);
		p1.add(header2, BorderLayout.CENTER);
		p1.add(new JSeparator(), BorderLayout.SOUTH);
		
		JPanel p3 = new JPanel(new BorderLayout(10,10));
		p3.add(p1, BorderLayout.NORTH);
		p3.add(p2, BorderLayout.CENTER);
		p3.add(header10, BorderLayout.SOUTH);
		
		JPanel p5 = new JPanel(new BorderLayout(10,10));
		p5.add(p3, BorderLayout.NORTH);
		p5.add(header8, BorderLayout.CENTER);
		p5.add(new JSeparator(), BorderLayout.SOUTH);
		
		JPanel p6 = new JPanel(new BorderLayout(10,10));
		p6.add(p5, BorderLayout.NORTH);
		p6.add(header9, BorderLayout.CENTER);
		
		panel.add(p6);
		panel.setBorder(tBorderNew);
		JScrollPane s = new JScrollPane(panel);
		
		jTab.addTab("About", s);
		jTab.setSelectedComponent(s);
	}
	
	private void welcome() {
		TitledBorder tBorderNew = BorderFactory.createTitledBorder("Welcome");
		tBorderNew.setTitleJustification(TitledBorder.CENTER);
		
		 JLabel lbl1 = new JLabel("");
		 ImageIcon icon1 = new ImageIcon("image/a.jpg");
		 lbl1.setIcon(icon1);
		 
		 JLabel lbl2 = new JLabel("");
		 ImageIcon icon2 = new ImageIcon("image/b.jpg");
		 lbl2.setIcon(icon2);
		
		 JLabel lbl3 = new JLabel("");
		 ImageIcon icon3 = new ImageIcon("image/c.jpg");
		 lbl3.setIcon(icon3);
		 
		 JPanel jPanelIcon = new JPanel(new BorderLayout(10,10));
		 jPanelIcon.add(lbl1, BorderLayout.NORTH);
		 jPanelIcon.add(lbl2, BorderLayout.CENTER);
		 jPanelIcon.add(lbl3, BorderLayout.SOUTH);
		 
		 JPanel panel = new JPanel();
		 panel.add(jPanelIcon);
		 panel.setBorder(tBorderNew);
		 
		 JScrollPane jspPictures = new JScrollPane(panel);
		 
		 
		 jTab.addTab("Welcome", jspPictures);
		 jTab.setSelectedComponent(jspPictures);
		
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		new MainMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		 if(e.getSource() == itemAddAirline) {
			 addAirline();
			 air = read_Airline(air);
			 for(int i=0; i<air.size(); i++)
			 {
				 String[] output = air.get(i).toStringAirline_Aircraft();
				 tbModel.addRow(output);
			 }
		 }
		 else if(e.getSource() == itemAddFlight) {
			  addFlight();
			  fli = read_Flight(fli);
			  for(int i=0; i<fli.size(); i++)
			  {
				  String[] output = fli.get(i).toStringFlight();
				  tbModelFlight.addRow(output);
			  }

		 }
		 else if(e.getSource() == itemUpdateFlight) {
			 final_Update();
		 } 
		 else if(e.getSource() == itemShowFlightInfo) {
			final_Search();
		 }	
		 else if(e.getSource() == itemShowDeparture) {
			final_SearchDep();
		 }
		 else if(e.getSource() == itemShowArrival) {
			final_SearchArr();
		 }
		 else if(e.getSource() == itemClear) {
			message();
		 }
		 else if(e.getSource() == itemWelcome) {
			welcome();
		 }	
		 else if(e.getSource() == itemAbout) {
			about();
		 }	
		 else if(e.getSource() == save) //save airline
		 {
			 perform_ButtonSaveAirline();
		 
		 }
		 else if(e.getSource() == clear)
		 {
			 perfrom_buttonClearAirline();
		 }
		 else if(e.getSource() == add)
		 {
			 perform_ButtonSaveFlight();
		 }
		 else if(e.getSource() == clear1)
		 {
			 perform_ButtonClearFlight();
		 }
		 else if(e.getSource() == search)
		 {
			 update_flightStatus(1);
		 }
		 else if(e.getSource() == clear2)
		 {
			 clearUpdate(1);
			 update.setEnabled(false);
		 }
		 else if(e.getSource() == update)
		 {
			 perform_btnUpdate();
		 }
		 else if(e.getSource() == cancel)
		 {
			 cboUpdate.setSelectedItem("");
		 }
		 else if(e.getSource() == search1)
		 {
			 update_flightStatus(2);
		 }
		 else if(e.getSource() == clear3)
		 {
			 clearUpdate(1);
		 }
		 else if(e.getSource() == search2)
		 {
			 showDeparture();
		 }
		 else if(e.getSource() == clear5)
		 {
			 int count = tbModelDep.getRowCount();
			 code.setText("");
			 num.setText("");
			 for(int j=count-1; j>=0; j--)
			 {
				 tbModelDep.removeRow(j);
			 }
			 
		 }
		 else if(e.getSource() == search3)
		 {
			 showArrival();
		 }
		 else if(e.getSource() == clear6)
		 {
			 int count = tbModelArr.getRowCount();
			 code.setText("");
			 num.setText("");
			 for(int j=count-1; j>=0; j--)
			 {
				 tbModelArr.removeRow(j);
			 }
		 }
		 else if(e.getSource() == search5)
		 {
			 perfrom_SearchAirline();
		 }
		 else if(e.getSource() == clear7)
		 {
			 clear_SearchAirline();
		 }
		 else if(e.getSource() == search6)
		 {
			 perfrom_Search();
		 }
		 else if(e.getSource() == clear8)
		 {
			 clear_SearchFlight();
		 }
 }
}
