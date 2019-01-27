package hdfs;

import java.util.List;

import physics.Node;

public class DataBlock implements Cloneable{
	//public  final int SIZE = 256;
	
	private int ID;
	private double size = 256;
	private double records;
	//private List<Node> nodeList;
	private int flag = 0;
	
	//The function for cloning a data block
	@Override
	public Object clone(){
	
		//Declare a new object
		DataBlock block = null;
		
		try{
			//The super class is Object
			//Cast the cloned object into DataBlock
			block = (DataBlock)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		
		return block;
	}
	
	//Getters and setters
	
	public int getID() {
		return ID;
	}
/*	public List<Node> getNodeList() {
		return nodeList;
	}
	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}*/
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public void setSize(double d) {
		this.size = d;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public double getSize() {
		return size;
	}
	
	
	
}
