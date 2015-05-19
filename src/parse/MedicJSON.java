package parse;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import entities.PersonalMedical;
import entities.TipPersonalMedical;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

/**
 * Created by marlen on 01.05.2015.
 */
public class MedicJSON {

    private Map<String,List<PersonalMedical>> listPersonalMedical = new HashMap<>();

    public void parseJsonFile(String filename){
        JSONParser jsonParser =  new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray)jsonParser.parse(new FileReader(filename));
            Iterator<JSONObject> jsonObjectIterator = jsonArray.iterator();
            while(jsonObjectIterator.hasNext()){
                // judet
                JSONObject dateJudet = jsonObjectIterator.next();

                // personal medical de fiecare tip pentru judet
                JSONArray personalMedicalPerJudet = (JSONArray)dateJudet.get("personalMedical");
                Iterator<JSONObject> personalMedicalIterator = personalMedicalPerJudet.iterator();
                List<PersonalMedical> personalMedicalList = new ArrayList<>();
                while (personalMedicalIterator.hasNext()){
                    PersonalMedical pm = new PersonalMedical();
                    pm.creatFromJsonObject(personalMedicalIterator.next());
                    personalMedicalList.add(pm);
                }
                listPersonalMedical.put(dateJudet.get("judet").toString(), personalMedicalList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<PersonalMedical>> getListPersonalMedical() {
        return listPersonalMedical;
    }


    public void writeRDFFile(String filename) throws IOException {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("opendata", Config.BASE_URL + "/resource");

        for(String judetPersonalMedical: listPersonalMedical.keySet()){
            MedicRDF medicRDF = new MedicRDF();
            medicRDF.setJudetPersonalMedical(judetPersonalMedical);
            Map<TipPersonalMedical, PersonalMedical> mapPersonalMedical = new HashMap<>();
            for(PersonalMedical personalMedical: listPersonalMedical.get(judetPersonalMedical)){
                mapPersonalMedical.put(personalMedical.getTipPersonalMedical(), personalMedical);
            }

            medicRDF.setPersonalMedical(mapPersonalMedical);
            model.add(medicRDF.modelPersonalMedical());
        }

        FileOutputStream fs=new FileOutputStream(new File(filename));
        model.write(fs,"N3");
        fs.close();
    }
}
