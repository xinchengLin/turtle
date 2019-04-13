package beone.controller;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import beone.entity.Order;
import beone.entity.State;
import beone.utils.ChannelUtil;
import beone.utils.DataUtil;
import beone.utils.ResultVOUtil;
import beone.vo.ResultVO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
/**
 * 发送命令控制类
 * @author xincheng
 *
 */
@Controller
@EnableAutoConfiguration
public class SendOrderController {

	/**
	 * 控制机器的运动
	 * @param direction
	 * @param value
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/device/move")
	@ResponseBody
	public ResultVO<Object> move(@RequestParam(value = "direction") String direction,
			@RequestParam(value = "value") int value, @RequestParam(value = "admin") boolean admin) {
		if(!admin){
			return ResultVOUtil.success("抱歉，你没有操作权限", "");
			
		}
		Order o = new Order("move", direction, value);
		/*
		 * 发送命令
		 */
		HashMap<String, ChannelHandlerContext> map = ChannelUtil.getMap();
		if (map == null || map.size() == 0) {
			return ResultVOUtil.fail("命令下达失败，设备不在线", null);

		}
		for (ChannelHandlerContext channel : map.values()) {
			try {
				channel.writeAndFlush(Unpooled.copiedBuffer(direction.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
				return ResultVOUtil.fail("命令下达失败，请检查设备", o);
			}
		}
		return ResultVOUtil.success("命令已接收", "");
	}

	@RequestMapping(value = "/device/openOff")
	@ResponseBody
	/*
	 * 控制设摄像头及照明灯的开关 name = Camera / LED value = 0 / 1 ，，1表示开。0表示关
	 */
	/**
	 * 控制机器人的开关
	 * @param key
	 * @param value
	 * @param admin
	 * @return
	 */
	public ResultVO<Object> openOff(@RequestParam(value = "name") String key,
			@RequestParam(value = "value") int value,
			 @RequestParam(value = "admin") boolean admin) {
		if(!admin){
			return ResultVOUtil.success("抱歉，你没有操作权限", "");
		}
		String name = key;
		if (name.equals("State")) {
			if (value == 1)
				name = "ON";
			else
				name = "OFF";
		}
		String sb = name + "_" + value;
		/*
		 * 发送命令
		 */
		HashMap<String, ChannelHandlerContext> map = ChannelUtil.getMap();
		if (map == null || map.size() == 0) {
			return ResultVOUtil.fail("命令下达失败，设备不在线", null);

		}
		for (ChannelHandlerContext channel : map.values()) {
			try {
				channel.writeAndFlush(Unpooled.copiedBuffer(sb.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
				return ResultVOUtil.fail("命令下达失败", sb);
			}
		}
		return ResultVOUtil.success("命令已接收", "");

	}

	/**
	 * 获取当前设备的状态
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/device/getState")
	@ResponseBody
	public ResultVO<Object> getState() throws InterruptedException {
		long outTime = 0;
		/*
		 * 发送命令
		 */
		HashMap<String, ChannelHandlerContext> map = ChannelUtil.getMap();
		if (map == null || map.size() == 0) {
			return ResultVOUtil.fail("命令下达失败，设备不在线", null);

		}
		String turleState = null;
		State state = null;
		for (ChannelHandlerContext channel : map.values()) {
			try {
				channel.writeAndFlush(Unpooled.copiedBuffer("Get".getBytes()));
				// 等待一秒，给出足够的时间给设备做出响应
				TimeUnit.MILLISECONDS.sleep(200);// 秒
			} catch (Exception e) {
				e.printStackTrace();
				return ResultVOUtil.fail("命令下达失败", null);
			}
			turleState = DataUtil.getTurleState();
			while (turleState == null) {
				// 如果还是没有拿到数据，重发获取指令，继续等待1秒
				channel.writeAndFlush(Unpooled.copiedBuffer("Get".getBytes()));
				TimeUnit.MILLISECONDS.sleep(100);// 秒
				turleState = DataUtil.getTurleState();
				outTime++;
				// 如果超过五秒，返回响应超时
				if (outTime > 5) {
					return ResultVOUtil.fail("获取状态超时", null);
				}
			}
			DataUtil.setTurleState(null);
			state = new Gson().fromJson(turleState, State.class);

		}
		return ResultVOUtil.success("状态获取成功", state);
	}

}
