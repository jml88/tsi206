package rssFeed;

import java.util.HashSet;
import java.util.Set;

import partidos.Partido;

public class RssFeedBB {

	public void escribirNoticias() {
		
		Set<Partido> partidos = new HashSet<Partido>();
		String title = "Jatrik-TSI2-v1.0";
		String description = "Noticias sobre los ultimos resultados.";
		String link = "https://localhost:8443/web_jatrik/faces/";
		Feed rssFeeder = new Feed(title, link, description);
		for (Partido p : partidos) {
			FeedItem feed = new FeedItem();
			String titulo = p.getLocal().getNombre() + " Vs " + p.getVisitante().getNombre();
			feed.setTitle(titulo);
			String descripcion = "Se ha jugado el partido contra " + p.getVisitante().getNombre() +"\n"
					+ "El resultado ha sido " + p.getLocal().getNombre() + " " + p.getResultado().getGolesLocal() 
					+ " : " + p.getResultado().getGolesVisitante() + " " + p.getVisitante().getNombre();
			feed.setDescription(descripcion);
			String linkFeed = "https://localhost:8443/web_jatrik/faces/";
			feed.setLink(linkFeed);
			rssFeeder.items.add(feed);
		}
		RSSFeedWriter writer = new RSSFeedWriter(rssFeeder, "noticias.rss");
		try {
			writer.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
