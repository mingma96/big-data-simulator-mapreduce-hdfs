package gui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Chart extends ApplicationFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LinkedList<LinkedList<DataPlot>> data;
	
	public Chart(String title, LinkedList<LinkedList<DataPlot>> dataset) {
		super(title);
		Chart.setData(dataset);
		// TODO Auto-generated constructor stub
		setContentPane(createDemoLine());
	}
	
	public static JPanel createDemoLine(){
		JFreeChart jfreechart = createChart(createDataset(data));
		return new ChartPanel(jfreechart);
	}
	
	public static JFreeChart createChart(DefaultCategoryDataset linedataset){
		//Create line chart
		JFreeChart chart = ChartFactory.createLineChart("Performance", "Size / GBs", "Time / mins", linedataset, PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinesVisible(true);
		//Define axis
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		
		return chart;
	}
	
	public static DefaultCategoryDataset createDataset(LinkedList<LinkedList<DataPlot>> data){
		//Create dataset
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		
		LinkedList<String> series = new LinkedList<String>();
		
		//Add lines for different number of nodes
		for(int i=0;i<data.size();i++){
			String newSeries = data.get(i).get(0).getNodeNum() + " nodes";
			series.add(newSeries);
		}
		
		//Add plots to each line
		for(int i=0;i<series.size();i++){
			for(int j=0;j<data.get(i).size();j++){
				String type = Double.toString(data.get(i).get(j).getSize() / 1024);
				double time = data.get(i).get(j).getTime();
				
				//Set the plot
				linedataset.addValue(time , series.get(i), type);
			}
		}
		
		return linedataset;
	}

	public static LinkedList<LinkedList<DataPlot>> getData() {
		return data;
	}

	public static void setData(LinkedList<LinkedList<DataPlot>> data) {
		Chart.data = data;
	}
	
	
	
}
