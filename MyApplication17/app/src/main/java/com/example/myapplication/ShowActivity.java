package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    Button retbt;

    ImageView imgV;

    TextView textView;
    MediaPlayer mediaPlayer;
    String [] str; // Список текстов к птицам

// Список звуков

    String [] birdsnd = { "chibis.mp3", "drozd.mp3", "malinovka.mp3",

            "newivolga.mp3", "schegol.mp3", "siniza_1.mp3", "solovey.mp3", };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);
        imgV = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.txtpoem);
        retbt = (Button) findViewById(R.id.retbt);
        str =getResources().getStringArray(R.array.poem); // Заполнение списка
        Bundle arguments = getIntent().getExtras();
        int ind = arguments.getInt("pos");
        retbt.setOnClickListener(this);
        birdchoose(ind);
    }


    public void birdchoose(int pos){

        int temp=R.raw.chibis;
        int commm =temp + pos;
        mediaPlayer=MediaPlayer.create(this, commm);
        mediaPlayer.start();

        switch (pos){

            case 0:
                imgV.setImageResource(R.drawable.chibis);
                textView.setText(str[pos]);
                break;

            case 1:
                imgV.setImageResource(R.drawable.drozd);
                textView.setText(str[pos]);
                break;

            case 2:
                imgV.setImageResource(R.drawable.malinovka);
                textView.setText(str[pos]);
                break;

            case 3:
                imgV.setImageResource(R.drawable.oriolus);
                textView.setText(str[pos]);
                break;

            case 4:
                imgV.setImageResource(R.drawable.schegol);
                textView.setText(str[pos]);
                break;

            case 5:
                imgV.setImageResource(R.drawable.siniza_1);
                textView.setText(str[pos]);
                break;

            case 6:
                imgV.setImageResource(R.drawable.nightingal);
                textView.setText(str[pos]);
                break;

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ShowActivity.this, MainActivity.class);
        this.startActivity(intent);
        mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        mediaPlayer.stop();
        super.onPause();
    }
}