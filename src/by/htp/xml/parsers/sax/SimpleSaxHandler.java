package by.htp.xml.parsers.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.xml.controler.XmlElement;
import by.htp.xml.model.Family;
import by.htp.xml.model.Father;
import by.htp.xml.model.Mother;
import by.htp.xml.model.Parent;

public class SimpleSaxHandler extends DefaultHandler {

	private String text;
	private StringBuilder builder;
	private Family family;
	private Parent parent;
	private List<Family> families;

	@Override
	public void characters(char[] buf, int start, int length) throws SAXException {
		text = builder.append(buf, start, length).toString().trim().toUpperCase();
	}

	@Override
	public void startDocument() throws SAXException {
		families = new ArrayList<Family>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {

		builder = new StringBuilder();

		if ("family".equals(qName)) {
			family = new Family();
			family.setName(attr.getValue("name"));
		}
		if ("mother".equals(qName)) {
			parent = new Mother();
		}
		if ("father".equals(qName)) {
			parent = new Father();
		}
		if ("children".equals(qName)) {
			((Mother) parent).setChildren(Integer.parseInt(attr.getValue("count")));
		}

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		XmlElement element = XmlElement.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (element) {
		case FAMILY:
			families.add(family);
			break;
		case MOTHER:
			family.setMother((Mother) parent);
			break;
		case FATHER:
			family.setFather((Father) parent);
			break;
		case NAME:
			parent.setName(text);
			break;
		case SURNAME:
			parent.setSurname(text);
			break;
		case AGE:
			parent.setAge(Integer.parseInt(text));
			break;
		case MAIDEN_NAME:
			((Mother) parent).setMaidenName(text);
			break;
		}
	}

	public List<Family> getFamilies() {
		return families;
	}
}
