package model;

public class CategorydetailVO {
	private int categoryDetailNum;
	private int categoryNum;
	private String categoryDetailName;
	private int listcnt;
	private String serchCondition;
	
	
	public String getSerchCondition() {
		return serchCondition;
	}
	public void setSerchCondition(String serchCondition) {
		this.serchCondition = serchCondition;
	}
	public int getCategoryDetailNum() {
		return categoryDetailNum;
	}
	public void setCategoryDetailNum(int categoryDetailNum) {
		this.categoryDetailNum = categoryDetailNum;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public String getCategoryDetailName() {
		return categoryDetailName;
	}
	public void setCategoryDetailName(String categoryDetailName) {
		this.categoryDetailName = categoryDetailName;
	}
	public int getListcnt() {
		return listcnt;
	}
	public void setListcnt(int listcnt) {
		this.listcnt = listcnt;
	}
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "세부카테고리 : "+this.categoryDetailNum+this.categoryDetailName;
//	}
	
	
}
