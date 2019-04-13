package beone.utils;

import java.util.HashMap;

import io.netty.channel.ChannelHandlerContext;

public class ChannelUtil {
	public static int channelNum = 0;
	private static HashMap<String, ChannelHandlerContext> channelHashMap;

	public static HashMap<String, ChannelHandlerContext> getMap() {
		return channelHashMap;
	}

	public static ChannelHandlerContext getChannelByName(String name) {
		if (channelHashMap == null || channelHashMap.isEmpty()) {
			return null;
		}
		return channelHashMap.get(name);
	}

	public static void addChannel(String name, ChannelHandlerContext channel) {
		if (channelHashMap == null) {
			channelHashMap = new HashMap<String, ChannelHandlerContext>();
		}
		channelHashMap.put(name, channel);
		channelNum++;
		System.out.println(channelHashMap);
		System.out.println(channelNum);
	}

	public static int removeChannelByName(String name) {
		if (channelHashMap.containsKey(name)) {
			channelHashMap.remove(name);
			channelNum--;
			return 0;
		} else {
			return 1;
		}
	}

}
