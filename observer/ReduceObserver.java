package observer;

import application.Application;
import physics.Node;

public interface ReduceObserver {
	public void reduce(double result, Node node);
}
