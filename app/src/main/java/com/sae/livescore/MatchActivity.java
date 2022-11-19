package com.sae.livescore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchActivity extends AppCompatActivity {
    private int scoreHome, scoreAway;
    private String winnerTeam;
    private Uri winnerImg;
    private TextView tvHomeTeam, tvAwayTeam, tvScoreHome, tvScoreAway;
    CircleImageView ivMatchHomeTeam, ivMatchAwayTeam;
    Button btnAddHome1, btnAddHome2, btnAddHome3, btnAddAway1, btnAddAway2, btnAddAway3, btnResult, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        tvHomeTeam = findViewById(R.id.tvHomeTeam);
        tvAwayTeam = findViewById(R.id.tvAwayTeam);
        tvScoreHome = findViewById(R.id.tvScoreHome);
        tvScoreAway = findViewById(R.id.tvScoreAway);
        ivMatchHomeTeam = findViewById(R.id.ivMatchHomeTeam);
        ivMatchAwayTeam = findViewById(R.id.ivMatchAwayTeam);
        btnAddHome1 = findViewById(R.id.btnAddHome1);
        btnAddHome2 = findViewById(R.id.btnAddHome2);
        btnAddHome3 = findViewById(R.id.btnAddHome3);
        btnAddAway1 = findViewById(R.id.btnAddAway1);
        btnAddAway2 = findViewById(R.id.btnAddAway2);
        btnAddAway3 = findViewById(R.id.btnAddAway3);
        btnResult = findViewById(R.id.btnResult);
        btnReset = findViewById(R.id.btnReset);

        scoreHome = 0;
        scoreAway = 0;
        tvScoreHome.setText(String.valueOf(scoreHome));
        tvScoreAway.setText(String.valueOf(scoreAway));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvHomeTeam.setText(bundle.getString("homeTeamName"));
            tvAwayTeam.setText(bundle.getString("awayTeamName"));
            ivMatchHomeTeam.setImageURI(Uri.parse(bundle.getString("homeImg")));
            ivMatchAwayTeam.setImageURI(Uri.parse(bundle.getString("awayImg")));
        }

        btnAddHome1.setOnClickListener(v -> {
            scoreHome += 1;
            tvScoreHome.setText(String.valueOf(scoreHome));
        });

        btnAddHome2.setOnClickListener(v -> {
            scoreHome += 2;
            tvScoreHome.setText(String.valueOf(scoreHome));
        });

        btnAddHome3.setOnClickListener(v -> {
            scoreHome += 3;
            tvScoreHome.setText(String.valueOf(scoreHome));
        });

        btnAddAway1.setOnClickListener(v -> {
            scoreAway += 1;
            tvScoreAway.setText(String.valueOf(scoreAway));
        });

        btnAddAway2.setOnClickListener(v -> {
            scoreAway += 2;
            tvScoreAway.setText(String.valueOf(scoreAway));
        });

        btnAddAway3.setOnClickListener(v -> {
            scoreAway += 3;
            tvScoreAway.setText(String.valueOf(scoreAway));
        });

        btnReset.setOnClickListener(v -> {
            scoreHome = 0;
            scoreAway = 0;
            tvScoreHome.setText(String.valueOf(scoreHome));
            tvScoreAway.setText(String.valueOf(scoreAway));
        });

        btnResult.setOnClickListener(view -> {
            if (scoreHome > scoreAway) {
                winnerTeam = tvHomeTeam.getText().toString();
                winnerImg = Uri.parse(bundle.getString("homeImg"));
            } else if (scoreHome < scoreAway) {
                winnerTeam = tvAwayTeam.getText().toString();
                winnerImg = Uri.parse(bundle.getString("awayImg"));
            } else {
                winnerTeam = "Draw";
            }

            Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
            intent.putExtra("winnerTeam", winnerTeam);
            if(winnerImg != null) {
                intent.putExtra("winnerImg", winnerImg.toString());
            }
            startActivity(intent);
        });

    }
}