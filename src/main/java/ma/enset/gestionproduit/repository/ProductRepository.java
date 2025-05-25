package ma.enset.gestionproduit.repository;

import ma.enset.gestionproduit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
