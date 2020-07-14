package fm.ui;


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

import fm.model.BeanCat;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmCatManager extends JFrame implements ActionListener{

	private JPanel contentPane;
	private	JButton btnadd = new JButton("添加");
	
	private Object tblCatTitle[]= {"种类编号","种类名称","简介"};
	private Object tblCatData[][];
	DefaultTableModel tabCatModel=new DefaultTableModel();
	private JTable dataTableCat=new JTable(tabCatModel);
	BeanCat coupon=null;
	private List<BeanCat> allCat=null;
	private final JLabel lblcatname = new JLabel("\u79CD\u7C7B\u540D\u79F0:");
	private final JTextField textcatname = new JTextField();
	private final JLabel lblcatdescribe = new JLabel("\u7B80\u4ECB:");
	private final JTextField textcatdescribe = new JTextField();
	private final JButton btnmodify = new JButton("修改");
	private final JButton btncancel = new JButton("删除");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCatManager frame = new FrmCatManager();
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
	public FrmCatManager() {
		textcatdescribe.setColumns(10);
		textcatname.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1116, 910);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(488)
							.addComponent(btnadd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(102)
							.addComponent(btnmodify, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(btncancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblcatname, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textcatname, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(lblcatdescribe, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textcatdescribe, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 976, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblcatname)
						.addComponent(textcatname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textcatdescribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcatdescribe))
					.addGap(52)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadd)
						.addComponent(btnmodify)
						.addComponent(btncancel))
					.addGap(109))
		);
		contentPane.setLayout(gl_contentPane);
		scrollPane.setViewportView(dataTableCat);
		this.btnadd.addActionListener(this);
		this.btnmodify.addActionListener(this);
		this.btncancel.addActionListener(this);
		this.reloadCatTable();
		this.dataTableCat.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmCatManager.this.dataTableCat.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择种类", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}

	private void reloadCatTable() {
		// TODO Auto-generated method stub
		try {
			allCat=FreshMarketUtil.CatManager.loadallCat();
		} catch (BaseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnadd) {
			String catname=this.textcatname.getText();
			String catdescribe=this.textcatdescribe.getText();
			try {
				FreshMarketUtil.CatManager.addCat(catname, catdescribe);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadCatTable();
		}
		else if (e.getSource()==this.btnmodify) {
			
			String catname=this.textcatname.getText();
			String catdescribe=this.textcatdescribe.getText();
			int i=dataTableCat.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择满折活动", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanCat cat=allCat.get(i);
			try {
				FreshMarketUtil.CatManager.modifyCat(cat, catname, catdescribe);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadCatTable();
		}
		else if (e.getSource()==this.btncancel) {
			int i=dataTableCat.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择满折活动", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanCat cat=allCat.get(i);
			try {
				FreshMarketUtil.CatManager.deleteCat(cat);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadCatTable();
		}
	}

}
