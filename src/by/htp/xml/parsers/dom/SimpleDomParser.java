package by.htp.xml.parsers.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.htp.xml.model.Family;
import by.htp.xml.model.Father;
import by.htp.xml.model.Mother;
import by.htp.xml.model.Parent;

public class SimpleDomParser {

	public List<Family> runParser(Document document) {

		Element root = document.getDocumentElement();

		List<Family> families = new ArrayList<Family>();
		NodeList familiesNode = root.getElementsByTagName("family");

		for (int i = 0; i < familiesNode.getLength(); i++) {
			Element familyElement = (Element) familiesNode.item(i);

			Family family = new Family();
			family.setName(familyElement.getAttribute("name"));

			Parent mother = createMother(familyElement);
			Parent father = createFather(familyElement);

			family.setMother((Mother) mother);
			family.setFather((Father) father);
			families.add(family);
		}
		return families;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}

	private static Parent createMother(Element familyElement) {
		Parent parent = new Mother();
		NodeList mList = familyElement.getElementsByTagName("mother");

		Element motherElement = (Element) mList.item(0);

		((Mother) parent).setName(getElementTextContent(motherElement, "name"));
		((Mother) parent).setSurname(getElementTextContent(motherElement, "surname"));
		((Mother) parent).setMaidenName(getElementTextContent(motherElement, "maiden-name"));
		((Mother) parent).setAge(Integer.parseInt(getElementTextContent(motherElement, "age")));

		NodeList childrenList = familyElement.getElementsByTagName("children");

		Element childrenElement = (Element) childrenList.item(0);
		((Mother) parent).setChildren(Integer.parseInt(childrenElement.getAttribute("count")));

		return parent;
	}

	private static Parent createFather(Element familyElement) {
		Parent parent = new Father();
		NodeList fList = familyElement.getElementsByTagName("father");
		Element fatherElement = (Element) fList.item(0);

		((Father) parent).setName(getElementTextContent(fatherElement, "name"));
		((Father) parent).setSurname(getElementTextContent(fatherElement, "surname"));
		((Father) parent).setAge(Integer.parseInt(getElementTextContent(fatherElement, "age")));

		return parent;
	}

}
