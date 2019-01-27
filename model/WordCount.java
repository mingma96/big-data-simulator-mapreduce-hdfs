package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WordCount {
	private String filePath;
	
	/**
	 * @param filePath: the path for the input file
	 * @throws IOException
	 */
	public void count(String filePath) throws IOException{
		
		//The bufferedReader, getting file by filePath
        BufferedReader br = new BufferedReader(new FileReader(filePath));  
        
        //The container for storing words
        List<String> lists = new ArrayList<String>();
        
        //Read file by each line
        String readLine = null;
        
        //Retrieve the file
		while((readLine = br.readLine()) != null){ 
			//Count alphabetic character
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");
            for (String word : wordsArr1) {  
            	//Remove empty word
                if(word.length() != 0){ 
                	//Put valid word into list
                    lists.add(word);  
                }  
            }  
        }  
          
		//Close the bufferedReader
        br.close();  
          
        //Create the TreeMap
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();
       
        for (String li : lists) {  
            if(wordsCount.get(li) != null){  
                wordsCount.put(li,wordsCount.get(li) + 1);  
            }else{  
                wordsCount.put(li,1);  
            }  
        }     
        
        SortMap(wordsCount); 
      
    }  
      
    public static void SortMap(Map<String,Integer> oldmap){  
          
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());  
          
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
            @Override  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue() - o1.getValue(); 
            }  
        });  
         
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}  
}
