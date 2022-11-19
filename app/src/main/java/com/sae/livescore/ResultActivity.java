package com.sae.livescore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultActivity extends AppCompatActivity {
    CircleImageView ivWinnerImage;
    TextView tvWinnerTeam;
    Button btnStartAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ivWinnerImage = findViewById(R.id.ivWinnerImage);
        tvWinnerTeam = findViewById(R.id.tvWinnerTeam);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvWinnerTeam.setText(bundle.getString("winnerTeam"));
            if (bundle.getString("winnerImg") != null) {
                ivWinnerImage.setImageURI(Uri.parse(bundle.getString("winnerImg")));
            } else {
                ivWinnerImage.setImageResource(R.drawable.image_placeholder);
            }
        }

        btnStartAgain = findViewById(R.id.btnStartAgain);
        btnStartAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}