package br.com.healthtrack.model;

public class FoodModel {

	private int foodId;
	private String foodName;
	private double quantityCalories;
	private String hour;
	private String foodDescription;
	private int userId;
	private String date;

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getQuantityCalories() {
		return quantityCalories;
	}

	public void setQuantityCalories(double quantityCalories) {
		this.quantityCalories = quantityCalories;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	public FoodModel() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
