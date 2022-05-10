package fr.williams.aaaaa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataActivity extends AppCompatActivity {

    private int offset = 5;
    private List<DataPoint> dpts = new ArrayList<>();
    private List<DataPoint> dphs = new ArrayList<>();
    private List<DataPoint> dpps = new ArrayList<>();
    private List<DataPoint> dprs = new ArrayList<>();
    private List<DataPoint> dpcs = new ArrayList<>();
    private GraphView graph;
    private GraphView graph2;
    private GraphView graph3;
    private GraphView graph4;
    private GraphView graph5;
    private LineGraphSeries<DataPoint> series;
    private LineGraphSeries<DataPoint> s;
    private LineGraphSeries<DataPoint> s2;
    private LineGraphSeries<DataPoint> s3;
    private LineGraphSeries<DataPoint> s4;
    private Button export, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        graph = (GraphView) findViewById(R.id.graph);
        graph2 = (GraphView) findViewById(R.id.graph2);
        graph3 = (GraphView) findViewById(R.id.graph3);
        graph4 = (GraphView) findViewById(R.id.graph4);
        graph5 = (GraphView) findViewById(R.id.graph5);

//        File f = new File(Utils.sdcard + "/data.json");
        if ((Utils.sdcard == null || !new File(Utils.sdcard).exists()) /*&& !Utils.isWifi(getApplicationContext())*/)
            Toast.makeText(this, "Merci d'insérez la carte sd", Toast.LENGTH_SHORT).show();
        else {
            if (Utils.sdcard != null && new File(Utils.sdcard).exists()) Utils.sdpresent = true;
//            else Utils.wifipresent = true;
        }

        back = findViewById(R.id.back);
        back.setOnClickListener(l -> {
            Intent ti = new Intent(DataActivity.this, MainActivity.class);
            startActivity(ti);
        });

        export = findViewById(R.id.export);
        export.setOnClickListener(l -> {
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
            if ((Utils.sdcard == null || !new File(Utils.sdcard).exists())/* && !Utils.isWifi(getApplicationContext())*/)
                Toast.makeText(this, "Merci d'insérez la carte sd", Toast.LENGTH_SHORT).show();
            else {
                if (Utils.sdcard != null && new File(Utils.sdcard).exists()) Utils.sdpresent = true;
                //  else Utils.wifipresent = true;
            }
            if (Utils.sdpresent) {
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
                            sendDatas(obj);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // Arrays.asList(f.list()).forEach(System.out::println);
                        //Arrays.asList( new File("/storage/emulated").list()).forEach(System.out::println);

//                        Toast.makeText(this, f.exists() + "", Toast.LENGTH_SHORT).show();
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
            } /*else {
                CompletableFuture.runAsync(() -> {
                    try {
                        URL url = new URL("http://192.168.4.1/getDatasFiles");
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("GET");
////                con.setRequestProperty("User-Agent", "Mozilla/5.0");
//                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
////                con.setConnectTimeout(5000);
////                con.setReadTimeout(5000);
//                con.setDoOutput(true);
//                con.connect();
//
//                String inputLine;
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                while ((inputLine = in.readLine()) != null) {
//                    System.out.println(inputLine);
//                }
//                in.close();
//                con.disconnect();
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        int responseCode = con.getResponseCode();
//                System.out.println("\nSending 'GET' request to URL : " + url);
//                System.out.println("Response Code : " + responseCode);
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        //print in String
                        System.out.println(response.toString());
                        //Read JSON response and print
                        JSONObject resp = new JSONObject(response.toString());
                        int size = resp.getInt("Size");
                        System.out.println(size);
                        for (int i = 0; i < size; i++) {
                            url = new URL("http://192.168.4.1/getDatasByFile?id=" + i);
                            con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");
                            con.setRequestProperty("User-Agent", "Mozilla/5.0");
                            responseCode = con.getResponseCode();
//                System.out.println("\nSending 'GET' request to URL : " + url);
//                System.out.println("Response Code : " + responseCode);
                            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            response = new StringBuffer();
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();
                            System.out.println(response.toString());
                            try {
                                if(response.equals("") || response.equals("null")) continue;
                                resp = new JSONObject(response.toString());
                                if(resp == null) continue;
                                setDatas(resp);
                            } catch (Exception e){
                                continue;
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).thenRun(() -> System.out.println("No exception occurred"));
            }*/
        });

//        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//        if (mWifi.isConnected() == false) {
//            Toast.makeText(this, "Merci d'insérer la carte sd", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            System.out.println(mWifi.getExtraInfo());
//            Toast.makeText(this, "WIFI is Enabled in your devide", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (Utils.sdpresent) {
            CompletableFuture.runAsync(() -> {
                for (File f : new File(Utils.sdcard + "/Datas").listFiles()) {
                    System.out.println(f.getAbsolutePath());
                    System.out.println(f.exists());
//            if (!f.exists()) {
//                Toast.makeText(this, "Merci d'insérer la carte sd", Toast.LENGTH_SHORT).show();
//                return;
//            }

                    try {
                        InputStream is = new FileInputStream(f);
                        int size = is.available();
                        byte[] buffer = new byte[size];
                        is.read(buffer);
                        is.close();
                        String json = new String(buffer, "UTF-8");
                        JSONObject obj = new JSONObject(json);
                        setDatas(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setGraphs();
            }).thenRun(() -> System.out.println("No exception occurred"));
            return;
        }/* else {
            CompletableFuture.runAsync(() -> {
                try {
                    URL url = new URL("http://192.168.4.1/getDatasFiles");
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("GET");
////                con.setRequestProperty("User-Agent", "Mozilla/5.0");
//                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
////                con.setConnectTimeout(5000);
////                con.setReadTimeout(5000);
//                con.setDoOutput(true);
//                con.connect();
//
//                String inputLine;
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                while ((inputLine = in.readLine()) != null) {
//                    System.out.println(inputLine);
//                }
//                in.close();
//                con.disconnect();
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
//                System.out.println("\nSending 'GET' request to URL : " + url);
//                System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    //print in String
                    System.out.println(response.toString());
                    //Read JSON response and print
                    JSONObject resp = new JSONObject(response.toString());
                    int size = resp.getInt("Size");
                    System.out.println(size);
                    for (int i = 0; i < size; i++) {
                        url = new URL("http://192.168.4.1/getDatasByFile?id=" + i);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        responseCode = con.getResponseCode();
//                System.out.println("\nSending 'GET' request to URL : " + url);
//                System.out.println("Response Code : " + responseCode);
                        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        System.out.println(response.toString());
                        try {
                            if(response.equals("") || response.equals("null")) continue;
                            resp = new JSONObject(response.toString());
                            if(resp == null) continue;
                            setDatas(resp);
                        } catch (Exception e){
                            continue;
                        }

                    }
                    setGraphs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).thenRun(() -> System.out.println("No exception occurred"));
        }*/


//        File sdCardDir = new File("F://test/");
//
//        for (File f : sdCardDir.listFiles()) {
//            JSONParser jsonParser = new JSONParser();
//            if (f.getName().equals("data.json")) continue;
//            try {
//                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(f));
//                System.out.println(jsonObject.size());
//                for (int i = 0; i < jsonObject.size(); i++) {
//                    System.out.println(i);
//                    System.out.println(jsonObject.get(i + ""));
//                    JSONArray arr = (JSONArray) jsonObject.get(i + "");
//                    System.out.println(arr.get(0));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
    }

    private void sendDatas(JSONObject obj) {
        try {
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
//                        try {
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

//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                    }
                }


//                    JSONObject temp = ar.getJSONObject(0);
//                    JSONObject hum = ar.getJSONObject(1);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setDatas(JSONObject obj) {
        try {
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
                    double temp = 0, hum = 0, pitch = 0, roll = 0, choc = 0;
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
                    if (jo.has("roll")) {
                        roll = jo.getDouble("roll");
                        System.out.println(jo.get("roll"));
                    }
                    if (jo.has("choc")) {
                        choc = jo.getDouble("choc");
                        System.out.println(jo.get("choc"));
                    }
                    //if (!jo.has("alert")) continue;
                    if (jo.has("alert")) {
                        alert = jo.getString("alert");
                        System.out.println(jo.get("alert"));
                    }
//                    int offsetc = offset;
                    dpts.add(new DataPoint(offset, temp));
                    dphs.add(new DataPoint(offset, hum));
                    dpps.add(new DataPoint(offset, pitch));
                    dprs.add(new DataPoint(offset, roll));
                    dpcs.add(new DataPoint(offset, choc));
                    offset = offset + 5;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setGraphs(){
        series = new LineGraphSeries<>(dpts.stream().toArray(DataPoint[]::new));
        s = new LineGraphSeries<>(dphs.stream().toArray(DataPoint[]::new));
        s2 = new LineGraphSeries<>(dpps.stream().toArray(DataPoint[]::new));
        s3 = new LineGraphSeries<>(dprs.stream().toArray(DataPoint[]::new));
        s4 = new LineGraphSeries<>(dpcs.stream().toArray(DataPoint[]::new));
        System.out.println(dpts.size());
        System.out.println(dphs.size());
        System.out.println(dpps.size());
        System.out.println(dprs.size());
        System.out.println(dpcs.size());

//        series.
        graph.addSeries(series);
        graph2.addSeries(s);
        graph3.addSeries(s2);
        graph4.addSeries(s3);
        graph5.addSeries(s4);

        graph.setTitle("Températures");
        graph2.setTitle("Humidité");
        graph3.setTitle("Inclinaison X");
        graph4.setTitle("Inclinaison Z");
        graph5.setTitle("Accelération");


        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);

        graph2.getViewport().setScalable(true);
        graph2.getViewport().setScrollable(true);
        graph2.getViewport().setScalableY(true);
        graph2.getViewport().setScrollableY(true);

        graph3.getViewport().setScalable(true);
        graph3.getViewport().setScrollable(true);
        graph3.getViewport().setScalableY(true);
        graph3.getViewport().setScrollableY(true);

        graph4.getViewport().setScalable(true);
        graph4.getViewport().setScrollable(true);
        graph4.getViewport().setScalableY(true);
        graph4.getViewport().setScrollableY(true);

        graph5.getViewport().setScalable(true);
        graph5.getViewport().setScrollable(true);
        graph5.getViewport().setScalableY(true);
        graph5.getViewport().setScrollableY(true);
    }
}