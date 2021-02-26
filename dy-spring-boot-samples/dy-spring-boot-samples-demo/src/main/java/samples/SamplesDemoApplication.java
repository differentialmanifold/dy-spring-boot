package samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"samples.core", "samples.mybatis", "samples.quartz", "samples.demo"})
@MapperScan(basePackages = "samples.mybatis.mapper")
public class SamplesDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(SamplesDemoApplication.class, args);
  }
}
