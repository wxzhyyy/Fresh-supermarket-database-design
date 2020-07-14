package fm.itf;

import java.util.List;

import fm.model.BeanCoupon;
import fm.model.BeanFullDisc;
import fm.util.BaseException;

public interface IFullDiscManager {

	/**
	 * 返回现有的所有满折活动
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFullDisc> loadAllSystemFullDiscs()throws BaseException;
	/**
	 * 管理员添加满折活动
	 * @param discid
	 * @param content
	 * @param number
	 * @param discount
	 * @param starttime
	 * @param endtime
	 * @throws BaseException
	 */
	public void addSystemFullDiscs(String content,String number,String discount,String
			 starttime,String endtime) throws BaseException;

	/**
	 * 删除，管理员才可以
	 * @param fulldisc
	 * @throws BaseException
	 */
	public void deleteSystemFullDisc(BeanFullDisc fulldisc) throws BaseException;
	
	/**
	 * 管理员修改满折活动
	 * @param fulldisc
	 * @param content
	 * @param number
	 * @param discount
	 * @param starttime
	 * @param endtime
	 * @throws BaseException
	 */
	public void modifySystemFullDisc(BeanFullDisc fulldisc,String content,String number,String discount,String
			 starttime,String endtime)throws BaseException;
	
}
