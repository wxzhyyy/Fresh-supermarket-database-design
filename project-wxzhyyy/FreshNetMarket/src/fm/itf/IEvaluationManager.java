package fm.itf;

import java.util.List;

import fm.model.BeanEvaluation;
import fm.util.BaseException;

public interface IEvaluationManager {

	/**
	 * 返回一个商品的所有评价
	 * @param comdid
	 * @return
	 * @throws BaseException
	 */
	public List<BeanEvaluation> loadEvalComd(String comdid)throws BaseException;
	
	/**
	 * 返回一个用户做出的所有评价
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public List<BeanEvaluation> loadEvalUser(String userid) throws BaseException;
	
	
	/**
	 * 添加评价
	 * @param userid
	 * @param comdid
	 * @param evalcontent
	 * @param evalstar
	 * @throws BaseException
	 */
	public void addComdEval(String userid, String comdid, String evalcontent, int evalstar) throws BaseException;

	/**
	 * 删除评价
	 * 管理员或者发出评价的人可执行该操作
	 * @param eva
	 * @throws BaseException
	 */
	public void deleteComdEval(BeanEvaluation eva) throws BaseException;
	/**
	 * 修改评价
	 * 只有发出评价的用户可执行
	 * @param eva
	 * @param evalcontent
	 * @param evalstar
	 * @throws BaseException
	 */
	public void modifyComdEval(BeanEvaluation eva, String evalcontent, int evalstar) throws BaseException;
	
	
}
