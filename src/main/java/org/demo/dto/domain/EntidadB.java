package org.demo.dto.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class EntidadB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "entidad_a_id")
    private EntidadA entidadA;

    @OneToMany(mappedBy = "entidadB", cascade = CascadeType.ALL)
    private List<EntidadC> entidadCList;
}