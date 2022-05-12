package fr.williams.aaaaa;

public class TempData {

    private final String alert;
    private final double temp, hum, pitch, roll, choc;

    public TempData(double temp, double hum, double pitch, double roll, double choc, String alert) {
        this.temp = temp;
        this.hum = hum;
        this.pitch = pitch;
        this.roll = roll;
        this.choc = choc;
        this.alert = alert;
    }

    public double getChoc() {
        return choc;
    }

    public double getHum() {
        return hum;
    }

    public double getPitch() {
        return pitch;
    }

    public double getRoll() {
        return roll;
    }

    public double getTemp() {
        return temp;
    }

    public String getAlert() {
        return alert;
    }

//    public void setAlert(String alert) {
//        this.alert = alert;
//    }
//
//    public void setChoc(float choc) {
//        this.choc = choc;
//    }
//
//    public void setHum(float hum) {
//        this.hum = hum;
//    }
//
//    public void setPitch(float pitch) {
//        this.pitch = pitch;
//    }
//
//    public void setRoll(float roll) {
//        this.roll = roll;
//    }
//
//    public void setTemp(float temp) {
//        this.temp = temp;
//    }
    
}
