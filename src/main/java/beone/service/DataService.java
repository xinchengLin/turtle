package beone.service;

import java.util.List;

import beone.entity.SensorData;

public interface DataService {

	public void putData(String body);
	public void saveData(SensorData body);
	public List<SensorData> getPastHourData();
	public List<SensorData> getHistory(long from, long to);
	public List<SensorData> get10line();

	

}
