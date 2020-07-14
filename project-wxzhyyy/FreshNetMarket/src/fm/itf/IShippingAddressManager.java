package fm.itf;

import java.util.List;

import fm.model.BeanShippingAddress;
import fm.util.BaseException;

public interface IShippingAddressManager {
	

	/**
	 * 加载所有当前用户对应的配送地址
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public List<BeanShippingAddress> loadAll(String userid) throws BaseException;
	/**
	 * 添加配送地址
	 * @param userid
	 * @throws BaseException
	 */
	public void addShippingAddress(String userid, String province, String city, String cell, String address, String linkman, String phone) throws BaseException;
	
	/**
	 * 删除配送地址
	 * @param userid
	 * @throws BaseException
	 */
	public void deleteShippingAddress(BeanShippingAddress sa) throws BaseException;
	/**
	 * 修改配送地址
	 * @param userid
	 * @throws BaseException
	 */
	public void modifyShippingAddress(BeanShippingAddress sa, String province, String city, String cell, String address, String linkman, String phone) throws BaseException;
}
