package fm.itf;

import fm.model.BeanUser;
import fm.util.BaseException;
import fm.util.BusinessException;

public interface IUesrManager {
	/**
	 * 注册：
	 * 要求用户名任意
	 * 两次输入的密码必须一致，密码不能为空
	 * 如果注册失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public BeanUser reg(String username, String pwd,String pwd2) throws BaseException;
	/**
	 * 登陆
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public BeanUser login(String username)throws BaseException;
	/**
	 * 修改密码
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
	/**
	 * 修改个人信息
	 * 修改用户名、性别、电话、邮箱、城市；
	 * @param user
	 * @param udername
	 * @param usersex
	 * @param userphone
	 * @param usercity
	 * @throws BaseException
	 */
	public void beVIP() throws BusinessException;
	
	
	
	public void changeInfo(BeanUser user, String username, String usersex, String userphone, String usermail, String usercity ) throws BaseException;

}
