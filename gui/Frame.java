package gui; 
 
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.io.IOException; 
import java.util.LinkedList; 
 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
 
import org.jfree.ui.RefineryUtilities; 
import org.json.simple.parser.ParseException; 
 
import hdfs.HDFS; 
import mapreduce.MapReduce; 
import model.Model; 
import simulator.Simulator; 
 
public class Frame  { 
     
    public Frame() throws IOException{ 
        JFrame frame = new JFrame("Simulator"); 
        frame.setLayout(new GridLayout(20,2,10,10)); 
         
        //JPanel panel1 = new JPanel(new GridLayout(15,2,10,10)); 
        JLabel iotime = new JLabel("I/O time (ms)"); 
        JLabel computeTime = new JLabel("Compute time per operation (ms)"); 
        JLabel recordsBias = new JLabel("Records bias per 1MB data (k)"); 
        JLabel sizeBias = new JLabel("Size bias after mapping"); 
        JLabel reducerNum = new JLabel("Number of reducers"); 
        JLabel dataBlockSize = new JLabel("Data Block Size (MB)"); 
        JLabel network = new JLabel("Network Bandwidth (GB/s)"); 
        JLabel nodeNum1 = new JLabel("Number of nodes min"); 
        JLabel nodeNum2 = new JLabel("Number of nodes max"); 
        JLabel nodeNumInc = new JLabel("Number of nodes increment"); 
        JLabel dataSize1 = new JLabel("Data size min (GB)"); 
        JLabel dataSize2 = new JLabel("Data size max (GB)"); 
        JLabel dataSizeInc = new JLabel("Data size increment (GB)"); 
         
        JTextField iotimeText = new JTextField("10"); 
        JTextField computeTimeText = new JTextField("0.002"); 
        JTextField recordsBiasText = new JTextField("1"); 
        JTextField sizeBiasText = new JTextField("1.1"); 
        JTextField reducerNumText = new JTextField("5"); 
        JTextField dataBlockSizeText = new JTextField("256"); 
        JTextField networkText = new JTextField("2"); 
        JTextField nodeNumText1 = new JTextField("50"); 
        JTextField nodeNumText2 = new JTextField("100"); 
        JTextField nodeNumIncText = new JTextField("10"); 
        JTextField dataSizeText1 = new JTextField("100"); 
        JTextField dataSizeText2 = new JTextField("200"); 
        JTextField dataSizeIncText = new JTextField("20"); 
         
        JLabel diagramSet = new JLabel("Input information for diagrams:"); 
        JLabel blank1 = new JLabel(); 
        JLabel blank3 = new JLabel(); 
        JLabel simulatorSet = new JLabel("Basic simulator set:"); 
         
        frame.add(simulatorSet); 
        frame.add(blank3); 
        frame.add(iotime); 
        frame.add(iotimeText); 
        frame.add(computeTime); 
        frame.add(computeTimeText); 
        frame.add(recordsBias); 
        frame.add(recordsBiasText); 
        frame.add(sizeBias); 
        frame.add(sizeBiasText); 
        frame.add(dataBlockSize); 
        frame.add(dataBlockSizeText); 
        frame.add(network); 
        frame.add(networkText); 
        frame.add(reducerNum); 
        frame.add(reducerNumText); 
         
        frame.add(diagramSet); 
        frame.add(blank1); 
        frame.add(nodeNum1); 
        frame.add(nodeNumText1); 
        frame.add(nodeNum2); 
        frame.add(nodeNumText2); 
        frame.add(nodeNumInc); 
        frame.add(nodeNumIncText); 
        frame.add(dataSize1); 
        frame.add(dataSizeText1); 
        frame.add(dataSize2); 
        frame.add(dataSizeText2); 
        frame.add(dataSizeInc); 
        frame.add(dataSizeIncText); 
         
         
        JButton simulate = new JButton("Simulate"); 
        JButton setDefault = new JButton("Default settings"); 
        frame.add(simulate); 
        frame.add(setDefault); 
         
        Simulator simulator = new Simulator(); 
        simulator.setModel(this.createModel("E:/UCC/Project/CloudVisualization/src/json/file1.txt", "E:/UCC/Project/CloudVisualization/src/json/file2.txt"));
         
        simulate.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                double ioTime = 10, computeTime = 0.002, recordsBias = 1, sizeBias = 1.1, dataBlockSize = 256, networkBW = 2; 
                double dataSizeMin = 100, dataSizeMax = 200, dataSizeInc = 20; 
                int nodeNum1 = 50, nodeNum2 = 100, nodeNumInc = 10, reducerNum = 5; 
                 
                if(!(iotimeText.getText().isEmpty())) ioTime = Double.parseDouble(iotimeText.getText()); 
                if(!(computeTimeText.getText().isEmpty()))computeTime = Double.parseDouble(computeTimeText.getText()); 
                if(!(recordsBiasText.getText().isEmpty()))recordsBias = Double.parseDouble(recordsBiasText.getText()); 
                if(!(sizeBiasText.getText().isEmpty()))sizeBias = Double.parseDouble(sizeBiasText.getText()); 
                if(!(nodeNumText1.getText().isEmpty()))nodeNum1 = Integer.parseInt(nodeNumText1.getText()); 
                if(!(nodeNumText2.getText().isEmpty()))nodeNum2 = Integer.parseInt(nodeNumText2.getText()); 
                if(!(nodeNumIncText.getText().isEmpty()))nodeNumInc = Integer.parseInt(nodeNumIncText.getText()); 
                if(!(reducerNumText.getText().isEmpty()))reducerNum = Integer.parseInt(reducerNumText.getText()); 
                if(!(dataBlockSizeText.getText().isEmpty()))dataBlockSize = Double.parseDouble(dataBlockSizeText.getText()); 
                if(!(dataSizeText1.getText().isEmpty()))dataSizeMin = Double.parseDouble(dataSizeText1.getText()); 
                if(!(dataSizeText2.getText().isEmpty()))dataSizeMax = Double.parseDouble(dataSizeText2.getText()); 
                if(!(dataSizeIncText.getText().isEmpty()))dataSizeInc = Double.parseDouble(dataSizeIncText.getText()); 
                if(!(networkText.getText().isEmpty())) networkBW = Double.parseDouble(networkText.getText()); 
                 
                 
                MapReduce.setIoTime(ioTime); 
                MapReduce.setComputeTime(computeTime); 
                MapReduce.setNetworkBW(networkBW); 
                simulator.setRecordBias(recordsBias); 
                simulator.setSizeBias(sizeBias); 
                simulator.getMr().setReducersNum(reducerNum); 
                HDFS.setDataBlockSize(dataBlockSize); 
                 
                LinkedList<LinkedList<DataPlot>> data = new LinkedList<LinkedList<DataPlot>>(); 
                 
                for(int nodeNum = nodeNum1;nodeNum<= nodeNum2;nodeNum += nodeNumInc){ 
                    simulator.setNodeNum(nodeNum); 
                    LinkedList<DataPlot> newData = new LinkedList<DataPlot>(); 
                    for(double size = dataSizeMin * 1024;size<= dataSizeMax * 1024;size += dataSizeInc * 1024){ 
                        System.out.println(); 
                        System.out.println(nodeNum + " nodes & " + reducerNum + " reducers & " + size + " GB"); 
                        HDFS.getFileList().clear(); 
                        simulator.getHdfs().addFile(size); 
                        //System.out.println(HDFS.getFileList().get(0).getSize()); 
                        try { 
                            simulator.initiate(); 
                        } catch (IOException | ParseException e1) { 
                            // TODO Auto-generated catch block 
                            e1.printStackTrace(); 
                        } 
                        double time = simulator.simulate(); 
                         
                        DataPlot newPlot = new DataPlot(); 
                        newPlot.setSize(size); 
                        newPlot.setTime(time); 
                        newPlot.setNodeNum(nodeNum); 
                        newData.add(newPlot); 
                    } 
                    data.add(newData); 
                } 
                 
                Chart chart = new Chart("Performance", data); 
                chart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
                //System.out.println(chart.getDefaultCloseOperation()); 
                chart.pack(); 
                RefineryUtilities.centerFrameOnScreen(chart); 
                chart.setVisible(true);                 
            } 
        }); 
         
        setDefault.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                iotimeText.setText("10"); 
                computeTimeText.setText("0.002"); 
                recordsBiasText.setText("1"); 
                sizeBiasText.setText("1.1"); 
                nodeNumText1.setText("50"); 
                nodeNumText2.setText("100"); 
                nodeNumIncText.setText("10"); 
                reducerNumText.setText("5"); 
                dataBlockSizeText.setText("256"); 
                dataSizeText1.setText("100"); 
                dataSizeText2.setText("200"); 
                dataSizeIncText.setText("20"); 
                networkText.setText("2"); 
            } 
        }); 
         
        JLabel predictLabel = new JLabel("Predicting data:"); 
        JTextField predictSize = new JTextField("100"); 
        JLabel numNodeLabel = new JLabel("Nodes num:"); 
        JTextField numNode = new JTextField("300"); 
        JButton predict = new JButton("Predict"); 
         
        JLabel predictBestConfiguration = new JLabel("Input data for predicting:"); 
        JLabel blank2 = new JLabel(); 
         
        frame.add(predictBestConfiguration); 
        frame.add(blank2); 
        frame.add(predictLabel); 
        frame.add(predictSize); 
        frame.add(numNodeLabel); 
        frame.add(numNode); 
        frame.add(predict); 
     
         
        predict.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                double ioTime = 10, computeTime = 0.002, recordsBias = 1, sizeBias = 1.1, dataBlockSize = 256, networkBW = 2; 
                double data = 100; 
                int nodeNum = 300, reducerNum = 5; 
                 
                if(!(iotimeText.getText().isEmpty())) ioTime = Double.parseDouble(iotimeText.getText()); 
                if(!(computeTimeText.getText().isEmpty()))computeTime = Double.parseDouble(computeTimeText.getText()); 
                if(!(recordsBiasText.getText().isEmpty()))recordsBias = Double.parseDouble(recordsBiasText.getText()); 
                if(!(sizeBiasText.getText().isEmpty()))sizeBias = Double.parseDouble(sizeBiasText.getText()); 
                if(!(reducerNumText.getText().isEmpty()))reducerNum = Integer.parseInt(reducerNumText.getText()); 
                if(!(dataBlockSizeText.getText().isEmpty()))dataBlockSize = Double.parseDouble(dataBlockSizeText.getText()); 
                if(!(predictSize.getText().isEmpty()))data = Double.parseDouble(predictSize.getText()); 
                if(!(networkText.getText().isEmpty())) networkBW = Double.parseDouble(networkText.getText()); 
                if(!(numNode.getText().isEmpty())) nodeNum = Integer.parseInt(numNode.getText()); 
 
                MapReduce.setIoTime(ioTime); 
                MapReduce.setComputeTime(computeTime); 
                simulator.setRecordBias(recordsBias); 
                simulator.setSizeBias(sizeBias); 
                simulator.getMr().setReducersNum(reducerNum); 
                HDFS.setDataBlockSize(dataBlockSize); 
                simulator.setNodeNum(nodeNum); 
                MapReduce.setNetworkBW(networkBW); 
                 
                HDFS.getFileList().clear(); 
                simulator.getHdfs().addFile(data * 1024); 
                try { 
                    simulator.initiate(); 
                } catch (IOException | ParseException e2) { 
                    // TODO Auto-generated catch block 
                    e2.printStackTrace(); 
                } 
                 
                double result = simulator.simulate(); 
                 
                double performance = 0, bestPerformance = 0, bestSize = 0; 
                LinkedList<Double> results = new LinkedList<Double>(); 
                 
                for(double size = 64; size <= 1024; size+=64){ 
                    System.out.println(); 
                    System.out.println("Data blocks size: " + size + "MB"); 
                    HDFS.setDataBlockSize(size); 
                    HDFS.getFileList().clear(); 
                    simulator.getHdfs().addFile(data * 1024); 
                    try { 
                        simulator.initiate(); 
                    } catch (IOException | ParseException e1) { 
                        // TODO Auto-generated catch block 
                        e1.printStackTrace(); 
                    } 
                    performance = simulator.simulate(); 
                    results.add(performance); 
                } 
                 
             
                if(results.size() != 0){ 
                    performance = results.get(0); 
                    bestPerformance = performance; 
                } 
                 
                for(int i=1;i<results.size();i++){ 
                    performance = results.get(i); 
                    if(bestPerformance >= performance ){ 
                        bestPerformance = performance; 
                        bestSize = 64 + 64*i; 
                    } 
                } 
                 
                String info = "Performance: " + result + "\n" + 
                        "Best configuration: " + bestSize + "\n" + 
                        "Best performance: " + bestPerformance; 
                 
                JOptionPane.showMessageDialog(null, info,"information",JOptionPane.INFORMATION_MESSAGE); 
                 
                 
            } 
        }); 
         
         
        frame.setSize(400, 600); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
        frame.pack(); 
         
    } 
 
    private Model createModel(String file1, String file2) throws IOException { 
            // TODO Auto-generated method stub 
            Model model = new Model(); 
            model.test(file1, file2); 
            return model; 
    } 
     
}