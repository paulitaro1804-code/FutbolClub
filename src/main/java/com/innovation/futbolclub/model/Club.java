package com.innovation.futbolclub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.*;

@Document(collection = "clubs")
public class Club {
  @Id
  private String id;
  private String nombre;
  private String ciudad;
  private Integer fundacion;

  @DBRef
  private List<Equipo> equipos = new ArrayList<>();

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCiudad() { return ciudad; }
  public void setCiudad(String ciudad) { this.ciudad = ciudad; }
  public Integer getFundacion() { return fundacion; }
  public void setFundacion(Integer fundacion) { this.fundacion = fundacion; }
  public List<Equipo> getEquipos() { return equipos; }
  public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }
}
