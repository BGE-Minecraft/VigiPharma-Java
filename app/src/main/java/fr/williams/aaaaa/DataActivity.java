package fr.williams.aaaaa;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataActivity extends AppCompatActivity {

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

        File f = new File(Utils.sdcard + "/data.json");
        System.out.println(f.getAbsolutePath());
        System.out.println(f.exists());
        if (!f.exists()) {
            Toast.makeText(this, "Merci d'insérer la carte sd", Toast.LENGTH_SHORT).show();
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
            int offset = 5;
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
                        if (jo.has("roll")){
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
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dpts.stream().toArray(DataPoint[]::new));
        LineGraphSeries<DataPoint> s = new LineGraphSeries<>(dphs.stream().toArray(DataPoint[]::new));
        LineGraphSeries<DataPoint> s2 = new LineGraphSeries<>(dpps.stream().toArray(DataPoint[]::new));
        LineGraphSeries<DataPoint> s3 = new LineGraphSeries<>(dprs.stream().toArray(DataPoint[]::new));
        LineGraphSeries<DataPoint> s4 = new LineGraphSeries<>(dpcs.stream().toArray(DataPoint[]::new));
//        series.
        graph.addSeries(series);
        graph2.addSeries(s);
        graph3.addSeries(s2);
        graph4.addSeries(s3);
        graph5.addSeries(s4);

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