package fr.williams.aaaaa;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class SettingsActivity extends AppCompatActivity {

    private Button back_button, save_button;
    private EditText txt_tempmin, txt_tempmax, txt_hummin, txt_hummax, txt_minpitch, txt_maxpitch, txt_minroll, txt_maxroll, txt_maxchoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txt_tempmin = findViewById(R.id.txt_tempmin);
        txt_tempmax = findViewById(R.id.txt_tempmax);
        txt_hummin = findViewById(R.id.txt_hummin);
        txt_hummax = findViewById(R.id.txt_hummax);

        txt_minpitch = findViewById(R.id.txt_minpitch);
        txt_maxpitch = findViewById(R.id.txt_maxpitch);
        txt_minroll = findViewById(R.id.txt_minroll);
        txt_maxroll = findViewById(R.id.txt_maxroll);
        txt_maxchoc = findViewById(R.id.txt_maxchoc);

        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(l -> {
//            if ((Utils.sdcard == null || !new File(Utils.sdcard).exists()) && !Utils.isWifi(getApplicationContext()))
//                Toast.makeText(this, "Merci d'insérez la carte sd ou de vous connectez au réseau de votre module VigiPharma", Toast.LENGTH_SHORT).show();
//            else {
//                if (Utils.sdcard != null && new File(Utils.sdcard).exists()) Utils.sdpresent = true;
//                else Utils.wifipresent = true;
//            }
//
//            if (Utils.wifipresent) {
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
                if (txt_minpitch.getText().toString() != null) {
                    input = txt_minpitch.getText().toString();
                    sendSettings("minpitch=" + input);
                }
                if (txt_maxpitch.getText().toString() != null) {
                    input = txt_maxpitch.getText().toString();
                    sendSettings("maxpitch=" + input);
                }
                if (txt_minroll.getText().toString() != null) {
                    input = txt_minroll.getText().toString();
                    sendSettings("minroll=" + input);
                }
                if (txt_maxroll.getText().toString() != null) {
                    input = txt_maxroll.getText().toString();
                    sendSettings("maxroll=" + input);
                }
                if (txt_maxchoc.getText().toString() != null) {
                    input = txt_maxchoc.getText().toString();
                    sendSettings("maxchoc=" + input);
                }
//            } else {
//                try {
//                    InputStream is = new FileInputStream(Utils.sdcard + "/config.json");
//                    int size = is.available();
//                    byte[] buffer = new byte[size];
//                    is.read(buffer);
//                    is.close();
//                    String json = new String(buffer, "UTF-8");
//                    JSONObject obj = new JSONObject(json);
//                    System.out.println(obj.toString());
//                    if (txt_tempmin.getText().toString() != null)
//                        obj.put("mintemp", txt_tempmin.getText().toString());
//
//                    if (txt_tempmax.getText().toString() != null)
//                        obj.put("maxtemp", txt_tempmax.getText().toString());
//
//                    if (txt_hummin.getText().toString() != null)
//                        obj.put("minhum", txt_hummin.getText().toString());
//
//                    if (txt_hummax.getText().toString() != null)
//                        obj.put("maxhum", txt_hummax.getText().toString());
//
//                    if (txt_minpitch.getText().toString() != null)
//                        obj.put("minpitch", txt_minpitch.getText().toString());
//
//                    if (txt_maxpitch.getText().toString() != null)
//                        obj.put("maxpitch", txt_maxpitch.getText().toString());
//
//                    if (txt_minroll.getText().toString() != null)
//                        obj.put("minroll", txt_minroll.getText().toString());
//
//                    if (txt_maxroll.getText().toString() != null)
//                        obj.put("maxroll", txt_maxroll.getText().toString());
//
//                    if (txt_maxchoc.getText().toString() != null)
//                        obj.put("maxchoc", txt_maxchoc.getText().toString());
//
//                    String jsonString = obj.toString();
//                    System.out.println(jsonString);
//                    //String jsonString = json.toString();
//
//                    FileOutputStream fos = new FileOutputStream(Utils.sdcard + "/config.json");
//                    fos.write(jsonString.getBytes());
//                    fos.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
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
                Toast.makeText(this, "Merci de vous connectez au réseau wifi Vigipharma", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println("No exception occurred");
        });

    }
}