package com.innovation.futbolclub.repository;

import com.innovation.futbolclub.model.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PartidoRepository extends MongoRepository<Partido, String> {

}
