package back;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"back"})
public class TorisonBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(TorisonBackApplication.class,args);
    }
}
