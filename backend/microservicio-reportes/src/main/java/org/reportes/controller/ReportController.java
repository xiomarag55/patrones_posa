package org.reportes.controller;

import org.reportes.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ReportController {

    private final ReportService reportService;

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
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime fechaInicio = parseFecha(inicio, isoFormatter, customFormatter, dateOnlyFormatter, true);
        LocalDateTime fechaFin = parseFecha(fin, isoFormatter, customFormatter, dateOnlyFormatter, false);

        return reportService.generarReporte(fechaInicio, fechaFin);
    }

    // Método auxiliar para intentar múltiples formatos
    private LocalDateTime parseFecha(String fechaStr,
                                     DateTimeFormatter isoFormatter,
                                     DateTimeFormatter customFormatter,
                                     DateTimeFormatter dateOnlyFormatter,
                                     boolean isStart) {
        fechaStr = fechaStr.trim();
        try {
            return LocalDateTime.parse(fechaStr, isoFormatter);
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(fechaStr, customFormatter);
            } catch (Exception ex) {
                try {
                    LocalDate date = LocalDate.parse(fechaStr, dateOnlyFormatter);
                    return isStart ? date.atStartOfDay() : date.atTime(23, 59, 59);
                } catch (Exception exc) {
                    throw new IllegalArgumentException("Formato de fecha inválido: " + fechaStr);
                }
            }
        }
    }
}
