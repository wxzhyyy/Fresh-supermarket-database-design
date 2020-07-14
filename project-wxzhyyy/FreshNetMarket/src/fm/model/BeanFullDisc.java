package fm.model;

import java.util.Date;

public class BeanFullDisc {
	private String disc_id;
	private String disc_content;
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
	public String getDisc_content() {
		return disc_content;
	}
	public void setDisc_content(String disc_content) {
		this.disc_content = disc_content;
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
	public Object getAdminCell(int col) {
		// TODO Auto-generated method stub
		if(col==0) return this.disc_id;
		else if(col==1) return this.disc_content;
		else if(col==2) return this.disc_fitnumber;
		else if(col==3) return this.disc_discount;
		else if(col==4) return this.disc_start_time;
		else if(col==5) return this.disc_end_time;
		else return "";
	}

	
}
