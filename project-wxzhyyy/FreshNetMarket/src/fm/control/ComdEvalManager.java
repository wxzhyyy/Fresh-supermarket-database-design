package fm.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import fm.itf.IComdEvalManager;
import fm.model.BeanComd;
import fm.model.BeanCoupon;
import fm.model.BeanEvaluation;
import fm.util.BaseException;
import fm.util.DBUtil;
import fm.util.DbException;

public class ComdEvalManager implements IComdEvalManager{

	@Override
	public List<BeanEvaluation> loadComdEval(BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanEvaluation> result=new ArrayList<BeanEvaluation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_evaluation where comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getComd_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanEvaluation eval=new BeanEvaluation();
				eval.setComd_id(rs.getString(1));
				eval.setUser_id(rs.getString(2));
				eval.setEval_content(rs.getString(3));
				eval.setEval_time(rs.getTimestamp(4));
				eval.setEval_star(rs.getInt(5));
				result.add(eval);
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

}
