package fm.model;

import java.util.Date;

public class BeanPromotion {
	private String promotion_id;
	private String comd_id;
	private float prom_price;
	private int prom_quantity;
	private Date prom_start_time;
	private Date prom_end_time;
	public String getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(String promotion_id) {
		this.promotion_id = promotion_id;
	}
	public String getComd_id() {
		return comd_id;
	}
	public void setComd_id(String comd_id) {
		this.comd_id = comd_id;
	}
	public float getProm_price() {
		return prom_price;
	}
	public void setProm_price(float prom_price) {
		this.prom_price = prom_price;
	}
	public int getProm_quantity() {
		return prom_quantity;
	}
	public void setProm_quantity(int prom_quantity) {
		this.prom_quantity = prom_quantity;
	}
	public Date getProm_start_time() {
		return prom_start_time;
	}
	public void setProm_start_time(Date prom_start_time) {
		this.prom_start_time = prom_start_time;
	}
	public Date getProm_end_time() {
		return prom_end_time;
	}
	public void setProm_end_time(Date prom_end_time) {
		this.prom_end_time = prom_end_time;
	}
	public Object getCell(int col) {
		if(col==0) return this.promotion_id;
		else if(col==1) return this.comd_id;
		else if(col==2) return this.prom_price;
		else if(col==3) return this.prom_quantity;
		else if(col==4) return this.prom_start_time;
		else if(col==5) return this.prom_end_time;
		else return "";
	}
}
