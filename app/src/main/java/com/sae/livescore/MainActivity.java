package com.sae.livescore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    private String homeTeamName, awayTeamName;
    private EditText etHomeTeam, etAwayTeam;
    private CircleImageView ivHomeTeam, ivAwayTeam;
    private Uri imgHomeTeam, imgAwayTeam;
    Button btnEditImgHome, btnEditImgAway, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHomeTeam = findViewById(R.id.etHomeTeam);
        etAwayTeam = findViewById(R.id.etAwayTeam);
        ivHomeTeam = findViewById(R.id.ivHomeTeam);
        ivAwayTeam = findViewById(R.id.ivAwayTeam);
        btnEditImgHome = findViewById(R.id.btnEditImgHome);
        btnEditImgAway = findViewById(R.id.btnEditImgAway);
        btnNext = findViewById(R.id.btnNext);

        btnEditImgHome.setOnClickListener(view -> startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE));

        btnEditImgAway.setOnClickListener(view -> startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE));

        btnNext.setOnClickListener(view -> {
            homeTeamName = etHomeTeam.getText().toString();
            awayTeamName = etAwayTeam.getText().toString();

            //check if homeTeamName and awayTeamName are empty
            if (homeTeamName.isEmpty() || awayTeamName.isEmpty()) {
                Toast.makeText(this, "Please enter team names", Toast.LENGTH_SHORT).show();
            }else if (imgHomeTeam == null || imgAwayTeam == null){
                //check if imgHomeTeam and imgAwayTeam are empty
                Toast.makeText(this, "Please select team images", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("homeTeamName", homeTeamName);
                intent.putExtra("awayTeamName", awayTeamName);
                intent.putExtra("homeImg", imgHomeTeam.toString());
                intent.putExtra("awayImg", imgAwayTeam.toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            Log.d(TAG, "Select image canceled");
        } else if (requestCode == HOME_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    imgHomeTeam = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    ivHomeTeam.setImageBitmap(bitmap);
                } catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        } else if (requestCode == AWAY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    imgAwayTeam = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    ivAwayTeam.setImageBitmap(bitmap);
                } catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }
}