package org.reportes.dto;

import java.util.Map;

public record ReporteDTO(String clienteId, String nombre, String rendimiento, Map<String, Object> metricas) {}

