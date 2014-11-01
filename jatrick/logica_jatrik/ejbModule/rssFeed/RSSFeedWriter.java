package rssFeed;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class RSSFeedWriter {

	private String outputFile;
	private Feed rssfeed;

	public RSSFeedWriter(Feed rssfeed, String outputFile) {
		this.rssfeed = rssfeed;
		this.outputFile = outputFile;
	}
	
	public void write() throws Exception {
		// create XMLEventWriter
		XMLEventWriter eventWriter = XMLOutputFactory.newInstance().createXMLEventWriter(new FileOutputStream(outputFile));
		// create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		// create open tag
		eventWriter.add(end);
		StartElement rssStart = eventFactory.createStartElement("", "", "rss");
		eventWriter.add(rssStart);
		eventWriter.add(eventFactory.createAttribute("version", "2.0"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createStartElement("", "", "channel"));
		eventWriter.add(end);
		// Write the different nodes
		createNode(eventWriter, "title", rssfeed.getTitle());
		createNode(eventWriter, "link", rssfeed.getLink());
		createNode(eventWriter, "description", rssfeed.getDescription());
		for (FeedItem entry : rssfeed.getItems()) {
			eventWriter.add(tab);
			eventWriter.add(eventFactory.createStartElement("", "", "item"));
			eventWriter.add(end);
			eventWriter.add(tab);
			createNode(eventWriter, "title", entry.getTitle());
			eventWriter.add(tab);
			createNode(eventWriter, "link", entry.getLink());
			eventWriter.add(tab);
			createNode(eventWriter, "description", entry.getDescription());
			eventWriter.add(tab);
			eventWriter.add(eventFactory.createEndElement("", "", "item"));
			eventWriter.add(end);
		}
		eventWriter.add(eventFactory.createEndElement("", "", "channel"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndElement("", "", "rss"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
}

/*
FORMATO DEL XML
//TODO falta adaptarlo para lo que se quiera mostrar.

<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0">
<channel>
	<title></title>
	<link></link>
	<description></description>
	<item>
		<title></title>
		<link></link>
		<description></description>
	</item>
	<item>
		<title></title>
		<description></description>
		<link></link>
	</item>
	.
	.
	.
	.
	<item>
		<title></title>
		<description></description>
		<link></link>
	</item>
</channel>
</rss>

*/

