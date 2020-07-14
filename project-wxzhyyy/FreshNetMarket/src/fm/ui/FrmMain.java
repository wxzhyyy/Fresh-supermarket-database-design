package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanCat;
import fm.model.BeanComd;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
import freshmarket.FreshMarketUtil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class FrmMain extends JFrame implements ActionListener {

	private JMenuBar menuBar=new JMenuBar();
	private JMenu mntmSearhch = new JMenu("搜索");
	private JMenu mnmyOrder = new JMenu("订单");
	private	JMenu mnMineInfo = new JMenu("我的");
	private JMenuItem mntmMyBlance = new JMenuItem("我的余额");
	private	JMenuItem mntmNewMyCoupon = new JMenuItem("我的优惠券");
	private	JMenuItem mntmMyVIP = new JMenuItem("成为VIP");
	private	JMenuItem mntmMyIfo = new JMenuItem("个人信息");
	private	JMenuItem mntmShippingAddr = new JMenuItem("配送地址");
	private JMenuItem mntmOrderdetail = new JMenuItem("历史订单");
	private JMenuItem mntmMyEval = new JMenuItem("可评价商品");
	private JMenuItem mntmSeachComd = new JMenuItem("搜索商品");
	private JMenuItem mntmSearchMenu = new JMenuItem("搜索菜单");
	private JMenuItem mntmChangPwd = new JMenuItem("修改密码");
	
	
	private Object tblCatTitle[]= {"分类名称","分类详情"};
	private Object tblCatData[][];
	DefaultTableModel tabCatModel=new DefaultTableModel();
	private JTable dataTableCat=new JTable(tabCatModel);


	
	private Object tblCatComdTitle[]= {"商品编号","商品名称","商品价格","VIP价格","库存"};
	private Object tblCatComdData[][];
	DefaultTableModel tabCatComdModel=new DefaultTableModel();
	private JTable dataTableCatComd=new JTable(tabCatComdModel);
	
	BeanCat curCat=null;
	List<BeanCat> allCat=null;
	List<BeanComd> allCatComd=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private FrmLogin dlgLogin=null;
	private JMenuItem mntmNewCart = new JMenuItem("购物车");
	private JMenuItem mntmSystemCoupon = new JMenuItem("领取优惠券");
	private JMenuItem mntmpromotion = new JMenuItem("促销商品");

	
	/**
	 * Create the frame.
	 */
	
	public FrmMain() {
		
		this.setTitle("生鲜超市");
		dlgLogin=new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
		if(BeanUser.currentLoginUser.getUser_id()==null)
			this.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setJMenuBar(menuBar);
		menuBar.add(mntmSearhch);
		
		mntmSearhch.add(mntmSeachComd);
		
		mntmSearhch.add(mntmSystemCoupon);
		
		
		mntmSearhch.add(mntmSearchMenu);
		
		mntmSearhch.add(mntmpromotion);
		menuBar.add(mnmyOrder);
		
		mnmyOrder.add(mntmNewCart);
		mnmyOrder.add(mntmOrderdetail);
		mnmyOrder.add(mntmMyEval);
		menuBar.add(mnMineInfo);
		mnMineInfo.add(mntmMyBlance);
		mnMineInfo.add(mntmNewMyCoupon);
		mnMineInfo.add(mntmMyVIP);
		mnMineInfo.add(mntmMyIfo);
		mnMineInfo.add(mntmShippingAddr);
		
		mnMineInfo.add(mntmChangPwd);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.mntmSearhch.addActionListener(this);
		this.mnMineInfo.addActionListener(this);
		this.mntmShippingAddr.addActionListener(this);
		this.mntmMyIfo.addActionListener(this);
		this.mntmNewMyCoupon.addActionListener(this);
		this.mnmyOrder.addActionListener(this);
		this.mntmMyVIP.addActionListener(this);
		this.mntmOrderdetail.addActionListener(this);
		this.mntmMyEval.addActionListener(this);
		this.mntmMyIfo.addActionListener(this);
		this.mntmMyBlance.addActionListener(this);
		this.mntmSeachComd.addActionListener(this);
		this.mntmSearchMenu.addActionListener(this);
		this.mntmNewCart.addActionListener(this);
		this.mntmSystemCoupon.addActionListener(this);
		this.mntmChangPwd.addActionListener(this);
		
		this.getContentPane().add(new JScrollPane(this.dataTableCat), BorderLayout.WEST);
	    this.dataTableCat.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.dataTableCat.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMain.this.reloadCatComdTabel(i);
			}
	    	
	    });
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCatComd), BorderLayout.CENTER);
	    
	    this.reloadCatTable();
	    this.dataTableCatComd.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.dataTableCatComd.getSelectedRow();
				if(i<0) {
					return;
				}
				BeanComd.currentComd=allCatComd.get(i);
				new FrmComdDetails().setVisible(true);
			}
	    	
	    });
	    
	}
	private void reloadCatTable(){//这是测试数据，需要用实际数替换
		try {
			allCat=FreshMarketUtil.CatManager.loadallCat();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCatData =  new Object[allCat.size()][tblCatTitle.length];
		for(int i=0;i<allCat.size();i++){
			for(int j=0;j<tblCatTitle.length;j++)
				tblCatData[i][j]=allCat.get(i).getCell(j);
		}
		tabCatModel.setDataVector(tblCatData,tblCatTitle);
		this.dataTableCat.validate();
		this.dataTableCat.repaint();
	}
	private void reloadCatComdTabel(int planIdx){
		if(planIdx<0) return;
		curCat=allCat.get(planIdx);
		try {
			allCatComd=FreshMarketUtil.ComdManager.loadComdCat(curCat.getCat_id());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCatComdData =new Object[allCatComd.size()][tblCatComdTitle.length];
		for(int i=0;i<allCatComd.size();i++){
			for(int j=0;j<tblCatComdTitle.length;j++)
				tblCatComdData[i][j]=allCatComd.get(i).getCell(j);
		}
		
		tabCatComdModel.setDataVector(tblCatComdData,tblCatComdTitle);
		this.dataTableCatComd.validate();
		this.dataTableCatComd.repaint();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.mntmShippingAddr) {
			FrmShippingAddr dlg=new FrmShippingAddr();
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.mntmSeachComd){
			FrmSearchComd dlg=new FrmSearchComd();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmOrderdetail) {
			FrmOrderManager dlg=new FrmOrderManager();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmMyEval) {
			FrmMyEvalList dlg=new FrmMyEvalList();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmSearchMenu) {
			FrmMenuList dlg=new FrmMenuList();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmMyBlance) {
			FrmBalanceManager dlg=new FrmBalanceManager();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmNewMyCoupon) {
			FrmMyCoupon dlg=new FrmMyCoupon();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmMyVIP) {
			try {
				FreshMarketUtil.userManager.beVIP();
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if (e.getSource()==this.mntmMyIfo) {
			FrmUserInfoManager dlg=new FrmUserInfoManager();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmChangPwd) {
			FrmChangePwd dlg=new FrmChangePwd();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.mntmNewCart) {
			FrmCart dlg = null;
			try {
				dlg = new FrmCart();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlg.setVisible(true);
		}
		  else if (e.getSource()==this.mntmSystemCoupon) {
			FrmSystemCoupon dlg=new FrmSystemCoupon();
			dlg.setVisible(true);
		}
		
	}

}
