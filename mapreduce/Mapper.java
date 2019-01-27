package mapreduce;

import java.util.LinkedList;
import java.util.Vector;

import application.Application;
import hdfs.DataBlock;
import hdfs.HDFS;
import observer.AppObserver;
import physics.Node;

public class Mapper {
	private static MapReduce mr;
	private Node node;
	
	private double recordsNum = 0;
	private LinkedList<DataBlock> dataVector = new LinkedList<DataBlock>();
	
	public Mapper(MapReduce mr){
		Mapper.mr = mr;
	}
	
	public double getDataSize() {
		double size = 0;
		
		for(int i=0;i<dataVector.size();i++){
			size += dataVector.get(i).getSize();
		}
		
		return size;
	}
	
	//Time - read data from local disk
	public double readData(){
		double readTime = 0;
		for(int i=0;i<this.getNode().getDataList().size();i++){
			if(this.getNode().getDataList().get(i).getFlag() == 1){
				this.dataVector.add(this.getNode().getDataList().get(i));
				Mapper.getMr();
				readTime += MapReduce.getIoTime() * HDFS.getDataBlockSize();
			}		
		}
		
		
		//System.out.println("Read " + this.dataVector.size() * 256 + " MB data from " + this.node.getNodeName()
		//			+ " in time: " + readTime);
		return readTime;
	}
	
	//Time - RecordsRead
	public double recordsRead(){
		double sumTime = 0;
	
		for(int i=0;i<this.getDataVector().size();i++){
			this.recordsNum += HDFS.getDataBlockSize() * MapReduce.getRecordBias() * 1000;
			
			//sumTime += (this.getNode().getDataList().get(i).getSize() * readTime * mr.getRecordBias() * 1000000);
			//this.recordsNum += this.getNode().getDataList().get(i).getRecords();
			sumTime += this.recordsNum * MapReduce.getComputeTime();
		}
		//System.out.println("Break data blocks into " + this.getRecordsNum() + " records on " + this.getNode().getNodeName());
		return sumTime;
	}
	
	//Time - Processing data
	public double compute(){
		double computeTime = 0;

		computeTime += mr.getModel().model(this.recordsNum);
		computeTime = computeTime / 1000 ;

		return computeTime;
	}
	
	//Time - Local sorting
	public double sorting(){
		double sortingTime = 0;
	
		sortingTime += Math.log(this.recordsNum) * this.recordsNum * MapReduce.getComputeTime();
		//System.out.println("Sorting time on Node " + this.getNode().getNodeName() + " : " + sortingTime);
		return sortingTime;
	}
	
	
	
	
	
	/*
	@Override
	public void map(Application app) {
		// TODO Auto-generated method stub
		
		//System.out.println("Map operated on App: " + app.getName());
		mr.map(app);
	}*/
	

	public static MapReduce getMr() {
		return mr;
	}



	public static void setMr(MapReduce mr) {
		Mapper.mr = mr;
	}



	public Node getNode() {
		return node;
	}



	public void setNode(Node node) {
		this.node = node;
	}

	
	public static void setMp(MapReduce mp) {
		Mapper.mr = mp;
	}

	public double getRecordsNum() {
		return recordsNum;
	}

	public void setRecordsNum(double recordsNum) {
		this.recordsNum = recordsNum;
	}

	public LinkedList<DataBlock> getDataVector() {
		return dataVector;
	}

	public void setDataVector(LinkedList<DataBlock> dataVector) {
		this.dataVector = dataVector;
	}
	
	
	
	

}
