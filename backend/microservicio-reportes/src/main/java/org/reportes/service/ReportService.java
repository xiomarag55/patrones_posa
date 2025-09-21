package org.reportes.service;

import org.reportes.filters.QueryFilter;
import org.reportes.filters.TransformFilter;
import org.reportes.filters.ValidationFilter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ReportService {

    private final ValidationFilter validationFilter;
    private final QueryFilter queryFilter;
    private final TransformFilter transformFilter;

    public ReportService(ValidationFilter validationFilter, QueryFilter queryFilter, TransformFilter transformFilter) {
        this.validationFilter = validationFilter;
        this.queryFilter = queryFilter;
        this.transformFilter = transformFilter;
    }

    public double generarReporte(LocalDateTime inicio, LocalDateTime fin) {
        validationFilter.validate(inicio, fin); // filtro 1
        var ventas = queryFilter.filter(inicio, fin); // filtro 2
        return transformFilter.transform(ventas); // filtro 3
    }
}