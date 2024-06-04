package iesmb.pp3.planillaProfesores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "iesmb.pp3.planillaProfesores")
@SpringBootApplication
public class PlanillaProfesoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanillaProfesoresApplication.class, args);
	}

}
