package fm.itf;

import java.util.List;

import fm.model.BeanCat;
import fm.util.BaseException;

public interface ICatManager {

	/**
	 * 添加分类
	 * 判断类别名称是否重复，若已存在则无法添加
	 * @param catname
	 * @param catdescribe
	 * @throws BaseException
	 */
	public void addCat(String catname, String catdescribe) throws BaseException;
	
	/**
	 * 删除类型
	 * 如果该类别下还存在商品则无法删除
	 * @param cat
	 * @throws BaseException
	 */
	public void deleteCat(BeanCat cat) throws BaseException;
	
	/**
	 * 修改种类信息
	 * 判断种类名称是否已存在，已存在不可更改
	 * @param cat
	 * @param catname
	 * @param catdescribe
	 * @throws BaseException
	 */
	public void modifyCat(BeanCat cat, String catname, String catdescribe) throws BaseException;
	/**
	 * 返回所有类别
	 * @return
	 * @throws BaseException
	 */
	public List<BeanCat> loadallCat() throws BaseException;
}
