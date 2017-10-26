package com.example.renan.appmemes;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static android.R.attr.name;

public class ActImitacoes extends AppCompatActivity {

    MediaPlayer mp ;

    ListView mListView;

    //frases diversas
    int[] audiosImitacoes = new int[]{
            1/*R.raw.im_pirula_mas_o_que,
            R.raw.im_felipe_neto_malakoi,
            R.raw.im_maestro_bandjido,
            R.raw.im_maestro_bofobics,
            R.raw.im_maestro_dando_boura,
            R.raw.im_maestro_ilicito,
            R.raw.im_pirula_ameacas_crentes,
            R.raw.im_pirula_ameacas_mesmo,
            R.raw.im_pirula_mas_o_que,
            R.raw.im_pirula_mosexual,*/



    };

    String[] frasesImitacoes = {"Mas o que é isso ?",
            "Malakoi",
            "Bandjido !",
            "Mophóbics",
            "Aah Dando Boura !",
            "Ilítico",
            "Ameaças crentes",
            "Ameaças mesmo",
            "Mas o que é isso ?",
            "Cavalos marinhos homossexuais",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_imitacoes);

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

                mp = MediaPlayer.create(ActImitacoes.this, audiosImitacoes[position]);
                mp.start();


            }
        });

        //clicando para compartilhar o áudio via whatsapp


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
            return frasesImitacoes.length;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_item, null);

            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view.findViewById(R.id.textView);
            ImageButton shareButton = (ImageButton) view.findViewById(R.id.shareButton);


            //  mImageView.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            mTextView.setText(frasesImitacoes[position]);

            //implementando o compartilhamento dos áudios
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("audio/*");
                    sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(
                            "android.resource://"+getPackageName()+"/raw/"+ getResources().getResourceEntryName(audiosImitacoes[position])

                    ));
                    startActivity(Intent.createChooser(sharingIntent, "Compartilhar via:"));
                }
            });

            return view;
        }
    }
}
