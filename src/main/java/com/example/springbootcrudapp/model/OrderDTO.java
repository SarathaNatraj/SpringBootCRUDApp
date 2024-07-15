package com.example.springbootcrudapp.model;

public class OrderDTO {
	private Long id;
	private String name;
	private Long productid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getProductid() {
		return productid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	
	public OrderDTO() {
		
	}
	@Override
	public String toString() {
		return "OrderDTO [name=" + name + ", productid=" + productid + ", count=" + count + "]";
	}
	

}
