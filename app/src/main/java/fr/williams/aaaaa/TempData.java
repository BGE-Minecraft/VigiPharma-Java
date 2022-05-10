package fr.williams.aaaaa;

public class TempData {

    private String alert;
    private float temp, hum, pitch, roll, choc;

    public TempData() {
        this.temp = temp;
        this.hum = hum;
        this.pitch = pitch;
        this.roll = roll;
        this.choc = choc;
        this.alert = alert;
    }

    public float getChoc() {
        return choc;
    }

    public float getHum() {
        return hum;
    }

    public float getPitch() {
        return pitch;
    }

    public float getRoll() {
        return roll;
    }

    public float getTemp() {
        return temp;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void setChoc(float choc) {
        this.choc = choc;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
    
}
