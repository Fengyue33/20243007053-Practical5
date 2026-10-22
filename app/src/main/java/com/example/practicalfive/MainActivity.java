package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText municipalityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets contentInsets = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                                    | WindowInsetsCompat.Type.ime()
                    );

                    v.setPadding(
                            contentInsets.left,
                            contentInsets.top,
                            contentInsets.right,
                            contentInsets.bottom
                    );

                    return insets;
                });

        municipalityInput = findViewById(R.id.municipalityInput);

        municipalityInput.setOnEditorActionListener(
                (textView, actionId, event) -> {
                    if (event != null
                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                        if (event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getRepeatCount() == 0) {
                            openMunicipality();
                        }

                        return true;
                    }

                    if (actionId == EditorInfo.IME_ACTION_GO
                            || actionId == EditorInfo.IME_ACTION_DONE) {
                        openMunicipality();
                        return true;
                    }

                    return false;
                });
    }

    private void openMunicipality() {
        String input = municipalityInput.getText().toString();

        String municipality =
                TextProcessor.processMunicipality(input);

        if (municipality == null) {
            municipalityInput.setError(
                    getString(R.string.invalid_municipality)
            );
            municipalityInput.requestFocus();
            return;
        }

        municipalityInput.setError(null);

        Intent intent = new Intent(this, Municipality.class);

        intent.putExtra(
                Municipality.EXTRA_MUNICIPALITY,
                municipality
        );

        startActivity(intent);
    }
}