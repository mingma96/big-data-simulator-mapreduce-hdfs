package application;

import physics.Node;

public class TestApp extends Application {

	public TestApp(){
		super.setName("New TestApp");
	}
	
	@Override
	public float getComplexity(int n) {
		
		return n;
	}
	
	@Override
	public int function(Node node) {
		int sum=0;
		for(int i=0;i<node.getDataList().size();i++)
			sum += node.getDataList().get(i).getRecords();
		return sum;
	}
	

	

}
