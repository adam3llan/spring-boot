package net.codejava.CodeJavaApp.User;

import javax.persistence.*;

@Entity
@Table(name = "products")

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @Column(nullable = false, name="valueDesc")
    private String valueDesc;
    @Column(nullable = false,name="valueName")
    private String valueName;
    @Column(nullable = false, name="valuePrice")
    private Double valuePrice;

    public Products() {
    }

    public Products(Long product_id, String valueDesc, String valueName, Double valuePrice) {
        this.product_id = product_id;
        this.valueDesc = valueDesc;
        this.valueName = valueName;
        this.valuePrice = valuePrice;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Double getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(Double valuePrice) {
        this.valuePrice = valuePrice;
    }
}
