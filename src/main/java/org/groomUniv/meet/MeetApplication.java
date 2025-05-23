package org.groomUniv.meet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 사용을 위한 설정
public class MeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetApplication.class, args);
	}

}
