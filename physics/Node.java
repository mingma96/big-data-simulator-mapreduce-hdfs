package physics;
import java.util.LinkedList;
import java.util.List;

import application.Application;
import hdfs.DataBlock;
import hdfs.HDFS;
import mapreduce.MapReduce;
import mapreduce.Reducer;
import observer.HDFSObserver;
import observer.MapObserver;
import observer.ReduceSubject;
import resource.CPU;
import resource.Disk;
import resource.Memory;
import resource.NetworkBW;


public class Node implements HDFSObserver, MapObserver, ReduceSubject{
	private String nodeName;
	private CPU CPU;
	private Memory memory;
	private NetworkBW networkBW;
	private Disk disk;
	private LinkedList<DataBlock> dataList = new LinkedList<DataBlock>();
	private static HDFS hdfs;
	public Node(){
		HDFS.getNodeList().add(this);
		MapReduce.getNodeList().add(this);
	}
	
	//Getters and setters
	
	
	public CPU getCPU() {
		return CPU;
	}
	public static HDFS getHdfs() {
		return hdfs;
	}

	public static void setHdfs(HDFS hdfs) {
		Node.hdfs = hdfs;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Disk getDisk() {
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public List<DataBlock> getDataList() {
		return dataList;
	}

	public void setDataList(LinkedList<DataBlock> dataList) {
		this.dataList = dataList;
	}

	public void setCPU(CPU cPU) {
		CPU = cPU;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	public NetworkBW getNetworkBW() {
		return networkBW;
	}
	public void setNetworkBW(NetworkBW networkBW) {
		this.networkBW = networkBW;
	}
	
	//Compute function
	public void compute(Application app){
		long startTime = System.nanoTime();
		double timeCons = 0;
		int sum = app.function(this);
		long endTime = System.nanoTime();
		timeCons = endTime - startTime;
		System.out.println("Time consumed on " + this.getNodeName() + " : " + timeCons + "ns");
		System.out.println("Local results on " + this.getNodeName() + " : " + sum);
		//return time consumption
		//return timeCons;
		this.send(timeCons, this);
	}
	
	
	@Override
	public void updateData(DataBlock data) {
		// TODO Auto-generated method stub
		this.dataList.add(data);
	}

	@Override
	public void addNode(Reducer reducer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNode(Reducer reducer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(double result, Node node) {
		// TODO Auto-generated method stub
		//MapReduce.getReducers().reduce(result, this);
	}
	
}
