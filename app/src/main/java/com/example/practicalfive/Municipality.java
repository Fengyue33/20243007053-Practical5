package com.example.practicalfive;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Municipality extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.MUNICIPALITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_municipality);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        String receivedName =
                getIntent().getStringExtra(EXTRA_MUNICIPALITY);

        String municipality =
                TextProcessor.processMunicipality(receivedName);

        if (municipality == null) {
            Toast.makeText(
                    this,
                    R.string.invalid_municipality,
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        TextView municipalityName =
                findViewById(R.id.municipalityName);

        municipalityName.setText(municipality);
    }
}