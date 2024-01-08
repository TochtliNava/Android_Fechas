package com.tochtlinava.fechas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnFecha1, btnFecha2, btnE;
    private EditText txtF1, txtF2;
    public int dia1, mes1, ano1, dia2, mes2, ano2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFecha1 = (Button) findViewById(R.id.buttonF1);
        btnFecha2 = (Button) findViewById(R.id.buttonF2);
        btnE = (Button) findViewById(R.id.buttonEjecutar);
        btnE.setEnabled(false);
        txtF1 = (EditText) findViewById(R.id.txtF1);
        txtF2 = (EditText) findViewById(R.id.txtF2);

        // En el método onCreate()
        txtF1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(txtF1.getText()) && !TextUtils.isEmpty(txtF2.getText())) {
                    btnE.setEnabled(true);
                } else {
                    btnE.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtF2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(txtF1.getText()) && !TextUtils.isEmpty(txtF2.getText())) {
                    btnE.setEnabled(true);
                } else {
                    btnE.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void mostrarVista(View v){
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int anio = calendario.get(Calendar.YEAR);

        DatePickerDialog dpd = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String fecha = i2 + "/" + i1 + "/"+ i;
                        if(v.getId() == R.id.buttonF1) {
                            dia1 = i2;
                            mes1 = i1;
                            ano1 = i;
                            txtF1.setText(fecha);
                        }
                        if(v.getId() == R.id.buttonF2) {
                            dia2 = i2;
                            mes2 = i1;
                            ano2 = i;
                            txtF2.setText(fecha);
                        }
                    }
                }, 2023, mes, dia
        );
        dpd.show();
    }

    public void calcular(View vista){
        AlertDialog.Builder vent = new AlertDialog.Builder(this);

        if(dia2 > dia1) {
            vent.setTitle("RESULTADO");
            vent.setMessage("Dias: " + (dia2 - dia1) +
                    "\nMeses: " + (mes2 - mes1) +
                    "\nAño: " + (ano2 - ano1));
        } else {
            vent.setTitle("ERROR");
            vent.setMessage("El primer día es mayor que el segundo día");
        }
        vent.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                txtF1.setText(null);
                txtF2.setText(null);
            }
        });

        vent.show();
    }
}