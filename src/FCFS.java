import java.sql.SQLOutput;
import java.util.*;

public class FCFS {

    String temp_pid;
    int quantum,  latency, time, temp_burst;
    ArrayList <Process> processList = new ArrayList<>();

    FCFS(int latency){
        this.latency = latency;
        this.time = 0;
    }

    public void setProcessList(ArrayList<Process> processList) {
        this.processList = processList;
    }

    public void listtest(){
        System.out.println(this.processList);
    }


    public String runSchedule() {
        for (Process p: this.processList) {
            if (this.time == 0){
                this.time += p.getBurstT();
                this.time+=this.latency;
                temp_pid = p.getPID();
                temp_burst = p.getBurstT();

            }
            else {
                this.time += p.getBurstT();
                this.time += this.latency;
                temp_pid = p.getPID();
                temp_burst = p.getBurstT();
            }

        }
        return this.toString();
    }


    public String toString(){
        return "@time = " +this.time + ", " + this.temp_pid+" selected for " + this.temp_burst+ " units\n";
    }
}