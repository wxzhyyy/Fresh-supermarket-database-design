package fm.itf;
 
import fm.util.BaseException;

import java.util.List;

import fm.model.BeanComd;

public interface ICmodManager {
	/**
	 * 返回所有商品
	 * @return
	 * @throws BaseException
	 */
	public List<BeanComd> loadall() throws BaseException;
}
