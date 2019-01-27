package observer;

import hdfs.DataBlock;

public interface HDFSObserver {
	public void updateData(DataBlock data);

}
