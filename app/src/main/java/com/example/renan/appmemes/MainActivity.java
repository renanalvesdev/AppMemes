package com.example.renan.appmemes;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp ;

    ListView mListView;

    //frases diversas
    int[] audiosRage = new int[]{R.raw.comosefosse,
            R.raw.ehvocequevaimeensinar,
            R.raw.esseehumporra,
            R.raw.eusouumbosta,
            R.raw.gordobaleiudotetudo,
            R.raw.maiorroubodahistoria,
            R.raw.naotemgraca,
            R.raw.paisderetardados,
            R.raw.putaquepariu,
            R.raw.veioviado,
            R.raw.vocedestroiessepais,
            R.raw.voceehumidiota,
            R.raw.voceentendeisso,
            R.raw.vocetarindo

    };

    String[] frasesDiversas = {"Como se fosse a maior piada do mundo",
            "É você que vai me ensinar ?",
            "Esse é um porra",
            "Eu sou um bosta",
            "Gordo baleiudo",
            "Maior roubo da história",
            "Não tem graça",
            "País de retardados",
            "Puta que pariu",
            "Véio Viado",
            "Você destrói esse país",
            "Você é um idiota",
            "Você entende isso ?",
            "Você tá rindo ?"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);

        //criando funcionalidades para a lista

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(mp != null && mp.isPlaying())
                {
                    mp.stop();
                   mp.release();
                    mp = null;
                }

                mp = MediaPlayer.create(MainActivity.this, audiosRage[position]);
                mp.start();


            }
        });

    }

    @Override
    protected void onStop() {
        if(mp != null)
            mp.stop();

        super.onStop();
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
