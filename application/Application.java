package application;

import java.util.List;

import hdfs.DataBlock;
import mapreduce.MapReduce;
import observer.AppSubject;
import physics.Node;

public abstract class Application implements AppSubject{
	private String name;
	private String fileName;
	private List<DataBlock> dataList;
	private static MapReduce mapReduce;
	
	public abstract float getComplexity(int n);
	//Getters and setters
	

	public static MapReduce getMapReduce() {
		return mapReduce;
	}
	

	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	/*public static void setMapReduce(MapReduce mapReduce) {
		Application.mapReduce = mapReduce;
	}*/


	public List<DataBlock> getDataList() {
		return dataList;
	}

	public void setDataList(List<DataBlock> dataList) {
		this.dataList = dataList;
	}
	

	@Override
	public void setMapReduce(MapReduce newMapReduce) {
		// TODO Auto-generated method stub
		Application.mapReduce = newMapReduce;
	}

	@Override
	public void deleteMapReduce(MapReduce obj) {
		// TODO Auto-generated method stub
		Application.mapReduce = null;
		
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("App started: " + this.getName());
		//MapReduce.getMappers().map(this);
		
	}
	
	public abstract int function(Node node);
	
}
