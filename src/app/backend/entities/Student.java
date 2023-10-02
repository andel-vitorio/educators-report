package app.backend.entities;

import java.time.LocalDate;

public class Student {
	private String name;
	private LocalDate dateOfEntry;
	private String registration;
	private String email;
	private String phoneNumber;
	private String nameOfMentee;
	private String status;

	public void setDateOfEntry(LocalDate dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameOfMentee(String nameOfMentee) {
		this.nameOfMentee = nameOfMentee;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDateOfEntry() {
		return dateOfEntry;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getNameOfMentee() {
		return nameOfMentee;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getRegistration() {
		return registration;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", dateOfEntry=" + dateOfEntry +
				", registration='" + registration + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", nameOfMentee='" + nameOfMentee + '\'' +
				", status='" + status + '\'' +
				'}';
	}

}
