package samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"samples.core", "samples.mybatis", "samples.quartz", "samples.demo"})
@MapperScan(basePackages = "samples.mybatis.mapper")
public class SamplesDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(SamplesDemoApplication.class, args);
  }
}
