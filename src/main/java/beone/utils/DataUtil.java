package beone.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import beone.entity.SensorData;
import beone.service.DataService;

@Component
public class DataUtil {
	
	public static String turleState;

	@Autowired
	private DataService dataServiceImpl;

	public static DataUtil dataUtil;

	@PostConstruct
	public void init() {
		dataUtil = this;
		dataUtil.dataServiceImpl = this.dataServiceImpl;
	}

	static List<String> list = new ArrayList<String>();
	
	public static void saveAllData(){
		for (String string : list) {
			stringToEntity(string);
			list.remove(string);
		}
		
	}

	public static SensorData stringToEntity(String body){
		Gson gson = new Gson();
		SensorData sensorData = null;
		try {
			sensorData = gson.fromJson(body, SensorData.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		if (sensorData != null) {
			sensorData.setTime(new Date().getTime());
		}
		try {
			dataUtil.dataServiceImpl.saveData(sensorData);
			list.remove(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensorData;
	}


	public static List<String> getList() {
		return list;
	}

	public static void putData(String data) {
		list.add(data);
		dataUtil.dataServiceImpl.putData(data);
	}

	public static String getTurleState() {
		return turleState;
	}

	public static void setTurleState(String turleState) {
		DataUtil.turleState = turleState;
	}
	

}
