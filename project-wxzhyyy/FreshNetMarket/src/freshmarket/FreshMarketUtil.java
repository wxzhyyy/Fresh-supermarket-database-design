package freshmarket;

import fm.control.CmodManager;
import fm.control.UserManager;
import fm.itf.ICmodManager;
import fm.itf.IUesrManager;

public class FreshMarketUtil {
	public static IUesrManager userManager=new UserManager();
	public static ICmodManager cmodManager=new CmodManager();
}
