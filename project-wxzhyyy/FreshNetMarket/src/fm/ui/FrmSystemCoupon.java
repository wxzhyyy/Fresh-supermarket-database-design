package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanCoupon;
import fm.model.BeanUser;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class FrmSystemCoupon extends JFrame implements ActionListener{

	private JPanel contentPane;
	private	JButton btnNewButton = new JButton("确认领取");
	private	JButton btnNewButton_1 = new JButton("取消");
	
	List<BeanCoupon> allSystemCoupons=null;

	private Object tblSystemCouponTitle[]= {"优惠券编号","满","可减","开始时间","结束时间",};
	private Object tblSystemCouponData[][];
	DefaultTableModel tabSystemCouponModel=new DefaultTableModel();
	private JTable dataTableSystemCoupon=new JTable(tabSystemCouponModel);

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSystemCoupon frame = new FrmSystemCoupon();
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
	public FrmSystemCoupon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(44))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		
	
		scrollPane.setViewportView(dataTableSystemCoupon);
		contentPane.setLayout(gl_contentPane);
		this.reloadSystemCouponTable();
		this.btnNewButton.addActionListener(this);
		this.btnNewButton_1.addActionListener(this);
	}

	private void reloadSystemCouponTable() {
		// TODO Auto-generated method stub
		try {
			allSystemCoupons=FreshMarketUtil.CouponManager.loadAllSystemCoupons();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblSystemCouponData =new Object[allSystemCoupons.size()][tblSystemCouponTitle.length];
		for(int i=0;i<allSystemCoupons.size();i++){
			for(int j=0;j<tblSystemCouponTitle.length;j++)
				tblSystemCouponData[i][j]=allSystemCoupons.get(i).getCell(j);
		}
		
		tabSystemCouponModel.setDataVector(tblSystemCouponData,tblSystemCouponTitle);
		this.dataTableSystemCoupon.validate();
		this.dataTableSystemCoupon.repaint();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnNewButton_1) {
			this.setVisible(false);
		}
		else if (e.getSource()==this.btnNewButton) {
			int i=dataTableSystemCoupon.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null,  "请选择图书","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanCoupon coupon=this.allSystemCoupons.get(i);
			try {
				FreshMarketUtil.CouponManager.addUserCoupons(BeanUser.currentLoginUser, coupon);
				JOptionPane.showMessageDialog(null,  "领取成功","提示",JOptionPane.OK_OPTION);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
