package freshmarket;


import fm.control.*;
import fm.itf.*;


public class FreshMarketUtil {
	
	public static IShippingAddressManager ShipAddrManager = new ShippingAddressManager();
	public static IUesrManager userManager=new UserManager();
	public static IComdManager ComdManager=new ComdManager();
	public static ICatManager CatManager=new CatManager();
	public static IOrderDetailManager OrderDetailManager=new OrderDetailManager();
	public static ICouponManager CouponManager=new CouponManager();
	public static IAdminManager AdminManager=new AdminManager();
	public static IFullDiscManager FullDiscManager=new FullDiscManager();
	public static IPromotionManager PromotionManager=new PromotionManager();
	public static IComdEvalManager ComdEvalManager=new ComdEvalManager();
	public static IOrderManager OrderManager=new OrderManager();
}
