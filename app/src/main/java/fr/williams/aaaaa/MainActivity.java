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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button settings, datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        settings = findViewById(R.id.settings_button);
        settings.setOnClickListener(l -> {
            //startActivity(new Intent(getApplicationContext(),SettingsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //setContentView(R.layout.activity_settings);
            //new SettingsActivity(savedInstanceState);
            Intent ti = new Intent(MainActivity.this, SettingsActivity.class);
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

            File f = new File("/storage/2D87-DCB1/data.json");
            System.out.println(f.getAbsolutePath());
            System.out.println(f.exists());


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

                    for (int j = 0; j < ay.length(); j++) {
                        System.out.println(ay.get(j));
                        JSONObject jo = ay.getJSONObject(j);
                        if(jo.has("temp")) System.out.println(jo.get("temp"));
                        if(jo.has("hum")) System.out.println(jo.get("hum"));
                        if(jo.has("pitch")) System.out.println(jo.get("pitch"));
                        if(jo.has("roll")) System.out.println(jo.get("roll"));
                        if(jo.has("roll")) System.out.println(jo.get("alert"));

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

        });
        try {
            //if(Utils.sdCardDir == null) tv.setText("Aucune carte sd");
            //else
            tv.setText("Connectez vous au module VigiPharma\nou brancher la carte sd du module dans la tablette");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        findViewById(R.id.button);
    }
}