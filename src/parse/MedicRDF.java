package parse;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;
import entities.PersonalMedical;
import entities.TipCategorieVarsta;
import entities.TipPersonalMedical;
import utilities.SPARQLQueries;

import java.util.Map;

/**
 * Created by marlen on 05.05.2015.
 */
public class MedicRDF {

    private String judetPersonalMedical;
    private Map<TipPersonalMedical, PersonalMedical> personalMedical;

    public Map<TipPersonalMedical, PersonalMedical> getPersonalMedical() {
        return personalMedical;
    }

    public void setPersonalMedical(Map<TipPersonalMedical, PersonalMedical> personalMedical) {
        this.personalMedical = personalMedical;
    }

    public String getJudetPersonalMedical() {
        return judetPersonalMedical;
    }

    public void setJudetPersonalMedical(String judetPersonalMedical) {
        this.judetPersonalMedical = judetPersonalMedical;
    }

    public Model modelPersonalMedical(){
        Model model = ModelFactory.createDefaultModel();
        Property judet = model.createProperty(Config.PROPERTY + "judet");

        Resource tipPersonalMedical = model.createResource();

        Resource medici = model.createResource();
        Resource categorieVarsta = model.createResource();

        Property mediciGeneral = model.createProperty(Config.PROPERTY + "medici_general");
        Property mediciDeFamilie = model.createProperty(Config.PROPERTY + "medici_familie");
        Property categorieVarsta1 = model.createProperty(Config.PROPERTY + "categorie_1");
        Property categorieVarsta2 = model.createProperty(Config.PROPERTY + "categorie_2");
        Property categorieVarsta3 = model.createProperty(Config.PROPERTY + "categorie_3");
        Property categorieVarsta4 = model.createProperty(Config.PROPERTY + "categorie_4");
        Property categorieVarsta5 = model.createProperty(Config.PROPERTY + "categorie_5");
        Property categorieVarsta6 = model.createProperty(Config.PROPERTY + "categorie_6");

        medici.addProperty(VCARD.N, categorieVarsta
                .addProperty(categorieVarsta1, personalMedical.get(TipPersonalMedical.MEDICI).
                        getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_25).toString())
                .addProperty(categorieVarsta2, personalMedical.get(TipPersonalMedical.MEDICI).
                        getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_25_34).toString())
                .addProperty(categorieVarsta3, personalMedical.get(TipPersonalMedical.MEDICI).
                        getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_35_44).toString())
                .addProperty(categorieVarsta4, personalMedical.get(TipPersonalMedical.MEDICI).
                        getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_45_54).toString())
                .addProperty(categorieVarsta5, personalMedical.get(TipPersonalMedical.MEDICI).
                        getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_55_64).toString())
                .addProperty(categorieVarsta6, personalMedical.get(TipPersonalMedical.MEDICI).
                        getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_65).toString()));
        medici.addProperty(mediciDeFamilie, personalMedical.get(TipPersonalMedical.MEDICI_DE_FAMILIE).getNrTotal() + "");
        medici.addProperty(mediciGeneral, personalMedical.get(TipPersonalMedical.MEDICI).getNrTotal() + "");

        Property dentisti = model.createProperty(Config.PROPERTY + "dentisti");
        Property farmacisti = model.createProperty(Config.PROPERTY + "farmacisti");

        Resource fiziokinetoterapeuti = model.createResource();
        Property fiziokinetoGeneral = model.createProperty(Config.PROPERTY + "fiziokineto");
        Resource fiziokinetoSpecific = model.createResource();
        Property fizioterapeuti = model.createProperty(Config.PROPERTY + "fizioterapeuti");

        Resource asistentiStudiiSuperioare = model.createResource();
        Property asistentiStudiiSuperioareGeneral = model.createProperty(Config.PROPERTY + "asistenti_studii_sup");
        Resource asistentiStudiiSuperioareSpecific = model.createResource();
        Property asistentiMedOG = model.createProperty(Config.PROPERTY + "asistenti_med_og");

        Property altPersonalStudiiSuperioare = model.createProperty(Config.PROPERTY + "alt_personal_studii_superioare");
        Property personalMediu = model.createProperty(Config.PROPERTY + "personal_mediu");

        Resource asistentiMedicali = model.createResource();
        Property asistentiMedicaliGeneral = model.createProperty(Config.PROPERTY + "asistenti_medicali");
        Resource asistentiMedicaliSpecific = model.createResource();
        Property moase = model.createProperty(Config.PROPERTY + "asistenti_medicali_moase");
        Resource asistentiMedicaliFiziokineto = model.createResource();
        Property asistentiMedicaliFiziokinetoGeneral = model.createProperty(Config.PROPERTY + "asistenti_medicali_fiziokineto");
        Resource asistentiMedicaliFiziokinetoterapeutiSpecific = model.createResource();
        Property asistentiMedicaliFizioterapeuti = model.createProperty(Config.PROPERTY + "asistenti_medicali_fizioterapeuti");

        Property personalMedicalAuxiliar = model.createProperty(Config.PROPERTY + "personal_medical_auxiliar");

        Resource personalMedicalDbpedia = model.createResource("http://dbpedia.org/page/Category:Medical_doctors_by_specialty");

        model.createResource(Config.LABEL_PREFIX + judetPersonalMedical)
                .addProperty(RDFS.label, Config.LABEL_PREFIX + judetPersonalMedical)
                .addProperty(RDF.type, personalMedicalDbpedia)
                .addProperty(judet, SPARQLQueries.getJudetResursa(judetPersonalMedical)!=null?SPARQLQueries.getJudetResursa(judetPersonalMedical).toString():"")
                .addProperty(VCARD.N, tipPersonalMedical
                                .addProperty(VCARD.N, medici)
                                .addProperty(dentisti, personalMedical.get(TipPersonalMedical.DENTISTI).getNrTotal() + "")
                                .addProperty(farmacisti, personalMedical.get(TipPersonalMedical.FARMACISTI).getNrTotal() + "")
                                .addProperty(VCARD.N, fiziokinetoterapeuti
                                                .addProperty(fiziokinetoGeneral, personalMedical.get(TipPersonalMedical.FIZIOKINETO).getNrTotal() + "")
                                                .addProperty(VCARD.N, fiziokinetoSpecific
                                                                .addProperty(fizioterapeuti,
                                                                        personalMedical.get(TipPersonalMedical.FIZIOTERAPEUTI).getNrTotal() + "")))
                                                .addProperty(VCARD.N, asistentiStudiiSuperioare
                                                                .addProperty(asistentiStudiiSuperioareGeneral,
                                                                        personalMedical.get(TipPersonalMedical.ASISTENTI_STUDII).getNrTotal() + "")
                                                                .addProperty(VCARD.N, asistentiStudiiSuperioareSpecific
                                                                .addProperty(asistentiMedOG,
                                                                        personalMedical.get(TipPersonalMedical.ASISTENTI_MED_GEN).getNrTotal() + "")
                                                                .addProperty(altPersonalStudiiSuperioare,
                                                                        personalMedical.get(TipPersonalMedical.ALT_PERSONAL).getNrTotal() + "")
                                                                .addProperty(personalMediu,
                                                                        personalMedical.get(TipPersonalMedical.SANITAR_MEDIU).getNrTotal() + "")))
                                                                .addProperty(VCARD.N, asistentiMedicali
                                                                .addProperty(asistentiMedicaliGeneral,
                                                                        personalMedical.get(TipPersonalMedical.ASISTENTI_MED_GEN).getNrTotal() + ""))
                                                                .addProperty(VCARD.N, asistentiMedicaliSpecific
                                                                        .addProperty(moase,
                                                                                personalMedical.get(TipPersonalMedical.MOASE).getNrTotal() + ""))
                                                                        .addProperty(VCARD.N, asistentiMedicaliFiziokineto
                                                                                .addProperty(asistentiMedicaliFiziokinetoGeneral,
                                                                                        personalMedical.get(TipPersonalMedical.ASISTENT_FIZIOKINETO).getNrTotal() + "")
                                                                                .addProperty(VCARD.N, asistentiMedicaliFiziokinetoterapeutiSpecific
                                                                                        .addProperty(asistentiMedicaliFizioterapeuti,
                                                                                                personalMedical.get(TipPersonalMedical.ASISTENT_FIZIO).getNrTotal() + "")
                                                                                        .addProperty(personalMedicalAuxiliar,
                                                                                                personalMedical.get(TipPersonalMedical.PERSONAL_AUXILIAR).getNrTotal() + ""))));
        return model;
    }
}
