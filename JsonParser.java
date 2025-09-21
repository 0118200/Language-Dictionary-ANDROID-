package parser;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {

    public static String parseFormat(String jsonString) {
        StringBuilder result = new StringBuilder();

        try {
            //ubah json jadi jsonarray
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject kataObj = jsonArray.getJSONObject(0);

            result.append("    Kata: ").append(kataObj.getString("word")).append("\n\n");

            //ambil phonetic
            if (kataObj.has("phonetic")) {
                result.append("    Pelafalan: ").append(kataObj.getString("phonetic")).append("\n\n");
            }

            //ambil origin
            if (kataObj.has("origin")) {
                result.append("    Asal Kata: ").append(kataObj.getString("origin")).append("\n\n");
            }

            //ambil meaning
            JSONArray meanings = kataObj.getJSONArray("meanings");

            //loop makna
            for (int i = 0; i < meanings.length(); i++) {
                JSONObject meaning = meanings.getJSONObject(i);

                //ambil cara pengucapan
                result.append("    Bagian Ucapan: ").append(meaning.getString("partOfSpeech")).append("\n\n");

                JSONArray definitions = meaning.getJSONArray("definitions");

                //cek definisi
                for (int j = 0; j < definitions.length(); j++) {
                    JSONObject def = definitions.getJSONObject(j);

                    //tampilkan definisi
                    result.append("    Definisi: ").append(def.getString("definition")).append("\n\n");

                    //cek contoh
                    if (def.has("example") && !def.isNull("example")) {
                        result.append("    Contoh: ").append(def.getString("example")).append("\n\n");
                    }

                    //cek sinonim
                    if (def.has("synonyms") && !def.isNull("synonyms")) {
                        JSONArray synonyms = def.getJSONArray("synonyms");
                        if (synonyms.length() > 0) {
                            StringBuilder synList = new StringBuilder("    Sinonim: ");
                            for (int k = 0; k < synonyms.length(); k++) {
                                synList.append(synonyms.getString(k));
                                if (k < synonyms.length() - 1) {
                                    synList.append(", ");
                                }
                            }
                            result.append(synList).append("\n\n");
                        }
                    }

                    //cek antonim
                    if (def.has("antonyms") && !def.isNull("antonyms")) {
                        JSONArray antonyms = def.getJSONArray("antonyms");
                        if (antonyms.length() > 0) {
                            StringBuilder anList = new StringBuilder("    Antonim: ");
                            for (int k = 0; k < antonyms.length(); k++) {
                                anList.append(antonyms.getString(k));
                                if (k < antonyms.length() - 1) {
                                    anList.append(", ");
                                }
                            }
                            result.append(anList).append("\n\n");
                        }
                    }
                }
                result.append("\n\n"); //misah antar makna
            }

        } catch (Exception e) {
            return "Gagal parsing: " + e.getMessage();
        }

        return result.toString();
    }
}
