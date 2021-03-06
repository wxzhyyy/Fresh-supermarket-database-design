package fm.control;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fm.itf.IUesrManager;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;


public class UserManager implements IUesrManager {

	
	@Override
	public BeanUser reg(String username, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		if (username==null) throw new BusinessException("用户名不能为空");
		if(!pwd.equals(pwd2))throw new BusinessException("两次密码输入不相同");
		Connection conn=null;
		BeanUser u=new BeanUser();
		//System.out.print("1");
		try {
			//System.out.println("2");
			conn=DBUtil.getConnection();
			u.setUser_name(username);
			u.setUser_pwd(pwd);
			String sql = "select * from user where user_name = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getUser_name());
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                throw new BusinessException("账号以存在，请更换用户名");
            }
            rs.close();
            pst.close();
			sql="select max(user_id+0) from user";
			//System.out.println("3");
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
		//	System.out.println("4");
			rs.next();
			if (rs.getString(1) != null) {
			//	System.out.println("5");
				u.setUser_id(rs.getString(1));
				//System.out.print("5");
				int num = Integer.parseInt(u.getUser_id().trim());
				//System.out.print("5");
				num = num +1;
			    u.setUser_id(String.valueOf(num));
			   // System.out.println(num);
			}else {
				u.setUser_id("1");
			}
			sql="insert into user(user_id,user_name,user_pwd,user_regtime) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, u.getUser_id());
			pst.setString(2, u.getUser_name());
			pst.setString(3, u.getUser_pwd());
			pst.setTimestamp(4,new java.sql.Timestamp(System.currentTimeMillis()));
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
	public BeanUser login(String username) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		BeanUser u=new BeanUser();
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_pwd from user where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString( 1, username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())throw new BusinessException("登陆账号不存在");
			u.setUser_id(rs.getString(1));
			u.setUser_name(rs.getString(2));
			u.setUser_pwd(rs.getString(3));
			BeanUser.currentLoginUser=u;
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
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		//System.out.print("1");
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		//System.out.print("1");
		if(newPwd==null) throw new BusinessException("新密码不能为空");
		//System.out.print("2");
		//System.out.print(oldPwd);
		//System.out.print("3");
		//System.out.print(user.getUser_pwd());
		if(!oldPwd.equals(user.getUser_pwd())) throw new BusinessException("原始密码错误");
		//System.out.print("1");
		if(!newPwd.equals(newPwd2))throw new BusinessException("两次密码输入不相同");
		Connection conn=null;
	//	System.out.print("1");
		try {
			conn=DBUtil.getConnection();
			String sql="update user set user_pwd=? where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, user.getUser_id());
			pst.execute();
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
	}
	public void changeInfo(BeanUser user, String username, String usersex , String userphone, String usermail, String usercity ) throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update user set user_name=?, user_sex=?, user_phone=?,user_email=?, user_city=? where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, usersex);
			pst.setString(3, userphone);
			pst.setString(4, usermail);
			pst.setString(5, usercity);
			pst.setString(6, user.getUser_id());
			pst.execute();
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
	}
	@Override
	public void beVIP() throws BusinessException {
		// TODO Auto-generated method stub
		if("1".equals(BeanUser.currentLoginUser.getUser_vip())) {
			JOptionPane.showMessageDialog(null, "您已经是会员了", "提示", JOptionPane.WARNING_MESSAGE);
			throw new BusinessException("您已经是会员了");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update user set user_vip=?, user_vip_endtime=date_add(NOW(), interval 1 MONTH) where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "1");
			pst.setString(2, BeanUser.currentLoginUser.getUser_id());
			pst.execute();
			JOptionPane.showMessageDialog(null, "您已成为会员", "提示", JOptionPane.WARNING_MESSAGE);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
	}
		
//	public static void main(String args[]) {
//		UserManager um=new UserManager();
//		try {
//			/*注册函数测试*/
//			um.reg("ckz", "123456", "123456");
//			System.out.print("cheng");
//			/*修改密码函数测试*/
////			BeanUser u=new BeanUser();
////			u.setUser_id("2");
////			u.setUser_pwd("123456");
////			System.out.print("cheng");
////			um.changePwd(u, "123456", "1234567", "1234567");
////			System.out.print("cheng");
////			BeanUser user=new BeanUser();
////			user.setUser_id("2");
////			um.changeInfo(user, "bjt", "男", "19867342243", "1237453d@163.com", "湖州");
////			System.out.print("cheng");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	

}
