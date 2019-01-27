package observer;

import application.Application;
import mapreduce.MapReduce;
import physics.Node;

public interface MapSubject {

	//Add observer
    void addNode(Node node);
    //Delete observer
    void deleteNode(Node node);
    //depoly the app
    void map(Application app);
    
}
