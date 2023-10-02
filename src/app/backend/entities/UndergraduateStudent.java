package app.backend.entities;

public class UndergraduateStudent extends Student {
	private String projectName;
	private String typeOfOrientation;

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setTypeOfOrientation(String typeOfOrientation) {
		this.typeOfOrientation = typeOfOrientation;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getTypeOfOrientation() {
		return typeOfOrientation;
	}

	@Override
	public String toString() {
		return "UndergraduateStudent{" +
				"projectName='" + projectName + '\'' +
				", typeOfOrientation='" + typeOfOrientation + '\'' +
				"} " + super.toString();
	}
}
