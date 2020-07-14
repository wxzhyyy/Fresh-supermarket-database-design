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

import fm.model.BeanCoupon;
import fm.model.BeanFullDisc;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

public class FrmFullDiscManager extends JFrame implements ActionListener{

	private JPanel contentPane;

	private	JScrollPane scrollPane = new JScrollPane();
	private	JLabel lblcontent = new JLabel("\u5185\u5BB9:");
	private	JTextField textcontent = new JTextField();
	private	JButton btnadd = new JButton("添加");
	private JLabel lblfull = new JLabel("满");
	private JTextField textfitnum = new JTextField();
	private JLabel lblnum = new JLabel("件");
	private JLabel lbldisc = new JLabel("打");
	private JTextField textdiscount = new JTextField();
	private JLabel lbldisc1 = new JLabel("\u6298");
	private JLabel lblstarttime = new JLabel("开始时间：");
	private JTextField textstarttime = new JTextField();
	private JLabel lblendtime = new JLabel("\u7ED3\u675F\u65F6\u95F4:");
	private JTextField textendtime = new JTextField();
	private JButton btnmodify = new JButton("修改");
	private JButton btncancel = new JButton("删除");
	
	BeanFullDisc fulldisc=null;
	private List<BeanFullDisc> allFullDisc=null;
	
	private Object tblFullDiscTitle[]= {"满折编号","简介","满（件）","折扣","开始时间","结束时间"};
	private Object tblFullDiscData[][];
	DefaultTableModel tabFullDiscModel=new DefaultTableModel();
	private JTable dataTableFullDisc=new JTable(tabFullDiscModel);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFullDiscManager frame = new FrmFullDiscManager();
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
	public FrmFullDiscManager() {
		textendtime.setColumns(10);
		textstarttime.setColumns(10);
		textdiscount.setColumns(10);
		textfitnum.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1055, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		textcontent.setColumns(10);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(783, Short.MAX_VALUE)
					.addComponent(btnadd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(btnmodify, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btncancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(87))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 932, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblcontent)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textcontent))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblstarttime)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textstarttime, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
							.addGap(68)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblfull, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textfitnum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblnum, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbldisc, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textdiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbldisc1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblendtime)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textendtime, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblcontent)
						.addComponent(textcontent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblfull)
						.addComponent(textfitnum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblnum)
						.addComponent(lbldisc)
						.addComponent(textdiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbldisc1))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblstarttime)
						.addComponent(textstarttime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblendtime)
						.addComponent(textendtime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadd)
						.addComponent(btnmodify)
						.addComponent(btncancel))
					.addGap(53))
		);
		
		scrollPane.setViewportView(dataTableFullDisc);
		contentPane.setLayout(gl_contentPane);
		this.btnadd.addActionListener(this);
		this.btnmodify.addActionListener(this);
		this.btncancel.addActionListener(this);
		
		this.reloadFullDiscTable();
		this.dataTableFullDisc.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmFullDiscManager.this.dataTableFullDisc.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择满折信息", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}

	private void reloadFullDiscTable() {
		// TODO Auto-generated method stub
		try {
			allFullDisc=FreshMarketUtil.FullDiscManager.loadAllSystemFullDiscs();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblFullDiscData =  new Object[allFullDisc.size()][tblFullDiscTitle.length];
		for(int i=0;i<allFullDisc.size();i++){
			for(int j=0;j<tblFullDiscTitle.length;j++)
				tblFullDiscData[i][j]=allFullDisc.get(i).getAdminCell(j);
		}
		tabFullDiscModel.setDataVector(tblFullDiscData,tblFullDiscTitle);
		this.dataTableFullDisc.validate();
		this.dataTableFullDisc.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnadd) {
			String content=this.textcontent.getText();
			String fit_number=this.textfitnum.getText();
			String disc_discount=this.textdiscount.getText();
			String start_time=this.textstarttime.getText();
			String end_time=this.textendtime.getText();
			
			try {
				FreshMarketUtil.FullDiscManager.addSystemFullDiscs(content, fit_number, disc_discount, start_time, end_time);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadFullDiscTable();
		}
		else if (e.getSource()==this.btnmodify) {
			String content=this.textcontent.getText();
			String fit_number=this.textfitnum.getText();
			String disc_discount=this.textdiscount.getText();
			String start_time=this.textstarttime.getText();
			String end_time=this.textendtime.getText();
			int i=dataTableFullDisc.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择满折活动", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanFullDisc fulldisc=allFullDisc.get(i);
			
			try {
				FreshMarketUtil.FullDiscManager.modifySystemFullDisc(fulldisc,content, fit_number, disc_discount, start_time, end_time);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadFullDiscTable();
		}
		else if (e.getSource()==this.btncancel) {
			int i=dataTableFullDisc.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择满折活动", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanFullDisc fulldisc=allFullDisc.get(i);
			try {
				FreshMarketUtil.FullDiscManager.deleteSystemFullDisc(fulldisc);
			} catch (BaseException e1) {
				
				e1.printStackTrace();
			}
			this.reloadFullDiscTable();
		}
	}

}
