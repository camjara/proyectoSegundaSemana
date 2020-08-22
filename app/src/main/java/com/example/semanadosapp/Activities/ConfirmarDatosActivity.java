package com.example.semanadosapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.semanadosapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ConfirmarDatosActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialTextView tvNombre, tvFecha,tvTelefono, tvEmail,tvDescripcion;
    MaterialButton btnEditarDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);
        tvNombre = (MaterialTextView) findViewById(R.id.tvNombre);
        tvFecha = (MaterialTextView) findViewById(R.id.tvFecha);
        tvTelefono = (MaterialTextView) findViewById(R.id.tvTelefono);
        tvEmail = (MaterialTextView) findViewById(R.id.tvEmail);
        tvDescripcion = (MaterialTextView) findViewById(R.id.tvDescripcion);
        btnEditarDatos = (MaterialButton) findViewById(R.id.btnEditarDatos);
        btnEditarDatos.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        String parametros [] = (String[]) bundle.get(getResources().getString(R.string.envio_datos));
        mostrarDatos(parametros);

    }

    private void mostrarDatos(String parametros[])
    {
        String nombre = parametros[0];
        String fecha = parametros[1];
        String telefono = parametros[2];
        String email = parametros[3];
        String descripcion = parametros[4];
        tvNombre.setText(nombre);
        tvFecha.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnEditarDatos:
                editarDatos();
                break;
            default:
                break;
        }
    }

    private void editarDatos() {
        finish();
    }
}