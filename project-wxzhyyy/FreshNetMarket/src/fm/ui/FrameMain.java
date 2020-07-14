package fm.ui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import fm.model.BeanComd;
import fm.util.DBUtil;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FrameMain extends JFrame {

	private JPanel contentPane;
	private JTable comdtable;
	private JTable currentorder;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain frame = new FrameMain();
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
	

	public FrameMain() {
		FrameLogin frame = new FrameLogin();
		frame.setVisible(true);
		
		setTitle("生鲜超市");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1546, 941);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu homepage = new JMenu("商城");
		menuBar.add(homepage);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u6EE1\u6298\u6D3B\u52A8\u533A");
		homepage.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("\u9650\u65F6\u4FC3\u9500\u533A");
		homepage.add(mntmNewMenuItem_6);
		
		JMenu classifypage = new JMenu("\u5206\u7C7B\u67E5\u8BE2");
		menuBar.add(classifypage);
		
		JMenu orderpage = new JMenu("\u5355\u54C1\u67E5\u8BE2");
		menuBar.add(orderpage);
		
		JMenu mypage = new JMenu("我的");
		menuBar.add(mypage);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("我的余额");
		mypage.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("我的优惠券");
		mypage.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("我的会员");
		mypage.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("我的订单");
		mypage.add(mntmNewMenuItem);
		
		JMenuItem mntmUserInfoManager = new JMenuItem("个人信息");
		mntmUserInfoManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUserInfoManager dlg=new FrmUserInfoManager();
				setVisible(true);
			}
		});
		mypage.add(mntmUserInfoManager);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 741, 813);
		contentPane.add(scrollPane);
		
		comdtable = new JTable();
		scrollPane.setViewportView(comdtable);
		comdtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"商品名称", "商品介绍", "价  格", "会员价格"
			}
		));
		
		JButton btnConfirmBuy = new JButton("\u786E\u8BA4\u8D2D\u4E70");
		btnConfirmBuy.setBounds(1333, 14, 97, 24);
		contentPane.add(btnConfirmBuy);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u8BA2\u5355");
		lblNewLabel.setBounds(861, 10, 97, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setToolTipText("");
		scrollPane_1.setBounds(815, 63, 691, 813);
		contentPane.add(scrollPane_1);
		
		currentorder = new JTable();
		currentorder.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(currentorder);
		
		JButton btnDeleteComdinOrder = new JButton("\u5220  \u9664");
		btnDeleteComdinOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteComdinOrder.setBounds(1064, 14, 97, 24);
		contentPane.add(btnDeleteComdinOrder);
		
		JLabel lblNewLabel_1 = new JLabel("\u63A8\u8350\u5546\u54C1");
		lblNewLabel_1.setBounds(89, 14, 62, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAddComdOrder = new JButton("\u52A0\u5165\u8BA2\u5355");
		btnAddComdOrder.setBounds(333, 15, 97, 23);
		contentPane.add(btnAddComdOrder);
		
		JButton btnSearchComd = new JButton("\u67E5\u8BE2\u5546\u54C1");
		btnSearchComd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchComd.setBounds(592, 15, 97, 23);
		contentPane.add(btnSearchComd);
		this.fillComdTable(new BeanComd());
	}
	private void fillComdTable(BeanComd comd) {
		DefaultTableModel dtm=(DefaultTableModel) comdtable.getModel();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			List<BeanComd> result=new ArrayList<BeanComd>();
			result=freshmarket.FreshMarketUtil.ComdManager.loadall();
			for(int i=0; i<result.size();i++) {
				BeanComd c=new BeanComd();
				c=result.get(i);
				Vector v=new Vector();
				v.add(c.getComd_name());
				v.add(c.getComd_details());
				v.add(c.getComd_price());
				v.add(c.getComd_vip_price());
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
}
