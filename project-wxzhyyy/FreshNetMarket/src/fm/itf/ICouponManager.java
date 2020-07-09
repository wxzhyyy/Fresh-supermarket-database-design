package fm.itf;

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
	
	public void addSystemCoupons(String couponcontent, float couponfitmoney, float couponprice,Date couponstarttime, Date couponendtime) throws BaseException;
}
