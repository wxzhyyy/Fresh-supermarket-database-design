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

import fm.model.BeanCoupon;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class FrmCouponManager extends JFrame implements ActionListener{

	private JPanel contentPane;
	private	JScrollPane scrollPane = new JScrollPane();
	private	JButton btnaddCoupon = new JButton("添加");
	private	JButton btnmodifyCoupon = new JButton("修改");
	private	JButton btncancelCoupon = new JButton("删除");

	private Object tblCouponTitle[]= {"优惠券编号","简介","满","减","开始时间","结束时间"};
	private Object tblCouponData[][];
	DefaultTableModel tabCouponModel=new DefaultTableModel();
	private JTable dataTableCoupon=new JTable(tabCouponModel);
	BeanCoupon coupon=null;
	private List<BeanCoupon> allCoupon=null;
	
	
	private JLabel lblCouponContent = new JLabel("优惠简介:");
	private JLabel lblFitPrice = new JLabel("满:");
	
	private JLabel lblCouponPrice = new JLabel("减：");
	private JLabel lblStartTime = new JLabel("开始时间：");
	private	JLabel lblEndTime = new JLabel("结束时间：");
	private JTextField textFitPrice = new JTextField();
	private JTextField textCouponPrice = new JTextField();
	private JTextField textCouponContent= new JTextField();
	private JTextField textStartTime= new JTextField();
	private JTextField textEndTime= new JTextField();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCouponManager frame = new FrmCouponManager();
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
	public FrmCouponManager() {
		
		setTitle("优惠券管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 966, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textCouponContent.setColumns(10);
		textStartTime.setColumns(10);
		textEndTime.setColumns(10);
		textCouponPrice.setColumns(10);
		textFitPrice.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 837, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartTime)
								.addComponent(lblCouponContent, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textStartTime, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(textCouponContent, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(44)
									.addComponent(lblFitPrice))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblEndTime)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(58)
									.addComponent(btnaddCoupon, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(67)
									.addComponent(btnmodifyCoupon, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(63)
									.addComponent(btncancelCoupon, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textEndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(39)
											.addComponent(lblCouponPrice, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textCouponPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCouponContent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFitPrice)
						.addComponent(textFitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCouponPrice)
						.addComponent(textCouponPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCouponContent, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndTime)
						.addComponent(textEndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textStartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartTime))
					.addGap(25)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnaddCoupon)
						.addComponent(btncancelCoupon)
						.addComponent(btnmodifyCoupon))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		this.btnaddCoupon.addActionListener(this);
		this.btncancelCoupon.addActionListener(this);
		this.btnmodifyCoupon.addActionListener(this);
		scrollPane.setViewportView(dataTableCoupon);
		contentPane.setLayout(gl_contentPane);
		this.reloadCouponTable();
		this.dataTableCoupon.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmCouponManager.this.dataTableCoupon.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择优惠券", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}

	private void reloadCouponTable(){//这是测试数据，需要用实际数替换
		try {
			allCoupon=FreshMarketUtil.CouponManager.loadAllSystemCoupons();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCouponData =  new Object[allCoupon.size()][tblCouponTitle.length];
		for(int i=0;i<allCoupon.size();i++){
			for(int j=0;j<tblCouponTitle.length;j++)
				tblCouponData[i][j]=allCoupon.get(i).getAdminCell(j);
		}
		tabCouponModel.setDataVector(tblCouponData,tblCouponTitle);
		this.dataTableCoupon.validate();
		this.dataTableCoupon.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btnaddCoupon) {
			String content=this.textCouponContent.getText();
			String fitprice=this.textFitPrice.getText();
			String couponprice=this.textCouponPrice.getText();
			String starttime=this.textStartTime.getText();
			String endtime=this.textEndTime.getText();
			
			try {
				FreshMarketUtil.CouponManager.addSystemCoupons(content, fitprice, couponprice, starttime, endtime);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadCouponTable();
		}
		else if (e.getSource()==this.btnmodifyCoupon) {
			String content=this.textCouponContent.getText();
			String fitprice=this.textFitPrice.getText();
			String couponprice=this.textCouponPrice.getText();
			String starttime=this.textStartTime.getText();
			String endtime=this.textEndTime.getText();
			int i=dataTableCoupon.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择优惠券", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanCoupon coupon=allCoupon.get(i);
			try {
				FreshMarketUtil.CouponManager.modifySystemCoupons(coupon, content, fitprice, couponprice, starttime, endtime);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.reloadCouponTable();
		}
		else if (e.getSource()==this.btncancelCoupon) {
			int i=dataTableCoupon.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择优惠券", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanCoupon coupon=allCoupon.get(i);
			try {
				FreshMarketUtil.CouponManager.deleteSystemCoupons(coupon);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.reloadCouponTable();
		}
	}
}
