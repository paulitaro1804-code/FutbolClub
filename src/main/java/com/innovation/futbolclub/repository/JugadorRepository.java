package com.innovation.futbolclub.repository;

import com.innovation.futbolclub.model.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface JugadorRepository extends MongoRepository<Jugador, String> {

  List<Jugador> findByEquipoId(String equipoId);
}
