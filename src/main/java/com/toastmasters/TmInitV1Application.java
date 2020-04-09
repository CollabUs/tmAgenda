package com.toastmasters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class TmInitV1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(TmInitV1Application.class, args);
	}

}
