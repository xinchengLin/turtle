package beone.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beone.dao.DataDao;
import beone.entity.SensorData;
import beone.service.DataService;
import beone.utils.DataUtil;
import beone.webscoket.PutDataScocket;

@Service
public class DataServiceImpl implements DataService {

	public static String data;

	@Autowired
	private DataDao dataDao;

	public void putData(String data) {
		PutDataScocket putData = new PutDataScocket();
		SensorData stringToEntity = DataUtil.stringToEntity(data);
		DataServiceImpl.data = stringToEntity.toString();
		System.out.println(DataServiceImpl.data);
		putData.start();
	}

	@Override
	public void saveData(SensorData body) {
		dataDao.save(body);
		
	}
	@Override
	/*
	 * 获取过去一个小时的传感器数据
	 * @see beone.service.DataService#getPastHourData()
	 */
	public List<SensorData> getPastHourData() {
		long date = new Date().getTime();
		List<SensorData> list = dataDao.getHistory(date-3600000,date); 
		return list;
	}

	@Override
	public List<SensorData> getHistory(long from, long to) {
		List<SensorData> list = dataDao.getHistory(from,to); 
		return list;
	}
	

}
