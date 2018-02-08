package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository){
        return (args) -> {
            repository.save(new Customer("Jack", "Black"));
            repository.save(new Customer("Cloe", "White"));
            repository.save(new Customer("Kim", "Black"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()){
                log.info(customer.toString());
            }
            log.info("");

            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            log.info("Customer found with findByLastName('Black');");
            log.info("--------------------------------------------");
            for (Customer black : repository.findByLastName("Black")){
                log.info(black.toString());
            }
            log.info("");
        };
    }
}
