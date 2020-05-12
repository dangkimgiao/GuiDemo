package GUI;

import java.awt.*;
import javax.swing.*;

import GUI.MenuDemo.PopupListener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu1  extends JFrame implements ActionListener{

	JTextArea mainContent;
	JScrollPane  scrollPane;
	String newLine = "\n";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu1  menu = new Menu1("MENU DEMO");

	}
	
	public Menu1(String title) {
		//set các thông số cơ bản
		this.setTitle(title);
		this.setSize(new Dimension(500,500));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		addControl();
		this.setVisible(true);
		
	}
	private void addControl() {
		JMenuBar menuBar;
		JMenu fileMenu, subMenu;
		JMenuItem menuItem;
		
		//tạo menu bar
		menuBar = new JMenuBar();
		
		//tạo menu File
		fileMenu = new JMenu("File");
		
		//add menu File vào menu bar
		menuBar.add(fileMenu);
		
		//tạo menu item FILE
		menuItem = new JMenuItem("New",createIcon("/resources/new_20.png")); 
		fileMenu.add(menuItem);
		
		//thiết lập tổ hợp phím tắt cho menu item
		//shift/ctrl + phím
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.SHIFT_MASK));
		//atl + phím
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(this);
		
		//tạo menu item EDIT đồng thời thiết lập tổ hợp phím Atl+E
		menuItem = new JMenuItem("Edit", KeyEvent.VK_E);
		menuItem.setIcon(createIcon("/resources/edit_20.png"));
		//ctrl+E
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		//add item Edit vào menu File
		fileMenu.add(menuItem );
		
		fileMenu.addSeparator();
		//thêm submenu
		subMenu = new JMenu("Help");
		//thiết lập tổ hợp phím tắt
		//thiết lập menuitem (Help 1 và Help 2) cho submenu
		menuItem = new JMenuItem("Help 1");
		menuItem.addActionListener(this);
		//add Help 1 vào Help
		subMenu.add(menuItem);
		
		menuItem = new JMenuItem("Help 2");
		menuItem.addActionListener(this);
		//add Help 2 vào Help
		subMenu.add(menuItem);
		
		//add submenu Help vào filemenu
		fileMenu.add(subMenu);
		//menuBar.add(subMenu);
		
		//them panel vào Frame
		this.setContentPane(createPanel());
		 
		//them popup
		this.createPopupMenu();
		
		this.setJMenuBar(menuBar);
	}
	
	private static ImageIcon createIcon(String path) {
		java.net.URL iconURL = Menu1.class.getResource(path);
		if(iconURL!=null) {
			return new ImageIcon(iconURL);
		}
		else {
			System.err.println("Không tìm thấy icon");
			return null;
		}
	}
	private JPanel createPanel() {
		JPanel panel;
		//tạo panel
		panel = new JPanel(new BorderLayout());
		
		//them text-area có scroll bar
		mainContent = new JTextArea(5,30);
		mainContent.setEditable(false);
		scrollPane = new JScrollPane(mainContent);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	private void createPopupMenu() {
		JMenuItem menuItem;
		
		//tạo popup
		JPopupMenu popup = new JPopupMenu();
		//tạo các menuitem
		menuItem = new JMenuItem("Refresh");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		
		menuItem = new JMenuItem("Help");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		
		//bắt sự kiện popup
		MouseListener popupListener = (MouseListener) new PopupListener(popup);
		mainContent.addMouseListener(popupListener);
	}
	
	class PopupListener extends MouseAdapter{
		
		JPopupMenu popup;
		
		PopupListener(JPopupMenu popupMenu){
			popup = popupMenu;
		}
		public void mousePressed(MouseEvent e) {
			showPopup(e);
		}
		public void mouseRelease(MouseEvent e) {
			showPopup(e);
		}
		public void showPopup(MouseEvent e) {
			if(e.isPopupTrigger()) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem item = (JMenuItem) e.getSource();
		String s = item.getText();
		
		mainContent.append(s + newLine);
	}

}
