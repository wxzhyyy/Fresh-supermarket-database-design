package fm.model;

import fm.util.BaseException;
import freshmarket.FreshMarketUtil;

public class BeanOrderDetails {
	private String order_id;
	private String comd_id;
	private int order_quantity;
	private float order_price;
	private float order_discount;
	private String disc_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getComd_id() {
		return comd_id;
	}
	public void setComd_id(String comd_id) {
		this.comd_id = comd_id;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public float getOrder_price() {
		return order_price;
	}
	public void setOrder_price(float order_price) {
		this.order_price = order_price;
	}
	public float getOrder_discount() {
		return order_discount;
	}
	public void setOrder_discount(float order_discount) {
		this.order_discount = order_discount;
	}
	public String getDisc_id() {
		return disc_id;
	}
	public void setDisc_id(String disc_id) {
		this.disc_id = disc_id;
	}
	public Object getCell(int col) throws BaseException {
		// TODO Auto-generated method stub
		if(col==0) return this.comd_id; 
		else if(col==1) return FreshMarketUtil.ComdManager.searchComdname(comd_id);
		else if(col==2) return this.order_price;
		else if(col==3) return this.order_quantity;
		else return "";
	}
	
}
