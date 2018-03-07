package logtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
@EnableAutoConfiguration
public class SpringBootLog4jApplication {

    private Logger logger = LogManager.getLogger(SpringBootLog4jApplication.class);

    @RequestMapping("/")
    @ResponseBody
    String home() {

        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootLog4jApplication.class, args);
    }
}
