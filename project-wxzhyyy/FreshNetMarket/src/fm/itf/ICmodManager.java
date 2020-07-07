package fm.itf;

import antlr.collections.List;
import fm.util.BaseException;
import fm.model.BeanComd;

public interface ICmodManager {
	
	public List<BeanComd> loadall() throws BaseException;
}
