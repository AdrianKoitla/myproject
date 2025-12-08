package eu.itcrafters.myproject.persistence.product;

import eu.itcrafters.myproject.persistence.producttype.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_TYPE_ID", nullable = false)
    private ProductType productType;

    @Size(max = 100)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "SIZE", nullable = false, length = 10)
    private String size;

    @Size(max = 100)
    @NotNull
    @Column(name = "COLOR", nullable = false, length = 100)
    private String color;

}