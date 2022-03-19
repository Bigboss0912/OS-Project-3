import java.util.ArrayList;
import java.util.Random;

public class Random_Scheduler {

    int latency, seed, time;
    ArrayList <Process> processList;
    ArrayList <Process> randomList = new ArrayList<>();
    ArrayList <Process> copyProcessList = new ArrayList<>();
    String output;
    String avgCal, AvgCalculated, avgCalRes, str_format;
    int avgTotal;
    Double avWaitT;
    Random rand = new Random();

    Random_Scheduler(int seed, int latency, ArrayList <Process> processList){
        this.seed = seed;
        this.latency = latency;
        this.time = 0;
        this.processList = processList;
        this.output = "";
        this.avgCal ="Avg Wait Time = (";
        this.avgCalRes = "Avg resp time = (";
        this.AvgCalculated = "(";
        this. avgTotal =0;
        this.avWaitT = 0.00;
        this.str_format="";

        this.rand.setSeed(this.seed);
    }

    public void randProcess() {
        for (Process p:processList) {
            copyProcessList.add(p);
        }
        while (copyProcessList.size()!=0) {
            int processIndex = this.rand.nextInt(this.copyProcessList.size());
            Process retProcess = this.copyProcessList.get(processIndex);
            this.randomList.add(retProcess);
            this.copyProcessList.remove(processIndex);
        }
    }

    public void runSchedule() {
        this.randProcess();
        for (int i = 0; i < this.randomList.size(); i++) {
            if (i == 0){
                this.time += this.processList.get(i).getArrivalT();
                this.output += "@time = " + this.time + ", " + this.randomList.get(i).getPID() +" selected for " +
                        this.randomList.get(i).getBurstT() + " units\n";
                this.avgCal += "("+this.time +"-"+this.randomList.get(i).getArrivalT()+")";
                this.AvgCalculated += (this.time - this.randomList.get(i).getArrivalT());
                this.avgCalRes += "("+this.time +"-"+this.randomList.get(i).getArrivalT()+")";
                this.avgTotal += this.time - this.randomList.get(i).getArrivalT();


                this.time += this.randomList.get(i).getBurstT();
                if (this.latency != 0) {
                    this.output += "@time = " + this.time + ", context switch 1 occurs\n";
                    this.time+=this.latency;
                }
            } else {
                if (this.latency != 0 && i < this.randomList.size()-1) {
                    this.output += "@time = " + this.time + ", " + this.randomList.get(i).getPID() +" selected for " +
                            this.randomList.get(i).getBurstT() + " units\n";
                    this.avgCal += "+("+this.time +"-"+this.randomList.get(i).getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - this.randomList.get(i).getArrivalT());
                    this.avgTotal += this.time - this.randomList.get(i).getArrivalT();
                    this.avgCalRes += "+("+this.time +"-"+this.randomList.get(i).getArrivalT()+")";
                    this.time += this.randomList.get(i).getBurstT();
                    this.output += "@time = " + this.time + ", context switch " + (i+1) + " occurs\n";
                    this.time+=this.latency;

                }
                else{
                    this.output += "@time = " + this.time + ", " + this.randomList.get(i).getPID() +" selected for " +
                            this.randomList.get(i).getBurstT() + " units\n";
                    this.avgCal += "+("+this.time +"-"+this.randomList.get(i).getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - this.randomList.get(i).getArrivalT());
                    this.avgCalRes += "+("+this.time +"-"+this.randomList.get(i).getArrivalT()+")";
                    this.avgTotal += this.time - this.randomList.get(i).getArrivalT();
                    this.time += this.randomList.get(i).getBurstT();
                }
            }
        }

        this.output += "@time = " + this.time + ", all processes complete\n";

        this.output += "Completed in " + this. time+ " cycles.\n";
        this.avWaitT = (this.avgTotal/(double)this.randomList.size());
        this.str_format = String.format("%.2f",avWaitT);
        this.avgCal += ")/"+this.randomList.size() + " = " + this.AvgCalculated+")/"+this.randomList.size() + " = " + this.avgTotal + " / "+this.randomList.size() +" = "+ str_format;
        this.avgCalRes += ")/"+this.randomList.size() + " = " + this.AvgCalculated+")/"+this.randomList.size() + " = " + this.avgTotal + " / "+this.randomList.size() +" = "+ str_format;
        this.output += this.avgCal +"\n";
        this.output += this.avgCalRes +"\n";
    }

    public String getOutput(){
        return this.output;
    }
}