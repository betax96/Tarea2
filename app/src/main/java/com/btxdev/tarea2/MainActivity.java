package com.btxdev.tarea2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtX1, edtY1, edtX2, edtY2;
    private Button btnPuntoMedio, btnPendiente, btnCuadrante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtX1 = findViewById(R.id.edtX1);
        edtY1 = findViewById(R.id.edtY1);
        edtX2 = findViewById(R.id.edtX2);
        edtY2 = findViewById(R.id.edtY2);

        btnPuntoMedio = findViewById(R.id.btnPuntoMedio);
        btnPendiente = findViewById(R.id.btnPendiente);
        btnCuadrante = findViewById(R.id.btnCuadrante);

        btnPuntoMedio.setOnClickListener(this);
        btnPendiente.setOnClickListener(this);
        btnCuadrante.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(!checkEditText()){
            Toast.makeText(getApplicationContext(),R.string.err_valores_no_validos,Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()){
            case R.id.btnPuntoMedio:
                Point p = puntoMedio(getPoint1(),getPoint2());
                Toast.makeText(getApplicationContext(),getString(R.string.punto_medio)+": "+p.toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPendiente:
                double m = pendiente(getPoint1(),getPoint2());
                Toast.makeText(getApplicationContext(),getString(R.string.pendiente)+": "+Point.getString(m),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCuadrante:
                int c1 = cuadrante(getPoint1());
                int c2 = cuadrante(getPoint2());
                Toast.makeText(getApplicationContext(),getString(R.string.cuandrante_punto_1)+": "+c1
                        +"\n"+getString(R.string.cuandrante_punto_2)+": "+c2,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mitAleatorio:
                clearEditText();
                randomNumberEditText();
                break;
            case R.id.mitDistancia:
                if(checkEditText()){
                    double d = distancia(getPoint1(),getPoint2());
                    Toast.makeText(getApplicationContext(),getString(R.string.distancia)+": "+Point.getString(d),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.err_valores_no_validos,Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private void clearEditText(){
        edtX1.setText("");
        edtY1.setText("");
        edtX2.setText("");
        edtY2.setText("");
    }

    private void randomNumberEditText(){
        edtX1.setText(Integer.toString(generateRandomNumber(-50,50)));
        edtY1.setText(Integer.toString(generateRandomNumber(-50,50)));
        edtX2.setText(Integer.toString(generateRandomNumber(-50,50)));
        edtY2.setText(Integer.toString(generateRandomNumber(-50,50)));
    }

    private boolean checkEditText(){
        return !TextUtils.isEmpty(edtX1.getText())&&!TextUtils.isEmpty(edtY1.getText())
                &&!TextUtils.isEmpty(edtX2.getText())&&!TextUtils.isEmpty(edtY2.getText());
    }

    private Point puntoMedio(Point point1, Point point2){
        double px = (point1.getX()+point2.getX())/2;
        double py = (point1.getY()+point2.getY())/2;
        return new Point(px,py);
    }

    private double pendiente(Point point1, Point point2){
        return (point2.getY()-point1.getY())/(point2.getX()-point1.getX());
    }

    private double distancia(Point point1, Point point2){
        return Math.sqrt(Math.pow(point2.getX()-point1.getX(),2)+Math.pow(point2.getY()-point1.getY(),2));
    }

    public static int cuadrante(Point p) {
        double x = p.getX();
        double y = p.getY();
        if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else if (x > 0 && y < 0) {
            return 4;
        } else {
            return 0;
        }
    }

    private Point getPoint1(){
        double x = Double.parseDouble(edtX1.getText().toString());
        double y = Double.parseDouble(edtY1.getText().toString());
        return new Point(x,y);
    }

    private Point getPoint2(){
        double x = Double.parseDouble(edtX2.getText().toString());
        double y = Double.parseDouble(edtY2.getText().toString());
        return new Point(x,y);
    }

}