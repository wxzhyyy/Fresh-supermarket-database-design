package fm.ui;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanComd;
import fm.model.BeanEvaluation;
import fm.model.BeanMenu;
import fm.model.BeanOrder;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmComdDetails extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	private JLabel lblquantity = new JLabel("购买数量");
	private JTextField textquantity = new JTextField();
	private JButton btnaddtoCart = new JButton("加入购物车");
	
	
	BeanComd comd= new BeanComd();
	List<BeanComd> recoComd=new ArrayList<BeanComd>();
	List<BeanMenu> recoMenu=new ArrayList<BeanMenu>();
	
	//商品详情
	private Object tblComdTitle[]= {"商品名称","商品价格","会员价格","商品详情","商品库存"};
	private Object tblComdData[][];
	DefaultTableModel tabComdModel=new DefaultTableModel();
	private JTable dataTableComd=new JTable(tabComdModel);


	//商品相关推荐
	
	private Object tblrecoComdTitle[]= {"商品名称", "商品价格", "VIP价格", "库存"};
	private Object tblrecoComdData[][];
	DefaultTableModel tabrecoComdModel=new DefaultTableModel();
	private final JTable dataTablerecoComd = new JTable(tabrecoComdModel);
	private final JScrollPane scrollPane_1 = new JScrollPane();
	//商品评价
	private Object tblComdEvalTitle[]= {"商品编号", "用户编号", "评价", "时间","星级"};
	private Object tblComdEvalData[][];
	DefaultTableModel tabComdEvalModel=new DefaultTableModel();
	private JTable dataTableComdEval = new JTable(tabComdEvalModel);
	BeanEvaluation eval=new BeanEvaluation();
	List<BeanEvaluation> allComdEval=null;
	
	
	
	private JLabel lblNewLabel = new JLabel("相关商品推荐");
	private JLabel lblNewLabel_1 = new JLabel("相关菜单推荐");
	
	
	//商品相关菜单
	private Object tblreMenuTitle[]= {"菜名", "所需材料", "步骤"};
	private Object tblreMenuData[][];
	DefaultTableModel tabreMenuModel=new DefaultTableModel();
	private final JTable dataTablereMenu = new JTable(tabreMenuModel);
	private final JLabel lblNewLabel_2 = new JLabel("商品评价");
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private final JTable table = new JTable();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComdDetails frame = new FrmComdDetails();
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
	public FrmComdDetails() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1090, 831);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textquantity.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 938, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 948, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 946, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
									.addGap(94))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblquantity, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textquantity, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addGap(56)
									.addComponent(btnaddtoCart, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(350)))
							.addGap(268)))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblquantity)
						.addComponent(textquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnaddtoCart))
					.addGap(31)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(169))
		);
		
		scrollPane_2.setViewportView(dataTableComdEval);
		this.btnaddtoCart.addActionListener(this);
		this.reloadreComdTable();
		scrollPane_1.setViewportView(dataTablereMenu);
		scrollPane.setViewportView(dataTablerecoComd);
		
		this.reloadComdEvalTable();
		this.reloadreComdTable();
		contentPane.setLayout(gl_contentPane);
	}
	private void reloadComdEvalTable() {
		// TODO Auto-generated method stub
		try {
			BeanComd comd=BeanComd.currentComd;
			allComdEval=FreshMarketUtil.ComdEvalManager.loadComdEval(comd);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblComdEvalData =  new Object[allComdEval.size()][tblComdEvalTitle.length];
		for(int i=0;i<allComdEval.size();i++){
			for(int j=0;j<tblComdEvalTitle.length;j++)
				tblComdEvalData[i][j]=allComdEval.get(i).getCell(j);
		}
		tabComdEvalModel.setDataVector(tblComdEvalData,tblComdEvalTitle);
		this.dataTableComdEval.validate();
		this.dataTableComdEval.repaint();
	}

	private void reloadreMenuTable() {
		// TODO Auto-generated method stub
		tabreMenuModel.setDataVector(tblreMenuData,tblreMenuTitle);
	}

	private void reloadreComdTable() {
		// TODO Auto-generated method stub
		tabrecoComdModel.setDataVector(tblrecoComdData,tblrecoComdTitle);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btnaddtoCart) {
			comd=BeanComd.currentComd;
			BeanOrder t_comdorder = new BeanOrder();
			BeanOrder comdorder = new BeanOrder();
			try {
				t_comdorder=FreshMarketUtil.OrderManager.loadlastOrder();
			} catch (BaseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if((t_comdorder.getOrder_id()!=null&&!t_comdorder.getOrder_id().equals(""))&&(t_comdorder.getOrder_status()==null||t_comdorder.getOrder_id().equals(""))) {
				comdorder=t_comdorder;
			}else {
				try {
					comdorder=FreshMarketUtil.OrderManager.creatOrder();
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			BeanOrder.cartOrder=comdorder;
			int num = Integer.parseInt(this.textquantity.getText().trim());
			try {
				FreshMarketUtil.OrderDetailManager.addComdtoOrder(comdorder, comd, num);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		}
	}


}

