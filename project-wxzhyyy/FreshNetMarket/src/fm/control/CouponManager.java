package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fm.itf.ICouponManager;
import fm.model.BeanCoupon;
import fm.model.BeanOrder;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;

public class CouponManager implements ICouponManager{

	@Override
	public List<BeanCoupon> loadAllSystemCoupons() throws BaseException {
		// TODO Auto-generated method stub
		List< BeanCoupon > result=new ArrayList<BeanCoupon>();
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from coupon where coupon_end_time > now()";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCoupon cp=new BeanCoupon();
				cp.setCoupon_id(rs.getString(1));
				cp.setCoupon_content(rs.getString(2));
				cp.setCoupon_fit_money(rs.getFloat(3));
				cp.setCoupon_price(rs.getFloat(4));
				cp.setCoupon_start_time(rs.getTimestamp(5));
				cp.setCoupon_end_time(rs.getTimestamp(6));
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
	public List<BeanCoupon> loadAllUserCoupons(BeanUser u) throws BaseException {
		// TODO Auto-generated method stub
		List< BeanCoupon > result=new ArrayList<BeanCoupon>();
		
		Connection conn=null;
		try {
		
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon WHERE coupon_id in"
					+ "(SELECT coupon_id FROM user_coupon WHERE user_id=?) "
					+ "and  coupon_end_time > now()";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				BeanCoupon cp=new BeanCoupon();
				cp.setCoupon_id(rs.getString(1));
				cp.setCoupon_content(rs.getString(2));
				cp.setCoupon_fit_money(rs.getFloat(3));
				cp.setCoupon_price(rs.getFloat(4));
				cp.setCoupon_start_time(rs.getTimestamp(5));
				cp.setCoupon_end_time(rs.getTimestamp(6));
				result.add(cp);
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
	public void addSystemCoupons(String couponcontent, String fitprice, String couponprice, String couponstarttime,
			String couponendtime) throws BaseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		try {
			date1 = sdf.parse( couponstarttime );
			//System.out.print(date1);
			date2 = sdf.parse( couponendtime );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long ls = date1.getTime();
//		System.out.print(ls);
//		Timestamp t=new Timestamp(ls);
//		System.out.print(t);
		long le = date2.getTime();
		BeanCoupon cp=new BeanCoupon();
		Connection conn=null;
		cp.setCoupon_content(couponcontent);
		float coupon_fitmoney=Float.parseFloat(fitprice);
		cp.setCoupon_fit_money(coupon_fitmoney);
		float coupon_price=Float.parseFloat(couponprice);
		//System.out.println(coupon_fitmoney);
		cp.setCoupon_price(coupon_price);
		try {
			conn=DBUtil.getConnection();
			String sql="select max(coupon_id+0) from coupon";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if (rs.getString(1) != null) {
				cp.setCoupon_id(rs.getString(1));
				int num = Integer.parseInt(cp.getCoupon_id().trim());
				num = num +1;
				cp.setCoupon_id(String.valueOf(num));
			}else {
				cp.setCoupon_id("1");
			}
			sql="insert into coupon(coupon_id,coupon_content,coupon_fit_money,coupon_price,coupon_start_time, coupon_end_time) "
					+ "value(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, cp.getCoupon_id());
			pst.setString(2, cp.getCoupon_content());
			pst.setFloat(3, cp.getCoupon_fit_money());
			pst.setFloat(4, cp.getCoupon_price());
			pst.setTimestamp(6, new java.sql.Timestamp( ls ));
			pst.setTimestamp(5, new java.sql.Timestamp( le ));
			//pst.setDate(5, new java.sql.Date( ls ));
//			pst.setDate(6, new java.sql.Date(le));
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
	public void addUserCoupons(BeanUser user, BeanCoupon coupon) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_coupon where coupon_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, coupon.getCoupon_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,  "该优惠券您已领取","提示",JOptionPane.ERROR_MESSAGE);
				throw new BaseException("该优惠券您已领取");
			}
			//System.out.print("1.1");
			sql="select * from commodity_order where user_id=? and coupon_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
			pst.setString(2, coupon.getCoupon_id());
			rs=pst.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,  "该优惠券您已领取","提示",JOptionPane.ERROR_MESSAGE);
				throw new BaseException("该优惠券您已领取");
			};
			sql="insert into user_coupon(user_id,coupon_id) value(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
			pst.setString(2, coupon.getCoupon_id());
			pst.execute();
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
	public void deleteSystemCoupons(BeanCoupon coupon) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from user_coupon where coupon_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, coupon.getCoupon_id());
			pst.execute();
			sql="delete from coupon where coupon_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, coupon.getCoupon_id());
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
	public void modifySystemCoupons(BeanCoupon coupon,String couponcontent, String fitprice, String couponprice,String couponstarttime, String couponendtime) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		BeanCoupon cp=new BeanCoupon();
		cp.setCoupon_content(couponcontent);
		float coupon_fitmoney=Float.parseFloat(fitprice);
		cp.setCoupon_fit_money(coupon_fitmoney);
		float coupon_price=Float.parseFloat(couponprice);
		//System.out.println(coupon_fitmoney);
		cp.setCoupon_price(coupon_price);
		try {
			date1 = sdf.parse( couponstarttime );
			//System.out.print(date1);
			date2 = sdf.parse( couponendtime );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long ls = date1.getTime();
//		System.out.print(ls);
//		Timestamp t=new Timestamp(ls);
//		System.out.print(t);
		long le = date2.getTime();
		try {
			conn=DBUtil.getConnection();
			String sql="update coupon set coupon_content=?,coupon_fit_money=?,coupon_price=?,"
					+ "coupon_start_time=?, coupon_end_time=? where coupon_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, cp.getCoupon_content());
			pst.setFloat(2, cp.getCoupon_fit_money());
			pst.setFloat(3, cp.getCoupon_price());
			pst.setTimestamp(4, new java.sql.Timestamp( ls ));
			pst.setTimestamp(5, new java.sql.Timestamp( le ));
			pst.setString(6, coupon.getCoupon_id());
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
	public void useUserCoupon(BeanUser user, BeanOrder or) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from user_coupon where user_id=? and coupon_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
			pst.setString(2, or.getCoupon_id());
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
	
//	public static void main(String args[]) {
//		CouponManager cpm=new CouponManager();
//		try {
//			
//		//	cpm.addSystemCoupons("满35减6元", 35, 6, "2020-07-10 09:34:10", "2020-07-17 09:30:10");
//
//			/**
//			 * loadall测试
//			 */
//			List<BeanCoupon> result=new ArrayList<BeanCoupon>();
//			result=cpm.loadAllSystemCoupons();
//			for (int i = 0; i < result.size(); i++) {
//				System.out.print(result.get(i).getCoupon_id());
//				System.out.print(result.get(i).getCoupon_content());
//				System.out.print(result.get(i).getCoupon_start_time());
//				
//			}
//			/*测试addusercoupon*/
//			BeanUser user=new BeanUser();
//			BeanCoupon coupon=new BeanCoupon();
//			user.setUser_id("1");
//			coupon.setCoupon_id("3");
////			System.out.print("1");
////			cpm.addUserCoupons(user, coupon);
////			System.out.print("2");
//			/*delete*/
//		//	cpm.deleteSystemCoupons(coupon);
//			
//			//cpm.modifySystemCoupons(coupon,"满35减6元", 35, 6, "2020-07-10 09:34:10", "2020-07-17 19:30:10");
//			cpm.useUserCoupon(user, coupon);
//			System.out.print("1");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	
}
