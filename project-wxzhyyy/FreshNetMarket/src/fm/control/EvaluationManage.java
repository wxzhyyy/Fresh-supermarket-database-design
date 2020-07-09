package fm.control;

import java.util.List;

import fm.itf.IEvaluationManager;
import fm.model.BeanEvaluation;
import fm.util.BaseException;

public class EvaluationManage implements IEvaluationManager{

	@Override
	public List<BeanEvaluation> loadEvalComd(String comdid) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BeanEvaluation> loadEvalUser(String userid) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComdEval(String userid, String comdid, String evalcontent, String evalstar) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComdEval(BeanEvaluation eva) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyComdEval(BeanEvaluation eva, String evalcontent, String evalstar) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
