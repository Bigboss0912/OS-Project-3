import java.util.*;

public class SJF {
    int latency, time;
    ArrayList <Process> processList;
    ArrayList<Process> queue = new ArrayList<>();
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

        for (Process p:
             this.processList) {
            queue.add(p);
        }

        int contexcount = 0;
        while(!queue.isEmpty()) {

            Process selectedProc = queue.get(0);
            queue.remove(0);

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
                sortListByBurstTime(queue, queue.size());
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