package hdfs;

import physics.Node;

public class UtilityElement {
	//The node where the data block is going to be stored
	private Node node;
	
	//One data block in HDFS data list
	private DataBlock dataBlock;
	
	//Will be set as 1 for original data block
	private int flag = 0;
	
	
	public Node getNode() {
		return node;
	}
	
	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public void setNode(Node node) {
		this.node = node;
	}
	public DataBlock getDataBlock() {
		return dataBlock;
	}
	public void setDataBlock(DataBlock dataBlock) {
		this.dataBlock = dataBlock;
	}
	
	
	
}
