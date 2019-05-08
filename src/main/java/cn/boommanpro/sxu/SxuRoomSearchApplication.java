package cn.boommanpro.sxu;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author BoomMan
 */
@SpringBootApplication
@EnableConfigurationProperties({SchoolConfigProperties.class})
public class SxuRoomSearchApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(SxuRoomSearchApplication.class, args);
        printWebPath(context);
    }


    public static void printWebPath(ApplicationContext context) throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) context.getBean("tomcatServletWebServerFactory");
        int port = tomcatServletWebServerFactory.getPort();
        String contextPath = tomcatServletWebServerFactory.getContextPath();
        String path = "http://" + host + ":" + port + contextPath + "/";
        System.out.println("<------------------------------------------  " + path + "  ------------------------------------------>");
    }

}
