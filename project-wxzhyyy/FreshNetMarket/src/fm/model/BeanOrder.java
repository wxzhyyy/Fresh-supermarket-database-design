package fm.model;

import java.sql.Date;

public class BeanOrder {
	private String order_id;
	private String user_id;
	private float original_price;
	private float actual_price;
	private String coupon_id;
	private Date required_delivery_time;
	private String addr_id;
	private String order_status;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public float getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(float original_price) {
		this.original_price = original_price;
	}
	public float getActual_price() {
		return actual_price;
	}
	public void setActual_price(float actual_price) {
		this.actual_price = actual_price;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public Date getRequired_delivery_time() {
		return required_delivery_time;
	}
	public void setRequired_delivery_time(Date required_delivery_time) {
		this.required_delivery_time = required_delivery_time;
	}
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	
}
