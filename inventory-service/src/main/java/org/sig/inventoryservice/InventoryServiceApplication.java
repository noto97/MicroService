package org.sig.inventoryservice;

import org.sig.inventoryservice.entities.Product;
import org.sig.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null,"Nintendo-Switch",4000));
            productRepository.save(new Product(null,"Xbox-X1",5000));
            productRepository.save(new Product(null,"Playstation5",4500));
            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });

        };
    }

}
