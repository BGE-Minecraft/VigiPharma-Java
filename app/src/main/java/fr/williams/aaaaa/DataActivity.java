package fr.williams.aaaaa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
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