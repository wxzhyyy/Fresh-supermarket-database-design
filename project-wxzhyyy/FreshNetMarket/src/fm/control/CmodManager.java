package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fm.itf.ICmodManager;
import fm.model.BeanComd;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class CmodManager implements ICmodManager {
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
}
