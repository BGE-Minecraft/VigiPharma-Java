package fr.williams.aaaaa;

import android.content.Intent;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button settings, datas, curves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        File storage = new File("/storage/");
//        List<String> fs = Arrays.asList(storage.list());
//        System.out.println(storage.exists());
//        fs.forEach(System.out::println);
//        fs = fs.stream().filter(s -> !s.equals("emulated") && !s.equals("self")).collect(Collectors.toList());
//        fs.forEach(System.out::println);
//        System.out.println(fs.size());
//        final String sf = fs.get(0);
        new Utils();
        tv = findViewById(R.id.textView);
        settings = findViewById(R.id.settings_button);
        curves = findViewById(R.id.curves_button);
        settings.setOnClickListener(l -> {
            //startActivity(new Intent(getApplicationContext(),SettingsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //setContentView(R.layout.activity_settings);
            //new SettingsActivity(savedInstanceState);
            Intent ti = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(ti);
        });

        curves.setOnClickListener(l -> {
            Intent ti = new Intent(MainActivity.this, DataActivity.class);
            startActivity(ti);
        });

        datas = findViewById(R.id.data_button);
        datas.setOnClickListener(l -> {
//            String sdpath = null, sd1path = null, usbdiskpath = null, sd0path = null;
//
//            if (new File("/storage/extSdCard/").exists()) {
//                sdpath = "/storage/extSdCard/";
//                System.out.println("Sd Cardext Path " + sdpath);
//            }
//            if (new File("/storage/sdcard1/").exists()) {
//                sd1path = "/storage/sdcard1/";
//                System.out.println("Sd Card1 Path " + sd1path);
//            }
//            if (new File("/storage/usbcard1/").exists()) {
//                usbdiskpath = "/storage/usbcard1/";
//                System.out.println("USB Path " + usbdiskpath);
//            }
//            if (new File("/storage/sdcard0/").exists()) {
//                sd0path = "/storage/sdcard0/";
//                System.out.println("Sd Card0 Path " + sd0path);
//            }
//
//            List<String> ls = Arrays.asList(sdpath, sd1path, usbdiskpath, sd0path);
//            ls = ls.stream().filter(f -> f != null).collect(Collectors.toList());
//            if (ls.size() == 0) {
//                Toast.makeText(this, "vraiment la y a pas de carte sd N WORD", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            System.out.println(ls.size());
//            ls.forEach(s -> {
//                if (s != null && s != "") {
            CompletableFuture.runAsync(() -> {
//                File f = new File("/storage/2D87-DCB1/data.json"
                System.out.println(new File(Utils.sdcard + "/Datas").getAbsolutePath());
                for (File f : new File(Utils.sdcard + "/Datas").listFiles()) {
//                File f = new File(Utils.sdcard + "/data.json");

                    System.out.println(f.getAbsolutePath());
                    System.out.println(f.exists());
                    if (!f.exists()) {
                        Toast.makeText(this, "Merci d'insérée la carte sd", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        InputStream is = new FileInputStream(f);
                        int size = is.available();
                        byte[] buffer = new byte[size];
                        is.read(buffer);
                        is.close();
                        String json = new String(buffer, "UTF-8");
                        JSONObject obj = new JSONObject(json);
                        JSONArray arr = obj.names();
                        System.out.println(arr);
                        for (int i = 0; i < arr.length(); i++) {
                            String ar = arr.getString(i);
                            System.out.println(ar);
                            JSONArray ay = obj.getJSONArray(ar);
                            if (ay.length() == 0) continue;

                            for (String urltype : Arrays.asList("createalert.php", "createmesure.php")) {
                                for (int j = 0; j < ay.length(); j++) {
                                    System.out.println(ay.get(j));
                                    JSONObject jo = ay.getJSONObject(j);
                                    double temp = 0, hum = 0, pitch = 0;
                                    String alert = null;
                                    if (jo.has("temp")) {
                                        temp = jo.getDouble("temp");
                                        System.out.println(jo.get("temp"));
                                    }
                                    if (jo.has("hum")) {
                                        hum = jo.getDouble("hum");
                                        System.out.println(jo.get("hum"));
                                    }
                                    if (jo.has("pitch")) {
                                        pitch = jo.getDouble("pitch");
                                        System.out.println(jo.get("pitch"));
                                    }
//                        if (jo.has("roll")){
//                            System.out.println(jo.get("roll"));
//                        }
                                    //if (!jo.has("alert")) continue;
                                    if (jo.has("alert")) {
                                        alert = jo.getString("alert");
                                        System.out.println(jo.get("alert"));
                                    }
                                    ;
                                    try {
                                        String url;
                                        String datas;
                                        URL lru;
                                        HttpURLConnection con;
                                        byte[] out;
                                        int length;
                                        String inputLine;
                                        BufferedReader in;

                                        switch (urltype) {
                                            case "createalert.php":
                                                if (alert == null || alert == "" || alert == "null") continue;
                                                url = Utils.ls.getServerUrl() + urltype;
                                                lru = new URL(url);
                                                con = (HttpURLConnection) lru.openConnection();
//            byte[] out = "{\"name\":\"test2\",\"min_temp\":\"10\",\"min_hum\":\"45\",\"max_temp\":\"30\",\"max_hum\":\"75\"}" .getBytes(StandardCharsets.UTF_8);
//            byte[] out = "{\"id_Modules\":\"4\",\"temp\":\"10\",\"hum\":\"45\"}" .getBytes(StandardCharsets.UTF_8);
                                                datas = "{\"id_Modules\":\"1\"" +
                                                        ",\"temp\":\"{temp}\"" +
                                                        ",\"hum\":\"{hum}\"" +
                                                        ",\"inclinaison\":\"{inclinaison}\"" +
                                                        ",\"choc\":\"{choc}\"" +
                                                        ",\"type\":\"{type}\"}";
                                                datas = datas.replace("{temp}", temp + "");
                                                datas = datas.replace("{hum}", hum + "");
                                                datas = datas.replace("{inclinaison}", pitch + "");
                                                datas = datas.replace("{choc}", 0 + "");
                                                datas = datas.replace("{type}", alert);
                                                //if (alert == null || alert == "") continue;
                                                System.out.println(datas);

                                                out = datas.getBytes(StandardCharsets.UTF_8);

//            byte[] out = "{\"id_Modules\":\"4\"}" .getBytes(StandardCharsets.UTF_8);

                                                length = out.length;

                                                con.setFixedLengthStreamingMode(length);
                                                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                                con.setConnectTimeout(5000);
                                                con.setReadTimeout(5000);
                                                con.setDoOutput(true);
                                                con.connect();
                                                try (OutputStream os = con.getOutputStream()) {
                                                    os.write(out);
                                                }

                                                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                                while ((inputLine = in.readLine()) != null) {
                                                    System.out.println(inputLine);
                                                }
                                                in.close();
                                                con.disconnect();

                                                break;

                                            case "createmesure.php":
                                                url = Utils.ls.getServerUrl() + urltype;
                                                lru = new URL(url);
                                                con = (HttpURLConnection) lru.openConnection();
                                                datas = "{\"id_Modules\":\"1\"" +
                                                        ",\"temp\":\"{temp}\"" +
                                                        ",\"hum\":\"{hum}\"}";
                                                datas = datas.replace("{temp}", temp + "");
                                                datas = datas.replace("{hum}", hum + "");
                                                System.out.println(datas);

                                                out = datas.getBytes(StandardCharsets.UTF_8);

//            byte[] out = "{\"id_Modules\":\"4\"}" .getBytes(StandardCharsets.UTF_8);

                                                length = out.length;

                                                con.setFixedLengthStreamingMode(length);
                                                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                                con.setConnectTimeout(5000);
                                                con.setReadTimeout(5000);
                                                con.setDoOutput(true);
                                                con.connect();
                                                try (OutputStream os = con.getOutputStream()) {
                                                    os.write(out);
                                                }

                                                in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                                                while ((inputLine = in.readLine()) != null) {
                                                    System.out.println(inputLine);
                                                }
                                                in.close();
                                                con.disconnect();

                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }


//                    JSONObject temp = ar.getJSONObject(0);
//                    JSONObject hum = ar.getJSONObject(1);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Arrays.asList(f.list()).forEach(System.out::println);
                    //Arrays.asList( new File("/storage/emulated").list()).forEach(System.out::println);

                    Toast.makeText(this, f.exists() + "", Toast.LENGTH_SHORT).show();
//                    if(!f.exists()) {
//                        Toast.makeText(this, "vraiment la y a pas de carte sd N WORD", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    //Arrays.asList(f.listFiles()).forEach(System.out::println);
                    // System.out.println(f.listFiles);
//                }

//            });

//           File extStore = Environment.getExternalStorageDirectory();
//           if(extStore == null){
//               Toast.makeText(this, "vraiment la y a pas de carte sd N WORD", Toast.LENGTH_SHORT).show();
//               return;
//           }
//           System.out.println(extStore);
//          // Arrays.asList(Utils.sdCardDir.listFiles()).parallelStream().forEach(file -> ls.add(file.getName()));
//           if(!ls.contains("data.json")){
//               Toast.makeText(this, "vraiment la y a pas de carte sd N WORD", Toast.LENGTH_SHORT).show();
//               return;
//           }

                }
            }).thenRun(() -> System.out.println("No exception occurred"));
        });
        try {
            //if(Utils.sdCardDir == null) tv.setText("Aucune carte sd");
            //else
            tv.setText("Connectez vous au module VigiPharma\nou branchez la carte sd du module dans la tablette");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        findViewById(R.id.button);
    }
}