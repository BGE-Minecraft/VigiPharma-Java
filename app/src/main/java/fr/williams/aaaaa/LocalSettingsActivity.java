package fr.williams.aaaaa;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LocalSettingsActivity extends AppCompatActivity {

    private Button save, back;
    private EditText txt_serverurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_settings);
        back = findViewById(R.id.back_button_l);
        back = findViewById(R.id.back_button_l);
        back.setOnClickListener(l -> {
            Intent ti = new Intent(LocalSettingsActivity.this, MainActivity.class);
            startActivity(ti);
        });

        txt_serverurl = findViewById(R.id.txt_serverurl);

        save = findViewById(R.id.save_button_l);
        save.setOnClickListener(l -> {
            String input;
            if (txt_serverurl.getText().toString() != null) {
                input = txt_serverurl.getText().toString();
                Utils.ls.setServerUrl(input);
                Toast.makeText(this, "Url serveur défini", Toast.LENGTH_SHORT).show();
            }
        });

    }
}