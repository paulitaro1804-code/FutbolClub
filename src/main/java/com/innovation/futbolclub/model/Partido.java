package com.innovation.futbolclub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDateTime;

@Document(collection = "partidos")
public class Partido {
  @Id
  private String id;

  private LocalDateTime fecha;
  private String estadio;

  @DBRef
  private Equipo local;

  @DBRef
  private Equipo visitante;

  private Integer golesLocal;
  private Integer golesVisitante;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public LocalDateTime getFecha() { return fecha; }
  public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
  public String getEstadio() { return estadio; }
  public void setEstadio(String estadio) { this.estadio = estadio; }
  public Equipo getLocal() { return local; }
  public void setLocal(Equipo local) { this.local = local; }
  public Equipo getVisitante() { return visitante; }
  public void setVisitante(Equipo visitante) { this.visitante = visitante; }
  public Integer getGolesLocal() { return golesLocal; }
  public void setGolesLocal(Integer golesLocal) { this.golesLocal = golesLocal; }
  public Integer getGolesVisitante() { return golesVisitante; }
  public void setGolesVisitante(Integer golesVisitante) { this.golesVisitante = golesVisitante; }
}
