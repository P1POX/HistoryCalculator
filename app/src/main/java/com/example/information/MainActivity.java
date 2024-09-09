package com.example.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView tvResultado;
    private ArrayList<String> listaHistorial = new ArrayList<>();
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnClean;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual, btnHistorial;
    private ArrayList<Double> numeros = new ArrayList<>();
    private ArrayList<String> operaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.etInputText);
        tvResultado = findViewById(R.id.tvResultado);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDot = findViewById(R.id.btnDot);
        btnClean = findViewById(R.id.btnClean);

        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);
        btnHistorial = findViewById(R.id.btnHistorial);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append("9");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.append(".");
            }
        });
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.setText("");
            }
        });

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("/");
            }
        });

        btnIgual.setOnClickListener(this::onClick);

        btnHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaTextosActivity.class);
            intent.putStringArrayListExtra("listaHistorial", listaHistorial);
            startActivity(intent);
        });
    }

    private void guardarNumeroYOperacion(String op) {
        String numeroIngresado = inputText.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            if (Double.parseDouble(numeroIngresado) == 0) {
                Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                return;
            }
            numeros.add(Double.parseDouble(numeroIngresado));
            operaciones.add(op);
            inputText.setText("");
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    private void onClick(View v) {
        String numeroIngresado = inputText.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            numeros.add(Double.parseDouble(numeroIngresado));
            double resultado = numeros.get(0);

            String operacion = "";
            if (resultado % 1 == 0) {
                operacion += String.valueOf((int) resultado);
            } else {
                operacion += String.valueOf(resultado);
            }

            for (int i = 0; i < operaciones.size(); i++) {
                switch (operaciones.get(i)) {
                    case "+":
                        resultado += numeros.get(i + 1);
                        operacion += "+";
                        break;
                    case "-":
                        resultado -= numeros.get(i + 1);
                        operacion += "-";
                        break;
                    case "*":
                        resultado *= numeros.get(i + 1);
                        operacion += "x";
                        break;
                    case "/":
                        if (numeros.get(i + 1) != 0) {
                            resultado /= numeros.get(i + 1);
                            operacion += "/";
                        } else {
                            Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }

                double numero = numeros.get(i + 1);
                if (numero % 1 == 0) {
                    operacion += String.valueOf((int) numero);
                } else {
                    operacion += String.valueOf(numero);
                }
            }

            if (resultado % 1 == 0) {
                tvResultado.setText("Resultado: " + (int) resultado);
                listaHistorial.add(operacion + "\n=" + String.valueOf((int) resultado));
            } else {
                tvResultado.setText("Resultado: " + resultado);
                listaHistorial.add(operacion + "\n=" + String.valueOf(resultado));
            }

            inputText.setText("");
            numeros.clear();
            operaciones.clear();
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }
}