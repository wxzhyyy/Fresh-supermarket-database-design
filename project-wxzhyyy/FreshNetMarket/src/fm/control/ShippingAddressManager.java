package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fm.itf.IShippingAddressManager;
import fm.model.BeanShippingAddress;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class ShippingAddressManager implements IShippingAddressManager{

	@Override
	public List<BeanShippingAddress> loadAll(String userid) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanShippingAddress> result=new ArrayList<BeanShippingAddress>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from shipping_address";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanShippingAddress sa=new BeanShippingAddress();
				sa.setUser_id(rs.getString(1));
				sa.setAddr_id(rs.getString(2));
				sa.setProvince(rs.getString(3));
				sa.setCity(rs.getString(4));
				sa.setCell(rs.getString(5));
				sa.setAddress(rs.getString(6));
				sa.setLinkman(rs.getString(7));
				sa.setPhone(rs.getString(8));
				result.add(sa);
				
			}
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
		
		return result;
	}

	@Override
	public void addShippingAddress(String userid, String province, String city, String cell, String address, String linkman, String phone) throws BaseException {
		// TODO Auto-generated method stub
		BeanShippingAddress sa=new BeanShippingAddress();
		sa.setUser_id(userid);
		sa.setAddress(address);
		sa.setCell(cell);
		sa.setCity(city);
		sa.setLinkman(linkman);
		sa.setPhone(phone);
		sa.setProvince(province);
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select max(addr_id) from shipping_address where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if (rs.next()) {
				sa.setAddr_id(rs.getString(1));
				int num = Integer.parseInt(sa.getAddr_id().trim());
				num = num +1;
				sa.setAddr_id(String.valueOf(num));
			}else {
				sa.setAddr_id("1");
			}
			//System.out.print(sa.getAddr_id());
			sql="insert into shipping_address(addr_id,user_id,province,city,cell,address,linkman,phone) value(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, sa.getAddr_id());
			pst.setString(2, sa.getUser_id());
			pst.setString(3, sa.getProvince());
			pst.setString(4, sa.getCity());
			pst.setString(5, sa.getCell());
			pst.setString(6, sa.getAddress());
			pst.setString(7, sa.getLinkman());
			pst.setString(8, sa.getPhone());
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
	public void delectShippingAddress(BeanShippingAddress sa) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from shipping_address where user_id=? and addr_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, sa.getUser_id());
			pst.setString(2, sa.getAddr_id());
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
	public void modifyShippingAddress(BeanShippingAddress sa, String province, String city, String cell, String address, String linkman, String phone) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update shipping_address set province=?, city=?,cell=?, address=?, linkman=?, phone=? where user_id=? and addr_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, province);
			pst.setString(2, city);
			pst.setString(3, cell);
			pst.setString(4, address);
			pst.setString(5, linkman);
			pst.setString(6, phone);
			pst.setString(7, sa.getUser_id());
			pst.setString(8, sa.getAddr_id());
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
		ShippingAddressManager sam=new ShippingAddressManager();
		try {
			/**
			 * loadall测试
			 */
//			List<BeanShippingAddress> result=new ArrayList<BeanShippingAddress>();
//			result=sam.loadAll("1");
//			for (int i = 0; i < result.size(); i++) {
//				System.out.print(result.get(i));
//			}
			/**
			 * 添加地址函数测试
			 */
			BeanShippingAddress sa=new BeanShippingAddress();
//			sam.addShippingAddress("1", "浙江", "建德", "李家", "山合", "小周", "19216548735");
//			System.out.print("cheng");
			/**
			 * 修改地址函数
			 */
			sa.setUser_id("1");
			sa.setAddr_id("2");
//			sam.modifyShippingAddress(sa, "浙江", "杭州", "建德", "大同", "小猪", "15628356547");
//			System.out.print("cheng");
			sam.delectShippingAddress(sa);
			System.out.print("cheng");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
