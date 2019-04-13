package beone.webscoket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import beone.service.impl.DataServiceImpl;

public class PutDataScocket extends Thread {
	
	@Override
	public void run() {
		String data = DataServiceImpl.data;
		putData(data);
	}

	public static void putData(String data) {
		CopyOnWriteArraySet<WebScocketServer> webSocketSet = WebScocketServer.getWebSocketSet();
		if(webSocketSet.isEmpty()){
			
		}else{
			for (WebScocketServer item : webSocketSet) {
				try {
					item.sendMessage(data);
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				} 
			}
		}
	}
}
