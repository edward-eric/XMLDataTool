package org.data.xml.support.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;

import org.data.xml.support.db.Catalog;
import org.data.xml.support.db.DBType;

public class DBConnectionDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1039832874930083260L;
	
	private boolean _closed = false;
	
	private DBType _dbtype = null;
	private JComboBox _dbtype_combo = null;
	private JComboBox _dbcatalog_combo = null;
	private JComboBox _credentialtypelist_combo = null;
	private JTextField _host_filed = null;
	private JTextField _port_field = null;
	private JTextField _database_field = null;
	private JTextField _username_field = null;
	private JPasswordField _password_field = null;
	private JButton _ok_btn = null;
	private JButton _cancel_btn = null;
	private boolean _connect = false;
	private JPanel _selectionPanel = null;
	private JComboBox _tunelType = null;

	
	public DBConnectionDialog(JFrame owner, String title, boolean modal){
		this(owner, title, modal, DBType.DB2.toString());
	}
	
	public DBConnectionDialog(JFrame owner, String title, boolean modal, String dbtype)
	{
		super(owner, title, modal);
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		WindowListener windowListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				close();
				
				if(!_closed){
					dispose();
				}
			}
		};
		
		this.addWindowListener(windowListener);
		
		KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			
			private static final long serialVersionUID = -4438923834512901830L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				close();
				
				if(!_closed){
					dispose();
				}
			}
		};
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);
		
		JPanel inputPanel = new JPanel(new GridBagLayout());
		mainPanel.add(BorderLayout.CENTER, inputPanel);
		
		FocusAdapter focusAdapter = new FocusAdapter() {
			public void focusGained(FocusEvent e){
				Component component = e.getComponent();
				if(component instanceof JTextComponent){
					((JTextComponent)component).selectAll();
				}
			}
			
			public void focusLost(FocusEvent e){}
		};
		
		int rownumber = 0;
		int linenumber = 0;
		GridBagConstraints c = new GridBagConstraints();
		
		//populate db catalog
		setGrid(linenumber, rownumber, 1, 1, c);
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 5, 0, 0);
		inputPanel.add(new JLabel("Catalog"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_dbcatalog_combo = new JComboBox();
		_dbcatalog_combo.setSelectedIndex(-1);
		inputPanel.add(_dbcatalog_combo, c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		inputPanel.add(new JLabel("Tunnel"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_tunelType = new JComboBox();
		_tunelType.addItem("QA");
		_tunelType.setSelectedIndex(-1);
		inputPanel.add(_tunelType, c);
		
		++rownumber;
		linenumber = 0;
		
		//host port
		setGrid(linenumber, rownumber, 1, 1, c);
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 5, 0, 0);
		inputPanel.add(new JLabel("Host"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_host_filed = new JTextField(25);
		_host_filed.addFocusListener(focusAdapter);
		inputPanel.add(_host_filed, c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		inputPanel.add(new JLabel("Port"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_port_field = new JTextField(25);
		_port_field.addFocusListener(focusAdapter);
		inputPanel.add(_port_field, c);
		
		++rownumber;
		linenumber = 0;
		
		//database
		setGrid(linenumber, rownumber, 1, 1, c);
		c.insets = new Insets(5, 5, 0, 0);
		inputPanel.add(new JLabel("Database"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_host_filed = new JTextField(25);
		_host_filed.addFocusListener(focusAdapter);
		inputPanel.add(_host_filed, c);
		
		
		++rownumber;
		linenumber = 0;
		
		//Credential type
		setGrid(linenumber, rownumber, 1, 1, c);
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 5, 0, 0);
		inputPanel.add(new JLabel("Credential Type"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_credentialtypelist_combo = new JComboBox();
		_credentialtypelist_combo.setSelectedIndex(-1);
		inputPanel.add(_credentialtypelist_combo, c);
		
		++rownumber;
		linenumber = 0;
		
		//database
		setGrid(linenumber, rownumber, 1, 1, c);
		c.insets = new Insets(5, 5, 0, 0);
		inputPanel.add(new JLabel("User Name"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_username_field = new JTextField(25);
		_username_field.addFocusListener(focusAdapter);
		inputPanel.add(_username_field, c);
		
		++rownumber;
		linenumber = 0;
		
		//database
		setGrid(linenumber, rownumber, 1, 1, c);
		c.insets = new Insets(5, 5, 0, 0);
		inputPanel.add(new JLabel("Password"), c);
		
		++linenumber;
		setGrid(linenumber, rownumber, 1, 1, c);
		_password_field = new JPasswordField(25);
		_password_field.addFocusListener(focusAdapter);
		inputPanel.add(_password_field, c);
		
		_dbtype = DBType.getDBType(dbtype);
		
		if(_dbtype != null)
		{
			++rownumber;
			linenumber = 0;
			
			//database
			setGrid(linenumber, rownumber, 1, 1, c);
			c.insets = new Insets(5, 5, 10, 0);
			inputPanel.add(new JLabel("Database Type"), c);
			
			++linenumber;
			setGrid(linenumber, rownumber, 1, 1, c);
			c.insets = new Insets(5, 5, 10, 5);
			_dbtype_combo = new JComboBox(DBType.values());
			_dbtype_combo.addFocusListener(focusAdapter);
			inputPanel.add(_dbtype_combo, c);
			
			_dbcatalog_combo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					loadDatabaseCatalog(_dbtype);					
				}
			});
		}
		
		
		_selectionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainPanel.add(BorderLayout.SOUTH, _selectionPanel);
		
		_ok_btn = new JButton("OK");
		_ok_btn.addActionListener(this);
		_selectionPanel.add(_ok_btn);
		
		_cancel_btn = new JButton("Cancel");
		_cancel_btn.addActionListener(this);
		_selectionPanel.add(_cancel_btn);
		
		getRootPane().setDefaultButton(_ok_btn);
		
		pack();
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ("OK".equals(e.getActionCommand())){
			_ok_btn.setEnabled(false);
			_connect = true;
		}else if( "Cancel".equals(e.getActionCommand())){
			_connect = false;
		}
		setVisible(false);
	}
	
	private void close() {
		setVisible(false);
		dispose();
		_closed = true;
	}
	
	private void setGrid(int x, int y, int width, int height, GridBagConstraints c){
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
	}
	
	private void loadDatabaseCatalog(DBType type){
		List<Catalog> dblist = new ArrayList<Catalog>(type.getCatalogs().values());
		Collections.sort(dblist);
		_dbcatalog_combo.setModel(new DefaultComboBoxModel(dblist.toArray()));
		_dbcatalog_combo.setSelectedIndex(-1);
	}
	
	

}
