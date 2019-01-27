package mapreduce;

import java.util.LinkedList;

import application.Application;
import hdfs.HDFS;
import model.Model;
import observer.MapSubject;
import physics.Node;

public class MapReduce{
	private static LinkedList<Mapper> mappers = new LinkedList<Mapper>();
	private static LinkedList<Reducer> reducers = new LinkedList<Reducer>();
	private static int reducersNum = 2;
	private static HDFS HDFS;
	private static LinkedList<Node> nodeList = new LinkedList<Node>();
	private Application app;
	private Model model;
	private static double computeTime = 1;
	private static double ioTime = 1;
	
	private static double sizeBias, recordBias;
	private static double networkBW ;
	
	public void initiate(Model model, double sizeBias, double recordBias){
		//Set basic factors
		MapReduce.sizeBias = sizeBias;
		MapReduce.recordBias = recordBias;
		this.model = model;
		
		//Create mappers according to the size of nodes list
		for(int i=0;i<MapReduce.getNodeList().size();i++){
			if(MapReduce.getNodeList().get(i).getDataList().size() != 0){
				Mapper newMapper = new Mapper(this);
				newMapper.setNode(MapReduce.getNodeList().get(i));
				//Add new mappers
				MapReduce.mappers.add(newMapper);	
			}
		}
		
		//Create reducers accoding to the set number
		for(int i=0;i<MapReduce.getReducersNum();i++){
			Reducer newReducer = new Reducer();
			newReducer.setName("Reducer " + i);
			//Add new reducers
			MapReduce.reducers.add(newReducer);
		}
		
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public double map(){
		double overallMappingTime = 0;
		double[] time = new double[mappers.size()];
		for(int i=0;i<mappers.size();i++){
			time[i] = 0;
			time[i] += mappers.get(i).readData();
			time[i] += mappers.get(i).recordsRead();
			time[i] += mappers.get(i).compute();
			time[i] += mappers.get(i).sorting();
			//System.out.println();
		}
		for(int i=0;i<mappers.size();i++){
			if(time[i] > overallMappingTime)
				overallMappingTime = time[i];
		}
		
		return overallMappingTime;
	}
	
	public double transfer(){
		double delayTime = 0, overAllDelay = 0;
		for(int mapperNum=0;mapperNum<MapReduce.getMappers().size();mapperNum++){
			for(int reducerNum=0;reducerNum<MapReduce.getReducers().size();reducerNum++){
				//Send data partitions to reducers
				MapReduce.getReducers().get(reducerNum).addRecordsNum(MapReduce.getMappers().get(mapperNum).getRecordsNum() / MapReduce.getReducersNum()) ;
				
				double data = MapReduce.getMappers().get(mapperNum).getDataSize() / MapReduce.getReducersNum();
				//Multiply size bias
				data *= MapReduce.getSizeBias();
				
				//Compute network delay
				delayTime = data / MapReduce.getNetworkBW();
				//Find the longest delay time
				if(delayTime > overAllDelay) overAllDelay = delayTime;
				MapReduce.getReducers().get(reducerNum).setDataSize(MapReduce.getReducers().get(reducerNum).getDataSize() + data);
			}
		}
		//System.out.println("*** Overall delay time: " + overAllDelay + " ***");
		
		return overAllDelay;
	}
	
	public double reduce(){
		double overallReducingTime = 0;
	
		double[] time = new double[MapReduce.getReducers().size()];
		for(int i=0;i<MapReduce.getReducers().size();i++){
			time[i] = 0 ;
			time[i] += reducers.get(i).merge();
			time[i] += reducers.get(i).output();
			//System.out.println();
		}
		for(int i=0;i<reducers.size();i++){
			if(time[i] > overallReducingTime)
				overallReducingTime = time[i];
		}
		//System.out.println("*** Overall Reducing time : " + overallReducingTime + " ***");
		
		return overallReducingTime;
	}
	
	
	public static int getReducersNum() {
		return reducersNum;
	}
	public void setReducersNum(int reducersNum) {
		MapReduce.reducersNum = reducersNum;
	}
	public static LinkedList<Mapper> getMappers() {
		return mappers;
	}
	public void setMappers(LinkedList<Mapper> mappers) {
		MapReduce.mappers = mappers;
	}
	public static LinkedList<Reducer> getReducers() {
		return reducers;
	}
	public void setReducers(LinkedList<Reducer> reducers) {
		MapReduce.reducers = reducers;
	}
	public static LinkedList<Node> getNodeList() {
		return nodeList;
	}
	public static void setNodeList(LinkedList<Node> nodeList) {
		MapReduce.nodeList = nodeList;
	}

	public static HDFS getHDFS() {
		return HDFS;
	}
	public static void setHDFS(HDFS hDFS) {
		HDFS = hDFS;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		//System.out.println("MapReduce set app:" + app.getName());
		this.app = app;
	}
	public static double getSizeBias() {
		return sizeBias;
	}

	public void setSizeBias(double sizeBias) {
		MapReduce.sizeBias = sizeBias;
	}

	public static double getRecordBias() {
		return recordBias;
	}

	public void setRecordBias(double recordBias) {
		MapReduce.recordBias = recordBias;
	}

	public static double getComputeTime() {
		return computeTime;
	}

	public static void setComputeTime(double computeTime) {
		MapReduce.computeTime = computeTime;
	}

	public static double getIoTime() {
		return ioTime;
	}

	public static void setIoTime(double ioTime) {
		MapReduce.ioTime = ioTime;
	}

	public static double getNetworkBW() {
		return networkBW;
	}

	public static void setNetworkBW(double networkBW) {
		MapReduce.networkBW = networkBW;
	}
	
	

	
	
	
	
	/*
	@Override
	public void addNode(Node node) {
		// TODO Auto-generated method stub
		MapReduce.getNodeList().add(node);
	}
	@Override
	public void deleteNode(Node node) {
		// TODO Auto-generated method stub
		MapReduce.getNodeList().remove(node);
	}
	
	@Override
	public void map(Application app) {
		// TODO Auto-generated method stub
		Node node = new Node();
		for(int i=0;i<MapReduce.getNodeList().size()-1;i++){
			node = MapReduce.getNodeList().get(i);
			node.compute(app);
		}
	}*/

	
	
}
