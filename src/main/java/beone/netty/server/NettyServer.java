package beone.netty.server;

import beone.netty.handlers.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer implements Runnable {

	public void run() {
		EventLoopGroup parentGroup = new NioEventLoopGroup(); // 线程组：用来处理网络事件处理（接受客户端连接）
		EventLoopGroup childGroup = new NioEventLoopGroup(); // 线程组：用来进行网络通讯读写
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(parentGroup, childGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024).handler(new LoggingHandler(LogLevel.INFO)) // 设置tcp缓冲区
					.option(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送缓冲大小
					.option(ChannelOption.SO_RCVBUF, 32 * 1024) // 这是接收缓冲大小
					.option(ChannelOption.SO_KEEPALIVE, true) // 保持连接
					
					.childHandler(new ChannelInitializer<SocketChannel>() {
						protected void initChannel(SocketChannel sc) throws Exception {
							// 5s没有交互，就会关闭channel
							sc.pipeline().addLast(new ServerHandler()); // 服务端业务处理类
							// sc.pipeline().addLast(new NettyServerHandler());
							// // 服务端业务处理类
						}
					});
			ChannelFuture channelFuture = null;
			channelFuture = serverBootstrap.bind(5678).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
			e.printStackTrace();
		}
	}
}
