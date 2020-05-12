package GUI;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class MenuDemo  extends JFrame implements ActionListener{
	JTextArea mainContent;
	JScrollPane scrollPane;
	String newLine = "\n";

	public static void main(String[] args) {
		MenuDemo form1 = new MenuDemo("FORM 1");
		
		
	}
	
	public MenuDemo(String title) {
		//thiết lập thông số cơ bản
		this.setTitle(title);
		this.setSize(new Dimension(500,500));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		addControl();
		this.setVisible(true);
	}
	private void addControl() {
		JMenuBar menuBar;
		JMenu fileMenu, helpMenu,subMenu;
		JMenuItem  menuItem;
		
		//tạo menu bar
		menuBar = new JMenuBar();
		
		//tạo menu
		fileMenu = new JMenu("File");

		//add menu vào menubar
		menuBar.add(fileMenu);
		
		//tạo  menuitem New
		menuItem = new JMenuItem("New",createIcon("/resources/new_20.png"));
		
		fileMenu.add(menuItem);
		//lắng nghe sự kiện click
		menuItem.addActionListener(this);
		
		//thiết lập tổ hợp phím tắt (shift hoặc ctrl +N)
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.SHIFT_MASK));
		//thiết lập tổ hợp phím tắt Alt + N
		menuItem.setMnemonic(KeyEvent.VK_N);
		
		//tạo menuitem Edit
		menuItem = new JMenuItem("Edit", KeyEvent.VK_E);
		//thiết lập tổ hợp phím tắt
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.SHIFT_MASK));
		menuItem.setIcon(createIcon("/resources/edit_20.png"));
		//lắng nghe sự kiện click
		menuItem.addActionListener(this);
		
		fileMenu.add(menuItem);
		
		//vẽ đường phân cách
		fileMenu.addSeparator();
		
		//tạo submenu
		subMenu = new JMenu("Help");
		subMenu.setMnemonic(KeyEvent.VK_H);
		//tạo menuitem cho submenu
		menuItem = new JMenuItem("Help 1");
		//menuItem.addActionListener(this);
		subMenu.add(menuItem);
		
		menuItem = new JMenuItem("Help 2");
		//menuItem.addActionListener(this);
		subMenu.add(menuItem);
		
		//add submenu vào menu File
		fileMenu.add(subMenu);
		
		//tạo vùng text area giữa form
		this.setContentPane(createPanel());
		
		//tạo poup menu
		createPopupMenu();
		
		//set menubar vào frame
		this.setJMenuBar(menuBar);
		
	}
	private JPanel createPanel() {
		JPanel panel;
		//tạo panel (content-pane)
		panel = new JPanel(new BorderLayout());
		
		//tạo text-area có scroll
		mainContent=new JTextArea(5,30);
		mainContent.setEditable(false);
		//đặt thanh cuộn cho textarea
		scrollPane = new JScrollPane(mainContent);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	//tạo icon
	private static ImageIcon createIcon(String path) {
		java.net.URL iconURL = MenuDemo.class.getResource(path);
		if(iconURL!=null) {
			return new ImageIcon(iconURL);
		}else {
			System.err.println("Không tìm thấy icon");
			return null;
		}
	}
 

	//xử lý sự kiện click item menu
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem item = (JMenuItem) e.getSource();
		String s = "Menu" + item.getText();
		//không dùng setText() mà dùng append() để nối text vào
		//mainContent.setText(s);
		mainContent.append(s + newLine);
		
	}
	
	//tạo popup menu
	private void createPopupMenu() {
		JMenuItem menuItem;
		
		//tạo popup menu
		JPopupMenu popup = new JPopupMenu();
		menuItem = new JMenuItem("Refresh");
		//bắt sự kiện click item
		menuItem.addActionListener(this);
		popup.add(menuItem);
		
		menuItem = new JMenuItem("Help");
		//bắt sự kiện click item
		menuItem.addActionListener(this);
		popup.add(menuItem);
		
		//bắt sự kiện popup
		MouseListener popupListener = (MouseListener) new PopupListener(popup);
		mainContent.addMouseListener(popupListener);
		
	}
	
	//class bắt sự kiện popup
	class PopupListener extends MouseAdapter{
		JPopupMenu popup;
	
		PopupListener (JPopupMenu popupMenu){
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


}
