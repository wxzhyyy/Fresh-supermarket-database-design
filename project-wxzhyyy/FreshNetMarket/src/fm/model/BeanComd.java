package fm.model;

public class BeanComd {
	
	private String comd_id;
	private String cat_id;
	private String promotion_id;
	private String comd_name;
	private float comd_price;
	private float comd_vip_price;
	private int comd_quantity;
	private String comd_specification;
	private String comd_details;
	
	public static BeanComd currentComd=null;
	
	
	public String getComd_id() {
		return comd_id;
	}
	public void setComd_id(String comd_id) {
		this.comd_id = comd_id;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(String promotion_id) {
		this.promotion_id = promotion_id;
	}
	public String getComd_name() {
		return comd_name;
	}
	public void setComd_name(String comd_name) {
		this.comd_name = comd_name;
	}
	public float getComd_price() {
		return comd_price;
	}
	public void setComd_price(float comd_price) {
		this.comd_price = comd_price;
	}
	public float getComd_vip_price() {
		return comd_vip_price;
	}
	public void setComd_vip_price(float comd_vip_price) {
		this.comd_vip_price = comd_vip_price;
	}
	public int getComd_quantity() {
		return comd_quantity;
	}
	public void setComd_quantity(int comd_quantity) {
		this.comd_quantity = comd_quantity;
	}
	public String getComd_specification() {
		return comd_specification;
	}
	public void setComd_specification(String comd_specification) {
		this.comd_specification = comd_specification;
	}
	public String getComd_details() {
		return comd_details;
	}
	public void setComd_details(String comd_details) {
		this.comd_details = comd_details;
	}
	public Object getCell(int col) {
		// TODO Auto-generated method stub
		if(col==0) return this.comd_id;
		else if(col==1) return this.comd_name;
		else if(col==2) return this.comd_price;
		else if(col==3) return this.comd_vip_price;
		else if(col==4) return this.comd_quantity;
		else if(col==5) return this.cat_id;
		else if(col==6) return this.promotion_id;
		else return "";
	}
	
}
