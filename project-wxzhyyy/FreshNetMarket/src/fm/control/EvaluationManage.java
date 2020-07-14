package fm.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fm.itf.IEvaluationManager;
import fm.model.BeanEvaluation;
import fm.model.BeanShippingAddress;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;

public class EvaluationManage implements IEvaluationManager{

	@Override
	public List<BeanEvaluation> loadEvalComd(String comdid) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanEvaluation> result=new ArrayList<BeanEvaluation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_evaluation where comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, comdid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanEvaluation eval=new BeanEvaluation();
				eval.setComd_id(rs.getString(1));
				eval.setUser_id(rs.getString(2));
				eval.setEval_content(rs.getString(3));
				eval.setEval_time(rs.getTimestamp(4));
				eval.setEval_star(rs.getInt(5));
				eval.setEval_pic(rs.getBytes(6));
				result.add(eval);
				
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
	public List<BeanEvaluation> loadEvalUser(String userid) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanEvaluation> result=new ArrayList<BeanEvaluation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_evaluation where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanEvaluation eval=new BeanEvaluation();
				eval.setComd_id(rs.getString(1));
				eval.setUser_id(rs.getString(2));
				eval.setEval_content(rs.getString(3));
				eval.setEval_time(rs.getTimestamp(4));
				eval.setEval_star(rs.getInt(5));
				eval.setEval_pic(rs.getBytes(6));
				result.add(eval);
				
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
	public void addComdEval(String userid, String comdid, String evalcontent, int evalstar) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_evaluation where user_id=? and comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, comdid);
			java.sql.ResultSet rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("您已经评论过该商品了");
			sql="insert into commodity_evaluation(user_id,comd_id,eval_content,eval_time,eval_star) value(?,?,?,now(),?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, comdid);
			pst.setString(3,evalcontent);
			pst.setInt(4, evalstar);
			pst.execute();
			pst.close();
		}  catch (SQLException e) {
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
	public void deleteComdEval(BeanEvaluation eva) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from commodity_evaluation where user_id=? and comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, eva.getUser_id());
			pst.setString(2, eva.getComd_id());
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
	public void modifyComdEval(BeanEvaluation eva, String evalcontent, int evalstar) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update commodity_evaluation set eval_content=?,eval_time=now(),eval_star=? where user_id=? and comd_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1,evalcontent);
			pst.setInt(2, evalstar);
			pst.setString(3, eva.getUser_id());
			pst.setString(4, eva.getComd_id());
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
	
	public static void main(String args[]) {
		EvaluationManage em=new EvaluationManage();
		BeanEvaluation eval=new BeanEvaluation();
		eval.setUser_id("1");
		eval.setComd_id("1");
		try {
			List<BeanEvaluation> result=new ArrayList<BeanEvaluation>();

			em.modifyComdEval(eval, "下次再来", 5);
			String comdid="1";
			result=em.loadEvalComd(comdid);
			for (int i = 0; i < result.size(); i++) {
				System.out.print(result.get(i).getEval_content());
				System.out.print(result.get(i).getEval_star());
				System.out.print(result.get(i).getEval_time());
				
			}
			
			//em.addComdEval("1", "1", "好吃", 4);
			//em.deleteComdEval(eval);
			//em.modifyComdEval(eval, "下次再来", 5);
			System.out.print("dd");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
