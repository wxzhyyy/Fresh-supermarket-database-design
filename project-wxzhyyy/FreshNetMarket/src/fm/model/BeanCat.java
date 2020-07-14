package fm.model;

public class BeanCat {

	private String cat_id;
	private String cat_name;
	private String cat_describe;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCat_describe() {
		return cat_describe;
	}
	public void setCat_describe(String cat_describe) {
		this.cat_describe = cat_describe;
	}
	public Object getCell(int col){
		
		if(col==0) return this.cat_id;
		else if(col==1) return this.cat_name;
		else if(col==2) return this.cat_describe;
		else return "";
	}
	
}
