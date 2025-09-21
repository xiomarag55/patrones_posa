package org.reportes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Double getTotal() {
        return total;
    }


}
