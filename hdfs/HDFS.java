package hdfs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import observer.HDFSSubject;
import physics.Node;

public class HDFS{
	//private static final ActionListener ActionListener = null;
	private static int replication = 1;
	private static Utility utility;
	private static LinkedList<DataBlock> dataList = new LinkedList<DataBlock>();
	private static LinkedList<Node> nodeList = new LinkedList<Node>();
	private static LinkedList<File> fileList = new LinkedList<File>();
	private static double dataBlockSize = 256;
	
	public void initiate() throws FileNotFoundException, IOException, ParseException{
		//this.readJson(str);
		//System.out.println("------HDFS: read json");
		HDFS.utility = new Utility(dataList, nodeList, replication);
	}
	
	public void addFile(double size){
		File file = new File();
		file.setSize(size);
		HDFS.getFileList().add(file);
		distribute(file);
	}
	
	
	//Break new file into data blocks
	private List<DataBlock> distribute(File file){
		//Temporary total size
		int sum=0;
		double size = file.getSize();
		
		//Create new data blocks until the size is larger than input
		while(sum<size){
			DataBlock newBlock = new DataBlock();
			//Data block size is defined in HDFS
			newBlock.setSize(HDFS.getDataBlockSize());
			sum += newBlock.getSize();
			//Add the new data block to data list
			HDFS.getDataList().add(newBlock);
		}
		
		return HDFS.getDataList();
	}
	
	// Create new File objects from Json file, then add to FileList
	public void readJson(String str) throws FileNotFoundException, IOException, ParseException{
		//Create new JSONParser
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(str));
		
		JSONObject jsonObject = (JSONObject) obj;
        JSONArray files = (JSONArray) jsonObject.get("files");
        
        //Retrieve all files defined in json
        for(Object file : files){
        	 File newFile = new File();
             JSONObject jsonFile = (JSONObject) file;
             
             String newFilename = (String)jsonFile.get("Filename");
             newFile.setFileName(newFilename);
             String newSize = jsonFile.get("Size").toString();
             newFile.setSize(Double.parseDouble(newSize));
             
             //Add new file into file list
             HDFS.fileList.add(newFile);
             distribute(newFile);
        }
	}

	public static int getReplication() {
		return replication;
	}

	public static void setReplication(int replication) {
		HDFS.replication = replication;
	}

	public static List<DataBlock> getDataList() {
		return dataList;
	}

	public static void setDataList(List<DataBlock> partition) {
		HDFS.dataList = (LinkedList<DataBlock>) partition;
	}

	public static List<Node> getNodeList() {
		return nodeList;
	}

	public static void setNodeList(LinkedList<Node> nodeList) {
		HDFS.nodeList = nodeList;
	}

	public static List<File> getFileList() {
		return fileList;
	}

	public static void setFileList(List<File> fileList) {
		HDFS.fileList = (LinkedList<File>) fileList;
	}


	public static double getDataBlockSize() {
		return dataBlockSize;
	}


	public static void setDataBlockSize(double dataBlockSize) {
		HDFS.dataBlockSize = dataBlockSize;
	}
	
	
	
}
