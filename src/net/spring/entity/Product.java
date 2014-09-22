package net.spring.entity;

public class Product {
	private int id;
	
	private String proName;

	//default
	public Product() {
		super();
	}
	
	//getter() and setter()
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", proName=" + proName + "]";
	}
	
}
