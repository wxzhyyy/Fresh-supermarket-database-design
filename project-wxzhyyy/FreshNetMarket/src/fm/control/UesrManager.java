package fm.control;

import java.sql.Connection;
import java.sql.SQLException;

import fm.itf.IUesrManager;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;


public class UesrManager implements IUesrManager {

	@Override
	public BeanUser reg(String username, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		if (username==null) throw new BusinessException("用户名不能为空");
		if(!pwd.equals(pwd2))throw new BusinessException("两次密码输入不相同");
		Connection conn=null;
		BeanUser u=new BeanUser();
		//System.out.print("1");
		try {
			//System.out.print("2");
			conn=DBUtil.getConnection();
			u.setUser_name(username);
			u.setUser_pwd(pwd);
			String sql="select max(user_id) from user";
			//System.out.print("3");
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			//System.out.print("4");
			if (rs.next()) {
				//System.out.print("5");
				u.setUser_id(rs.getString(1));
				//System.out.print("5");
				int num = Integer.parseInt(u.getUser_id().trim());
				//System.out.print("5");
				num = num +1;
			    u.setUser_id(String.valueOf(num));
			   // System.out.print("5");
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
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		BeanUser u=new BeanUser();
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_pwd from user where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString( 1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())throw new BusinessException("登陆账号不存在");
			
			rs.close();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return u;
	}

	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		System.out.print("1");
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		System.out.print("1");
		if(newPwd==null) throw new BusinessException("新密码不能为空");
		System.out.print("2");
		if(!oldPwd.equals(user.getUser_pwd())) throw new BusinessException("原始密码错误");
		System.out.print("1");
		if(!newPwd.equals(newPwd2))throw new BusinessException("两次密码输入不相同");
		Connection conn=null;
		System.out.print("1");
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
	
	public static void main(String args[]) {
		UesrManager um=new UesrManager();
		try {
			/*注册函数测试*/
//			um.reg("ckz", "123456", "123456");
//			System.out.print("cheng");
			/*修改密码函数测试*/
//			BeanUser u=new BeanUser();
//			u.setUser_id("2");
//			u.setUser_pwd("123456");
//			System.out.print("cheng");
//			um.changePwd(u, "123456", "1234567", "1234567");
//			System.out.print("cheng");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
