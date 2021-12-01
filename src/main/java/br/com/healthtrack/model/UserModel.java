package br.com.healthtrack.model;

public class UserModel {

	private int userId;
	private String userName;
	private String userMail;
	private String userPassword;
	private String userGender;
	private double userWeight;
	private double userHeight;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public double getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(double userWeight) {
		this.userWeight = userWeight;
	}

	public double getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(double userHeight) {
		this.userHeight = userHeight;
	}

	public Double calculateIMC(double weight, double height) {
		return weight / (height * height);
	}

	public UserModel() {
	}

}
