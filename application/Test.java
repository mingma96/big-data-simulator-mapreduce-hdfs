package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.jfree.ui.RefineryUtilities;
import org.json.simple.parser.ParseException;

import hdfs.DataBlock;
import hdfs.File;
import hdfs.HDFS;
import mapreduce.MapReduce;
import mapreduce.Mapper;
import mapreduce.Reducer;
import model.Model;
import physics.Node;
import simulator.Simulator;
import gui.Chart;
import gui.DataPlot;
import gui.Frame;

public class Test {
	public static void main (String[] args) throws FileNotFoundException, IOException, ParseException{
		
		new Frame();
		
		/*Model model = new Model();
		model.test("E:/UCC/Project/CloudVisualization/src/json/file1.txt", "E:/UCC/Project/CloudVisualization/src/json/file2.txt");
		
		Simulator simulator = new Simulator();
		simulator.setFile("E:/UCC/Project/CloudVisualization/src/json/example.json");
		simulator.setModel(model);

		simulator.setRecordBias(1); // *1000
		simulator.setSizeBias(1.1);
		MapReduce.setIoTime(10);
		MapReduce.setComputeTime(0.002);
		simulator.getMr().setReducersNum(10);
		simulator.getHdfs();
		//HDFS.setDataBlockSize(64);
		
		
		LinkedList<LinkedList<DataPlot>> data = new LinkedList<LinkedList<DataPlot>>();
		
		for(int nodeNum = 50;nodeNum<= 100;nodeNum += 10){
			simulator.setNodeNum(nodeNum);
			LinkedList<DataPlot> newData = new LinkedList<DataPlot>();
			for(double size = 1 * 1024;size<= 100 * 1024;size += 20 * 1024){
				System.out.println("Node num:" + nodeNum + " && Input size: " + size + " MB");
				HDFS.getFileList().clear();
				simulator.getHdfs().addFile(size);
				//System.out.println(HDFS.getFileList().get(0).getSize());
				simulator.initiate();
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
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);*/
	
	}
}
