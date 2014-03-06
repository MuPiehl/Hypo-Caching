package de.vergleich.fedex.backendservice;

public class User {

	
	private String name;
	private Integer id;
	private Integer coins;
	
	public User(Integer id, String name, Integer coins) {
		this.id = id;
		this.name = name;
		this.coins = coins;
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
