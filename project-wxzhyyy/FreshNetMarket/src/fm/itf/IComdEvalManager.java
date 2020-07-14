package fm.itf;

import java.util.List;

import fm.model.BeanComd;
import fm.model.BeanEvaluation;
import fm.util.BaseException;

public interface IComdEvalManager {
	/**
	 * 一个商品收获的评价
	 * @param comd
	 * @return
	 * @throws BaseException
	 */
	public List<BeanEvaluation> loadComdEval(BeanComd comd)throws BaseException;
	
	/**
	 * 加载所有购买过的商品
	 * @return
	 * @throws BaseException
	 */
	public List<BeanComd> allbuycomd() throws BaseException;
	
	/**
	 * 添加评价
	 * @param comd
	 * @param content
	 * @param star
	 * @throws BaseException
	 */
	public void addeval(BeanComd comd, String content,String star) throws BaseException;
	
	
}
