package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fm.itf.IFullDiscManager;
import fm.model.BeanCoupon;
import fm.model.BeanFullDisc;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class FullDiscManager implements IFullDiscManager{

	@Override
	public List<BeanFullDisc> loadAllSystemFullDiscs() throws BaseException {
		// TODO Auto-generated method stub
		List< BeanFullDisc > result=new ArrayList<BeanFullDisc>();
		Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from full_discount where disc_end_time > now()";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanFullDisc cp=new BeanFullDisc();
				cp.setDisc_id(rs.getString(1));
				cp.setDisc_content(rs.getString(2));
				cp.setDisc_fitnumber(rs.getInt(3));
				cp.setDisc_discount(rs.getFloat(4));
				cp.setDisc_start_time(rs.getTimestamp(5));
				cp.setDisc_end_time(rs.getTimestamp(6));
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
	public void addSystemFullDiscs(String content, String number, String discount, String starttime,
			String endtime) throws BaseException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
				java.util.Date date1 = null;
				java.util.Date date2 = null;
				try {
					date1 = sdf.parse( starttime );
					//System.out.print(date1);
					date2 = sdf.parse( endtime );
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				long ls = date1.getTime();
//				System.out.print(ls);
//				Timestamp t=new Timestamp(ls);
//				System.out.print(t);
				long le = date2.getTime();
				BeanFullDisc cp=new BeanFullDisc();
				Connection conn=null;
				cp.setDisc_content(content);
				int fitnumber=Integer.parseInt(number.trim());
				cp.setDisc_fitnumber(fitnumber);
				float disc_discount=Float.parseFloat(discount);
				//System.out.println(coupon_fitmoney);
				cp.setDisc_discount(disc_discount);
				try {
					conn=DBUtil.getConnection();
					String sql="select max(disc_id+0) from full_discount";
					java.sql.PreparedStatement pst=conn.prepareStatement(sql);
					java.sql.ResultSet rs=pst.executeQuery();
					rs.next();
					if (rs.getString(1) != null) {
						cp.setDisc_id(rs.getString(1));
						int num = Integer.parseInt(cp.getDisc_id().trim());
						num = num +1;
						cp.setDisc_id(String.valueOf(num));
					}else {
						cp.setDisc_id("1");
					}
					sql="insert into full_discount(disc_id,disc_content,disc_fitnumber,disc_discount,disc_start_time, disc_end_time) "
							+ "value(?,?,?,?,?,?)";
					pst=conn.prepareStatement(sql);
					pst.setString(1, cp.getDisc_id());
					pst.setString(2, cp.getDisc_content());
					pst.setInt(3, cp.getDisc_fitnumber());
					pst.setFloat(4, cp.getDisc_discount());
					pst.setTimestamp(5, new java.sql.Timestamp( ls ));
					pst.setTimestamp(6, new java.sql.Timestamp( le ));
					//pst.setDate(5, new java.sql.Date( ls ));
//					pst.setDate(6, new java.sql.Date(le));
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
	public void deleteSystemFullDisc(BeanFullDisc fulldisc) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from disc_ass where disc_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, fulldisc.getDisc_id());
			pst.execute();
			sql="delete from full_discount where disc_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, fulldisc.getDisc_id());
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
	public void modifySystemFullDisc(BeanFullDisc fulldisc, String content, String number, String discount,
			String starttime, String endtime) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		BeanFullDisc cp=new BeanFullDisc();
		cp.setDisc_content(content);
		int fitnumber=Integer.parseInt(number.trim());
		cp.setDisc_fitnumber(fitnumber);
		float disc_discount=Float.parseFloat(discount);
		//System.out.println(discount);
		cp.setDisc_discount(disc_discount);
		try {
			date1 = sdf.parse( starttime );
			//System.out.print(date1);
			date2 = sdf.parse( endtime );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long ls = date1.getTime();
		//System.out.print(ls);
//		Timestamp t=new Timestamp(ls);
//		System.out.print(t);
		long le = date2.getTime();
		try {
			conn=DBUtil.getConnection();
			String sql="update full_discount set disc_content=?,disc_fitnumber=?,disc_discount=?,"
					+ "disc_start_time=?, disc_end_time=? where disc_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//System.out.println(cp.getDisc_id());
			pst.setString(1, cp.getDisc_content());
			pst.setInt(2, cp.getDisc_fitnumber());
			pst.setFloat(3, cp.getDisc_discount());
			pst.setTimestamp(4, new java.sql.Timestamp( ls ));
			pst.setTimestamp(5, new java.sql.Timestamp( le ));
			pst.setString(6, fulldisc.getDisc_id());
			pst.execute();
			//System.out.println("3");
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
		FullDiscManager fdm=new FullDiscManager();
		BeanFullDisc fd=new BeanFullDisc();
		fd.setDisc_id("1");
		try {
			
			fdm.modifySystemFullDisc(fd, "ÂúÈý¼þÆßÕÛ", "3", "7", "2020-07-20 12:45:00", "2020-07-20 12:45:00");
		} catch (BaseException e1) {
			
			e1.printStackTrace();
		}
	}
}
