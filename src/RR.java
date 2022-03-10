import java.util.ArrayList;

public class RR {

    int quantum, latency, time;
    ArrayList <Process> processList = new ArrayList<>();
    String output;

    RR(int quantum, int latency, ArrayList <Process> processList){
        this.quantum = quantum;
        this.latency = latency;
        this.time = 0;
        this.processList = processList;
        this.output = "";
    }

    public void runSchedule() {

        for (int i = 0; i < this.processList.size(); i++) {
            if (i == 0){
                this.output += "@time = " + this.time + ", " + this.processList.get(i).getPID() +" selected for " +
                        this.processList.get(i).getBurstT() + " units\n";
                this.time += this.processList.get(i).getBurstT();
                this.time+=this.latency;
            } else {
                this.output += "@time = " + this.time + ", " + this.processList.get(i).getPID() +" selected for " +
                        this.processList.get(i).getBurstT() + " units\n";
                this.time += this.processList.get(i).getBurstT();
                this.time += this.latency;
            }
        }
    }

    public String getOutput(){
        return this.output;
    }
}