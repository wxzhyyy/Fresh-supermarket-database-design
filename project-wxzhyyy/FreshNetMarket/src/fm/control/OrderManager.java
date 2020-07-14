package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fm.itf.IOrderManager;
import fm.model.BeanOrder;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;

public class OrderManager implements IOrderManager {

	@Override
	public BeanOrder loadlastOrder() throws BaseException {
		Connection conn=null;
		BeanOrder or=new BeanOrder();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_order where order_id=(SELECT max(order_id+0) from commodity_order where user_id=?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				or.setOrder_id(rs.getString(1));
				or.setUser_id(rs.getString(2));
				or.setOriginal_price(rs.getFloat(3));
				or.setActual_price(rs.getFloat(4));
				or.setCoupon_id(rs.getString(5));
				or.setRequired_delivery_time(rs.getDate(6));
				or.setAddr_id(rs.getString(7));
				or.setOrder_status(rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return or;
	}

	@Override
	public List<BeanOrder> loadallOrder() throws BaseException {
		// TODO Auto-generated method stub
		List< BeanOrder > result=new ArrayList<BeanOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_order where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrder cp=new BeanOrder();
				cp.setOrder_id(rs.getString(1));
				cp.setOriginal_price(rs.getFloat(3));
				cp.setActual_price(rs.getFloat(4));
				cp.setCoupon_id(rs.getString(5));
				cp.setRequired_delivery_time(rs.getTimestamp(6));
				cp.setAddr_id(rs.getString(7));
				cp.setOrder_status(rs.getString(8));
				result.add(cp);
			}
			
			return result;
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
	public void nextStatus(BeanOrder or) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		if(or.getOrder_status().equals("送达")) {
			JOptionPane.showMessageDialog(null, "商品已送达", "提示", JOptionPane.WARNING_MESSAGE);
			throw new BusinessException("商品已送达");
			
		}
		if(or.getOrder_status().equals("退货")) {
			JOptionPane.showMessageDialog(null, "您已退货", "提示", JOptionPane.WARNING_MESSAGE);
			throw new BusinessException("您已退货");
		}
		String next_status=null;
		if(or.getOrder_status().equals("下单")) {
			next_status="配送";
		}
		if(or.getOrder_status().equals("配送")) {
			next_status="送达";
		}
		try {
			conn=DBUtil.getConnection();
			String sql="update commodity_order set order_status=? where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, next_status);
			pst.setString(2, or.getOrder_id());
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
	public void backOrder(BeanOrder or) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String back_status=null;
		if(or.getOrder_status().equals("退货")) {
			JOptionPane.showMessageDialog(null, "您已退货", "提示", JOptionPane.WARNING_MESSAGE);
			throw new BusinessException("您已退货");
			
		}else if(or.getOrder_status().equals("")) {
			JOptionPane.showMessageDialog(null, "订单未下单", "提示", JOptionPane.WARNING_MESSAGE);
			throw new BusinessException("订单未下单");
			
		}
		else {
			back_status="退货";
		}
		
		
		try {
			conn=DBUtil.getConnection();
			String sql="update commodity_order set order_status=? where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, back_status);
			pst.setString(2, or.getOrder_id());
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
	public BeanOrder creatOrder() throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		BeanUser user=BeanUser.currentLoginUser;
		BeanOrder or=new BeanOrder();
		try {
			conn=DBUtil.getConnection();
			String sql="select max(order_id+0) from commodity_order";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if (rs.getString(1) != null) {
				or.setOrder_id(rs.getString(1));
				int num = Integer.parseInt(or.getOrder_id().trim());
				num = num +1;
				or.setOrder_id(String.valueOf(num));
			}else {
				or.setOrder_id("1");
			}
			sql="insert into commodity_order(order_id,user_id)value(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, or.getOrder_id());
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
		return or;
	}
	
}
