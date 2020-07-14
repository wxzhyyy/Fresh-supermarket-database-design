package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fm.model.BeanComd;
import fm.model.BeanPromotion;
import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmPromotion extends JFrame {

	private JPanel contentPane;
	
	
	private Object tblPromotionTitle[]= {"促销编号","商品编号","促销价格","促销商品数量","开始时间","结束时间"};
	private Object tblPromotionData[][];
	DefaultTableModel tabPromotionModel=new DefaultTableModel();
	private JTable dataTablePromotion=new JTable(tabPromotionModel);
	List<BeanPromotion> allPromotion=null;
	BeanPromotion prom=new BeanPromotion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPromotion frame = new FrmPromotion();
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
	public FrmPromotion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(dataTablePromotion);
		contentPane.setLayout(gl_contentPane);
		this.reloadPromotionTable();
		this.dataTablePromotion.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmPromotion.this.dataTablePromotion.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择商品", "提示", JOptionPane.ERROR_MESSAGE);
				}
				BeanPromotion p=allPromotion.get(i);
				BeanComd comd=new BeanComd();
				comd.setComd_id(p.getComd_id());
				BeanComd.currentComd=comd;
				new FrmComdDetails().setVisible(true);
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
}
