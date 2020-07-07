package fm.model;

import java.sql.Date;

public class BeanFullDisc {
	private String disc_id;
	private String user_id;
	private int disc_fitnumber;
	private float disc_discount;
	private Date disc_start_time;
	private Date disc_end_time;
	public String getDisc_id() {
		return disc_id;
	}
	public void setDisc_id(String disc_id) {
		this.disc_id = disc_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getDisc_fitnumber() {
		return disc_fitnumber;
	}
	public void setDisc_fitnumber(int disc_fitnumber) {
		this.disc_fitnumber = disc_fitnumber;
	}
	public float getDisc_discount() {
		return disc_discount;
	}
	public void setDisc_discount(float disc_discount) {
		this.disc_discount = disc_discount;
	}
	public Date getDisc_start_time() {
		return disc_start_time;
	}
	public void setDisc_start_time(Date disc_start_time) {
		this.disc_start_time = disc_start_time;
	}
	public Date getDisc_end_time() {
		return disc_end_time;
	}
	public void setDisc_end_time(Date disc_end_time) {
		this.disc_end_time = disc_end_time;
	}
	
}
