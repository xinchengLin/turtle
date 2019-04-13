package beone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import beone.entity.SensorData;
import beone.service.DataService;
import beone.utils.ResultVOUtil;
import beone.vo.ResultVO;

@Controller
@EnableAutoConfiguration
public class DataController {

		@Autowired
		private DataService dataServiceImpl;
		
		@RequestMapping(value = "/data/pastHour",method = RequestMethod.GET)
		@ResponseBody
		public ResultVO<Object> getPastHourData(){
			List<SensorData> dataList =  new ArrayList<SensorData>();
			dataList = dataServiceImpl.getPastHourData();
		
			return ResultVOUtil.success("已获取最近一个小时的传感器数据", dataList);

			
		}
			
		
		@RequestMapping(value = "/data/history",method = RequestMethod.POST)
		@ResponseBody
		public ResultVO<Object> getHistory(long from ,long to){
			List<SensorData> dataList =  dataServiceImpl.getHistory(from,to);
			return ResultVOUtil.success("已获取规定时间内的传感器数据", dataList);
			
		}
		
}
