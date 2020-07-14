package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fm.itf.IOrderManager;
import fm.model.BeanCoupon;
import fm.model.BeanOrder;
import fm.model.BeanOrderDetails;
import fm.model.BeanShippingAddress;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;
import freshmarket.FreshMarketUtil;

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

	@Override
	public void addOrderAddr(BeanOrder or, BeanShippingAddress sa) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update commodity_order set addr_id=? where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, sa.getAddr_id());
			pst.setString(2, or.getOrder_id());
			pst.execute();
			BeanOrder.cartOrder.setCoupon_id(sa.getAddr_id());
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
	public void addOrderCoupon(BeanOrder or, BeanCoupon cp) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			if(cp.getCoupon_fit_money()>or.getOriginal_price()) {
				JOptionPane.showMessageDialog(null, "该优惠券不适用", "提示", JOptionPane.WARNING_MESSAGE);
				throw new BusinessException("该优惠券不适用");
			}
			System.out.println(or.getCoupon_id());
			if(or.getCoupon_id()!=null) {
				JOptionPane.showMessageDialog(null, "您已选择过优惠券", "提示", JOptionPane.WARNING_MESSAGE);
				throw new BusinessException("您已选择过优惠券");
			}
			float actualprice=or.getActual_price()-cp.getCoupon_price();
			or.setActual_price(actualprice);
			String sql="update commodity_order set coupon_id=?, actual_price=? where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, cp.getCoupon_id());
			pst.setFloat(2, actualprice);
			pst.setString(3, or.getOrder_id());
			pst.execute();
			BeanOrder.cartOrder.setCoupon_id(cp.getCoupon_id());
			BeanOrder.cartOrder.setActual_price(actualprice);
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
	public String OriginalPrice(BeanOrder or) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanOrderDetails> comdlist=new ArrayList<BeanOrderDetails>();
		Connection conn=null;
		float originalprice = 0;
		String soriginalprice=null;
		comdlist=FreshMarketUtil.OrderDetailManager.loadAll(or);
		for (int i = 0; i < comdlist.size(); i++) {
			BeanOrderDetails ord=comdlist.get(i);
			float comdprice=0;
			try {
				conn=DBUtil.getConnection();
				String sql="select comd_price from commodity where comd_id=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, ord.getComd_id());
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					comdprice=rs.getFloat(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			float ordprice=comdprice*ord.getOrder_quantity();
			originalprice=originalprice+ordprice;
		}
		try {
			conn=DBUtil.getConnection();
			String sql="update commodity_order set original_price=? where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setFloat(1, originalprice);
			pst.setString(2, or.getOrder_id());
			pst.execute();
			BeanOrder.cartOrder.setOriginal_price(originalprice);
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
		soriginalprice=Float.toString(originalprice);
		return soriginalprice;
	}

	@Override
	public String ActualPrice(BeanOrder or) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanOrderDetails> comdlist=new ArrayList<BeanOrderDetails>();
		Connection conn=null;
		float actualprice = 0;
		String sactualprice=null;
		comdlist=FreshMarketUtil.OrderDetailManager.loadAll(or);
		for (int i = 0; i < comdlist.size(); i++) {
			BeanOrderDetails ord=comdlist.get(i);
			float ordprice=ord.getOrder_price()*ord.getOrder_quantity();
			actualprice=actualprice+ordprice;
		}
		
		try {
			conn=DBUtil.getConnection();
			String sql="select coupon_id from commodity_order where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, or.getOrder_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				float couponprice=0;
				BeanOrder.cartOrder.setCoupon_id(rs.getString(1));
				sql="select coupon_price from coupon where coupon_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, BeanOrder.cartOrder.getCoupon_id());
				rs=pst.executeQuery();
				if(rs.next()) {
					couponprice=rs.getFloat(1);
				}
				actualprice=actualprice-couponprice;
			}
			sql="update commodity_order set actual_price=? where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setFloat(1, actualprice);
			pst.setString(2, or.getOrder_id());
			pst.execute();
			BeanOrder.cartOrder.setActual_price(actualprice);
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
		sactualprice=Float.toString(actualprice);
		return sactualprice;
	}

	@Override
	public void OrderSubmit(BeanOrder or) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			
			String sql="select addr_id from commodity_order where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, or.getOrder_id());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getString(1) == null) {
				JOptionPane.showMessageDialog(null, "请选择地址", "提示", JOptionPane.WARNING_MESSAGE);
				throw new BusinessException("请选择地址");
			}
			sql="update commodity_order set order_status=? where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, "下单");
			pst.setString(2, or.getOrder_id());
			pst.execute();
			JOptionPane.showMessageDialog(null, "下单成功", "提示", JOptionPane.WARNING_MESSAGE);
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
	
}
