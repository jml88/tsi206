package rssFeed;

public class FeedItem {

	String title;
	String description;
	String link;
	
	public FeedItem() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String toString() {
		return  "FeedMessage [titulo: " + this.title + ", descripcion: " + this.description + ", link: " + this.link + "]";
	}
}
