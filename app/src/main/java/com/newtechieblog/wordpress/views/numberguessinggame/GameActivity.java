package com.newtechieblog.wordpress.views.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewWrite, textViewHint;
    private Button btnConfirm;
    private EditText editTextGuess;

    boolean twoDigits, threeDigits, fourDigits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewLast = findViewById(R.id.textViewLast);
        textViewWrite = findViewById(R.id.textViewWrite);
        textViewHint = findViewById(R.id.textViewHint);

        btnConfirm = findViewById(R.id.btnConfirm);
        editTextGuess = findViewById(R.id.editTextGuess);

        twoDigits = getIntent().getBooleanExtra("two", false);
        threeDigits = getIntent().getBooleanExtra("three", false);
        fourDigits = getIntent().getBooleanExtra("four", false);

    }
}