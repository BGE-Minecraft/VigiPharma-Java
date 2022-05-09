package fr.williams.aaaaa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataActivity extends AppCompatActivity {

    private int offset = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        GraphView graph2 = (GraphView) findViewById(R.id.graph2);
        GraphView graph3 = (GraphView) findViewById(R.id.graph3);
        GraphView graph4 = (GraphView) findViewById(R.id.graph4);
        GraphView graph5 = (GraphView) findViewById(R.id.graph5);
        List<DataPoint> dpts = new ArrayList<>();
        List<DataPoint> dphs = new ArrayList<>();
        List<DataPoint> dpps = new ArrayList<>();
        List<DataPoint> dprs = new ArrayList<>();
        List<DataPoint> dpcs = new ArrayList<>();

//        File f = new File(Utils.sdcard + "/data.json");
        if ((Utils.sdcard == null || !new File(Utils.sdcard).exists()) && !Utils.isWifi(getApplicationContext()))
            Toast.makeText(this, "Merci d'insérez la carte sd ou de vous connectez au wifi du module Vigipharma", Toast.LENGTH_SHORT).show();
        else {
            if (Utils.sdcard != null && new File(Utils.sdcard).exists()) Utils.sdpresent = true;
            else Utils.wifipresent = true;
        }

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
        if(Utils.sdpresent) {
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
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dpts.stream().toArray(DataPoint[]::new));
                LineGraphSeries<DataPoint> s = new LineGraphSeries<>(dphs.stream().toArray(DataPoint[]::new));
                LineGraphSeries<DataPoint> s2 = new LineGraphSeries<>(dpps.stream().toArray(DataPoint[]::new));
                LineGraphSeries<DataPoint> s3 = new LineGraphSeries<>(dprs.stream().toArray(DataPoint[]::new));
                LineGraphSeries<DataPoint> s4 = new LineGraphSeries<>(dpcs.stream().toArray(DataPoint[]::new));
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
            }).thenRun(() -> System.out.println("No exception occurred"));
        return;
        }
        else {
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
                     resp = new JSONObject(response.toString());

                }
            } catch (Exception e){
                e.printStackTrace();
            }
            }).thenRun(() -> System.out.println("No exception occurred"));
        }


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
}