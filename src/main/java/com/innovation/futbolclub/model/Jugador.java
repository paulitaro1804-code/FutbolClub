package com.innovation.futbolclub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "jugadores")
public class Jugador {
  @Id
  private String id;
  private String nombre;
  private Integer dorsal;
  private String posicion;

  @DBRef
  private Equipo equipo;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public Integer getDorsal() { return dorsal; }
  public void setDorsal(Integer dorsal) { this.dorsal = dorsal; }
  public String getPosicion() { return posicion; }
  public void setPosicion(String posicion) { this.posicion = posicion; }
  public Equipo getEquipo() { return equipo; }
  public void setEquipo(Equipo equipo) { this.equipo = equipo; }
}
