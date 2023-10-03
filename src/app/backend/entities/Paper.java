package app.backend.entities;

import java.time.LocalDate;

public class Paper {
	private String title;
	private String authors;
	private LocalDate publicationDate;
	private String keywords;
	private String description;
	private String category;
	private String url;
	private int id;

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthors() {
		return authors;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getKeywords() {
		return keywords;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "Paper{" +
				"title='" + title + '\'' +
				", authors='" + authors + '\'' +
				", publicationDate=" + publicationDate +
				", keywords='" + keywords + '\'' +
				", description='" + description + '\'' +
				", category='" + category + '\'' +
				", url='" + url + '\'' +
				'}';
	}
	
}
