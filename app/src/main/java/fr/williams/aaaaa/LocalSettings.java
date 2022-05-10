package fr.williams.aaaaa;

public class LocalSettings {

    private String serverUrl;
//    private int idModule;

    public LocalSettings() {
        serverUrl = "http://172.17.1.185/RestAPI/api/";
//        idModule = 1;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverurl) {
        this.serverUrl = serverurl;
    }

//    public int getIdModule() {
//        return idModule;
//    }
//
//    public void setIdModule(int idModule) {
//        this.idModule = idModule;
//    }
}
