package observer;

import hdfs.DataBlock;
import hdfs.File;
import physics.Node;

public interface HDFSSubject {
	//Add observer
    void addNode(Node obj);
    //Delete observer
    void deleteNode(Node obj);
   
    void publish(DataBlock db);
}
