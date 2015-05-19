import com.sun.java.swing.plaf.motif.MotifEditorPaneUI;
import parse.MedicJSON;
import parse.MedicXLS;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

import java.io.IOException;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input1 = args[0];
		String input2 = args[1];

		String outputJSON = "personalMedical.json";
		String outputRDF = "personalMedical.rdf";

		try {
			MedicXLS medicXLS = new MedicXLS();
			medicXLS.parseRdfData(input1, input2);
			medicXLS.toJSONFile(outputJSON);

			MedicJSON medicJSON = new MedicJSON();
			medicJSON.parseJsonFile(outputJSON);
			medicJSON.writeRDFFile(outputRDF);

		} catch (IOException e) {
			e.printStackTrace();
		}



	}

}
