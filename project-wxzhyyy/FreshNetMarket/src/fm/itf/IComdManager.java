package fm.itf;
 
import fm.util.BaseException;

import java.util.List;

import fm.model.BeanComd;

public interface IComdManager {
	/**
	 * 返回所有商品
	 * @return
	 * @throws BaseException
	 */
	public List<BeanComd> loadall() throws BaseException;
	
	/**
	 * 搜索商品
	 * 模糊搜索，根据关键词
	 * 搜索框里写：请输入商品名称
	 * @param keyboard
	 * @return
	 * @throws BaseException
	 */
	public List<BeanComd> searchComd(String keyboard) throws BaseException;
	
	/**
	 * 返回商品名称
	 * @param comd_id
	 * @return
	 * @throws BaseException
	 */
	public String searchComdname(String comd_id) throws BaseException;
	
	/**
	 * 搜索某一种类别的所有商品
	 * @param catid
	 * @return
	 * @throws BaseException
	 */
	public List<BeanComd> loadComdCat(String catid) throws BaseException;
	
	
	
	/**
	 * 添加商品
	 * 管理员执行
	 * 判断商品名称是否重复，若有重复则无法添加
	 * @param catid
	 * @param comdname
	 * @param comdprice
	 * @param comdvipprice
	 * @param comdquantity
	 * @param comdspecif
	 * @param comddetail
	 * @throws BaseException
	 */
	public void addcomd(String catid,String comdname, String comdprice, String comdvipprice, String comdquantity) throws BaseException;
	
	/**
	 * 修改商品信息
	 * 管理员
	 * @param comdname
	 * @param comdprice
	 * @param comdvipprice
	 * @param comdquantity
	 * @param comdspecif
	 * @param comddetail
	 * @throws BaseException
	 */
	public void modifycomd(BeanComd comd,String catid,String comdname, String comdprice, String comdvipprice, String comdquantity) throws BaseException;
	
	/**
	 * 删除商品
	 * 若商品数量不为0，发出警告，确定后可删除
	 * @param comdname
	 * @param comdprice
	 * @param comdvipprice
	 * @param comdquantity
	 * @param comdspecif
	 * @param comddetail
	 * @throws BaseException
	 */
	public void deletecomd(BeanComd comd) throws BaseException;
	
	
}
