package fr.williams.aaaaa;

import android.content.Intent;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class SettingsActivity extends AppCompatActivity {

    private Button back_button, save_button;
    private EditText txt_tempmin, txt_tempmax, txt_hummin, txt_hummax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txt_tempmin = findViewById(R.id.txt_tempmin);
        txt_tempmax = findViewById(R.id.txt_tempmax);
        txt_hummin = findViewById(R.id.txt_hummin);
        txt_hummax = findViewById(R.id.txt_hummax);

        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(l -> {
            String input;
            if (txt_tempmin.getText().toString() != null) {
                input = txt_tempmin.getText().toString();
                sendSettings("mintemp=" + input);
            }
            if (txt_tempmax.getText().toString() != null) {
                input = txt_tempmax.getText().toString();
                sendSettings("maxtemp=" + input);
            }
            if (txt_hummin.getText().toString() != null) {
                input = txt_hummin.getText().toString();
                sendSettings("minhum=" + input);
            }
            if (txt_hummax.getText().toString() != null) {
                input = txt_hummax.getText().toString();
                sendSettings("maxhum=" + input);
            }
        });

        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(l -> {
            Intent ti = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(ti);
        });
    }

    private void sendSettings(String txt) {
        CompletableFuture.runAsync(() -> {
            try {
                URL url = new URL("http://192.168.4.1/helloWorld?" + txt);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
               // con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
//                con.setConnectTimeout(5000);
//                con.setReadTimeout(5000);
//                con.setDoOutput(true);
                con.connect();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                }
                in.close();
//                int responseCode = con.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) { // success
//                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String inputLine;
//                    StringBuffer response = new StringBuffer();
//
//                    while ((inputLine = in .readLine()) != null) {
//                        response.append(inputLine);
//                    } in .close();

                    // print result
//                    System.out.println(response.toString());
//                }
                con.disconnect();

            } catch (Exception e) {
//            toast.setDuration();
//            toast.makeText(CONTEXT_RESTRICTED, "MA BITR", Toast.LENGTH_LONG);
                Toast.makeText(this, "MA BITR", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println("No exception occurred");
        });

    }
}