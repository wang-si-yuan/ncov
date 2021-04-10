package site.ncov.www.ncov;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("site.ncov.www.ncov.**.mapper")
public class NcovApplication {

    public static void main(String[] args) {
        SpringApplication.run(NcovApplication.class, args);
    }

}
