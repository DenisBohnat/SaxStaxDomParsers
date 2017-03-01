package by.htp.xml.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.xml.model.Family;
import by.htp.xml.parsers.dom.SimpleDomParser;
import by.htp.xml.parsers.sax.SimpleSaxHandler;
import by.htp.xml.parsers.stax.SimpleStaxParser;

public class FamilyBuilder {

	private List<Family> families;

	public List<Family> run(String parserName) {
		families = null;
		switch (parserName) {
		case "sax":
			try {
				SimpleSaxHandler handler = new SimpleSaxHandler();
				XMLReader readerSax = XMLReaderFactory.createXMLReader();
				readerSax.setContentHandler(handler);
				readerSax.parse("resources/Family.xml");
				families = handler.getFamilies();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "stax":
			SimpleStaxParser staxParser = new SimpleStaxParser();
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader readerStax = null;
			InputStream input;
			try {
				input = new FileInputStream(new File("resources/Family.xml"));
				readerStax = inputFactory.createXMLStreamReader(input);
				families = staxParser.runParser(readerStax);
			} catch (FileNotFoundException | XMLStreamException e1) {
				e1.printStackTrace();
			}
			break;
		case "dom":
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			SimpleDomParser domParser = new SimpleDomParser();
			try {
				builder = factory.newDocumentBuilder();
				Document document = builder.parse("resources/Family.xml");
				families = domParser.runParser(document);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return families;
	}
}
