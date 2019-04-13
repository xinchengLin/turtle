package beone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import beone.netty.server.NettyServer;


@EnableAutoConfiguration
@ComponentScan("beone")
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
		try {
			runNettyServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void runNettyServer() {
		Thread thread = new Thread(new NettyServer());
		thread.start();
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }


}