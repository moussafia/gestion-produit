package ma.enset.gestionproduit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id @GeneratedValue
    private Long id;
    @NotEmpty @Size(min = 2, max = 50)
    private String name;
    @Min(0)
    private Double price;
    @Min(1)
    private int quantity;
}
