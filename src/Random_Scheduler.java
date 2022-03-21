import java.util.ArrayList;
import java.util.Random;

public class Random_Scheduler {

    int latency, seed, time;
    ArrayList <Process> processList;
    ArrayList <Process> randomList = new ArrayList<>();
    ArrayList <Process> copyProcessList = new ArrayList<>();
    ArrayList <Process> queue = new ArrayList<>();
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

    public void randProcess(ArrayList <Process> list, int t) {
        copyProcessList.clear();
        for (Process p: list) {
            if (p.getArrivalT() <= t) {
                copyProcessList.add(p);
            }
        }
        while (copyProcessList.size()!=0) {
            int processIndex = this.rand.nextInt(this.copyProcessList.size());
            Process retProcess = this.copyProcessList.get(processIndex);
            this.randomList.add(retProcess);
            this.copyProcessList.remove(processIndex);
        }
    }

    public void runSchedule() {


        for (Process p:
                this.processList) {
            queue.add(p);
        }

        int contexcount = 0;
        Process selectedProc;
        while(!queue.isEmpty()) {

            this.randProcess(queue, this.time);
            if (this.randomList.isEmpty()) {
                selectedProc = queue.get(0);
                queue.remove(0);
            } else {
                selectedProc = this.randomList.get(this.rand.nextInt(this.randomList.size()));
                System.out.println(selectedProc.getPID());
                queue.remove(selectedProc);
                this.randomList.clear();
            }

            if (this.time == 0){

                this.time += selectedProc.getArrivalT();
                this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                        selectedProc.getBurstT() + " units\n";
                this.avgCal += "("+this.time +"-"+selectedProc.getArrivalT()+")";
                this.AvgCalculated += (this.time - selectedProc.getArrivalT());
                this.avgCalRes += "("+this.time +"-"+selectedProc.getArrivalT()+")";
                this.avgTotal += this.time - selectedProc.getArrivalT();


                this.time += selectedProc.getBurstT();
                if (this.latency != 0) {
                    this.output += "@time = " + this.time + ", context switch 1 occurs\n";
                    this.time+=this.latency;
                }

            } else {
                if (this.latency != 0 && !queue.isEmpty()) {
                    if(selectedProc.getArrivalT() > this.time){
                        queue.add(selectedProc);
                        continue;
                    }
                    this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                            selectedProc.getBurstT() + " units\n";
                    this.avgCal += "+("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - selectedProc.getArrivalT());
                    this.avgTotal += this.time - selectedProc.getArrivalT();
                    this.avgCalRes += "+("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.time += selectedProc.getBurstT();
                    contexcount++;
                    this.output += "@time = " + this.time + ", context switch " + contexcount + " occurs\n";
                    this.time+=this.latency;

                }
                else{
                    if(selectedProc.getArrivalT() > this.time){
                        queue.add(selectedProc);
                        continue;
                    }
                    this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                            selectedProc.getBurstT() + " units\n";
                    this.avgCal += "+("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - selectedProc.getArrivalT());
                    this.avgCalRes += "+("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.avgTotal += this.time - selectedProc.getArrivalT();
                    this.time += selectedProc.getBurstT();
                }
            }
        }

        this.output += "@time = " + this.time + ", all processes complete\n";

        this.output += "Completed in " + this. time+ " cycles.\n";
        this.avWaitT = (this.avgTotal/(double)this.processList.size());
        this.str_format = String.format("%.2f",avWaitT);
        this.avgCal += ")/"+this.processList.size() + " = " + this.AvgCalculated+")/"+this.processList.size() + " = " + this.avgTotal + " / "+this.processList.size() +" = "+ str_format;
        this.avgCalRes += ")/"+this.processList.size() + " = " + this.AvgCalculated+")/"+this.processList.size() + " = " + this.avgTotal + " / "+this.processList.size() +" = "+ str_format;
        this.output += this.avgCal +"\n";
        this.output += this.avgCalRes +"\n";
    }

    public String getOutput(){
        return this.output;
    }
}