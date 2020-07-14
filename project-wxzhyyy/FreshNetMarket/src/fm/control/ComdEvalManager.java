package fm.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Connection;

import fm.itf.IComdEvalManager;
import fm.model.BeanComd;
import fm.model.BeanCoupon;
import fm.model.BeanEvaluation;
import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;
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

	@Override
	public List<BeanComd> allbuycomd() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanComd> result=new ArrayList<BeanComd>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select comd_id, comd_name from commodity where comd_id in(select comd_id from order_details WHERE order_id in (SELECT order_id FROM commodity_order WHERE user_id=?))";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanComd comd=new BeanComd();
				comd.setComd_id(rs.getString(1));
				comd.setComd_name(rs.getString(2));
				result.add(comd);
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
	public void addeval(BeanComd comd, String content, String star) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		int istar = Integer.parseInt(star.trim());
		try {
			conn=DBUtil.getConnection();
			String sql="select comd_id from commodity_evaluation where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
				JOptionPane.showMessageDialog(null, "已评价过", "提示", JOptionPane.WARNING_MESSAGE);
				throw new BusinessException("已评价过");
			}
			}
			
			sql="insert into commodity_evaluation(comd_id,user_id,eval_content,eval_star,eval_time)value(?,?,?,?,now()) ";
			pst=conn.prepareStatement(sql);
			pst.setString(1, comd.getComd_id());
			pst.setString(2, BeanUser.currentLoginUser.getUser_id());
			pst.setString(3,content);
			pst.setInt(4, istar);
			if(pst.execute()==true) {
				JOptionPane.showMessageDialog(null, "评价成功", "提示", JOptionPane.WARNING_MESSAGE);
			}
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
