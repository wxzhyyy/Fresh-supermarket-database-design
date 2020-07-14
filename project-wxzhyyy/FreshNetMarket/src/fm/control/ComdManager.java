package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fm.itf.IComdManager;
import fm.model.BeanComd;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class ComdManager implements IComdManager {
	/**
	 * 查询所有商品信息
	 */
	public List<BeanComd> loadall() throws BaseException{
		List<BeanComd> result=new ArrayList<BeanComd>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanComd c=new BeanComd();
				c.setComd_id(rs.getString(1));
				c.setCat_id(rs.getString(2));
				c.setPromotion_id(rs.getString(3));
				c.setComd_name(rs.getString(4));
				c.setComd_price(rs.getFloat(5));
				c.setComd_vip_price(rs.getFloat(6));
				c.setComd_quantity(rs.getInt(7));
				c.setComd_specification(rs.getNString(8));
				c.setComd_details(rs.getNString(9));
				result.add(c);
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
	public List<BeanComd> searchComd(String keyboard) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanComd> result=new ArrayList<BeanComd>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity where comd_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+keyboard+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanComd c=new BeanComd();
				c.setComd_id(rs.getString(1));
				c.setCat_id(rs.getString(2));
				c.setComd_name(rs.getString(3));
				c.setComd_price(rs.getFloat(4));
				c.setComd_vip_price(rs.getFloat(5));
				c.setComd_quantity(rs.getInt(6));
				c.setComd_specification(rs.getNString(7));
				c.setComd_details(rs.getNString(8));
				result.add(c);
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
	public List<BeanComd> loadComdCat(String catid) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanComd> result=new ArrayList<BeanComd>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select comd_id,cat_id,promotion_id,comd_name,comd_price,comd_vip_price,comd_quantity,comd_specification,comd_details from commodity where cat_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, catid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanComd c=new BeanComd();
				c.setComd_id(rs.getString(1));
				c.setCat_id(rs.getString(2));
				c.setPromotion_id(rs.getString(3));
				c.setComd_name(rs.getString(4));
				c.setComd_price(rs.getFloat(5));
				c.setComd_vip_price(rs.getFloat(6));
				c.setComd_quantity(rs.getInt(7));
				c.setComd_specification(rs.getNString(8));
				c.setComd_details(rs.getNString(9));
				result.add(c);
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
	public void addcomd(String catid, String comdname, String comdprice, String comdvipprice, String comdquantity) throws BaseException
	 {
		// TODO Auto-generated method stub
		Connection conn=null;
		BeanComd comd=new BeanComd();
		comd.setCat_id(catid);
		comd.setComd_name(comdname);
		float fcomdprice=Float.parseFloat(comdprice);
		float fcomdvipprice=Float.parseFloat(comdvipprice);
		int icomdquantity=Integer.parseInt(comdquantity.trim());
		comd.setComd_price(fcomdprice);
		comd.setComd_vip_price(fcomdvipprice);
		comd.setComd_quantity(icomdquantity);
		try {
			conn=DBUtil.getConnection();
			String sql="select max(comd_id+0) from commodity";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if (rs.getString(1) != null) {
				comd.setComd_id(rs.getString(1));
				int num = Integer.parseInt(comd.getComd_id().trim());
				num = num +1;
				comd.setComd_id(String.valueOf(num));
			}else {
				comd.setComd_id("1");
			}
			sql="insert into commodity(comd_id,cat_id,comd_name,comd_price,comd_vip_price,comd_quantity) value(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getComd_id());
			pst.setString(2, comd.getCat_id());
			pst.setString(3, comd.getComd_name());
			pst.setFloat(4, comd.getComd_price());
			pst.setFloat(5, comd.getComd_vip_price());
			pst.setInt(6, comd.getComd_quantity());
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
	public void modifycomd(BeanComd comd, String catid,String comdname, String comdprice, String comdvipprice, String comdquantity) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		if(catid!=null&&!catid.equals(""))
		comd.setCat_id(catid);
		if(comdname!=null&&!comdname.equals(""))
		comd.setComd_name(comdname);
		if(comdprice!=null&&!comdprice.equals("")) {
			float fcomdprice=Float.parseFloat(comdprice);
			comd.setComd_price(fcomdprice);
		}
		if (comdvipprice!=null&&!comdvipprice.equals("")) {
			float fcomdvipprice=Float.parseFloat(comdvipprice);
			comd.setComd_vip_price(fcomdvipprice);
		}
		if (comdquantity!=null&&!comdquantity.equals("")) {
			int icomdquantity=Integer.parseInt(comdquantity.trim());
			comd.setComd_quantity(icomdquantity);
		}
		
		try {
			conn=DBUtil.getConnection();
			String sql="update commodity set cat_id=?,comd_name=?,comd_price=?,comd_vip_price=?,comd_quantity=? where comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getCat_id());
			pst.setString(2, comd.getComd_name());
			pst.setFloat(3, comd.getComd_price());
			pst.setFloat(4, comd.getComd_vip_price());
			pst.setInt(5,comd.getComd_quantity());
			pst.setString(6, comd.getComd_id());
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
	public void deletecomd(BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from commodity where comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getComd_id());
			pst.execute();
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
	public String searchComdname(String comd_id) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		String comdname=new String();
		try {
			conn=DBUtil.getConnection();
			String sql="select comd_name from commodity where comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setNString(1,comd_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) comdname=rs.getNString(1);
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
		return comdname;
	}
}
