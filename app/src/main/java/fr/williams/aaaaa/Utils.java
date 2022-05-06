package fr.williams.aaaaa;

import android.os.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    //public static File sdCardDir = Environment.getExternalStorageDirectory();

    public static LocalSettings ls = new LocalSettings();
    public static String sdcard;

    public Utils(){
        File storage = new File("/storage/");
        List<String> fs = Arrays.asList(storage.list());
        System.out.println(storage.exists());
        fs.forEach(System.out::println);
        fs = fs.stream().filter(s -> !s.equals("emulated") && !s.equals("self")).collect(Collectors.toList());
        fs.forEach(System.out::println);
        System.out.println(fs.size());
        if(fs.isEmpty()) return;
        sdcard = "/storage/" + fs.get(0);
    }



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
