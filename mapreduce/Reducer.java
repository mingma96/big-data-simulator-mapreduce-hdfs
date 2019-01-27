package mapreduce;

import hdfs.HDFS;
import observer.ReduceObserver;
import physics.Node;

public class Reducer {
	private String name;
	private static MapReduce mr;
	private double timeCons = 0;
	private double recordsNum = 1;
	private double dataSize = 0;

	public double merge(){
		double mergeTime = 0;
		////System.out.println("Records Num : " + this.recordsNum);
		
		mergeTime += Math.log(recordsNum) * recordsNum * MapReduce.getComputeTime();
		//System.out.println("Merge time on " + this.getName() + " : " + mergeTime);
		return mergeTime;
	}
	
	public double output(){
		double outputTime = 0, sum = 0;
		double dataBlockSize = HDFS.getDataBlockSize();
		
		while(sum<this.getDataSize()){
			outputTime += MapReduce.getIoTime() * HDFS.getDataBlockSize();
			sum += dataBlockSize;
		}
		
		//System.out.println("Output time on " + this.getName() + " to HDFS : " + outputTime);
		return outputTime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static MapReduce getMr() {
		return mr;
	}

	public static void setMr(MapReduce mr) {
		Reducer.mr = mr;
	}
	
	public double getTimeCons() {
		return timeCons;
	}

	public void setTimeCons(double timeCons) {
		this.timeCons = timeCons;
	}

	public double getRecordsNum() {
		return recordsNum;
	}

	public void setRecordsNum(double recordsNum) {
		this.recordsNum = recordsNum;
	}
	
	public void addRecordsNum(double recordsNum) {
		this.recordsNum += recordsNum;
	}

	public double getDataSize() {
		return dataSize;
	}

	public void setDataSize(double dataSize) {
		this.dataSize = dataSize;
	}

	
	
	
	/*@Override
	public void reduce(double result, Node node) {
		// TODO Auto-generated method stub
		timeCons += result;
		//System.out.println("Received result from node: " + node.getNodeName()  + " with time consumed " + result + "ns");
		//System.out.println("Total time consumed: " + timeCons);
	}*/
	
	
}
