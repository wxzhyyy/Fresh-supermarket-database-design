package fm.model;

import java.sql.Date;

public class BeanCoupon {
	private String coupon_id;
	private String coupon_content;
	private float coupon_fit_money;
	private float coupon_price;
	private Date coupon_start_time;
	
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_content() {
		return coupon_content;
	}
	public void setCoupon_content(String coupon_content) {
		this.coupon_content = coupon_content;
	}
	public float getCoupon_fit_money() {
		return coupon_fit_money;
	}
	public void setCoupon_fit_money(float coupon_fit_money) {
		this.coupon_fit_money = coupon_fit_money;
	}
	public float getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(float coupon_price) {
		this.coupon_price = coupon_price;
	}
	public Date getCoupon_start_time() {
		return coupon_start_time;
	}
	public void setCoupon_start_time(Date coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}
	
}
