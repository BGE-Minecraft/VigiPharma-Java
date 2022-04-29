package fr.williams.aaaaa;

import android.os.Environment;

import java.io.File;

public class Utils {

    public static File sdCardDir = Environment.getExternalStorageDirectory();

//
//        for (File f : sdCardDir.listFiles()) {
//            JSONParser jsonParser = new JSONParser();
//            if (f.getName().equals("config.json")) continue;
//            try {
//                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(f));
//                System.out.println(jsonObject.size());
//                for (int i = 0; i < jsonObject.size(); i++) {
//                    System.out.println(i);
//                    System.out.println(jsonObject.get(i + ""));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
}
