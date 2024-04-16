package org.demo.dto.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EntidadC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;

    @ManyToOne
    @JoinColumn(name = "entidad_b_id")
    private EntidadB entidadB;
}