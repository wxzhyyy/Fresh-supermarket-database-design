package fm.ui;

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


import fm.model.BeanShippingAddress;
import fm.model.BeanUser;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmShippingAddr extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JButton btnadd = new JButton("添加");
	private	JButton btnmodify = new JButton("修改");
	private	JButton btncancel = new JButton("删除");
	
	private Object tblShipAddrTitle[]= {"地址编号","省","市","区","详细地址","联系人","电话",};
	private Object tblShipAddrData[][];
	DefaultTableModel tabShipAddrModel=new DefaultTableModel();
	private JTable dataTableShipAddr=new JTable(tabShipAddrModel);
	List<BeanShippingAddress> allShipAddr=null;
	
	JLabel lblNewLabel = new JLabel("省：");
	JLabel lblNewLabel_1 = new JLabel("市：");
	JLabel lblNewLabel_2 = new JLabel("区：");
	JLabel lblNewLabel_3 = new JLabel("详细地址：");
	JLabel lblNewLabel_4 = new JLabel("联系人：");
	JLabel lblNewLabel_5 = new JLabel("电话：");
	private JTextField textprovince;
	private JTextField textcity;
	private JTextField textcell;
	private JTextField textAreaaddress;
	private JTextField textlinkman;
	private JTextField textphone;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmShippingAddr frame = new FrmShippingAddr();
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
	public FrmShippingAddr() {
		setTitle("配送信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 939, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		textprovince = new JTextField();
		textprovince.setColumns(10);
		textcity = new JTextField();
		textcity.setColumns(10);
		textcell = new JTextField();
		textcell.setColumns(10);
		textAreaaddress = new JTextField();
		textAreaaddress.setColumns(10);
		textlinkman = new JTextField();
		textlinkman.setColumns(10);
		textphone = new JTextField();
		textphone.setColumns(10);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(248)
					.addComponent(btnadd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(btnmodify, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(btncancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textlinkman, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textprovince, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textcity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textcell, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_3)
							.addGap(16)
							.addComponent(textAreaaddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(297, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textprovince, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textcity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(textcell, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(textAreaaddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textlinkman, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(textphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadd)
						.addComponent(btnmodify)
						.addComponent(btncancel))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		
		scrollPane.setViewportView(dataTableShipAddr);
		contentPane.setLayout(gl_contentPane);
		this.btnadd.addActionListener(this);
		this.btncancel.addActionListener(this);
		this.btnmodify.addActionListener(this);
		
		this.reloadShipAddrTable();
		this.dataTableShipAddr.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmShippingAddr.this.dataTableShipAddr.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择地址", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}
	private void reloadShipAddrTable(){//这是测试数据，需要用实际数替换
		try {
			String userid=BeanUser.currentLoginUser.getUser_id();
			allShipAddr=FreshMarketUtil.ShipAddrManager.loadAll(userid);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShipAddrData =  new Object[allShipAddr.size()][tblShipAddrTitle.length];
		for(int i=0;i<allShipAddr.size();i++){
			for(int j=0;j<tblShipAddrTitle.length;j++)
				tblShipAddrData[i][j]=allShipAddr.get(i).getCell(j);
		}
		tabShipAddrModel.setDataVector(tblShipAddrData,tblShipAddrTitle);
		this.dataTableShipAddr.validate();
		this.dataTableShipAddr.repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btnadd) {
			
			String province=this.textprovince.getText();
			String city=this.textcity.getText();
			String cell=this.textcell.getText();
			String address=this.textAreaaddress.getText();
			String linkman=this.textlinkman.getText();
			String phone=this.textphone.getText();
			String userid=BeanUser.currentLoginUser.getUser_id();
			try {
				FreshMarketUtil.ShipAddrManager.addShippingAddress(userid, province, city, cell, address, linkman, phone);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.reloadShipAddrTable();
		}
		else if (e.getSource()==this.btncancel) {
			int i=this.dataTableShipAddr.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanShippingAddress sa=allShipAddr.get(i);
			System.out.println(sa.getAddr_id());
			System.out.println(sa.getUser_id());
			try {
				FreshMarketUtil.ShipAddrManager.deleteShippingAddress(sa);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.reloadShipAddrTable();
		}
		else if (e.getSource()==this.btnmodify) {
			int i=this.dataTableShipAddr.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanShippingAddress sa=allShipAddr.get(i);
			String province=this.textprovince.getText();
			String city=this.textcity.getText();
			String cell=this.textcell.getText();
			String address=this.textAreaaddress.getText();
			String linkman=this.textlinkman.getText();
			String phone=this.textphone.getText();
			try {
				FreshMarketUtil.ShipAddrManager.modifyShippingAddress(sa, province, city, cell, address, linkman, phone);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.reloadShipAddrTable();
		}
	}
}
