package fm.itf;

import java.util.List;

import fm.model.BeanComd;
import fm.model.BeanOrder;
import fm.model.BeanOrderDetails;
import fm.util.BaseException;

public interface IOrderDetailManager {

	/**
	 * 订单明细
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public List<BeanOrderDetails> loadAll(BeanOrder comdorder) throws BaseException;
	
	/**
	 * 在订单里增加商品
	 * 若商品已存在则数量加一
	 * 若不存在，则增加一条记录
	 * @param orderid
	 * @param comdid
	 * @param orderquantity
	 * @throws BaseException
	 */
	public void addComdtoOrder(BeanOrder comdorder, BeanComd comd,int num) throws BaseException;

	/**
	 * 删除订单详情中的商品
	 * @param od
	 * @throws BaseException
	 */
	public void deleteComdtoOrder(BeanOrderDetails od)throws BaseException;

	public float CalOriginalPriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException;

	public float CalPrivilegePriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException;
	/**
	 *修改购买商品的数量
	 * @param od
	 * @param num
	 * @throws BaseException
	 */
	public void modifyComdOrder(BeanOrderDetails od,int num) throws BaseException;

}
