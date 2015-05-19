package entities;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalMedical {

	private String  			   				judet;
	private TipPersonalMedical     				tipPersonalMedical;
	private int					   				nrTotal;
	private int 				   				nrPublic;
	private int					   				nrPrivat;
	private Map<TipCategorieVarsta, Integer> 	categoriiVarsta =  new HashMap<>();
	
	
	public PersonalMedical() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PersonalMedical(String judet, TipPersonalMedical tipPersonalMedical,
			int nrTotal, int nrPublic, int nrPrivat,
			List<PersonalMedical> categoriiSubordonate) {
		super();
		this.judet = judet;
		this.tipPersonalMedical = tipPersonalMedical;
		this.nrTotal = nrTotal;
		this.nrPublic = nrPublic;
		this.nrPrivat = nrPrivat;
	}


	public String getJudet() {
		return judet;
	}


	public void setJudet(String judet) {
		this.judet = judet;
	}


	public TipPersonalMedical getTipPersonalMedical() {
		return tipPersonalMedical;
	}


	public void setTipPersonalMedical(String tipPersonalMedical) {
		this.tipPersonalMedical = TipPersonalMedical.valueOf(tipPersonalMedical);
	}

	public void setTipPersonalMedical(TipPersonalMedical tipPersonalMedical) {
		this.tipPersonalMedical = tipPersonalMedical;
	}

	public int getNrTotal() {
		return nrTotal;
	}


	public void setNrTotal(int nrTotal) {
		this.nrTotal = nrTotal;
	}


	public int getNrPublic() {
		return nrPublic;
	}


	public void setNrPublic(int nrPublic) {
		this.nrPublic = nrPublic;
	}


	public int getNrPrivat() {
		return nrPrivat;
	}


	public void setNrPrivat(int nrPrivat) {
		this.nrPrivat = nrPrivat;
	}

	public Map<TipCategorieVarsta, Integer> getCategoriiVarsta() {
		return categoriiVarsta;
	}

	public void setCategoriiVarsta(Map<TipCategorieVarsta, Integer> categoriiVarsta) {
		this.categoriiVarsta = categoriiVarsta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PersonalMedical that = (PersonalMedical) o;

		if (nrPrivat != that.nrPrivat) return false;
		if (nrPublic != that.nrPublic) return false;
		if (nrTotal != that.nrTotal) return false;
		if (judet != null ? !judet.equals(that.judet) : that.judet != null) return false;
		if (tipPersonalMedical != that.tipPersonalMedical) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = judet != null ? judet.hashCode() : 0;
		result = 31 * result + (tipPersonalMedical != null ? tipPersonalMedical.hashCode() : 0);
		result = 31 * result + nrTotal;
		result = 31 * result + nrPublic;
		result = 31 * result + nrPrivat;
		return result;
	}

	public JSONObject getJsonObject(){
		JSONObject personalMedical = new JSONObject();
		personalMedical.put("tip_personal", getTipPersonalMedical().toString());
		personalMedical.put("judet", getJudet().toString());
		personalMedical.put("nr_total", getNrTotal());
		personalMedical.put("nr_privat", getNrPrivat());
		personalMedical.put("nr_public", getNrPublic());

        if(getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_25) != null) {
            personalMedical.put("categorii_varsta25", getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_25));
            personalMedical.put("categorii_varsta25_34", getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_25_34));
            personalMedical.put("categorii_varsta35_44", getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_35_44));
            personalMedical.put("categorii_varsta45_54", getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_45_54));
            personalMedical.put("categorii_varsta55_64", getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_55_64));
            personalMedical.put("categorii_varsta65", getCategoriiVarsta().get(TipCategorieVarsta.CATEGORIE_VARSTA_65));
        }

		return personalMedical;
	}

	public void creatFromJsonObject(JSONObject personalMedicalJSON){
		setTipPersonalMedical(personalMedicalJSON.get("tip_personal").toString());
		setJudet(personalMedicalJSON.get("judet").toString());
		setNrTotal(Integer.parseInt(personalMedicalJSON.get("nr_total").toString()));
		setNrPrivat(Integer.parseInt(personalMedicalJSON.get("nr_privat").toString()));
		setNrPublic(Integer.parseInt(personalMedicalJSON.get("nr_public").toString()));
		if(personalMedicalJSON.get("categorii_varsta25") != null) {
			Map<TipCategorieVarsta, Integer> categoriiVarsta = new HashMap<>();
			categoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_25, Integer.parseInt(personalMedicalJSON.get("categorii_varsta25").toString()));
			categoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_25_34, Integer.parseInt(personalMedicalJSON.get("categorii_varsta25_34").toString()));
			categoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_35_44, Integer.parseInt(personalMedicalJSON.get("categorii_varsta35_44").toString()));
			categoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_45_54, Integer.parseInt(personalMedicalJSON.get("categorii_varsta45_54").toString()));
			categoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_55_64, Integer.parseInt(personalMedicalJSON.get("categorii_varsta55_64").toString()));
			categoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_65, Integer.parseInt(personalMedicalJSON.get("categorii_varsta65").toString()));
			setCategoriiVarsta(categoriiVarsta);
		}
	}
}
