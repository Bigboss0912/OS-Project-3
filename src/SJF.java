import java.util.*;

public class SJF {
    int latency, time;
    ArrayList <Process> processList;
    String output;
    String avgCal, AvgCalculated, avgCalRes, str_format;
    int avgTotal;
    Double avWaitT;

    SJF(int latency, ArrayList <Process> processList){
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
    }

    public static void sortListByBurstTime(ArrayList<Process> list, int n) {
        if (n == 1) {
            return;
        }

        for (int i=0; i < n-1; i++) {
            if (list.get(i).getBurstT() > list.get(i+1).getBurstT()) {
                Process temp = list.get(i);
                list.set(i, list.get(i+1));
                list.set(i+1, temp);
            }
        }
        sortListByBurstTime(list, n-1);
    }

    public void runSchedule() {
        this.sortListByBurstTime(this.processList, this.processList.size());
        for (int i = 0; i < this.processList.size(); i++) {
            if (i == 0){
                this.processList.get(i).setArrivalT(0);
                this.output += "@time = " + this.time + ", " + this.processList.get(i).getPID() +" selected for " +
                        this.processList.get(i).getBurstT() + " units\n";
                this.avgCal += "("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                this.AvgCalculated += (this.time - this.processList.get(i).getArrivalT());
                this.avgCalRes += "("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                this.avgTotal += this.time - this.processList.get(i).getArrivalT();


                this.time += this.processList.get(i).getBurstT();
                if (this.latency != 0) {
                    this.output += "@time = " + this.time + ", context switch 1 occurs\n";
                    this.time+=this.latency;
                }
            } else {
                if (this.latency != 0 && i < this.processList.size()-1) {
                    this.output += "@time = " + this.time + ", " + this.processList.get(i).getPID() +" selected for " +
                            this.processList.get(i).getBurstT() + " units\n";
                    this.avgCal += "+("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - this.processList.get(i).getArrivalT());
                    this.avgTotal += this.time - this.processList.get(i).getArrivalT();
                    this.avgCalRes += "+("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                    this.time += this.processList.get(i).getBurstT();
                    this.output += "@time = " + this.time + ", context switch " + (i+1) + " occurs\n";
                    this.time+=this.latency;

                }
                else{
                    this.output += "@time = " + this.time + ", " + this.processList.get(i).getPID() +" selected for " +
                            this.processList.get(i).getBurstT() + " units\n";
                    this.avgCal += "+("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - this.processList.get(i).getArrivalT());
                    this.avgCalRes += "+("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                    this.avgTotal += this.time - this.processList.get(i).getArrivalT();
                    this.time += this.processList.get(i).getBurstT();
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