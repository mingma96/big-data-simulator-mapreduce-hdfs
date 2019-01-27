package simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import hdfs.HDFS;
import mapreduce.MapReduce;
import model.Model;
import physics.Node;

public class Simulator {
	Model model;
	String file;
	
	MapReduce mr = new MapReduce();
	HDFS hdfs = new HDFS();
	
	int nodeNum;
	double sizeBias, recordBias;
	
	public void initiate() throws FileNotFoundException, IOException, ParseException{
		
		for(int i=0;i<nodeNum;i++){
			Node node = new Node();
			node.setNodeName("Node " + (i+1));
		}
		
		hdfs.initiate();
		mr.initiate(model,sizeBias,recordBias);
	}
	
	public double simulate(){
		double mapTime = mr.map() / 1000 / 60;
		double transferTime = mr.transfer() / 60;
		double reduceTime = mr.reduce() / 1000 / 60;
		
		double simulatedTime = mapTime + transferTime + reduceTime;
		System.out.println("Performance: " + simulatedTime );
		System.out.println("     MapTime: " + mapTime);
		System.out.println("     NetworkDelay: " + transferTime);
		System.out.println("     ReduceTime: " + reduceTime);
		
		HDFS.getNodeList().clear();
		MapReduce.getNodeList().clear();
		MapReduce.getMappers().clear();
		MapReduce.getReducers().clear();
		HDFS.getDataList().clear();
		
		return simulatedTime;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public MapReduce getMr() {
		return mr;
	}

	public void setMr(MapReduce mr) {
		this.mr = mr;
	}

	public HDFS getHdfs() {
		return hdfs;
	}

	public void setHdfs(HDFS hdfs) {
		this.hdfs = hdfs;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public double getSizeBias() {
		return sizeBias;
	}

	public void setSizeBias(double sizeBias) {
		this.sizeBias = sizeBias;
	}

	public double getRecordBias() {
		return recordBias;
	}

	public void setRecordBias(double recordBias) {
		this.recordBias = recordBias;
	}
	
	
}
