package fm.ui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fm.control.AdminManager;
import fm.control.UserManager;
import fm.model.BeanAdmin;
import fm.model.BeanOrder;
import fm.model.BeanUser;
import fm.util.BaseException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;


public class FrmLogin extends JDialog implements ActionListener{
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmLogin frame = new FrmLogin("µ«¬º",true);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnLogin = new Button("µ«¬Ω");
	private Button btnCancel = new Button("ÕÀ≥ˆ");
	private JLabel labelUser = new JLabel("\u8D26  \u53F7\uFF1A");
	private JLabel labelPwd = new JLabel("\u5BC6  \u7801\uFF1A");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private final Button btnregister = new Button("◊¢≤·");
	private	JRadioButton rdbtnAdmin = new JRadioButton("π‹¿Ì‘±");
	private	JRadioButton rdbtnUser = new JRadioButton("”√ªß");
	ButtonGroup group=new ButtonGroup();
	String usertype=null;
	
	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		setTitle("…˙œ ≥¨ –");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnLogin);
		toolBar.add(btnregister);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		
		
		group.add(rdbtnAdmin);
		group.add(rdbtnUser);
		GroupLayout gl_workPane = new GroupLayout(workPane);
		gl_workPane.setHorizontalGroup(
			gl_workPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_workPane.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_workPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_workPane.createSequentialGroup()
							.addComponent(rdbtnAdmin, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(rdbtnUser, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_workPane.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_workPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(labelUser, Alignment.LEADING)
								.addComponent(labelPwd, Alignment.LEADING))
							.addGap(35)
							.addGroup(gl_workPane.createParallelGroup(Alignment.LEADING)
								.addComponent(edtPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(edtUserId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_workPane.setVerticalGroup(
			gl_workPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_workPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_workPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAdmin)
						.addComponent(rdbtnUser))
					.addGap(18)
					.addGroup(gl_workPane.createParallelGroup(Alignment.LEADING)
						.addComponent(labelUser)
						.addComponent(edtUserId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_workPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPwd)
						.addComponent(edtPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		workPane.setLayout(gl_workPane);
		this.setSize(439, 281);
		// ∆¡ƒªæ”÷–œ‘ æ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		btnregister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			if(this.rdbtnUser.isSelected()) {
				usertype="user";
				UserManager um=new UserManager();
				String username=this.edtUserId.getText();
				String pwd=new String(this.edtPwd.getPassword());
				try {
					BeanUser user=um.login(username);
					if(pwd.equals(user.getUser_pwd())){
						BeanUser.currentLoginUser=user;
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,  "√‹¬Î¥ÌŒÛ","¥ÌŒÛÃ· æ",JOptionPane.ERROR_MESSAGE);
					}
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛÃ· æ",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			if (this.rdbtnAdmin.isSelected()) {
				usertype="admin";
				AdminManager um=new AdminManager();
				String username=this.edtUserId.getText();
				String pwd=new String(this.edtPwd.getPassword());
				try {
					BeanAdmin user=um.login(username);
					if(pwd.equals(user.getAdmin_pwd())){
						BeanAdmin.currentLoginAdmin=user;
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,  "√‹¬Î¥ÌŒÛ","¥ÌŒÛÃ· æ",JOptionPane.ERROR_MESSAGE);
					}
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛÃ· æ",JOptionPane.ERROR_MESSAGE);
				}
				FrmAdminMain dlg=new FrmAdminMain();
				dlg.setVisible(true);
			}
		}
		else if (e.getSource()==this.btnregister) {
				if(this.rdbtnUser.isSelected()) {
					usertype="user";
					FrmRegister frg=new FrmRegister(this,"µ«¬º",true,usertype);
					frg.setVisible(true);
				}
				if(this.rdbtnAdmin.isSelected()){
					usertype="admin";
					FrmRegister frg=new FrmRegister(this,"µ«¬º",true,usertype);
					frg.setVisible(true);
				}
				
			}
			else if (e.getSource() == this.btnCancel) {
				System.exit(0);
			}
		
	}
}
