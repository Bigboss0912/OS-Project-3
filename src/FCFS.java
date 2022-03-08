import java.util.*;

public class FCFS {

    int quantum,  latency, time;
    ArrayList <Process> processList = new ArrayList<Process>();

    FCFS(int quantum, int latency, ArrayList <Process> processList){
        this.quantum = quantum;
        this.latency = latency;
        this.processList = processList;
        this.time = 0;
    }


    public void runSchedule() {
        for (Process p: this.processList) {

        }
    }
}