package com.example.semanadosapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.semanadosapp.Models.DatosPersonales;
import com.example.semanadosapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText edNombre,edFecha, edTelefono, edEmail, edDescripcion;
    MaterialButton btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNombre = (TextInputEditText) findViewById(R.id.edNombre);
        edFecha = (TextInputEditText) findViewById(R.id.edFecha);
        edTelefono = (TextInputEditText) findViewById(R.id.edTelefono);
        edEmail = (TextInputEditText) findViewById(R.id.edEmail);
        edDescripcion = (TextInputEditText) findViewById(R.id.edDescripcion);
        btnSiguiente = (MaterialButton) findViewById(R.id.btnSiguiente);
        edFecha.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.edFecha:
                showPickerDialog();
                break;
            case R.id.btnSiguiente:
                sendDatos();
                break;
            default:
                break;
        }
    }

    private void sendDatos() {
        DatosPersonales datos = new DatosPersonales();
        datos.setNombre(edNombre.getText().toString());
        datos.setFecha(edFecha.getText().toString());
        datos.setTelefono(edTelefono.getText().toString());
        datos.setEmail(edEmail.getText().toString());
        datos.setDescripcion(edDescripcion.getText().toString());
        String listaDatosPersonales[] = {datos.getNombre(), datos.getFecha(),datos.getTelefono(),datos.getEmail(),datos.getDescripcion()};

        Intent intent = new Intent(this, ConfirmarDatosActivity.class);
        intent.putExtra(getResources().getString(R.string.envio_datos),listaDatosPersonales);
        startActivity(intent);
    }

    private void showPickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                edFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }
}