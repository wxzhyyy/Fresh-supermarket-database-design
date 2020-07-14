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

import fm.model.BeanPromotion;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmPromotionManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private	JLabel lblcomdid = new JLabel("商品编号");
	private JTextField textcomdid = new JTextField();
	private	JButton btnmodify = new JButton("修改");
	
	
	private Object tblPromotionTitle[]= {"促销编号","商品编号","促销价格","促销商品数量","开始时间","结束时间"};
	private Object tblPromotionData[][];
	DefaultTableModel tabPromotionModel=new DefaultTableModel();
	private JTable dataTablePromotion=new JTable(tabPromotionModel);
	List<BeanPromotion> allPromotion=null;
	BeanPromotion prom=new BeanPromotion();

	private JLabel lblpromprice = new JLabel("限时价格");
	private JTextField textpromprice = new JTextField();
	private JLabel lblpromquantity = new JLabel("限时数量");
	private JTextField textpromquantity = new JTextField();
	private JLabel lblstarttime = new JLabel("开始时间");
	private JTextField textstarttime = new JTextField();
	private JButton btnadd = new JButton("添加");
	private JButton btncancel = new JButton("删除");
	private JLabel lblendtime = new JLabel("结束时间");
	private JTextField textendtime = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPromotionManager frame = new FrmPromotionManager();
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
	public FrmPromotionManager() {
		textendtime.setColumns(10);
		textstarttime.setColumns(10);
		textpromquantity.setColumns(10);
		textpromprice.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1143, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		textcomdid.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(511, Short.MAX_VALUE)
					.addComponent(btnadd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addComponent(btnmodify, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btncancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(209))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 998, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblcomdid, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textcomdid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblpromprice, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textpromprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblpromquantity, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textpromquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblstarttime, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textstarttime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblendtime, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textendtime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblcomdid)
						.addComponent(textcomdid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblpromprice)
						.addComponent(textpromprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblpromquantity)
						.addComponent(textpromquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblstarttime)
						.addComponent(textstarttime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblendtime)
						.addComponent(textendtime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnmodify)
						.addComponent(btnadd)
						.addComponent(btncancel))
					.addGap(50))
		);
		
		scrollPane.setViewportView(dataTablePromotion);
		contentPane.setLayout(gl_contentPane);
		this.btnadd.addActionListener(this);
		this.btnmodify.addActionListener(this);
		this.btncancel.addActionListener(this);
		
		this.reloadPromotionTable();
		this.dataTablePromotion.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmPromotionManager.this.dataTablePromotion.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择促销信息", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}

	private void reloadPromotionTable() {
		// TODO Auto-generated method stub
		try {
			allPromotion=FreshMarketUtil.PromotionManager.loadAllPromotions();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPromotionData =  new Object[allPromotion.size()][tblPromotionTitle.length];
		for(int i=0;i<allPromotion.size();i++){
			for(int j=0;j<tblPromotionTitle.length;j++)
				tblPromotionData[i][j]=allPromotion.get(i).getCell(j);
		}
		
		tabPromotionModel.setDataVector(tblPromotionData,tblPromotionTitle);
		this.dataTablePromotion.validate();
		this.dataTablePromotion.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnadd) {
			String comdid=this.textcomdid.getText();
			String promprice=this.textpromprice.getText();
			String promquantity=this.textpromquantity.getText();
			String start_time=this.textstarttime.getText();
			String end_time=this.textendtime.getText();
			
			try {
				FreshMarketUtil.PromotionManager.addPromotion(comdid, promprice, promquantity, start_time, end_time);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadPromotionTable();
		}
		else if (e.getSource()==this.btnmodify) {
			String comdid=this.textcomdid.getText();
			String promprice=this.textpromprice.getText();
			String promquantity=this.textpromquantity.getText();
			String start_time=this.textstarttime.getText();
			String end_time=this.textendtime.getText();
			int i=dataTablePromotion.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择促销商品", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanPromotion prom=allPromotion.get(i);
			
			try {
				FreshMarketUtil.PromotionManager.modifyPromotion(prom,comdid, promprice, promquantity, start_time, end_time);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadPromotionTable();
		}
		else if (e.getSource()==this.btncancel) {
			int i=dataTablePromotion.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择促销商品", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanPromotion prom=allPromotion.get(i);
			try {
				FreshMarketUtil.PromotionManager.deletePromotion(prom);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadPromotionTable();
		}
	}

}
