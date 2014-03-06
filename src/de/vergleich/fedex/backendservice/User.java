package de.vergleich.fedex.backendservice;

public class User {

	private String id;
	private Integer coins;

	public User(String id, Integer coins) {
		this.id = id;
		this.coins = coins;
	}

	public User() {
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public void addCoins(Integer coins) {
		this.coins += coins;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
