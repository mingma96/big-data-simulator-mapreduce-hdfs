package observer;

import mapreduce.MapReduce;

public interface AppSubject {
	//Add observer
    void setMapReduce(MapReduce obj);
    //Delete observer
    void deleteMapReduce(MapReduce obj);
    //Notify the mapper
    void start();
}
