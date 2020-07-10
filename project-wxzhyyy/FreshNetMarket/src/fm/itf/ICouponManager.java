package fm.itf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import fm.model.BeanCoupon;
import fm.model.BeanUser;
import fm.util.BaseException;

public interface ICouponManager {

	/**
	 * 返回系统所有可领取的优惠券
	 * 要求优惠券的结束时间晚于当前时间
	 * @return
	 * @throws BaseException
	 */
	public List<BeanCoupon> loadAllSystemCoupons() throws BaseException;
	/**
	 * 返回该用户所有拥有的优惠券
	 * 过滤用户所有已过期的优惠券
	 * @param u
	 * @return
	 * @throws BaseException
	 */
	public List<BeanCoupon> loadAllUserCoupons(BeanUser u) throws BaseException;
	/**
	 * z管理员增加优惠券
	 * 输入优惠券的简介，适用价格，优惠券优惠额，起止时间
	 * @param couponcontent
	 * @param couponfitmoney
	 * @param couponprice
	 * @param couponstarttime
	 * @param couponendtime
	 * @throws BaseException
	 */
	
	public void addSystemCoupons(String couponcontent, float couponfitmoney, float couponprice,String couponstarttime, String couponendtime) throws BaseException;
/**
 * 管理员删除系统优惠券
 * 还有被领取但未使用的不能被删除
 * @param coupon
 * @throws BaseException
 */
	public void deleteSystemCoupons(BeanCoupon coupon) throws BaseException;
	/**
	 * 
	 * @param coupon
	 * @param couponcontent
	 * @param couponfitmoney
	 * @param couponprice
	 * @param couponstarttime
	 * @param couponendtime
	 * @throws BaseException
	 */
	public void modifySystemCoupons(BeanCoupon coupon,String couponcontent, float couponfitmoney, float couponprice,String couponstarttime, String couponendtime)throws BaseException;
	
	/**
	 * 用户选择获得优惠券
	 * 相同编号的优惠券只能获得一次
	 * 用户已使用的优惠券会在user_coupon表中被删除
	 * 用户已使用的的优惠券无法再次领取（查询商品订单表）
	 * @param user
	 * @param coupon
	 * @throws BaseException
	 */
	public void addUserCoupons(BeanUser user, BeanCoupon coupon) throws BaseException;
	/**
	 * 用户在订单中确认使用优惠券后该优惠券在user_coupon就会被删除
	 * @param user
	 * @param coupon
	 * @throws BaseException
	 */
	public void useUserCoupon(BeanUser user, BeanCoupon coupon) throws BaseException;
}
