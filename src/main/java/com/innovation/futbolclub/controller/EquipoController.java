package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Club;
import com.innovation.futbolclub.model.Equipo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final MongoTemplate mongoTemplate;

    public EquipoController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping
    public List<Equipo> listar(@RequestParam(required = false) String clubId) {
        if (clubId == null) {
            return mongoTemplate.find(new Query(), Equipo.class, "equipos");
        }
        Query query = new Query(Criteria.where("club.$id").is(clubId));
        return mongoTemplate.find(query, Equipo.class, "equipos");
    }

    @GetMapping("/{id}")
    public Equipo obtener(@PathVariable String id) {
        Equipo equipo = mongoTemplate.findById(id, Equipo.class, "equipos");
        if (equipo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }
        return equipo;
    }

    @PostMapping
    public Equipo crear(@RequestBody Map<String, Object> payload) {
        Equipo equipo = new Equipo();
        equipo.setNombre((String) payload.get("nombre"));
        equipo.setCategoria((String) payload.get("categoria"));
        bindClub(equipo, payload);
        equipo.setId(null);
        return mongoTemplate.save(equipo, "equipos");
    }

    @PutMapping("/{id}")
    public Equipo actualizar(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        Equipo existente = mongoTemplate.findById(id, Equipo.class, "equipos");
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }
        existente.setNombre((String) payload.get("nombre"));
        existente.setCategoria((String) payload.get("categoria"));
        bindClub(existente, payload);
        return mongoTemplate.save(existente, "equipos");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "equipos");
    }

    private void bindClub(Equipo equipo, Map<String, Object> payload) {
        Object clubId = payload.get("clubId");
        if (clubId != null) {
            Club club = mongoTemplate.findById(clubId.toString(), Club.class, "clubs");
            equipo.setClub(club);
        } else {
            equipo.setClub(null);
        }
    }
}
