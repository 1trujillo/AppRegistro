package com.example.appregistro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class DescriptionActivity extends AppCompatActivity {

    private EditText editDescripcion;
    private Button btnGuardarDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        editDescripcion = findViewById(R.id.editDescripcion);
        btnGuardarDescripcion = findViewById(R.id.btnGuardarDescripcion);

        btnGuardarDescripcion.setOnClickListener(v -> {
            String descripcion = editDescripcion.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("descripcion", descripcion);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
