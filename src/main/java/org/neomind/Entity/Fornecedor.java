package org.neomind.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String description;
    private String cnpj;

}
