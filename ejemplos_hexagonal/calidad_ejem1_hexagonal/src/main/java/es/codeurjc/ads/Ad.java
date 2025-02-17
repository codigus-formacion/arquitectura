package es.codeurjc.ads;

public class Ad {

	private long id = -1;
	private String name;
	private String title;
	private String comment;

	public Ad() {
	}

	public Ad(String name, String title, String comment) {
		super();
		this.name = name;
		this.title = title;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ",name=" + name + ", title=" + title + ", comment=" + comment + "]";
	}

}
