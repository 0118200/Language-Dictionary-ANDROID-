package com.example.languagedictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.languagedictionary.model.Definition;
import com.example.languagedictionary.model.Meaning; // ⚠️ WAJIB DIIMPORT!
import com.example.languagedictionary.model.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etSearchWord;
    private Button btnSearch;
    private TextView tvWord, tvDefinition, tvSynonyms, tvExample, tvError;
    private ProgressBar pbLoading;
    private Spinner spinnerLanguage;

    private String selectedLanguage = "Bahasa Inggris";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupSpinner();

        btnSearch.setOnClickListener(v -> handleSearch());
    }

    private void initViews() {
        etSearchWord = findViewById(R.id.etSearchWord);
        btnSearch = findViewById(R.id.btnSearch);
        tvWord = findViewById(R.id.tvWord);
        tvDefinition = findViewById(R.id.tvDefinition);
        tvSynonyms = findViewById(R.id.tvSynonyms);
        tvExample = findViewById(R.id.tvExample);
        tvError = findViewById(R.id.tvError);
        pbLoading = findViewById(R.id.pbLoading);
        spinnerLanguage = findViewById(R.id.spinnerLanguage);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.language_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguage = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void handleSearch() {
        String word = etSearchWord.getText().toString().trim();

        if (word.isEmpty()) {
            Toast.makeText(this, "Masukkan kata terlebih dahulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        pbLoading.setVisibility(View.VISIBLE);
        hideAllResults();
        tvError.setVisibility(View.GONE);

        // ✅ Perbaikan 1: Base URL tanpa spasi
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/")
                .addConverterFactory(GsonConverterFactory.create()) // ✅ Perbaikan 2: .create()
                .build();

        // ✅ Perbaikan 3: Pastikan DictionaryApi sudah dibuat & di-import
        DictionaryApi api = retrofit.create(DictionaryApi.class);

        Call<List<Word>> call = api.getWordDefinitions(word);

        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                pbLoading.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    Word wordObj = response.body().get(0); // ✅ Nama variabel kecil: wordObj

                    StringBuilder result = new StringBuilder();
                    result.append("Kata: ").append(wordObj.getWord()).append("\n\n");

                    if (wordObj.getPhonetic() != null) {
                        result.append("Pelafalan: ").append(wordObj.getPhonetic()).append("\n\n");
                    }

                    for (Meaning meaning : wordObj.getMeanings()) {
                        result.append("Jenis Kata: ").append(meaning.getPartOfSpeech()).append("\n");

                        for (Definition def : meaning.getDefinitions()) {
                            result.append(" • Definisi: ").append(def.getDefinition()).append("\n");
                            if (def.getExample() != null) {
                                result.append("   Contoh: ").append(def.getExample()).append("\n");
                            }
                            if (def.getSynonyms() != null && !def.getSynonyms().isEmpty()) {
                                result.append("   Sinonim: ").append(String.join(", ", def.getSynonyms())).append("\n");
                            }
                            result.append("\n");
                        }
                        result.append("--------------------\n\n");
                    }

                    tvWord.setText(result.toString());
                    tvWord.setVisibility(View.VISIBLE);

                } else {
                    showError("Kata \"" + word + "\" tidak ditemukan.");
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) { // ✅ Perbaikan 4: Call<List<Word>>
                pbLoading.setVisibility(View.GONE); // ✅ Perbaikan 5: hapus "|"
                showError("Gagal terhubung ke server: " + t.getMessage());
            }
        });
    }

    private void showResult(String word, String definition, String synonyms, String example) {
        tvWord.setText("Kata: " + word);
        tvDefinition.setText("Definisi: " + definition);
        tvSynonyms.setText("Sinonim: " + synonyms);
        tvExample.setText("Contoh: " + example);

        tvWord.setVisibility(View.VISIBLE);
        tvDefinition.setVisibility(View.VISIBLE);
        tvSynonyms.setVisibility(View.VISIBLE);
        tvExample.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }

    private void hideAllResults() {
        tvWord.setVisibility(View.GONE);
        tvDefinition.setVisibility(View.GONE);
        tvSynonyms.setVisibility(View.GONE);
        tvExample.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }
}
