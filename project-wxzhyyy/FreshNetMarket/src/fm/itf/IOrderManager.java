package fm.itf;

import java.util.List;

import fm.model.BeanCoupon;
import fm.model.BeanOrder;
import fm.model.BeanShippingAddress;
import fm.model.BeanUser;
import fm.util.BaseException;

public interface IOrderManager {
	/**
	 * 返回该用户的当前订单（未提交，做购物车使用）
	 * 寻找该用户最后一个订单
	 * 判断最后一个订单状态
	 * 若最后一个订单有状态，则新建订单
	 * 若无状态则更新该订单，并将它设为当前订单
	 * @param user
	 * @return
	 * @throws BaseException
	 */
	public BeanOrder loadlastOrder()throws BaseException;
	
	/**
	 * 返回当前用户的所有订单
	 * @return
	 * @throws BaseException
	 */
	public List<BeanOrder> loadallOrder()throws BaseException;
	/**
	 * 订单状态下一步
	 * @param or
	 * @throws BaseException
	 */
	public void nextStatus(BeanOrder or)throws BaseException;
	/**
	 * 退单
	 * @param or
	 * @throws BaseException
	 */
	public void backOrder(BeanOrder or)throws BaseException;
	
	/**
	 * 创建订单，确认下单
	 * @return 
	 * @throws BaseException
	 */
	public BeanOrder creatOrder() throws BaseException;
	
	/**
	 * 选择订单的地址
	 * @param or
	 * @param sa
	 * @throws BaseException
	 */
	public void addOrderAddr(BeanOrder or, BeanShippingAddress sa) throws BaseException;
	
	/**
	 * 选择可用优惠券
	 * @param or
	 * @param cp
	 * @throws BaseException
	 */
	public void addOrderCoupon(BeanOrder or,BeanCoupon cp) throws BaseException;
	
	/**
	 * 计算总金额
	 * @param or
	 * @return
	 * @throws BaseException
	 */
	public String OriginalPrice(BeanOrder or) throws BaseException;
	
	/**
	 * 计算实际需支付
	 * @param or
	 * @return
	 * @throws BaseException
	 */
	public String ActualPrice(BeanOrder or)throws BaseException;
	/**
	 * 
	 * @param or
	 * @throws BaseException
	 */
	public void OrderSubmit(BeanOrder or)throws BaseException;
	
	
	
}
