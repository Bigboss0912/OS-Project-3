import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;


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
    private JRadioButton rbFCFS;
    private JRadioButton rbSJF;
    private JRadioButton rbRR;
    private JRadioButton rbRand;
    private ButtonGroup btngrpScheduling;
    private JTextArea txtCalculationsOut;
    private JTextField txtSeed;
    private JTextField txtNumProcesses;
    private JTextField txtArrivalTime;
    private JTextField txtBurstTime;
    private JTextField txtQuantum;
    private JTextField txtLatency;
    private JScrollPane scrollOut;


    public GUIFrame() {
        // Set up frame
        setTitle("Process Scheduling Algorithms");
        setBounds(300, 90, 1500, 800);
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
        lblSeed.setLocation(980, 150);
        container.add(lblSeed);

        // Set up seed txt field
        txtSeed = new JTextField();
        txtSeed.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtSeed.setSize(100, 30);
        txtSeed.setLocation(1040, 150);
        container.add(txtSeed);

        // Set up a number of processes label and add to container
        lblNumProcesses = new JLabel("Number of Processes:");
        lblNumProcesses.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblNumProcesses.setSize(200, 30);
        lblNumProcesses.setLocation(835, 200);
        container.add(lblNumProcesses);

        // Set up number of processes txt field
        txtNumProcesses = new JTextField();
        txtNumProcesses.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtNumProcesses.setSize(100, 30);
        txtNumProcesses.setLocation(1040, 200);
        container.add(txtNumProcesses);

        // Set up a Last possible arrival time label and add to container
        lblArrivalTime = new JLabel("Last Possible Arrival Time:");
        lblArrivalTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblArrivalTime.setSize(300, 30);
        lblArrivalTime.setLocation(800, 250);
        container.add(lblArrivalTime);

        // Set up Last possible arrival time txt field
        txtArrivalTime = new JTextField();
        txtArrivalTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtArrivalTime.setSize(100, 30);
        txtArrivalTime.setLocation(1040, 250);
        container.add(txtArrivalTime);

        // Set up a Last possible arrival time label and add to container
        lblBurstTime = new JLabel("Maximum Burst Time:");
        lblBurstTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblBurstTime.setSize(300, 30);
        lblBurstTime.setLocation(840, 300);
        container.add(lblBurstTime);

        // Set up Last possible arrival time txt field
        txtBurstTime = new JTextField();
        txtBurstTime.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtBurstTime.setSize(100, 30);
        txtBurstTime.setLocation(1040, 300);
        container.add(txtBurstTime);

        // Set up a Quantum label and add to container
        lblQuantum = new JLabel("Quantum:");
        lblQuantum.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblQuantum.setSize(100, 30);
        lblQuantum.setLocation(937, 350);
        container.add(lblQuantum);

        // Set up Quantum txt field
        txtQuantum = new JTextField();
        txtQuantum.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtQuantum.setSize(100, 30);
        txtQuantum.setLocation(1040, 350);
        container.add(txtQuantum);

        // Set up a Latency label and add to container
        lblLatency = new JLabel("Latency:");
        lblLatency.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblLatency.setSize(100, 30);
        lblLatency.setLocation(950, 400);
        container.add(lblLatency);

        // Set up Latency txt field
        txtLatency = new JTextField();
        txtLatency.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        txtLatency.setSize(100, 30);
        txtLatency.setLocation(1040, 400);
        container.add(txtLatency);


        // Set up label for scheduling
        lblScheduling = new JLabel("Scheduling Type");
        lblScheduling.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        lblScheduling.setSize(200, 30);
        lblScheduling.setLocation(1200, 150);
        container.add(lblScheduling);

        // Set up Radio Button FCFS, set action, and add to container
        rbFCFS = new JRadioButton(new AbstractAction("FCFS") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code

            }
        });
        rbFCFS.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        rbFCFS.setSize(150, 20);
        rbFCFS.setLocation(1200, 200);
        container.add(rbFCFS);

        // Set up Radio Button SJF, set action, and add to container
        rbSJF = new JRadioButton(new AbstractAction("SJF") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code

            }
        });
        rbSJF.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        rbSJF.setSize(150, 20);
        rbSJF.setLocation(1200, 250);
        container.add(rbSJF);

        // Set up  Radio Button RR, set action, and add to container
        rbRR = new JRadioButton(new AbstractAction("RR") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code

            }
        });
        rbRR.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        rbRR.setSize(150, 20);
        rbRR.setLocation(1200, 300);
        container.add(rbRR);

        // Set up Random Radio Button, set action, and add to container
        rbRand = new JRadioButton(new AbstractAction("Random") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code

            }
        });
        rbRand.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        rbRand.setSize(150, 20);
        rbRand.setLocation(1200, 350);
        container.add(rbRand);


        // Adds all of the radio buttons to a button group
        btngrpScheduling = new ButtonGroup();
        btngrpScheduling.add(rbFCFS);
        btngrpScheduling.add(rbSJF);
        btngrpScheduling.add(rbRR);
        btngrpScheduling.add(rbRand);


        // Set up calculate button, set action, and add to container
        btnCalculate = new JButton(new AbstractAction("Calculate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code

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

}