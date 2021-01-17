package samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "samples.mybatis.mapper")
public class SamplesMybatisApplication {
  public static void main(String[] args) {
    SpringApplication.run(SamplesMybatisApplication.class, args);
  }
}
