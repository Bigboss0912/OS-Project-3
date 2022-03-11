
public class Process {

    String PID;
    int arrivalT, burstT;



    Process (String PID, int arrivalT, int burstT){
        this.PID = PID;
        this.arrivalT = arrivalT;
        this.burstT = burstT;
    }

    public String getPID() {
        return PID;
    }
    public int getArrivalT() {
        return arrivalT;
    }
    public int getBurstT() {
        return burstT;
    }

    public void setArrivalT(int arrivalT) {
        this.arrivalT = arrivalT;
    }

    public void setBurstT(int burstT) {
        this.burstT = burstT;
    }
}
