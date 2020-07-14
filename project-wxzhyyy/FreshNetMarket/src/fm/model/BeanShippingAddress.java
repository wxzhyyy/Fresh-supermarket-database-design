package fm.model;

public class BeanShippingAddress {
	public static BeanShippingAddress currentShippingAddress=null;
	private String addr_id;
	private String user_id;
	private String province;
	private String city;
	private String cell;
	private String address;
	private String linkman;
	private String phone;
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Object getCell(int col) {
		// TODO Auto-generated method stub
		if (col==0) return this.addr_id;
		else if(col==1) return this.province;
		else if(col==2) return this.city;
		else if(col==3) return this.cell; 
		else if(col==4) return this.address;
		else if(col==5) return this.linkman ;
		else if(col==6) return this.phone;
		else return "";
	}
	
}
