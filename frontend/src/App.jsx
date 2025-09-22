"use client"

import { useState } from "react"

export default function App() {
  const [fechaInicial, setFechaInicial] = useState("")
  const [fechaFinal, setFechaFinal] = useState("")
  const [loading, setLoading] = useState(false)
  const [resultado, setResultado] = useState("")
  const [showResult, setShowResult] = useState(false)
  const [error, setError] = useState("")

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)
    setShowResult(false)
    setError("")

    // Define la URL base de tu servicio.
    const API_BASE_URL = "http://localhost:8080/ventas"

    // Construye la URL completa con las fechas como parámetros de consulta.
    const url_completa = `${API_BASE_URL}?inicio=${fechaInicial}&fin=${fechaFinal}`
    console.log("URL de la API:", url_completa)

    try {
      // Realiza la solicitud GET
      const response = await fetch(url_completa)
      
      // Manejo de errores de HTTP
      if (!response.ok) {
        throw new Error(`Error de red: ${response.statusText}`)
      }

      // Se asume que el servicio retorna un valor de texto simple como "0.0"
      const data = await response.text()
      
      // Actualiza el estado con el resultado de la API
      setResultado(data)
      setShowResult(true)
    } catch (error) {
      setError("Error al procesar la solicitud. Verifica la URL y la conexión.")
      setShowResult(true)
      setResultado("")
    } finally {
      setLoading(false)
    }
  }

  return (
    <>
      <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        crossOrigin="anonymous"
      />
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" />

      <div className="min-vh-100 bg-light d-flex align-items-center py-5">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-lg-5 col-md-7">
              <div className="card border-0 shadow-lg">
                <div className="card-header bg-primary text-white text-center py-4 border-0">
                  <div className="mb-2">
                    <i className="bi bi-calendar-range fs-1"></i>
                  </div>
                  <h2 className="mb-0 fw-bold">Consulta de Fechas</h2>
                  <p className="mb-0 opacity-75">Selecciona el rango de fechas para consultar</p>
                </div>

                <div className="card-body p-4">
                  <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                      <label className="form-label fw-semibold text-dark mb-2">
                        <i className="bi bi-calendar-event me-2 text-primary"></i>
                        Fecha Inicial
                      </label>
                      <input
                        type="date"
                        className="form-control form-control-lg border-2 rounded-3"
                        value={fechaInicial}
                        onChange={(e) => setFechaInicial(e.target.value)}
                        required
                        style={{ borderColor: "#e9ecef" }}
                      />
                    </div>

                    <div className="mb-4">
                      <label className="form-label fw-semibold text-dark mb-2">
                        <i className="bi bi-calendar-check me-2 text-primary"></i>
                        Fecha Final
                      </label>
                      <input
                        type="date"
                        className="form-control form-control-lg border-2 rounded-3"
                        value={fechaFinal}
                        onChange={(e) => setFechaFinal(e.target.value)}
                        required
                        min={fechaInicial}
                        style={{ borderColor: "#e9ecef" }}
                      />
                    </div>

                    <button
                      type="submit"
                      className="btn btn-primary btn-lg w-100 rounded-3 py-3 fw-semibold position-relative"
                      disabled={loading}
                      style={{
                        background: loading ? "#6c757d" : "linear-gradient(135deg, #0d6efd 0%, #0b5ed7 100%)",
                        border: "none",
                        transition: "all 0.3s ease",
                      }}
                    >
                      {loading ? (
                        <>
                          <span
                            className="spinner-border spinner-border-sm me-2"
                            role="status"
                            aria-hidden="true"
                          ></span>
                          Procesando...
                        </>
                      ) : (
                        <>
                          <i className="bi bi-search me-2"></i>
                          Consultar Datos
                        </>
                      )}
                    </button>
                  </form>

                  {showResult && (
                    <div className="mt-4">
                      {error ? (
                        <div className="alert alert-danger border-0 rounded-3 shadow-sm" role="alert">
                          <div className="d-flex align-items-center">
                            <i className="bi bi-x-circle-fill fs-4 me-3 text-danger"></i>
                            <div>
                              <h6 className="alert-heading mb-1 fw-bold">Error</h6>
                              <p className="mb-0">{error}</p>
                            </div>
                          </div>
                        </div>
                      ) : (
                        <div className="alert alert-success border-0 rounded-3 shadow-sm" role="alert">
                          <div className="d-flex align-items-center">
                            <i className="bi bi-check-circle-fill fs-4 me-3 text-success"></i>
                            <div>
                              <h6 className="alert-heading mb-1 fw-bold">¡Consulta Exitosa!</h6>
                              <p className="mb-0">{resultado}</p>
                            </div>
                          </div>
                        </div>
                      )}
                    </div>
                  )}
                </div>

                <div className="card-footer bg-light border-0 text-center py-3">
                  <small className="text-muted">
                    <i className="bi bi-info-circle me-1"></i>
                    Los datos se procesarán en tiempo real
                  </small>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}
