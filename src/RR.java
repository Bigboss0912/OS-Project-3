import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class RR {

    int latency, time;
    ArrayList <Process> processList;
    Queue<Process> queue = new LinkedList<>();
    String output;
    String avgCal, AvgCalculated, avgCalRes, str_format, str_formatRes, AvgCalculatedRes;
    int avgTotal, quantum, temp;
    Double avWaitT, avResT, totalResT;

    RR(int quantum, int latency, ArrayList <Process> processList){
        this.latency = latency;
        this.quantum = quantum;
        this.time = 0;
        this.processList = processList;
        this.output = "";
        this.avgCal ="Avg Wait Time = (";
        this.avgCalRes = "Avg resp time = (";
        this.AvgCalculated = "(";
        this.AvgCalculatedRes = "(";
        this.avgTotal =0;
        this.avWaitT = 0.00;
        this.avResT = 0.00;
        this.totalResT = 0.00;
        this.str_format="";
        this.temp = 0;
    }

    public static void sortListByArrivalTime(ArrayList<Process> list, int n) {
        if (n == 1) {
            return;
        }

        for (int i=0; i < n-1; i++) {
            if (list.get(i).getArrivalT() > list.get(i+1).getArrivalT()) {
                Process temp = list.get(i);
                list.set(i, list.get(i+1));
                list.set(i+1, temp);
            }
        }
        sortListByArrivalTime(list, n-1);
    }

    public void runSchedule() {
        sortListByArrivalTime(this.processList, this.processList.size());
        for (Process p : this.processList) {
            queue.add(p);
        }

        for (int i = 0; i < this.processList.size(); i++) {
            if (i == 0) {

                this.time += this.processList.get(i).getArrivalT();

                this.AvgCalculatedRes += (this.time - this.processList.get(i).getArrivalT());
                this.avgCalRes += "("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                this.totalResT += this.time - this.processList.get(i).getArrivalT();


                if ( this.processList.get(i).getBurstT() <= this.quantum) {
                    this.time += this.processList.get(i).getBurstT();
                } else {
                    this.time += this.quantum;
                }

                if (this.latency != 0) {
                    this.time+=this.latency;
                }
            }

            else if ( this.processList.get(i).getBurstT() <= this.quantum) {
                if(this.processList.get(i).getArrivalT() > this.time){
                    this.time = this.processList.get(i).getArrivalT();
                }

                this.AvgCalculatedRes += "+"+(this.time - this.processList.get(i).getArrivalT());
                this.avgCalRes += "+("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                this.totalResT += this.time - this.processList.get(i).getArrivalT();
                this.time += this.processList.get(i).getBurstT();
                if (i == this.processList.size()-1) {
                    this.time += this.latency;
                }


                if (this.latency != 0) {
                    this.time+=this.latency;
                }

            } else {

                if (i == this.processList.size()-1) {
                    this.time += this.latency;
                }

                if(this.processList.get(i).getArrivalT() > this.time){
                    this.time = this.processList.get(i).getArrivalT();
                }

                this.AvgCalculatedRes += "+"+(this.time - this.processList.get(i).getArrivalT());
                this.avgCalRes += "+("+this.time +"-"+this.processList.get(i).getArrivalT()+")";
                this.totalResT += this.time - this.processList.get(i).getArrivalT();
                this.time += this.quantum;


                if (this.latency != 0) {
                    this.time+=this.latency;
                }
            }
        }


        int contextCount = 0;
        this.time = 0;
        boolean finalProc = false;
        while (!queue.isEmpty()) {
            Process selectedProc = queue.remove();

            if (this.time == 0){
                this.time += selectedProc.getArrivalT();

                if (selectedProc.getBurstT() <= this.quantum) {
                    this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                            selectedProc.getBurstT() + " units\n";
                    this.avgCal += "("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.AvgCalculated += (this.time - selectedProc.getArrivalT());
                    this.avgTotal += this.time - selectedProc.getArrivalT();

                    if (this.latency != 0) {
                        contextCount++;
                        this.output += "@time = " + this.time + ", context switch " + contextCount + " occurs\n";
                        this.time+=this.latency;
                    }
                    finalProc = true;

                } else {
                    this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                            this.quantum + " units\n";
                    this.time += this.quantum;

                    if (this.latency != 0) {
                        contextCount++;
                        this.output += "@time = " + this.time + ", context switch " + contextCount + " occurs\n";
                        this.time+=this.latency;
                    }

                    int tempBurT = selectedProc.getBurstT();
                    selectedProc.setBurstT((tempBurT-this.quantum));
                    queue.add(selectedProc);
                }
            }else if (selectedProc.getBurstT() <= this.quantum) {
                if(selectedProc.getArrivalT() > this.time){
                    this.time = selectedProc.getArrivalT();
                }
                this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                        selectedProc.getBurstT() + " units\n";
                if (finalProc == true) {
                    this.avgCal += "+("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.AvgCalculated += "+"+(this.time - selectedProc.getArrivalT());
                } else {
                    this.avgCal += "("+this.time +"-"+selectedProc.getArrivalT()+")";
                    this.AvgCalculated += (this.time - selectedProc.getArrivalT());
                    finalProc = true;
                }

                this.avgTotal += this.time - selectedProc.getArrivalT();

                this.time += selectedProc.getBurstT();

                if (queue.isEmpty()) {
                    break;
                }

                if (this.latency != 0) {
                    contextCount++;
                    this.output += "@time = " + this.time + ", context switch " + contextCount + " occurs\n";
                    this.time+=this.latency;
                }


            } else {
                if(selectedProc.getArrivalT() > this.time){
                    this.time = selectedProc.getArrivalT();
                }
                this.output += "@time = " + this.time + ", " + selectedProc.getPID() +" selected for " +
                        this.quantum + " units\n";
                this.time += this.quantum;
                if (this.latency != 0) {
                    contextCount ++;
                    this.output += "@time = " + this.time + ", context switch " + contextCount + " occurs\n";
                    this.time+=this.latency;
                }

                int tempBurT = selectedProc.getBurstT();
                selectedProc.setBurstT((tempBurT-this.quantum));
                queue.add(selectedProc);
            }
        }

        this.output += "@time = " + this.time + ", all processes complete\n";

        this.output += "Completed in " + this. time+ " cycles.\n";
        this.avWaitT = (this.avgTotal/(double)this.processList.size());
        this.avResT = (this.totalResT/(double)this.processList.size());
        this.str_formatRes = String.format("%.2f", avResT);
        this.str_format = String.format("%.2f",avWaitT);
        this.avgCal += ")/"+this.processList.size() + " = " + this.AvgCalculated+")/"+this.processList.size() + " = " + this.avgTotal + " / "+this.processList.size() +" = "+ str_format;
        this.avgCalRes += ")/"+this.processList.size() + " = " + this.AvgCalculatedRes+")/"+this.processList.size() + " = " + this.totalResT + " / "+this.processList.size() +" = "+ str_formatRes;
        this.output += this.avgCal +"\n";
        this.output += this.avgCalRes +"\n";
    }

    public String getOutput(){
        return this.output;
    }
}