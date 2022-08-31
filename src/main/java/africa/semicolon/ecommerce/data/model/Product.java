package africa.semicolon.ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Positive
    private BigDecimal price;
    private String description;
    private String imageUrl;
    @Min(value = 1, message = "quantity should be greater than zero")
    private int quantity;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateAdded = LocalDate.now();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Review> reviews;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_product_category",
            joinColumns = { @JoinColumn(name = "fk_product") },
            inverseJoinColumns = { @JoinColumn(name = "fk_product_category") })
    private List<ProductCategory> productCategory = new ArrayList<>();




}
// TODO: Image bucket aws