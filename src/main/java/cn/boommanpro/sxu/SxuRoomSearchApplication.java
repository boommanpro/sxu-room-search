package cn.boommanpro.sxu;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author BoomMan
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({SchoolConfigProperties.class})
public class SxuRoomSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxuRoomSearchApplication.class, args);
    }

}
