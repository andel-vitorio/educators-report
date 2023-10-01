package app.backend.entities;

import java.time.*;

public class Teacher {
	private String name;
	private LocalDate birthDay;
	private int indenticatorNumber;
	private String email;
	private String phone;
	private String trainingArea;
	private int yearsOfExperience;

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIndenticatorNumber(int indenticatorNumber) {
		this.indenticatorNumber = indenticatorNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTrainingArea(String trainingArea) {
		this.trainingArea = trainingArea;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public String getEmail() {
		return email;
	}

	public int getIndenticatorNumber() {
		return indenticatorNumber;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getTrainingArea() {
		return trainingArea;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
}
