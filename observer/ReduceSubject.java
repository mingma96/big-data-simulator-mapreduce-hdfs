package observer;

import mapreduce.Reducer;
import physics.Node;

public interface ReduceSubject {
	//Add observer
    void addNode(Reducer reducer);
    //Delete observer
    void deleteNode(Reducer reducer);
    //depoly the app
    void send(double result, Node node);
}
