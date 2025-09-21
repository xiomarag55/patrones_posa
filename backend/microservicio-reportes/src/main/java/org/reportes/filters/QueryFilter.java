package org.reportes.filters;


import org.reportes.entity.Venta;
import org.reportes.repository.VentaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class QueryFilter {
    private final VentaRepository ventaRepository;

    public QueryFilter(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> filter(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }
}
