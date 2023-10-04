package app.backend.entities;

import java.time.LocalDate;

public class PosgraduateStudent extends Student {
	private String posgraduateProgram;
	private String researchTitle;
	private LocalDate defenseDate;

	public void setDefenseDate(LocalDate defenseDate) {
		this.defenseDate = defenseDate;
	}

	public void setPosgraduateProgram(String posgraduateProgram) {
		this.posgraduateProgram = posgraduateProgram;
	}

	public void setResearchTitle(String researchTitle) {
		this.researchTitle = researchTitle;
	}

	public LocalDate getDefenseDate() {
		return defenseDate;
	}

	public String getPosgraduateProgram() {
		return posgraduateProgram;
	}

	public String getResearchTitle() {
		return researchTitle;
	}

	@Override
	public String toString() {
		return "PosgraduateStudent{" +
				"posgraduateProgram='" + posgraduateProgram + '\'' +
				", researchTitle='" + researchTitle + '\'' +
				", defenseDate=" + defenseDate +
				"} " + super.toString();
	}
}
