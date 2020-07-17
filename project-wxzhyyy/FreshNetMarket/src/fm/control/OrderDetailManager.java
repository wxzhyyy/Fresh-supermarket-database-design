package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fm.itf.IOrderDetailManager;
import fm.model.BeanComd;
import fm.model.BeanOrder;
import fm.model.BeanOrderDetails;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class OrderDetailManager implements IOrderDetailManager{

	@Override
	public List<BeanOrderDetails> loadAll(BeanOrder comdorder) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanOrderDetails> result=new ArrayList<BeanOrderDetails>();
		Connection conn=null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="select * from order_details where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, comdorder.getOrder_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrderDetails od=new BeanOrderDetails();
				od.setOrder_id(rs.getString(1));
				od.setComd_id(rs.getString(2));
				od.setOrder_quantity(rs.getInt(3));
				od.setOrder_price(rs.getFloat(4));
				od.setOrder_discount(rs.getFloat(5));
				od.setDisc_id(rs.getString(6));
				result.add(od);
				
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

	@SuppressWarnings("resource")
	@Override
	public void addComdtoOrder(BeanOrder comdorder, BeanComd comd, int num) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		int currentnum=0;
		float orderdiscount=1;
		String discid=null;
		try {
			conn=DBUtil.getConnection();
			
			
			String sql="select sum(order_quantity) from order_details where disc_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, comdorder.getOrder_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if (rs.next()) {
				currentnum=rs.getInt(1);
			}
			//查找商品满足的满折id
			sql="select disc_id from disc_ass where comd_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getComd_id());
			rs=pst.executeQuery();
			while(rs.next()) {
				discid=rs.getString(1);
			}
			//查找该满折需要满足的条件以及折扣
			sql="select disc_fitnumber,disc_discount from full_discount where disc_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, discid);
			rs=pst.executeQuery();
			int discfitnumber = 0;
			float discdiscount = 0;
			while(rs.next()) {
				discfitnumber=rs.getInt(1);
				discdiscount = rs.getFloat(2);
			}
			
			
			float orderpeice=comd.getComd_price();
			sql="select user_vip from user where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setNString(1, BeanUser.currentLoginUser.getUser_id());
			rs=pst.executeQuery();
			rs.next();
			if(rs.getString(1) != null) {
				BeanUser.currentLoginUser.setUser_vip(rs.getString(1));
				if(BeanUser.currentLoginUser.getUser_vip().equals("1")) {
				orderpeice=comd.getComd_vip_price();
			}
			}
		
			sql="select prom_price from promotion where comd_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getPromotion_id());
			rs=pst.executeQuery();
			while(rs.next()) {
				orderpeice=rs.getFloat(1);
			}
	
			
			//查找订单中已有的商品数量
			sql="select order_quantity from order_details where order_id=? and comd_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, comdorder.getOrder_id());
			pst.setString(2, comd.getComd_id());
			//System.out.println("12");
			rs=pst.executeQuery();
			//System.out.println("12");
			if(rs.next()) {
				currentnum=currentnum+num-rs.getInt(1);
				if(currentnum>=discfitnumber) {
					orderdiscount=discdiscount;
				}
				sql="update order_details set order_quantity=?,order_price=?, order_discount=?,disc_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, num);
				pst.setFloat(2, orderpeice);
				pst.setFloat(3, orderdiscount);
				pst.setString(4, discid);
				pst.execute();
				rs.close();
				pst.close();
			}else {

				//System.out.println("12else");
				currentnum=num;
				if(currentnum>=discfitnumber) {
					orderdiscount=discdiscount;
				}
				sql="insert into order_details(order_id,comd_id,order_quantity,order_price,order_discount,disc_id)value(?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);	
				pst.setString(1, comdorder.getOrder_id());
				pst.setString(2, comd.getComd_id());
				pst.setInt(3, num);
				pst.setFloat(4, orderpeice);
				pst.setFloat(5, orderdiscount);
				pst.setString(6, discid);
			//	System.out.println("12");
				pst.execute();
			//	System.out.println("else");
				pst.close();
			}
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
	}
	@Override
	public void deleteComdtoOrder(BeanOrderDetails od) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from order_details where order_id=? and comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, od.getOrder_id());
			pst.setString(2, od.getComd_id());
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
	public void modifyComdOrder(BeanOrderDetails od, int num) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update order_details set order_quantity=? where order_id=? and comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setString(2, od.getOrder_id());
			pst.setString(3, od.getComd_id());
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
	public float CalOriginalPriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float CalPrivilegePriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}
//	
//	public static void main(String args[]) throws BaseException {
//		OrderDetailManager odm=new OrderDetailManager();
//		BeanComd comd=new BeanComd();
//		BeanOrder comdorder=new BeanOrder();
//		BeanOrderDetails od=new BeanOrderDetails();
//		String comdid="1";
//		od.setOrder_id("0");
//		od.setComd_id("1");
//		comd.setComd_id("1");
//		int num=9;
////		comdorder.setOrder_id("1");
////		odm.addComdtoOrder(comdorder, comd, 2);
////		System.out.print("1");
////		odm.deleteComdtoOrder(comdid);
//		odm.modifyComdOrder(od, num);
//		System.out.print(od.getOrder_quantity());
//	}

	

}
