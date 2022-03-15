package com.core.warehouse.tester;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.core.warehouse.exceptions.WarehouseException;
import com.core.warehouse.operations.CollOperations;
import com.core.warehouse.operations.CollOperationsImpl;
import com.core.warehouse.operations.IORelatedOpearations;
import com.core.warehouse.pojo.WarehouseItem;
import com.core.warehouse.utils.CommonUtils;

/**
 * @author Shahrukh
 * @since 18-Feb-2022
 */
public class WarehouseUIApplication extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CollOperations collOperations;
	private JLabel[] labels;
	private JTextField[] texts;
	private JTextArea tarea;
	private JPanel panel1, panel2;
	private JButton add, remove, sort;
	private HashMap<Long, WarehouseItem> itemMap;
	private MyDialog dialog;

	public static void main(String[] args) {
		try {
			WarehouseUIApplication uiApplication = new WarehouseUIApplication();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WarehouseUIApplication() {
		collOperations = new CollOperationsImpl();
		initGUI();
		initBL();
	}
	
	private void initGUI() {
		setTitle("Warehouse UI Application");
		setSize(400, 320);
		setLocationRelativeTo(null);
		
		panel1 = new JPanel(new GridLayout(7, 2, 20, 10));
		String[] names = {"Item code:", "Title", "Description:", "Category:", "Shipment Date (dd/mm/yyyy):", "Quantity:", "Price(per piece): "};
		labels = new JLabel[names.length];
		texts = new JTextField[names.length];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(names[i]);
			texts[i] = new JTextField(25);
			panel1.add(labels[i]);
			panel1.add(texts[i]);
		}
		add(panel1);
		
		panel2 = new JPanel(new GridLayout(1, 3, 30, 10));
		add = new JButton("Add Item");
			add.addActionListener(this);
			panel2.add(add);
		remove = new JButton("Remove Item");
			remove.addActionListener(this);
			panel2.add(remove);
		sort = new JButton("Sort By Price");
			sort.addActionListener(this);
			panel2.add(sort);
		add(panel2, BorderLayout.SOUTH);
		
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					IORelatedOpearations.saveCartDataToFile(itemMap);
				} catch (Exception e1) {
					System.err.println("Error in saving data to file");
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		dialog = new MyDialog();
	}
	
	private void initBL() {
		try {
			itemMap = (HashMap<Long, WarehouseItem>) IORelatedOpearations.restoreCartFromFile();
		} catch (Exception e) {
			System.err.println("Error in restoring data from file");
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			if(ae.getSource() == add && ae.getActionCommand().equalsIgnoreCase("Add Item")) {
				WarehouseItem item = new WarehouseItem(
						Long.parseLong(texts[0].getText()),
						texts[1].getText(), 
						texts[2].getText(), 
						texts[3].getText(), 
						CommonUtils.getSdf().parse(texts[4].getText()),
						Integer.parseInt(texts[5].getText()), 
						Double.parseDouble(texts[6].getText())
						);
				boolean status = collOperations.addItem(item, itemMap);
				String statusMsg = (status == true) ? "Item added successfully!" : "Item not added.";
				JOptionPane.showMessageDialog(WarehouseUIApplication.this, statusMsg);
			} else if(ae.getSource() == remove) {
				boolean status = collOperations.removeItem(Long.parseLong(texts[0].getText()), itemMap);
				String statusMsg = (status == true)? "Item removed" : "Item not removed.";
				JOptionPane.showMessageDialog(WarehouseUIApplication.this, statusMsg);
			} else if(ae.getSource() == sort) {
				tarea.setText("");
				ArrayList<WarehouseItem> list = collOperations.sortByPrice(itemMap);
				for(WarehouseItem item : list) 
					tarea.append(item.toString() + "\n");
				dialog.setVisible(true);
			}
			System.out.println("item map: " + itemMap);
		}catch (Exception | WarehouseException ex) {
			ex.printStackTrace();
		}
	}
	
	//inner-class
	class MyDialog extends JDialog {
		
		public MyDialog() {
			super(WarehouseUIApplication.this, "Sorted Items", true);
			setSize(300, 200);
			tarea = new JTextArea(20, 60);
			JScrollPane scroll = new JScrollPane(tarea);
			add(scroll);
			addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});
		}
	}
}
