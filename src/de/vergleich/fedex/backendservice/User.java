package de.vergleich.fedex.backendservice;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String imei;
	private Integer coins;
	private Set<Element> elemente;

	public User(String imei, Integer coins) {
		this();
		this.imei = imei;
		this.coins = coins;
	}

	public User() {
		this.elemente = new HashSet<Element>();
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public Set<Element> getElemente() {
		return elemente;
	}

	public void addElement(Element element) {
		this.elemente.add(element);
	}

	public Element getHausFortschritt() {
		if (elemente.contains(Element.ROHBAU_DACH))
			return Element.ROHBAU_DACH;
		if (elemente.contains(Element.ROHBAU))
			return Element.ROHBAU;
		if (elemente.contains(Element.FUNDAMENT))
			return Element.FUNDAMENT;

		return Element.NICHTS;
	}

	public boolean elementExists(Element element) {
		return elemente.contains(element);
	}
}
