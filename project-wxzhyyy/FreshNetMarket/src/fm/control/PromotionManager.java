package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fm.itf.IPromotionManager;
import fm.model.BeanCoupon;
import fm.model.BeanPromotion;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class PromotionManager implements IPromotionManager{

	@Override
	public void addPromotion(String comdid, String promprice, String promquantity, String promstarttime,
			String promendtime) throws BaseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		try {
			date1 = sdf.parse( promstarttime );
			//System.out.print(date1);
			date2 = sdf.parse( promendtime );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long ls = date1.getTime();
//		System.out.print(ls);
//		Timestamp t=new Timestamp(ls);
//		System.out.print(t);
		long le = date2.getTime();
		BeanPromotion p=new BeanPromotion();
		Connection conn=null;
		p.setComd_id(comdid);
		float fpromprice=Float.parseFloat(promprice);
		p.setProm_price(fpromprice);
		int ipromquantity=Integer.parseInt(promquantity);
		//System.out.println(coupon_fitmoney);
		p.setProm_quantity(ipromquantity);
		try {
			conn=DBUtil.getConnection();
			String sql="select max(promotion_id+0) from promotion";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if (rs.getString(1) != null) {
				p.setPromotion_id(rs.getString(1));
				int num = Integer.parseInt(p.getPromotion_id().trim());
				num = num +1;
				p.setPromotion_id(String.valueOf(num));
			}else {
				p.setPromotion_id("1");
			}
			sql="insert into promotion(promotion_id,comd_id,prom_price,prom_quantity,prom_start_time,prom_end_time) "
					+ "value(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, p.getPromotion_id());
			pst.setString(2, p.getComd_id());
			pst.setFloat(3, p.getProm_price());
			pst.setInt(4, p.getProm_quantity());
			pst.setTimestamp(5, new java.sql.Timestamp( ls ));
			pst.setTimestamp(6, new java.sql.Timestamp( le ));
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
	public List<BeanPromotion> loadAllPromotions() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanPromotion> result=new ArrayList<BeanPromotion>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from promotion where prom_end_time > now()";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanPromotion p=new BeanPromotion();
				p.setPromotion_id(rs.getString(1));
				p.setComd_id(rs.getString(2));
				p.setProm_price(rs.getFloat(3));
				p.setProm_quantity(rs.getInt(4));
				p.setProm_end_time(rs.getTimestamp(5));
				p.setProm_end_time(rs.getTimestamp(6));
				result.add(p);
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
	public void deletePromotion(BeanPromotion prom) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from promotion where promotion_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, prom.getPromotion_id());
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
	public void modifyPromotion(BeanPromotion prom, String comdid, String promprice, String promquantity,
			String start_time, String end_time) throws BaseException{
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		try {
			date1 = sdf.parse( start_time );
			date2 = sdf.parse( end_time );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long ls = date1.getTime();
		long le = date2.getTime();
		Connection conn=null;
		if(comdid!=null&&!comdid.equals(""))
		prom.setComd_id(comdid);
		if(promprice!=null&&!promprice.equals("")) {
			float fpromprice=Float.parseFloat(promprice);
		prom.setProm_price(fpromprice);
		}
		if(promquantity!=null&&!promquantity.equals("")) {
			int ipromquantity=Integer.parseInt(promquantity);
		//System.out.println(coupon_fitmoney);
		prom.setProm_quantity(ipromquantity);
		}
		
		try {
			conn=DBUtil.getConnection();
			String sql="update promotion set comd_id=?,prom_price=?,prom_quantity=?,prom_start_time=?, prom_end_time=? where promotion_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(6, prom.getPromotion_id());
			pst.setString(1, prom.getComd_id());
			pst.setFloat(2, prom.getProm_price());
			pst.setInt(3, prom.getProm_quantity());
			pst.setTimestamp(4, new java.sql.Timestamp( ls ));
			pst.setTimestamp(5, new java.sql.Timestamp( le ));
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

}
