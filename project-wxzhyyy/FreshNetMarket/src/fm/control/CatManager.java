package fm.control;

import java.sql.Connection;
import java.sql.SQLException;

import fm.itf.ICatManager;
import fm.model.BeanCat;
import fm.util.BaseException;
import fm.util.BusinessException;
import fm.util.DBUtil;
import fm.util.DbException;

public class CatManager implements ICatManager{

	@Override
	public void addCat(String catname, String catdescribe) throws BaseException {
		// TODO Auto-generated method stub
		BeanCat cat=new BeanCat();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from category where cat_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, catname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该类别已存在");
			System.out.print(1);
			sql="select max(cat_id) from category";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs.next()) {
				cat.setCat_id(rs.getString(1));
				int num=Integer.parseInt(cat.getCat_id().trim());
				num=num+1;
				cat.setCat_id(String.valueOf(num));
				System.out.print(num);
			}else {
				cat.setCat_id("1");
			}
			
			sql="insert into category(cat_id,cat_name,cat_describe) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString( 1, cat.getCat_id());
			pst.setString(2, catname);
			pst.setString(3, catdescribe);
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
	public void deleteCat(BeanCat cat) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			//判断该分类中有无商品
			String sql="select * from commodity where cat_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, cat.getCat_id());
			System.out.print(cat.getCat_id());
			java.sql.ResultSet rs=pst.executeQuery();
			System.out.print(rs.next());
			if (rs.next())throw new BusinessException("该分类中存在商品，无法删除");
			sql="delete from category where cat_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, cat.getCat_id());
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
	public void modifyCat(BeanCat cat, String catname, String catdescribe) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from category where cat_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, catname);
			java.sql.ResultSet rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("该类别已存在，无法修改");
			sql="update category set cat_name=?, cat_describe=? where cat_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, catname);
			pst.setString(2, catdescribe);
			pst.setString(3, cat.getCat_id());
			pst.executeUpdate();
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
	public static void main(String args[]){
		CatManager cm=new CatManager();
		try {
			BeanCat cat=new BeanCat();
			cat.setCat_id("3");
			/* 添加分类函数测试 */
//			cm.addCat("冰淇淋","可可可可可爱多");
			/*删除分类函数测试*/
			
			//cm.deleteCat(cat);
			/*修改分类函数测试*/
//			cm.modifyCat(cat, "水果", "鲜牛奶");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
