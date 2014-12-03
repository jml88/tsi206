package rssFeed;

public class prueboLeer {
	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser("http://feeds.abcnews.com/abcnews/sportsheadlines");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedItem message : feed.getItems()) {
			System.out.println(message);
		}
	}
}
