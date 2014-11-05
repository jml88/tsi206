package rssFeed;

public class RssFeedBB {

	public void metodo() {
		// create the rss feed
		String title = "Jatrik-TSI2-v1.0";
		String description = "Noticias sobre los ultimos resultados.";
		String link = "https://localhost:8443/web_jatrik/faces/";
		Feed rssFeeder = new Feed(title, link, description);

		// now add one example entry
		FeedItem feed1 = new FeedItem();
		feed1.setTitle("Titulo de una noticia.");
		feed1.setDescription("Descripcion de una noticia.");
		feed1.setLink("Link de una noticia");
		rssFeeder.getItems().add(feed1);

		FeedItem feed2 = new FeedItem();
		feed2.setTitle("Titulo de otra noticia.");
		feed2.setDescription("Descripcion de otra noticia.");
		feed2.setLink("Link de otra noticia");
		rssFeeder.getItems().add(feed2);

		FeedItem feed3 = new FeedItem();
		feed3.setTitle("Titulos de mas noticias.");
		feed3.setDescription("Descripcions de mas noticias.");
		feed3.setLink("Links de mas noticias");
		rssFeeder.getItems().add(feed3);

		// now write the file
		RSSFeedWriter writer = new RSSFeedWriter(rssFeeder,
				"WebContent/resources/rss/noticias.rss");
		try {
			writer.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
