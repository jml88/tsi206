package rssFeed;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	final String title;
	final String link;
	final String description;

	final List<FeedItem> items = new ArrayList<FeedItem>();

	public Feed(String title, String link, String description) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public List<FeedItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Feed [Titulo: " + this.title + ", descripcion: " + this.description + ", link: " + this.link + "]";
	}
}
