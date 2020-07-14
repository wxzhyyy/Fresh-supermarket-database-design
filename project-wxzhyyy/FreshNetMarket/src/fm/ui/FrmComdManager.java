package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanComd;
import fm.model.BeanComd;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmComdManager extends JFrame implements ActionListener{

	private JPanel contentPane;

	private	JLabel lblcatid = new JLabel("分类编号");
	private	JButton btnadd = new JButton("添加");
	private JTextField textcatid = new JTextField();
	private final JLabel lblcomdname = new JLabel("商品名称");
	private final JTextField textcomdname = new JTextField();
	private final JLabel lblcomdprice = new JLabel("商品价格");
	private final JTextField textcomdprice = new JTextField();
	private final JLabel lblcomdvipprice = new JLabel("VIP价格");
	private final JTextField textcomdvipprice = new JTextField();
	private final JButton btnmodify = new JButton("修改");
	private final JButton btncancel = new JButton("删除");
	private final JLabel lblcomdquantity = new JLabel("库存");
	private final JTextField textcomdquantity = new JTextField();
	
	private Object tblComdTitle[]= {"商品编号","商品名称","商品价格","VIP价格","库存","种类编号","促销编号"};
	private Object tblComdData[][];
	DefaultTableModel tabComdModel=new DefaultTableModel();
	private JTable dataTableComd=new JTable(tabComdModel);
	BeanComd coupon=null;
	private List<BeanComd> allComd=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComdManager frame = new FrmComdManager();
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
	public FrmComdManager() {
		textcomdquantity.setColumns(10);
		textcomdvipprice.setColumns(10);
		textcomdprice.setColumns(10);
		textcomdname.setColumns(10);
		setTitle("商品管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 975, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		textcatid.setColumns(10);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(303, Short.MAX_VALUE)
					.addComponent(btnadd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(btnmodify, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(btncancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(244))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblcomdquantity, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textcomdquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 852, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblcatid, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textcatid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblcomdname, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textcomdname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(lblcomdprice, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textcomdprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(lblcomdvipprice, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textcomdvipprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblcatid)
						.addComponent(textcatid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcomdname)
						.addComponent(textcomdname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcomdprice)
						.addComponent(textcomdprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcomdvipprice)
						.addComponent(textcomdvipprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblcomdquantity)
						.addComponent(textcomdquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadd)
						.addComponent(btnmodify)
						.addComponent(btncancel))
					.addContainerGap(110, Short.MAX_VALUE))
		);
		
		scrollPane.setViewportView(dataTableComd);
		contentPane.setLayout(gl_contentPane);
		this.reloadComdTable();
		this.btnadd.addActionListener(this);
		this.btnmodify.addActionListener(this);
		this.btncancel.addActionListener(this);
		this.dataTableComd.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmComdManager.this.dataTableComd.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择满折信息", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
		
	}

	private void reloadComdTable() {
		// TODO Auto-generated method stub
		try {
			allComd=FreshMarketUtil.ComdManager.loadall();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblComdData =  new Object[allComd.size()][tblComdTitle.length];
		for(int i=0;i<allComd.size();i++){
			for(int j=0;j<tblComdTitle.length;j++)
				tblComdData[i][j]=allComd.get(i).getCell(j);
		}
		tabComdModel.setDataVector(tblComdData,tblComdTitle);
		this.dataTableComd.validate();
		this.dataTableComd.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnadd) {
			String catid=this.textcatid.getText();
			String comdname=this.textcomdname.getText();
			String comdprice=this.textcomdprice.getText();
			String comdvipprice=this.textcomdvipprice.getText();
			String comdquantity=this.textcomdquantity.getText();
			
			try {
				FreshMarketUtil.ComdManager.addcomd(catid, comdname, comdprice, comdvipprice, comdquantity);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadComdTable();
		}
		else if (e.getSource()==this.btnmodify) {
			String catid=this.textcatid.getText();
			String comdname=this.textcomdname.getText();
			String comdprice=this.textcomdprice.getText();
			String comdvipprice=this.textcomdvipprice.getText();
			String comdquantity=this.textcomdquantity.getText();
			int i=dataTableComd.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanComd comd=allComd.get(i);
			
			try {
				FreshMarketUtil.ComdManager.modifycomd(comd, catid,comdname, comdprice, comdvipprice, comdquantity);	
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadComdTable();
		}
		else if (e.getSource()==this.btncancel) {
			int i=dataTableComd.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanComd comd=allComd.get(i);
			try {
				FreshMarketUtil.ComdManager.deletecomd(comd);;
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadComdTable();
		}
	}
	

}
