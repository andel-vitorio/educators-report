package app.backend.entities;

import java.time.LocalDate;

public class CoordinationActivity {
	private String activityTitle;
	private String nameOfPersonResponsible;
	private LocalDate startDate;
	private LocalDate endDate;
	private String priority;
	private String status;
	private String description;
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setNameOfPersonResponsible(String nameOfPersonResponsible) {
		this.nameOfPersonResponsible = nameOfPersonResponsible;
	}

	public void setPriiority(String priiority) {
		this.priority = priiority;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getNameOfPersonResponsible() {
		return nameOfPersonResponsible;
	}

	public String getPriiority() {
		return priority;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "CoordinationActivity{" +
				"activityTitle='" + activityTitle + '\'' +
				", nameOfPersonResponsible='" + nameOfPersonResponsible + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", priority='" + priority + '\'' +
				", status='" + status + '\'' +
				", description='" + description + '\'' +
				'}';
	}

}
