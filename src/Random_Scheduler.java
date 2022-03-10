import java.util.ArrayList;
import java.util.Random;

public class Random_Scheduler {

    int seed, latency, time;
    ArrayList<Process> processList = new ArrayList<>();
    String output;
    Random rand = new Random();

    Random_Scheduler(int seed, int latency, ArrayList <Process> processList){
        this.seed = seed;
        this.latency = latency;
        this.time = 0;
        this.processList = processList;
        this.output = "";
        this.rand = rand;

        this.rand.setSeed(this.seed);
    }

    public Process randProcess() {
        int processIndex = rand.nextInt(this.processList.size());
        Process retProcess = this.processList.get(processIndex);
        this.processList.remove(processIndex);

        return retProcess;
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