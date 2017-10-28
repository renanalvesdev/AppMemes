package com.moura.nando.memes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ActMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageButton btnRage = (ImageButton) findViewById(R.id.btnRages);
        ImageButton btnImitacoes = (ImageButton) findViewById(R.id.btnImitacoes);

        //função que comanda a imagem de rage para abrir a janela de rage
        btnRage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActMain.this, MainActivity.class);
                startActivity(it);
            }
        });

        btnImitacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActMain.this, ActImitacoes.class);
                startActivity(it);
            }
        });




        {

        }
    }
}
