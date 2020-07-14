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
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmMyEvalList extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textcontent;
	private JTextField textstart;
	private JButton btnNewButton = new JButton("\u6DFB\u52A0\u8BC4\u4EF7");
	
	private	JButton btnNewButton_1 = new JButton("\u5220\u9664\u8BC4\u4EF7");
	
	private Object tblBuyComdTitle[]= {"商品编号","商品名称"};
	private Object tblBuyComdData[][];
	DefaultTableModel tabBuyComdModel=new DefaultTableModel();
	private JTable dataTableBuyComd=new JTable(tabBuyComdModel);
	BeanComd comd=null;
	private List<BeanComd> allBuyComd=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMyEvalList frame = new FrmMyEvalList();
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
	public FrmMyEvalList() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1064, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u53EF\u8BC4\u4EF7\u5546\u54C1");
		
		JLabel lblNewLabel_1 = new JLabel("\u8BC4\u4EF7");
		
		JLabel lblNewLabel_2 = new JLabel("\u8BC4\u7EA7");
		
		textcontent = new JTextField();
		textcontent.setColumns(10);
		
		textstart = new JTextField();
		textstart.setColumns(10);
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textcontent, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 790, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(162, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(635, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(textstart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(125))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addGap(108)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textstart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textcontent, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(79))
		);
		
		this.btnNewButton.addActionListener(this);
		this.btnNewButton_1.addActionListener(this);
		scrollPane.setViewportView(dataTableBuyComd);
		contentPane.setLayout(gl_contentPane);
		this.reloadBuyComdTable();
		this.dataTableBuyComd.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMyEvalList.this.dataTableBuyComd.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择评价", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	    	
	    });
	}

	private void reloadBuyComdTable() {
		// TODO Auto-generated method stub
		try {
			allBuyComd=FreshMarketUtil.ComdEvalManager.allbuycomd();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblBuyComdData =  new Object[allBuyComd.size()][tblBuyComdTitle.length];
		for(int i=0;i<allBuyComd.size();i++){
				tblBuyComdData[i][0]=allBuyComd.get(i).getComd_id();
				tblBuyComdData[i][1]=allBuyComd.get(i).getComd_name();
		}
		tabBuyComdModel.setDataVector(tblBuyComdData,tblBuyComdTitle);
		this.dataTableBuyComd.validate();
		this.dataTableBuyComd.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnNewButton) {
			int i=FrmMyEvalList.this.dataTableBuyComd.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择评价", "提示", JOptionPane.ERROR_MESSAGE);
			}
			BeanComd comd=allBuyComd.get(i);
			String content=this.textcontent.getText();
			String star =this.textstart.getText();
			try {
				FreshMarketUtil.ComdEvalManager.addeval(comd, content, star);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
