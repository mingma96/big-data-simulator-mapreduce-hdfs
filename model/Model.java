package model;

import java.io.File;
import java.io.IOException;

public class Model {
	private WordCount wordCount = new WordCount();
	private Point p1 = new Point();
	private Point p2 = new Point();
	
	private double a,b;
	
	public void test(String file1, String file2) throws IOException{
		p1.setSize(getFileSize(file1));
		
		long startTime = System.currentTimeMillis();
		wordCount.count(file1);
		long endTime = System.currentTimeMillis();
		p1.setTime(endTime - startTime);
		//System.out.println("File 1 size: " + p1.getSize() + " time : " + p1.getTime());
		
		p2.setSize(getFileSize(file2));
		
		startTime = System.currentTimeMillis();
		wordCount.count(file2);
		endTime = System.currentTimeMillis();
		p2.setTime(endTime - startTime);
	
	}
	
	 private double getFileSize(String str) {
		File file = new File(str);
		double fileSize;
	    if (file.exists() && file.isFile()){
	    	fileSize = file.length();
	    	fileSize = fileSize / 1024 / 1024;
	    	return fileSize;
	    }
	    else return 0;
	 }
	 
	 public double model(double x){
		 double y;
		 
		 a = (p1.getTime() - p2.getTime()) / (p1.getSize() - p2.getSize());
		 b = p1.getTime() - a * p1.getSize();
		 
		 y = a * x + b;
		 
		 return y;
		 
	 }
}
