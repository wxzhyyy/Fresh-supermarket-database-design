package fm.itf;

import java.util.List;

import fm.model.BeanPromotion;
import fm.util.BaseException;

public interface IPromotionManager {

	/**
	 * 添加限时促销商品
	 * @param comdid
	 * @param promprice
	 * @param promquantity
	 * @param promstarttime
	 * @param promendtion
	 * @throws BaseException
	 */
	public void addPromotion(String comdid, String promprice, String promquantity,String promstarttime,String promendtime) throws BaseException;

	/**
	 * 返回所有促销商品
	 * @return
	 * @throws BaseException
	 */
	public List<BeanPromotion> loadAllPromotions()throws BaseException;

	/**
	 * 删除促销
	 * @param prom
	 */
	public void deletePromotion(BeanPromotion prom) throws BaseException;

	/**
	 * 修改促销商品
	 * @param prom
	 * @param comdid
	 * @param promprice
	 * @param promquantity
	 * @param start_time
	 * @param end_time
	 */
	public void modifyPromotion(BeanPromotion prom, String comdid, String promprice, String promquantity,
			String start_time, String end_time) throws BaseException; 


}
