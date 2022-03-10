import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;


public class GUI {
    public static void main(String[] args) {
        GUIFrame gui =  new GUIFrame();
    }
}


class GUIFrame extends JFrame {

    private Container container;
    private JLabel title;
    private JLabel lblSeed;
    private JLabel lblNumProcesses;
    private JLabel lblArrivalTime;
    private JLabel lblBurstTime;
    private JLabel lblQuantum;
    private JLabel lblLatency;
    private JLabel lblScheduling;
    private JButton btnCalculate;
    private JTextArea txtCalculationsOut;
    private JTextField txtSeed;
    private JTextField txtNumProcesses;
    private JTextField txtArrivalTime;
    private JTextField txtBurstTime;
    private JTextField txtQuantum;
    private JTextField txtLatency;
    private JScrollPane scrollOut;

    int numSeed, numProcesses, numArrival, numBurst, numQuantum, numLatency;
    ArrayList <Process> processList = new ArrayList<Process>();
    ArrayList<String> FCFS_output = new ArrayList<>();
    ArrayList<String> SJF_output = new ArrayList<>();
    ArrayList<String> Random_output = new ArrayList<>();
    ArrayList<String> RR_output = new ArrayList<>();
    Random rand = new Random();



    public GUIFrame() {
        // Set up frame
        setTitle("Process Scheduling Algorithms");
        setBounds(300, 90, 1265, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);


        // Set up a title label and add to container
        title = new JLabel("Process Scheduling Algorithms");
        title.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        title.setSize(400, 30);
        title.setLocation(550, 30);
        container.add(title);

        // Set up a seed label and add to container
        lblSeed = new JLabel("Seed:");
        lblSeed.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblSeed.setSize(100, 30);
        lblSeed.setLocation(985, 150);
        container.add(lblSeed);

        // Set up seed txt field
        txtSeed = new JTextField();
        txtSeed.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtSeed.setSize(100, 30);
        txtSeed.setLocation(1040, 150);
        container.add(txtSeed);



        // Set up a number of processes label and add to container
        lblNumProcesses = new JLabel("Number of Processes (2, 100):");
        lblNumProcesses.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblNumProcesses.setSize(300, 30);
        lblNumProcesses.setLocation(780, 200);
        container.add(lblNumProcesses);

        // Set up number of processes txt field
        txtNumProcesses = new JTextField();
        txtNumProcesses.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtNumProcesses.setSize(100, 30);
        txtNumProcesses.setLocation(1040, 200);
        container.add(txtNumProcesses);

        // Set up a Last possible arrival time label and add to container
        lblArrivalTime = new JLabel("Last Possible Arrival Time (0, 99):");
        lblArrivalTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblArrivalTime.setSize(300, 30);
        lblArrivalTime.setLocation(764, 250);
        container.add(lblArrivalTime);

        // Set up Last possible arrival time txt field
        txtArrivalTime = new JTextField();
        txtArrivalTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtArrivalTime.setSize(100, 30);
        txtArrivalTime.setLocation(1040, 250);
        container.add(txtArrivalTime);

        // Set up a Last possible arrival time label and add to container
        lblBurstTime = new JLabel("Maximum Burst Time (1, 100):");
        lblBurstTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblBurstTime.setSize(300, 30);
        lblBurstTime.setLocation(787, 300);
        container.add(lblBurstTime);

        // Set up Last possible arrival time txt field
        txtBurstTime = new JTextField();
        txtBurstTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtBurstTime.setSize(100, 30);
        txtBurstTime.setLocation(1040, 300);
        container.add(txtBurstTime);

        // Set up a Quantum label and add to container
        lblQuantum = new JLabel("Quantum (1,100):");
        lblQuantum.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblQuantum.setSize(170, 30);
        lblQuantum.setLocation(883, 350);
        container.add(lblQuantum);

        // Set up Quantum txt field
        txtQuantum = new JTextField();
        txtQuantum.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtQuantum.setSize(100, 30);
        txtQuantum.setLocation(1040, 350);
        container.add(txtQuantum);

        // Set up a Latency label and add to container
        lblLatency = new JLabel("Latency (0,10):");
        lblLatency.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblLatency.setSize(170, 30);
        lblLatency.setLocation(906, 400);
        container.add(lblLatency);

        // Set up Latency txt field
        txtLatency = new JTextField();
        txtLatency.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtLatency.setSize(100, 30);
        txtLatency.setLocation(1040, 400);
        container.add(txtLatency);



        // Set up calculate button, set action, and add to container
        btnCalculate = new JButton(new AbstractAction("Calculate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code

                try{
                    String seedStr = txtSeed.getText();
                    numSeed = Integer.parseInt(seedStr);

                }catch (NumberFormatException ex1) {
                    JOptionPane.showMessageDialog(new JFrame("Seed!!!"), "Please Enter Integer Value", "Seed!!!", JOptionPane.INFORMATION_MESSAGE);
                    txtSeed.setText("");
                }
                try {
                    String numProcessesStr = txtNumProcesses.getText();
                    numProcesses = Integer.parseInt(numProcessesStr);

                    if (numProcesses < 2 || numProcesses > 100) {
                        JOptionPane.showMessageDialog(new JFrame("Number of Processes!!!"), "Please Enter Value Between 2 and 100", "Number of Processes!!!", JOptionPane.INFORMATION_MESSAGE);
                        txtNumProcesses.setText("");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(new JFrame("Number of Processes!!!"), "Please Enter Integer Value", "Number of Processes!!!", JOptionPane.INFORMATION_MESSAGE);
                    txtNumProcesses.setText("");
                }

                try {
                    String arrivalTimeStr = txtArrivalTime.getText();
                    numArrival = Integer.parseInt(arrivalTimeStr);

                    if (numArrival < 0 || numArrival > 99) {
                        JOptionPane.showMessageDialog(new JFrame("Arrival Time!!!"), "Please Enter Value Between 0 and 99", "Arrival Time!!!", JOptionPane.INFORMATION_MESSAGE);
                        txtArrivalTime.setText("");
                    }
                }catch (NumberFormatException ex2){
                    JOptionPane.showMessageDialog(new JFrame("Arrival Time!!!"), "Please Enter Integer Value", "Arrival Time!!!", JOptionPane.INFORMATION_MESSAGE);
                    txtArrivalTime.setText("");
                }
                try{
                    String burstTimeStr = txtBurstTime.getText();
                    numBurst = Integer.parseInt(burstTimeStr);

                    if (numBurst < 1 || numBurst > 100) {
                        JOptionPane.showMessageDialog(new JFrame(), "Please Enter Value Between 1 and 100", "Burst Time!!!", JOptionPane.INFORMATION_MESSAGE);
                        txtBurstTime.setText("");
                    }

                } catch(NumberFormatException ex3){
                    JOptionPane.showMessageDialog(new JFrame("Burst Time!!!"), "Please Enter Integer Value", "Burst Time!!!", JOptionPane.INFORMATION_MESSAGE);
                    txtBurstTime.setText("");
                }
                try {
                    String quantumStr = txtQuantum.getText();
                    numQuantum = Integer.parseInt(quantumStr);
                    if (numQuantum < 1 || numQuantum > 100) {
                        JOptionPane.showMessageDialog(new JFrame("Quantum!!!"), "Please Enter Value Between 1 and 100", "Quantum!!!", JOptionPane.INFORMATION_MESSAGE);
                        txtQuantum.setText("");
                    }

                }catch (NumberFormatException ex4) {
                    JOptionPane.showMessageDialog(new JFrame("Quantum!!!"), "Please Enter Integer Value", "Quantum!!!", JOptionPane.INFORMATION_MESSAGE);
                    txtQuantum.setText("");
                }
                try{
                    String latencyStr = txtLatency.getText();
                    numLatency = Integer.parseInt(latencyStr);
                    if (numLatency < 0 || numLatency > 10) {
                        JOptionPane.showMessageDialog(new JFrame("Latency!!!"), "Please Enter Value Between 0 and 10", "Latency!!!", JOptionPane.INFORMATION_MESSAGE);
                        txtLatency.setText("");
                    }
                }catch(NumberFormatException ex5) {
                    JOptionPane.showMessageDialog(new JFrame("Latency!!!"), "Please Enter Integer Value", "Latency!!!", JOptionPane.INFORMATION_MESSAGE);
                    txtLatency.setText("");
                }

                rand.setSeed(numSeed);

                //Text Area
                txtCalculationsOut.append("User values---------------------------------------------- \n" );
                txtCalculationsOut.append("Seed Value: " + txtSeed.getText()+"\n") ;
                txtCalculationsOut.append("Number of Processes value: " + txtNumProcesses.getText()+"\n");
                txtCalculationsOut.append("Arrival Time: " + txtArrivalTime.getText()+"\n");
                txtCalculationsOut.append("Burst Time: " + txtBurstTime.getText()+"\n");
                txtCalculationsOut.append("Quantum Size: " + txtQuantum.getText()+"\n");
                txtCalculationsOut.append("Latency: " + txtLatency.getText()+"\n");
                txtCalculationsOut.append("\n");
                txtCalculationsOut.append(txtNumProcesses.getText()+" processes created.\n");
                txtCalculationsOut.append(String.format("%7s %18s %18s", "Process", "Arrival", "Burst\n"));

                createProcesses();
                sortListByArrivalTime(processList,processList.size());

                for (Process p : processList) {
                    txtCalculationsOut.append(String.format("%5s %20s %20s", p.getPID(), p.getArrivalT(), p.getBurstT() + "\n"));
                }

                txtCalculationsOut.append("\n");

                //Random Schedule instance printout
                Random_Scheduler random_Scheduler = new Random_Scheduler(numSeed, numLatency, processList);
                txtCalculationsOut.append("Random: \n");
                txtCalculationsOut.append(random_Scheduler.getOutput() + "\n\n");

                //FCFS Schedule instance printout
                FCFS FCFS_schedule = new FCFS(numLatency, processList);
                txtCalculationsOut.append("FCFS: \n");
                FCFS_schedule.runSchedule();
                txtCalculationsOut.append(FCFS_schedule.getOutput()+ "\n\n");

                //SJF Schedule instance printout
                SJF SJF_schedule = new SJF(numLatency, processList);
                txtCalculationsOut.append("SJF: \n");
                SJF_schedule.runSchedule();
                txtCalculationsOut.append(SJF_schedule.getOutput() + "\n\n");

                //RR Schedule instance printout
                RR RR_schedule = new RR(numQuantum, numLatency, processList);
                txtCalculationsOut.append("RR (q = " + numLatency + "):\n");
                txtCalculationsOut.append(RR_schedule.getOutput() + "\n\n");

                txtSeed.setText("");
                txtLatency.setText("");
                txtQuantum.setText("");
                txtBurstTime.setText("");
                txtNumProcesses.setText("");
                txtArrivalTime.setText("");
                processList.clear();


                System.out.println("\n");
            }
        });
        btnCalculate.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnCalculate.setSize(250, 40);
        btnCalculate.setLocation(600, 700);
        container.add(btnCalculate);


        // Sets up output text area and scroller
        txtCalculationsOut = new JTextArea();
        txtCalculationsOut.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        txtCalculationsOut.setEditable(false);

        scrollOut = new JScrollPane(txtCalculationsOut);
        scrollOut.setSize(700, 500);
        scrollOut.setLocation(50, 150);

        container.add(scrollOut);

        setVisible(true);

    }

    public void createProcesses(){

        for (int i = 0; i < numProcesses; i++) {
            String PID = "P" + (i+1);
            Process newProcess = new Process(PID, randArrivalTime(), randBurstTime());
            processList.add(newProcess);
        }
    }

    public int randArrivalTime() {
        int retArrivalTime = rand.nextInt(numArrival+1);
        return retArrivalTime;
    }

    public int randBurstTime() {
        int retBurstTime = rand.nextInt(numBurst);
        retBurstTime ++;
        return retBurstTime;
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
}