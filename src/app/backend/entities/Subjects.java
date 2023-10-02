package app.backend.entities;

import java.time.LocalTime;

public class Subjects {

	private String code;
	private String name;
	private String description;
	private LocalTime startTime;
	private LocalTime endTime;
	private String classroom;
	private String teacherName;
	private String requirements;
	private int courseLoad;
	private int credits;
	private int numberOfVacancies;

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCourseLoad(int courseLoad) {
		this.courseLoad = courseLoad;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumberOfVacancies(int numberOfVacancies) {
		this.numberOfVacancies = numberOfVacancies;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getClassroom() {
		return classroom;
	}

	public String getCode() {
		return code;
	}

	public int getCourseLoad() {
		return courseLoad;
	}

	public int getCredits() {
		return credits;
	}

	public String getDescription() {
		return description;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfVacancies() {
		return numberOfVacancies;
	}

	public String getRequirements() {
		return requirements;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	@Override
	public String toString() {
		return "Subjects{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", classroom='" + classroom + '\'' +
				", teacherName='" + teacherName + '\'' +
				", requirements='" + requirements + '\'' +
				", courseLoad=" + courseLoad +
				", credits=" + credits +
				", numberOfVacancies=" + numberOfVacancies +
				'}';
	}
}
