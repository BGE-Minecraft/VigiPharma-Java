package fr.williams.aaaaa;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button settings, datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        settings =  findViewById(R.id.settings_button);
        settings.setOnClickListener(l -> {
            //startActivity(new Intent(getApplicationContext(),SettingsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //setContentView(R.layout.activity_settings);
            //new SettingsActivity(savedInstanceState);
            Intent ti = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(ti);
        });
        datas =  findViewById(R.id.data_button);
//        datas.setOnClickListener(l -> setContentView(R.layout.activity_datas));
        try {
            //if(Utils.sdCardDir == null) tv.setText("Aucune carte sd");
            //else
            tv.setText("Connectez vous au module VigiPharma\nou brancher la carte sd du module dans la tablette");
        } catch (Exception e){
            e.printStackTrace();
        }

//        findViewById(R.id.button);
    }
}