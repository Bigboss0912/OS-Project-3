import java.util.*;

public class FCFS {

    int latency, time;
    ArrayList <Process> processList = new ArrayList<>();
    String output;

    FCFS(int latency, ArrayList <Process> processList){
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
                if (this.latency != 0) {
                    this.output += "@time = " + this.time + ", context switch 1 occurs\n";
                    this.time+=this.latency;
                }
            } else {
                this.output += "@time = " + this.time + ", " + this.processList.get(i).getPID() +" selected for " +
                        this.processList.get(i).getBurstT() + " units\n";
                this.time += this.processList.get(i).getBurstT();
                if (this.latency != 0 || i != this.processList.size()) {
                    this.output += "@time = " + this.time + ", context switch " + (i+1) + " occurs\n";
                    this.time+=this.latency;
                }
            }
        }
        this.output += "@time = " + this.time + ", all processes complete\n";
    }

    public String getOutput(){
        return this.output;
    }
}