package com.newtechieblog.wordpress.views.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewWrite, textViewHint;
    private Button btnConfirm;
    private EditText editTextGuess;
    boolean twoDigits, threeDigits, fourDigits;

    Random r = new Random();
    int random;
    int remainingWrite = 10;
    ArrayList<Integer> guessesList = new ArrayList<>();
    int userAttempts = 0;


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

        if (twoDigits) {
            random = r.nextInt(90) + 10;
        }
        if (threeDigits) {
            random = r.nextInt(900) + 100;
        }
        if (fourDigits) {
            random = r.nextInt(9000) + 1000;
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editTextGuess.getText().toString();
                if (guess.equals("")) {
                    Toast.makeText(GameActivity.this, R.string.toast_message, Toast.LENGTH_LONG).show();
                } else {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewWrite.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    userAttempts++;

                    remainingWrite--;
                    int userGuess = Integer.parseInt(guess);
                    guessesList.add(userGuess);

                    textViewLast.setText(guess);
                    textViewWrite.setText(remainingWrite);

                    if (random == userGuess) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle(R.string.dialog_title);
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations!!! My guess was"
                                + random
                                + "\n\n You know my number in "
                                + userAttempts + "attempts. \n\n Your guesses: "
                                + guessesList + "\n\n Would you like to play again?");
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();
                    }
                    if (random < userGuess) {
                        textViewHint.setText(R.string.increase_guess);
                    }

                    if (random > userGuess) {
                        textViewHint.setText(R.string.decrease_guess);
                    }

                    if (remainingWrite == 0) {

                    }

                    editTextGuess.setText("");
                }
            }
        });
    }
}