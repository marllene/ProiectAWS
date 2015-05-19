package parse;
import entities.PersonalMedical;
import entities.TipCategorieVarsta;
import entities.TipPersonalMedical;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;


public class MedicXLS {

	private Map<String,List<PersonalMedical>> listPersonalMedical;
	public MedicXLS() {
		// TODO Auto-generated constructor stub
	}
	
	public void parseRdfData(String inputPersonal, String inputCategoriiVarsta){
		try {
			listPersonalMedical = new HashMap<>();

			FileInputStream file = new FileInputStream(new File(inputPersonal));
			FileInputStream fileCategoriiVarsta = new FileInputStream(new File(inputCategoriiVarsta));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
		    XSSFSheet spreadsheet = workbook.getSheetAt(0);
		    Iterator<Row> rowIterator = spreadsheet.iterator();


			XSSFWorkbook workbookCategoriiVarsta = new XSSFWorkbook(fileCategoriiVarsta);
			XSSFSheet spreadsheetCategoriiVarsta = workbook.getSheetAt(0);
			Iterator<Row> rowIteratorCategoriiVarsta = spreadsheetCategoriiVarsta.iterator();

			for(int i = 0; i < 5; i++){
				rowIterator.next();
				rowIteratorCategoriiVarsta.next();
			}
			while(rowIterator.hasNext() && rowIteratorCategoriiVarsta.hasNext()){
				List<PersonalMedical> rowInXLS = new ArrayList<>();
				Row row = rowIterator.next();

				// date despre medici
				PersonalMedical medic = new PersonalMedical();
				medic.setTipPersonalMedical(TipPersonalMedical.MEDICI);
				Iterator<Cell> cellIterator = row.cellIterator();
				cellIterator.next();
				String judet = cellIterator.next().getStringCellValue();
		    	medic.setJudet(judet);
				medic.setNrTotal((int) cellIterator.next().getNumericCellValue());
				medic.setNrPublic((int)cellIterator.next().getNumericCellValue());
				medic.setNrPrivat((int)cellIterator.next().getNumericCellValue());

				// pentru medici afla categoria de varsta
				Row categoriiVarsta = rowIteratorCategoriiVarsta.next();
				Iterator<Cell> cellIteratorVarsta = categoriiVarsta.iterator();
				Map<TipCategorieVarsta, Integer> mediciPeCategoriiVarsta = new HashMap<>();
                cellIteratorVarsta.next();
                cellIteratorVarsta.next();
                mediciPeCategoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_25,(int)cellIteratorVarsta.next().getNumericCellValue());
				mediciPeCategoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_25_34,(int)cellIteratorVarsta.next().getNumericCellValue());
				mediciPeCategoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_35_44,(int)cellIteratorVarsta.next().getNumericCellValue());
				mediciPeCategoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_45_54,(int)cellIteratorVarsta.next().getNumericCellValue());
				mediciPeCategoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_55_64,(int)cellIteratorVarsta.next().getNumericCellValue());
				mediciPeCategoriiVarsta.put(TipCategorieVarsta.CATEGORIE_VARSTA_65,(int)cellIteratorVarsta.next().getNumericCellValue());

				medic.setCategoriiVarsta(mediciPeCategoriiVarsta);
		    	rowInXLS.add(medic);

				// date despre medicii de familie
				PersonalMedical medicDeFamilie = new PersonalMedical();
                medicDeFamilie.setJudet(judet);
				medicDeFamilie.setTipPersonalMedical(TipPersonalMedical.MEDICI_DE_FAMILIE);
				medicDeFamilie.setNrTotal((int) cellIterator.next().getNumericCellValue());
				medicDeFamilie.setNrPublic((int)cellIterator.next().getNumericCellValue());
				medicDeFamilie.setNrPrivat((int)cellIterator.next().getNumericCellValue());
				rowInXLS.add(medicDeFamilie);

				// date despre dentisti
				PersonalMedical dentisti = new PersonalMedical();
                dentisti.setJudet(judet);
                dentisti.setTipPersonalMedical(TipPersonalMedical.DENTISTI);
				dentisti.setNrTotal((int) cellIterator.next().getNumericCellValue());
				dentisti.setNrPublic((int)cellIterator.next().getNumericCellValue());
				dentisti.setNrPrivat((int)cellIterator.next().getNumericCellValue());
				rowInXLS.add(dentisti);

				// date despre farmacisti
				PersonalMedical farmacisti = new PersonalMedical();
                farmacisti.setJudet(judet);
				farmacisti.setTipPersonalMedical(TipPersonalMedical.FARMACISTI);
				farmacisti.setNrTotal((int) cellIterator.next().getNumericCellValue());
				farmacisti.setNrPublic((int)cellIterator.next().getNumericCellValue());
				farmacisti.setNrPrivat((int)cellIterator.next().getNumericCellValue());
				rowInXLS.add(farmacisti);
                cellIterator.next();
                cellIterator.next();

				// date despre fiziokineto
				PersonalMedical fiziokineto = new PersonalMedical();
                fiziokineto.setJudet(judet);
				fiziokineto.setTipPersonalMedical(TipPersonalMedical.FIZIOKINETO);
				fiziokineto.setNrTotal((int) cellIterator.next().getNumericCellValue());
				fiziokineto.setNrPublic((int) cellIterator.next().getNumericCellValue());
				fiziokineto.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(fiziokineto);

				// date despre fizioterapeuti
				PersonalMedical fizioterapeuti = new PersonalMedical();
                fizioterapeuti.setJudet(judet);
				fizioterapeuti.setTipPersonalMedical(TipPersonalMedical.FIZIOTERAPEUTI);
				fizioterapeuti.setNrTotal((int) cellIterator.next().getNumericCellValue());
				fizioterapeuti.setNrPublic((int) cellIterator.next().getNumericCellValue());
				fizioterapeuti.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(fizioterapeuti);

				// date despre asistentiMedicaliStudii studii superioare
				PersonalMedical asistentiMedicaliStudii = new PersonalMedical();
                asistentiMedicaliStudii.setJudet(judet);
				asistentiMedicaliStudii.setTipPersonalMedical(TipPersonalMedical.ASISTENTI_STUDII);
				asistentiMedicaliStudii.setNrTotal((int) cellIterator.next().getNumericCellValue());
				asistentiMedicaliStudii.setNrPublic((int) cellIterator.next().getNumericCellValue());
				asistentiMedicaliStudii.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(asistentiMedicaliStudii);

				// date despre asistentiMedGenerala
				PersonalMedical asistentiMedGenerala = new PersonalMedical();
                asistentiMedGenerala.setJudet(judet);
                asistentiMedGenerala.setTipPersonalMedical(TipPersonalMedical.ASISTENTI_MED_GEN);
				asistentiMedGenerala.setNrTotal((int) cellIterator.next().getNumericCellValue());
				asistentiMedGenerala.setNrPublic((int)cellIterator.next().getNumericCellValue());
				asistentiMedGenerala.setNrPrivat((int)cellIterator.next().getNumericCellValue());
				rowInXLS.add(asistentiMedGenerala);
                cellIterator.next();
                cellIterator.next();

				// date despre alt personal medical
				PersonalMedical altPersonalMedical = new PersonalMedical();
                altPersonalMedical.setJudet(judet);
                altPersonalMedical.setTipPersonalMedical(TipPersonalMedical.ALT_PERSONAL);
				altPersonalMedical.setNrTotal((int) cellIterator.next().getNumericCellValue());
				altPersonalMedical.setNrPublic((int) cellIterator.next().getNumericCellValue());
				altPersonalMedical.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(altPersonalMedical);

				// date despre personal sanitar mediu
				PersonalMedical personalSanitarMediu = new PersonalMedical();
                personalSanitarMediu.setJudet(judet);
                personalSanitarMediu.setTipPersonalMedical(TipPersonalMedical.SANITAR_MEDIU);
				personalSanitarMediu.setNrTotal((int) cellIterator.next().getNumericCellValue());
				personalSanitarMediu.setNrPublic((int) cellIterator.next().getNumericCellValue());
				personalSanitarMediu.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(personalSanitarMediu);

				// date despre asistenti medicali
				PersonalMedical asistentiMedicali = new PersonalMedical();
                asistentiMedicali.setJudet(judet);
                asistentiMedicali.setTipPersonalMedical(TipPersonalMedical.ASISTENT_MEDICAL);
				asistentiMedicali.setNrTotal((int) cellIterator.next().getNumericCellValue());
				asistentiMedicali.setNrPublic((int) cellIterator.next().getNumericCellValue());
				asistentiMedicali.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(asistentiMedicali);
                cellIterator.next();
                cellIterator.next();

                // date despre moase
				PersonalMedical moase = new PersonalMedical();
                moase.setJudet(judet);
                moase.setTipPersonalMedical(TipPersonalMedical.MOASE);
				moase.setNrTotal((int) cellIterator.next().getNumericCellValue());
				moase.setNrPublic((int) cellIterator.next().getNumericCellValue());
				moase.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(moase);

				// date despre asistenti medicali fiziokinetoterapeuti
				PersonalMedical asistentiFizioKineto = new PersonalMedical();
                asistentiFizioKineto.setJudet(judet);
                asistentiFizioKineto.setTipPersonalMedical(TipPersonalMedical.ASISTENT_FIZIOKINETO);
				asistentiFizioKineto.setNrTotal((int) cellIterator.next().getNumericCellValue());
				asistentiFizioKineto.setNrPublic((int) cellIterator.next().getNumericCellValue());
				asistentiFizioKineto.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(asistentiFizioKineto);

				// date despre asistenti medicali fizioterapeuti
				PersonalMedical asistentiFizio = new PersonalMedical();
                asistentiFizio.setJudet(judet);
                asistentiFizio.setTipPersonalMedical(TipPersonalMedical.ASISTENT_FIZIO);
				asistentiFizio.setNrTotal((int) cellIterator.next().getNumericCellValue());
				asistentiFizio.setNrPublic((int) cellIterator.next().getNumericCellValue());
				asistentiFizio.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(asistentiFizio);

				// date despre asistentiMedGenerala
				PersonalMedical personalAuxiliar = new PersonalMedical();
                personalAuxiliar.setJudet(judet);
                personalAuxiliar.setTipPersonalMedical(TipPersonalMedical.PERSONAL_AUXILIAR);
				personalAuxiliar.setNrTotal((int) cellIterator.next().getNumericCellValue());
				personalAuxiliar.setNrPublic((int) cellIterator.next().getNumericCellValue());
				personalAuxiliar.setNrPrivat((int) cellIterator.next().getNumericCellValue());
				rowInXLS.add(personalAuxiliar);

				listPersonalMedical.put(judet, rowInXLS);
		    }
		    workbook.close();
            workbookCategoriiVarsta.close();
		    file.close();
            fileCategoriiVarsta.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        listPersonalMedical.remove("");
	}

	public void toJSONFile(String filename) throws IOException {
		JSONArray arrayJSON = new JSONArray();
		for (String judet: listPersonalMedical.keySet())
		{
			JSONObject dateJudet = new JSONObject();
			dateJudet.put("judet", judet);
			JSONArray listPersonalJSON = new JSONArray();
			for(PersonalMedical personalMedical: listPersonalMedical.get(judet)){
				listPersonalJSON.add(personalMedical.getJsonObject());
			}
			dateJudet.put("personalMedical", listPersonalJSON);
			arrayJSON.add(dateJudet);
		}

		FileWriter jsonFile = new FileWriter(filename);
        arrayJSON.writeJSONString(jsonFile);
		jsonFile.close();

	}
}
