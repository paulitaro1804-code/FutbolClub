package com.innovation.futbolclub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.*;

@Document(collection = "equipos")
public class Equipo {
  @Id
  private String id;

  private String nombre;
  private String categoria;

  @DBRef
  private Club club;

  @DBRef
  private List<Jugador> jugadores = new ArrayList<>();

  @DBRef
  private List<Partido> comoLocal = new ArrayList<>();

  @DBRef
  private List<Partido> comoVisitante = new ArrayList<>();

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getCategoria() { return categoria; }
  public void setCategoria(String categoria) { this.categoria = categoria; }
  public Club getClub() { return club; }
  public void setClub(Club club) { this.club = club; }
  public List<Jugador> getJugadores() { return jugadores; }
  public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }
}
