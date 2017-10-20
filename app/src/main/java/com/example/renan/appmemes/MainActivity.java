package com.example.renan.appmemes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ListView mListView;

    //frases diversas
    String[] frasesDiversas = {"Acabou o tempo de rir",
            "Você entende isso ou não ?",
            "Mas o que é isso ?",
            "Puta que pariu !",
            "Véio viado !",
            "País de retardados",
            "Gordo baleiudo",
            " É você que vai me ensinar ?",
            " Você tá rindo ?",
            "Sou eu o doido",
            "Eu sou um bosta",
            "Dilma é o caralho",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return frasesDiversas.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_item, null);

            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view.findViewById(R.id.textView);

           //  mImageView.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            mTextView.setText(frasesDiversas[position]);

            return view;
        }
    }
}
