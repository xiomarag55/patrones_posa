package org.reportes.filters;

import org.reportes.entity.Venta;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TransformFilter {
    public double transform(List<Venta> ventas) {

        return ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }
}
