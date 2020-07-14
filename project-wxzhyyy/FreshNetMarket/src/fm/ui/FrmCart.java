package fm.ui;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanComd;
import fm.model.BeanOrder;
import fm.model.BeanOrderDetails;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class FrmCart extends JFrame implements ActionListener{

	private JPanel contentPane;

	private JButton btnCancelComd = new JButton("删除商品");
	private Object tblCartDetailTitle[]= {"商品编号","商品名称","商品价格","购买数量"};
	private Object tblCartDetailData[][];
	DefaultTableModel tabCartDetailModel=new DefaultTableModel();
	private JTable dataTableCartDetail = new JTable(tabCartDetailModel);
	private final JButton btnModifyNum = new JButton("确认修改");
	private final JButton btnSubmitorder = new JButton("提交订单");
	private final JTextField textField = new JTextField();
	
	List<BeanOrderDetails> CartComdList=new ArrayList<BeanOrderDetails>();
	private final JLabel lblNewLabel = new JLabel("输入要修改的商品数量：");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCart frame = new FrmCart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws BaseException 
	 */
	public FrmCart() throws BaseException {
		textField.setColumns(10);
		setTitle("购物车");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 849, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 697, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCancelComd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnSubmitorder, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(lblNewLabel)
							.addGap(1)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(btnModifyNum, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCancelComd)
								.addComponent(btnSubmitorder)
								.addComponent(lblNewLabel))
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnModifyNum)
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		this.btnCancelComd.addActionListener(this);
		this.btnModifyNum.addActionListener(this);
		this.btnSubmitorder.addActionListener(this);
		
		this.reloadCartDetailTable();
		scrollPane.setViewportView(dataTableCartDetail);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void reloadCartDetailTable() throws BaseException {
		// TODO Auto-generated method stub
	
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
		try {
			CartComdList=FreshMarketUtil.OrderDetailManager.loadAll(comdorder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCartDetailData=  new Object[CartComdList.size()][tblCartDetailTitle.length];
		for(int i=0;i<CartComdList.size();i++){
			for(int j=0;j<tblCartDetailTitle.length;j++)
				System.out.println((tblCartDetailData[i][j]=CartComdList.get(i).getCell(j)));
			
		}
		tabCartDetailModel.setDataVector(tblCartDetailData, tblCartDetailTitle);
		this.dataTableCartDetail.validate();
		this.dataTableCartDetail.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btnCancelComd) {
			int i=this.dataTableCartDetail.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanOrderDetails od=this.CartComdList.get(i);
			try {
				FreshMarketUtil.OrderDetailManager.deleteComdtoOrder(od);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.reloadCartDetailTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.reloadCartDetailTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource()==this.btnModifyNum) {
			int i=this.dataTableCartDetail.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanOrderDetails od=this.CartComdList.get(i);
			int num = Integer.parseInt(this.textField.getText().trim());
			try {
				FreshMarketUtil.OrderDetailManager.modifyComdOrder(od, num);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.reloadCartDetailTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource()==this.btnSubmitorder) {
			FrmSubmitOrder dlg=new FrmSubmitOrder();
			dlg.setVisible(true);
		}
	}

}
