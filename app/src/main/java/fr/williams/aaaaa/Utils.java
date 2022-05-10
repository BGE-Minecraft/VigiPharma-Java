package fr.williams.aaaaa;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    //public static File sdCardDir = Environment.getExternalStorageDirectory();

    public static LocalSettings ls = new LocalSettings();
    public static String sdcard;
    public static boolean /*wifipresent = false,*/ sdpresent = false;

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

//    public static boolean isWifi(Context context){
//        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        if (manager.isWifiEnabled()) {
//            WifiInfo wifiInfo = manager.getConnectionInfo();
//            if (wifiInfo == null)  return false;
////                Toast.makeText(context, "Merci d'insérer la carte sd ou de vous connté au Module Vigipharma via le wifi", Toast.LENGTH_SHORT).show();
//             else {
//                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
//                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR){
//                    Utils.wifipresent = true;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }



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
