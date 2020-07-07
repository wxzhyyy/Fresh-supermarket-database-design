package fm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmMain extends JFrame {

	private JPanel contentPane;
	private JTable comdtable;

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

	/**
	 * Create the frame.
	 */
	public FrmMain() {
		setTitle("\u751F\u9C9C\u8D85\u5E02");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 575);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu homepage = new JMenu("\u9996\u9875");
		menuBar.add(homepage);
		
		JMenu classifypage = new JMenu("\u5206\u7C7B");
		menuBar.add(classifypage);
		
		JMenu orderpage = new JMenu("\u5546\u54C1\u8BA2\u5355");
		menuBar.add(orderpage);
		
		JMenu mypage = new JMenu("\u6211\u7684");
		menuBar.add(mypage);
		
		JMenu vippage = new JMenu(" V I P");
		mypage.add(vippage);
		
		JMenu rechargepage = new JMenu("\u5145  \u503C");
		mypage.add(rechargepage);
		
		JMenu setpage = new JMenu("\u8BBE  \u7F6E");
		mypage.add(setpage);
		
		JMenu exitpage = new JMenu("\u9000\u51FA\u767B\u5F55");
		mypage.add(exitpage);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 414, 515);
		contentPane.add(scrollPane);
		
		comdtable = new JTable();
		scrollPane.setViewportView(comdtable);
		comdtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5546\u54C1\u540D\u79F0", "\u4EF7   \u683C", "\u4F1A\u5458\u4EF7\u683C", "\u9650\u65F6\u7279\u4EF7"
			}
		));
	}
}
