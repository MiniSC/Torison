package back;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"back","back.util.config"})
public class TorisonBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(TorisonBackApplication.class,args);
    }
}
