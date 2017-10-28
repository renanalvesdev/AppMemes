package com.example.renan.appmemes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ActImitacoes extends AppCompatActivity {

    private static final int REQUEST_READ_STORAGE = 112 ;// REQUEST_READ_STORAGE = 113;


    MediaPlayer mp ;

    ListView mListView;

    int[] audiosRage = new int[]{
            R.raw.fino_mas_o_que,
            R.raw.im_pirula_mas_o_que,
            R.raw.im_felipe_neto_malakoi,
            R.raw.im_maestro_bandjido,
            R.raw.im_maestro_bofobics,
            R.raw.im_maestro_dando_boura,
            R.raw.im_maestro_ilicito,
            R.raw.im_pirula_ameacas_crentes,
            R.raw.im_pirula_ameacas_mesmo,
            R.raw.im_pirula_mosexual

    };

    String[] frasesDiversas = {
            "Mickey Moura - Mas o que é isso ?",
            "Mas o que é isso !?",
            "Malakoi",
            "Bandjido !",
            "Mophobics",
            "Ahh Dando Moura",
            "Ilícito",
            "Esse tipo de ameaça só parte dos crentes",
            "Ameaças, ameaças mesmo",
            "Cavalos marinhos mossexuais",

    };

    public ActImitacoes() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_imitacoes);

        //permissao
        EnableRuntimePermission(REQUEST_READ_STORAGE);


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

                /*audiosRage[position]*/

                mp = MediaPlayer.create(ActImitacoes.this, audiosRage[position]);
                mp.start();


            }
        });

        //clicando para compartilhar o áudio via whatsapp


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_READ_STORAGE:

                if (grantResults.length > 0) {

                    boolean writeExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (writeExternalFile) {
                    } else {
                        Toast.makeText(ActImitacoes.this, "Allow permissions to Edit the Image", Toast.LENGTH_LONG).show();
                    }

                }
                break;
        }
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_item, null);

            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view.findViewById(R.id.textView);
            ImageButton shareButton = (ImageButton) view.findViewById(R.id.shareButton);


            //  mImageView.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            mTextView.setText(frasesDiversas[position]);



            //implementando o compartilhamento dos áudios
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v)
                {
                    InputStream inputStream;
                    FileOutputStream fileOutputStream;

                    //teste depois sem absolute Path
                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/appMemes2/");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }

                    try
                    {
                        inputStream = getResources().openRawResource(audiosRage[position]);
                        fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory()+"/appMemes2/",
                                getResources().getResourceEntryName(audiosRage[position])+".mp3" ));

                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = inputStream.read(buffer)) > 0)
                        {
                            fileOutputStream.write(buffer, 0, length);
                        }

                        inputStream.close();
                        fileOutputStream.close();


                    }

                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_STREAM,
                            Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/appMemes2/"+getResources().getResourceEntryName(audiosRage[position])+".mp3" ));
                    intent.setType("audio/*");
                    startActivity(Intent.createChooser(intent, "Share sound"));
                }
            });

            return view;
        }
    }



    //método para compartilhamento


    private void EnableRuntimePermission(int codigo) {
        if (ContextCompat.checkSelfPermission(ActImitacoes.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (ActImitacoes.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(ActImitacoes.this, "Allow permissions", Toast.LENGTH_LONG).show();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, codigo);
                }
            }
        }
    }




}
