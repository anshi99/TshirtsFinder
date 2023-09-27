package com.nagarro.training.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tshirts")
public class TShirt {
	@Id
	private String id;
	private String name;
	private String color;
	private String gender;
	private String size;
	private double price;
	private double rating;
	private String avalibility;

	public TShirt() {

	}

	public void setID(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setAvailibility(String avalibility) {
		this.avalibility = avalibility;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getGender() {
		return gender;
	}

	public String getSize() {
		return size;
	}

	public double getPrice() {
		return price;
	}

	public double getRating() {
		return rating;
	}

	public String getAvalibility() {
		return avalibility;
	}

	@Override
	public String toString() {
		return "TShirtsModel [id=" + id + ", name=" + name + ", color=" + color + ", gender=" + gender + ", size="
				+ size + ", price=" + price + ", rating=" + rating + ", avalibility=" + avalibility + "]";
	}

}
