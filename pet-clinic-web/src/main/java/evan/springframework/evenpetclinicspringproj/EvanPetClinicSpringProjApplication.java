package evan.springframework.evenpetclinicspringproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//本来视频里是不需要加scanBasePackages的，但是出于某种原因spring并没有scan pet clinic data里的services
// 这个package
@SpringBootApplication(scanBasePackages = "evan.springframework.evanpetclinicspringproj.services")

public class EvanPetClinicSpringProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvanPetClinicSpringProjApplication.class, args);
	}

}
