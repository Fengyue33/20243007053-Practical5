package com.example.practicalfive;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

        ImageView municipalityImage =
                findViewById(R.id.municipalityImage);

        TextView municipalityInfo =
                findViewById(R.id.municipalityInfo);

        Button visitSiteButton =
                findViewById(R.id.visitSiteButton);

        int imageResource;
        int colourResource;
        int summaryIndex;

        switch (municipality) {
            case "Beijing":
                imageResource = R.drawable.beijing;
                colourResource = R.color.municipality_beijing;
                summaryIndex = 0;
                break;

            case "Shanghai":
                imageResource = R.drawable.shanghai;
                colourResource = R.color.municipality_shanghai;
                summaryIndex = 1;
                break;

            case "Chongqing":
                imageResource = R.drawable.chongqing;
                colourResource = R.color.municipality_chongqing;
                summaryIndex = 2;
                break;

            case "Tianjin":
                imageResource = R.drawable.tianjin;
                colourResource = R.color.municipality_tianjin;
                summaryIndex = 3;
                break;

            default:
                finish();
                return;
        }

        String[] summaries = getResources().getStringArray(
                R.array.municipalities_array
        );

        int themeColour =
                ContextCompat.getColor(this, colourResource);

        int lightText =
                ContextCompat.getColor(this, R.color.color_text_light);

        int darkText =
                ContextCompat.getColor(this, R.color.color_text_dark);

        int defaultBackground = ContextCompat.getColor(
                this,
                R.color.color_default_background
        );

        boolean isChongqing = "Chongqing".equals(municipality);

        int textOnTheme = isChongqing ? darkText : lightText;

        int infoBackground =
                isChongqing ? darkText : defaultBackground;

        municipalityName.setText(municipality);
        municipalityName.setBackgroundColor(themeColour);
        municipalityName.setTextColor(textOnTheme);

        municipalityImage.setImageResource(imageResource);

        municipalityInfo.setText(summaries[summaryIndex]);
        municipalityInfo.setTextColor(themeColour);
        municipalityInfo.setBackgroundColor(infoBackground);

        visitSiteButton.setBackgroundTintList(
                ColorStateList.valueOf(themeColour)
        );
        visitSiteButton.setTextColor(textOnTheme);
    }
}