package model;

public class PayDetailVO {
	private int payDetailNum;
	private int payNum;
	private int productNum;
	private int payCount;
	
	
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	public int getPayDetailNum() {
		return payDetailNum;
	}
	public void setPayDetailNum(int payDetailNum) {
		this.payDetailNum = payDetailNum;
	}
	public int getPayNum() {
		return payNum;
	}
	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return productNum+" | "+payCount;
	}
	
	

}
