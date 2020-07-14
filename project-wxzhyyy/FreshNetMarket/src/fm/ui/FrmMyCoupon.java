package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class FrmMyCoupon extends JFrame {

	private JPanel contentPane;
	List<BeanCoupon> allUserCoupons=null;

	private Object tblUserCouponTitle[]= {"优惠券编号","满","可减","开始时间","结束时间",};
	private Object tblUserCouponData[][];
	DefaultTableModel tabUserCouponModel=new DefaultTableModel();
	private JTable dataTableUserCoupon=new JTable(tabUserCouponModel);

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMyCoupon frame = new FrmMyCoupon();
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
	public FrmMyCoupon() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 771, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
	
		scrollPane.setViewportView(dataTableUserCoupon);
		contentPane.setLayout(gl_contentPane);
		this.reloadUserCouponTable();
	}
	private void reloadUserCouponTable() {
		// TODO Auto-generated method stub
		try {
			allUserCoupons=FreshMarketUtil.CouponManager.loadAllUserCoupons(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserCouponData =new Object[allUserCoupons.size()][tblUserCouponTitle.length];
		for(int i=0;i<allUserCoupons.size();i++){
			for(int j=0;j<tblUserCouponTitle.length;j++)
				tblUserCouponData[i][j]=allUserCoupons.get(i).getCell(j);
		}
		
		tabUserCouponModel.setDataVector(tblUserCouponData,tblUserCouponTitle);
		this.dataTableUserCoupon.validate();
		this.dataTableUserCoupon.repaint();
		
		
	}
}
