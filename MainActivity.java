package com.example.languagedictionary;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import parser.JsonParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String mockJson = "[\n" +
                "  {\n" +
                "    \"word\": \"hello\",\n" +
                "    \"phonetic\": \"həˈləʊ\",\n" +
                "    \"phonetics\": [\n" +
                "      {\n" +
                "        \"text\": \"həˈləʊ\",\n" +
                "        \"audio\": \"//ssl.gstatic.com/dictionary/static/sounds/20200429/hello--_gb_1.mp3\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"text\": \"hɛˈləʊ\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"origin\": \"early 19th century: variant of earlier hollo ; related to holla.\",\n" +
                "    \"meanings\": [\n" +
                "      {\n" +
                "        \"partOfSpeech\": \"exclamation\",\n" +
                "        \"definitions\": [\n" +
                "          {\n" +
                "            \"definition\": \"used as a greeting or to begin a phone conversation.\",\n" +
                "            \"example\": \"hello there, Katie!\",\n" +
                "            \"synonyms\": [],\n" +
                "            \"antonyms\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"partOfSpeech\": \"noun\",\n" +
                "        \"definitions\": [\n" +
                "          {\n" +
                "            \"definition\": \"an utterance of ‘hello’; a greeting.\",\n" +
                "            \"example\": \"she was getting polite nods and hellos from people\",\n" +
                "            \"synonyms\": [],\n" +
                "            \"antonyms\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"partOfSpeech\": \"verb\",\n" +
                "        \"definitions\": [\n" +
                "          {\n" +
                "            \"definition\": \"say or shout ‘hello’.\",\n" +
                "            \"example\": \"I pressed the phone button and helloed\",\n" +
                "            \"synonyms\": [],\n" +
                "            \"antonyms\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        String hasil = JsonParser.parseFormat(mockJson);
        Toast.makeText(this, hasil, Toast.LENGTH_LONG).show();
    }
}
