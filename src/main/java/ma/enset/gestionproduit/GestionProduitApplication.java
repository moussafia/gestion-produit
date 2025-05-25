package ma.enset.gestionproduit;

import ma.enset.gestionproduit.entities.Product;
import ma.enset.gestionproduit.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GestionProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionProduitApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			List<Product> products = List.of(
					Product.builder().name("PC").price(5400d).quantity(50).build(),
					Product.builder().name("Printer").price(1200d).quantity(20).build(),
					Product.builder().name("Smart phone").price(10400d).quantity(33).build()
			);
			productRepository.saveAll(products);
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
