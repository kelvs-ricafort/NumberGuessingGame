package com.newtechieblog.wordpress.views.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private RadioButton radioTwoDigit, radioThreeDigit, radioFourDigit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        radioTwoDigit = findViewById(R.id.radioTwoDigit);
        radioThreeDigit = findViewById(R.id.radioThreeDigit);
        radioFourDigit = findViewById(R.id.radioFourDigit);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);

            if (!radioTwoDigit.isChecked() && !radioThreeDigit.isChecked() && !radioFourDigit.isChecked()) {
                Snackbar.make(v, R.string.snackbar_message, Snackbar.LENGTH_LONG).show();
            } else {
                if (radioTwoDigit.isChecked()) {
                    intent.putExtra("two", true);
                }
                if (radioThreeDigit.isChecked()) {
                    intent.putExtra("three", true);
                }
                if (radioFourDigit.isChecked()) {
                    intent.putExtra("four", true);
                }

                startActivity(intent);
            }
        });
    }
}