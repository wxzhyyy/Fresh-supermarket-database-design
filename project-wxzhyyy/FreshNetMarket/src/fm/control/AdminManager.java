package fm.control;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fm.itf.IAdminManager;
import fm.model.BeanAdmin;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;

public class AdminManager implements IAdminManager{

	@Override
	public BeanAdmin reg(String adminname, String pwd, String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		
		if (adminname==null) {
			JOptionPane.showInternalMessageDialog(null, "用户名不能为空", "提示", JOptionPane.ERROR_MESSAGE);
			throw new BusinessException("用户名不能为空");
		}
		if(!pwd.equals(pwd2)) {
			JOptionPane.showInternalMessageDialog(null, "两次密码输入不相同", "提示", JOptionPane.ERROR_MESSAGE);
			throw new BusinessException("两次密码输入不相同");
		}
		Connection conn=null;
		BeanAdmin u=new BeanAdmin();
		//System.out.print("1");
		try {
			//System.out.println("2");
			conn=DBUtil.getConnection();
			u.setAdmin_name(adminname);
			u.setAdmin_pwd(pwd);
			String sql = "select * from admin where admin_name = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getAdmin_name());
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                throw new BusinessException("账号以存在，请更换用户名");
            }
			sql="select max(admin_id+0) from admin";
			//System.out.println("3");
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
		//	System.out.println("4");
			rs.next();
			if (rs.getString(1) != null)  {
			//	System.out.println("5");
				u.setAdmin_id(rs.getString(1));
				//System.out.print("5");
				int num = Integer.parseInt(u.getAdmin_id().trim());
				//System.out.print("5");
				num = num +1;
			    u.setAdmin_id(String.valueOf(num));
			   // System.out.println(num);
			}else {
				u.setAdmin_id("1");
			}
			sql="insert into admin(admin_id,admin_name,admin_pwd) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, u.getAdmin_id());
			pst.setString(2, u.getAdmin_name());
			pst.setString(3, u.getAdmin_pwd());
			pst.execute();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return u;
	}

	@Override
	public BeanAdmin login(String adminname) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		BeanAdmin u=new BeanAdmin();
		try {
			conn=DBUtil.getConnection();
			String sql="select admin_id,admin_name,admin_pwd from admin where admin_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString( 1, adminname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())throw new BusinessException("登陆账号不存在");
			u.setAdmin_id(rs.getString(1));
			u.setAdmin_name(rs.getString(2));
			u.setAdmin_pwd(rs.getString(3));
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return u;
	}

	@Override
	public void changePwd(BeanAdmin admin, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
