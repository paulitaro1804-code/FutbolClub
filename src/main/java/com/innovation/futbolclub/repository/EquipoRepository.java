package com.innovation.futbolclub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.innovation.futbolclub.model.Equipo;

public interface EquipoRepository extends MongoRepository<Equipo, String> {
  List<Equipo> findByClubId(String clubId);
}
