package fm.control;

import java.util.List;

import fm.itf.IOrderDetailManager;
import fm.model.BeanComd;
import fm.model.BeanOrder;
import fm.model.BeanOrderDetails;
import fm.util.BaseException;

public class OrderDetailManager implements IOrderDetailManager{

	@Override
	public List<BeanOrderDetails> loadAll(BeanOrder comdorder) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComdtoOrder(BeanOrder comdorder, BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reduceComdtoOrder(BeanOrder comdorder, BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float CalOriginalPriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float CalPrivilegePriceofComd(BeanOrder comdorder, BeanComd comd) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

}
