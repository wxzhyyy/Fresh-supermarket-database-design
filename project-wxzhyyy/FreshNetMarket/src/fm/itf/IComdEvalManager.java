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
}
