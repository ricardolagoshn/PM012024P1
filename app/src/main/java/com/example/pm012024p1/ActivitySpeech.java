package com.example.pm012024p1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ActivitySpeech extends AppCompatActivity {

    private static int REQUEST_SPEECH = 100;
    private SpeechRecognizer speechRecognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results)
            {

                ArrayList<String> resultado = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if( resultado != null && !resultado.isEmpty())
                {
                    String texto = resultado.get(0);
                    //TextView textViewResult = findViewById(R.id.textViewResult);
                    //textViewResult.setText(texto);
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        Button botonvoz = findViewById(R.id.button);
        botonvoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarReconocimientoVoz();
            }
        });


    }

    private void IniciarReconocimientoVoz()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try
        {
            startActivityForResult(intent, REQUEST_SPEECH);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "No se puede realizar reconomiento de voz",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_SPEECH && resultCode == RESULT_OK  && data != null)
        {
            ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if( resultado != null && !resultado.isEmpty())
            {
                String texto = resultado.get(0);
                TextView textViewResult = findViewById(R.id.textViewResult);
                textViewResult.setText(texto);
            }
        }
    }
}