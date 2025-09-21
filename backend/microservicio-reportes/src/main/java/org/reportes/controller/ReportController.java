package org.reportes.controller;

import org.reportes.service.ReportService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    private final ReportService reportService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/ventas")
    public double generarReporte(
            @RequestParam String inicio,
            @RequestParam String fin) {

        // Lista de posibles formatos
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime fechaInicio = parseFecha(inicio, isoFormatter, customFormatter);
        LocalDateTime fechaFin = parseFecha(fin, isoFormatter, customFormatter);

        return reportService.generarReporte(fechaInicio, fechaFin);
    }

    // Método auxiliar para intentar múltiples formatos
    private LocalDateTime parseFecha(String fechaStr, DateTimeFormatter... formatters) {
        fechaStr = fechaStr.trim();
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(fechaStr, formatter);
            } catch (Exception e) {
                // sigue intentando con el siguiente formato
            }
        }
        throw new IllegalArgumentException("Formato de fecha inválido: " + fechaStr);
    }
}