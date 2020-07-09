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
	public void addComdtoOrder(BeanOrder comdorder, BeanComd comd) throws BaseException;
	/**
	 * 在订单在减少商品
	 * 若商品数量大于1，则数量减一
	 * 若商品数量小于1，则删除记录
	 * @param orderid
	 * @param comdid
	 * @param orderquantity
	 * @throws BaseException
	 */
	public void reduceComdtoOrder(BeanOrder comdorder, BeanComd comd) throws BaseException;
	/**
	 * 计算商品的原价格
	 * @param comdorder
	 * @param comd
	 * @return
	 * @throws BaseException
	 */
//	public float CalOriginalPriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException;
//	/**
//	 * 计算商品优惠后的价格
//	 * @param comdorder
//	 * @param comd
//	 * @return
//	 * @throws BaseException
//	 */
//	public float CalPrivilegePriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException;
//	

	public float CalOriginalPriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException;

	public float CalPrivilegePriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException;
}
