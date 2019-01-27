package hdfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import physics.Node;

public class Utility {
	private LinkedList<Vector<UtilityElement>> list = new LinkedList<Vector<UtilityElement>>();
	private LinkedList<DataBlock> dataList = new LinkedList<DataBlock>();
	private List<Node> nodeList;
	private int replication;
	
	public Utility(LinkedList<DataBlock> dataList, List<Node> nodeList, int replication) {
		// TODO Auto-generated constructor stub
		this.dataList = dataList;
		this.nodeList = nodeList;
		this.replication = replication;
		
		this.distribute();
		//System.out.println("------HDFS: utility distributre");
		////System.out.println("------HDFS: list size: " + list.size());
		
		this.allocate(nodeList);
		////System.out.println("------HDFS: utility allocate");
		
		UtilityElement e;
		for(int i=0;i<list.size();i++){
			for(int j=0;j<replication;j++){
				e = list.get(i).get(j);
				//System.out.printf("{%s, %.0f MB, flag=%d}",e.getNode().getNodeName(),e.getDataBlock().getSize(),e.getDataBlock().getFlag());
				//if(j != replication - 1) System.out.print(" ---> ");
			}
			//System.out.println();
		}
		
	}
	
	public void distribute(){
		int nodeNum=0;
		
		for(int DataBlockNum=0;DataBlockNum<dataList.size();DataBlockNum++){
			//Create new vector for a new data block
			Vector<UtilityElement> newVector = new Vector<UtilityElement>();
			for(int newDataBlock=0;newDataBlock<replication;newDataBlock++,nodeNum++){
				if(nodeNum>=nodeList.size()) nodeNum=0;
				
				//Create new element
				UtilityElement element = new UtilityElement();
				DataBlock dataBlock = new DataBlock();
				
				//Use clone function in data block to copy replication
				dataBlock = (DataBlock)dataList.get(DataBlockNum).clone();
				
				//For original data block, set the flag to 1
				if(newDataBlock==0) {
					dataBlock.setFlag(1);
				}
				element.setDataBlock(dataBlock);
				element.setNode(nodeList.get(nodeNum));
				
				//Add element to vector
				newVector.add(element);
			}
			
			//Add vector to data block list
			list.add(newVector);
		}	
	}
	
	public void allocate(List<Node> nodeList){
		for(int dataBlcokNum=0;dataBlcokNum<list.size();dataBlcokNum++){
			for(int replicationNum=0;replicationNum<list.get(dataBlcokNum).size();replicationNum++){
				
				//Get the information about data block and node
				UtilityElement element = new UtilityElement();
				element = list.get(dataBlcokNum).get(replicationNum);
				int id = nodeList.indexOf(element.getNode());
				
				//Add the data block the data list in nodes
				nodeList.get(id).getDataList().add(element.getDataBlock());
			}
		}
	}

	public LinkedList<Vector<UtilityElement>> getList() {
		return list;
	}
	
	
}
