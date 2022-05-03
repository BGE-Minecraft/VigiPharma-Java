package fr.williams.aaaaa;

public class LocalSettings {

    private String serverurl;

    public LocalSettings(){
        serverurl = "http://172.17.1.185/RestAPI/api/";
    }

    public String getServerurl() {
        return serverurl;
    }

    public void setServerurl(String serverurl) {
        this.serverurl = serverurl;
    }
}
