package by.htp.xml.controler;

import java.util.List;

import by.htp.xml.model.Family;

public class Main {

	public static void main(String[] args) {
		List<Family> families = null;

		FamilyBuilder familyBuilder = new FamilyBuilder();
		
		/*Enter sax, stax or dom*/
		families = familyBuilder.run("dom");

		for (int i = 0; i < families.size(); i++) {
			System.out.println(families.get(i).toString());
		}
	}
}
