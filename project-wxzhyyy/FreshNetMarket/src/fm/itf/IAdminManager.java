package fm.itf;

import fm.model.BeanAdmin;
import fm.util.BaseException;

public interface IAdminManager {
	/**
	 * 注册：
	 * 要求用户名任意
	 * 两次输入的密码必须一致，密码不能为空
	 * 如果注册失败，则抛出异常
	 * @param adminid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public BeanAdmin reg(String adminname, String pwd,String pwd2) throws BaseException;
	/**
	 * 登陆
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param adminid
	 * @return
	 * @throws BaseException
	 */
	public BeanAdmin login(String adminname)throws BaseException;
	/**
	 * 修改密码
	 * 如果没有成功修改，则抛出异常
	 * @param admin    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	public void changePwd(BeanAdmin admin, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
}
