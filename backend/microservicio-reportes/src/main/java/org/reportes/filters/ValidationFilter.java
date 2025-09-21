package org.reportes.filters;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class ValidationFilter {
    public void validate(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin.");
        }
    }
}