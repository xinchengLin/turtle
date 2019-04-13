package beone.netty.handlers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import beone.service.DataService;
import beone.utils.ChannelUtil;
import beone.utils.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

//@Controller
public class ServerHandler extends ChannelHandlerAdapter {
	@Autowired
	private DataService dataServiceImpl ;
	
	public static ServerHandler serverHandler;
	
	@PostConstruct
	public void init(){
		serverHandler = this ;
		serverHandler.dataServiceImpl = this.dataServiceImpl;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server channel active... ");
		System.out.println(ctx.channel().remoteAddress());
		String id = ctx.channel().id().asShortText();
		System.out.println(id);
		ChannelUtil.addChannel(id, ctx);
		System.out.println("tianjia");

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		try {
			buf.readBytes(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String body = new String(req, "utf-8");
		System.out.println("Server 接收到 :" + body);
		try {
			//判断是传过来的数据是海龟的状态数据 还是传感器数据
			if(body.contains("State")){
				DataUtil.setTurleState(body);
			}else
				DataUtil.putData(body);
			/*
			 * 将接受到的数据保存在DataUtil的list里面
			 */
			
//			DataUtil.saveAllData();
		} catch (Exception e) {
			e.printStackTrace();
		}
    
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server: 读完了");
		System.out.println(DataUtil.getList());
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("连接关闭");
		String name = ctx.channel().id().asShortText();
		ChannelUtil.removeChannelByName(name);
		ctx.close();
	}
}
