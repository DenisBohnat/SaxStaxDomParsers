package by.htp.xml.parsers.stax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.xml.controler.XmlElement;
import by.htp.xml.model.Family;
import by.htp.xml.model.Father;
import by.htp.xml.model.Mother;
import by.htp.xml.model.Parent;

public class SimpleStaxParser {

	private Family family;
	private Parent parent;
	private List<Family> families;

	public List<Family> runParser(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {

		families = new ArrayList<Family>();
		XmlElement elementName = null;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = XmlElement.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
				switch (elementName) {
				case FAMILY:
					family = new Family();
					family.setName(reader.getAttributeValue(null, "name"));
					break;
				case MOTHER:
					parent = new Mother();
					break;
				case FATHER:
					parent = new Father();
					break;
				case CHILDREN:
					((Mother) parent).setChildren(Integer.parseInt(reader.getAttributeValue(null, "count")));
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();

				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
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
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = XmlElement.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
				switch (elementName) {
				case FAMILY:
					families.add(family);
					break;
				case MOTHER:
					family.setMother((Mother) parent);
					break;
				case FATHER:
					family.setFather((Father) parent);
					break;
				}
			}
		}

		return families;

	}
}
