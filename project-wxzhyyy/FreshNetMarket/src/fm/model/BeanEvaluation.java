package fm.model;

import java.sql.Date;

public class BeanEvaluation {
	private String comd_id;
	private String user_id;
	private String eval_content;
	private Date eval_time;
	private int  eval_star;
	private byte[] eval_pic;
	
	
	public String getComd_id() {
		return comd_id;
	}
	public void setComd_id(String comd_id) {
		this.comd_id = comd_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEval_content() {
		return eval_content;
	}
	public void setEval_content(String eval_content) {
		this.eval_content = eval_content;
	}
	public Date getEval_time() {
		return eval_time;
	}
	public void setEval_time(Date eval_time) {
		this.eval_time = eval_time;
	}
	public int getEval_star() {
		return eval_star;
	}
	public void setEval_star(int eval_star) {
		this.eval_star = eval_star;
	}
	public byte[] getEval_pic() {
		return eval_pic;
	}
	public void setEval_pic(byte[] eval_pic) {
		this.eval_pic = eval_pic;
	}
}
