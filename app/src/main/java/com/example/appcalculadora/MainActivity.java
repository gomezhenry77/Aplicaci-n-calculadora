package com.example.appcalculadora;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Instanciar los IDs del archivo activity_main.xml. Acá se llaman por el tipo(editText, button, etc y cómo se hace así ya no es necesario anteponerle el prefijo
    EditText valor1, valor2;
    TextView resultado;
    Button sumar, restar, multiplicar, dividir, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Referenciar los objetos con los elementos del archivo xml
        valor1 = findViewById(R.id.etvalor1);
        valor2 = findViewById(R.id.etvalor2);
        resultado = findViewById(R.id.tvresultado);
        sumar = findViewById(R.id.btnsumar);
        restar = findViewById(R.id.btnrestar);
        multiplicar = findViewById(R.id.btnmultiplicar);
        dividir = findViewById(R.id.btndividir);
        limpiar = findViewById(R.id.btnlimpiar);

        //Generar los eventos para realizar el código por cada uno de los botones en un evento
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1.setText("");
                valor2.setText("");
                resultado.setText("");
                valor1.requestFocus(); //se envía el foco al control valor1
            }
        });
    }

    //Este metodo se comparte para todos los botones, teniendo en cuenta el id de cada boton
    @Override
    public void onClick(View v) {

        //validar que los datos (valor1 y valor2) se hayan digitado
        if (!valor1.getText().toString().isEmpty() && !valor2.getText().toString().isEmpty()) {
            double mvalor1 = parseDouble(valor1.getText().toString());
            double mvalor2 = parseDouble(valor2.getText().toString());
            double mresultado = 0;
            //Chequear a cual botón se hizo clic
            switch (v.getId()) {
                case R.id.btnsumar:
                    mresultado = mvalor1 + mvalor2;
                    break;
                case R.id.btnrestar:
                    mresultado = mvalor1 - mvalor2;
                    break;
                case R.id.btnmultiplicar:
                    mresultado = mvalor1 * mvalor2;
                    break;
                case R.id.btndividir:
                    mresultado = mvalor1 / mvalor2;
                    break;
            }
            DecimalFormat oNumero = new DecimalFormat("###,###,###,###.#");
            resultado.setText(String.valueOf(oNumero.format(mresultado)));
        } else {
            //notificacion
            Toast.makeText(getApplicationContext(), "Debe ingresar los 2 valores", Toast.LENGTH_SHORT).show();
        }
    }
}