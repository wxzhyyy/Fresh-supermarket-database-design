package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanOrder;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class FrmOrderManager extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btngo = new JButton("前进");
	private	JButton btnback = new JButton("退单");


	private Object tblOrderTitle[]= {"订单编号","原价","实付","优惠券编号","要求送达时间","地址编号","状态"};
	private Object tblOrderData[][];
	DefaultTableModel tabOrderModel=new DefaultTableModel();
	private JTable dataTableOrder=new JTable(tabOrderModel);
	BeanOrder or=null;
	private List<BeanOrder> allOrder=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmOrderManager frame = new FrmOrderManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmOrderManager() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1114, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
							.addGap(36))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btngo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(btnback, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(255))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(70)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btngo)
						.addComponent(btnback))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		
		scrollPane.setViewportView(dataTableOrder);
		contentPane.setLayout(gl_contentPane);
		this.btngo.addActionListener(this);
		this.btnback.addActionListener(this);
		this.reloadOrderTable();
		this.dataTableOrder.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmOrderManager.this.dataTableOrder.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择订单", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}

	private void reloadOrderTable() {
		// TODO Auto-generated method stub
		try {
			allOrder=FreshMarketUtil.OrderManager.loadallOrder();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrderData =  new Object[allOrder.size()][tblOrderTitle.length];
		for(int i=0;i<allOrder.size();i++){
			for(int j=0;j<tblOrderTitle.length;j++)
				tblOrderData[i][j]=allOrder.get(i).getCell(j);
		}
		tabOrderModel.setDataVector(tblOrderData,tblOrderTitle);
		this.dataTableOrder.validate();
		this.dataTableOrder.repaint();
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btngo) {
			int i=dataTableOrder.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanOrder ord=allOrder.get(i);
			try {
				FreshMarketUtil.OrderManager.nextStatus(ord);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			this.reloadOrderTable();
		}
		else if(e.getSource()==this.btnback) {
			int i=dataTableOrder.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanOrder ord=allOrder.get(i);
			try {
				FreshMarketUtil.OrderManager.backOrder(ord);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			this.reloadOrderTable();
		}
	}
}
