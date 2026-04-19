package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Equipo;
import com.innovation.futbolclub.model.Partido;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {
    private final MongoTemplate mongoTemplate;

    public PartidoController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping
    public List<Partido> listar() {
        return mongoTemplate.find(new Query(), Partido.class, "partidos");
    }

    @GetMapping("/{id}")
    public Partido obtener(@PathVariable String id) {
        Partido partido = mongoTemplate.findById(id, Partido.class, "partidos");
        if (partido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partido no encontrado");
        }
        return partido;
    }

    @PostMapping
    public Partido crear(@RequestBody Map<String, Object> payload) {
        Partido partido = new Partido();
        partido.setEstadio((String) payload.get("estadio"));
        partido.setFecha(parseFecha(payload.get("fecha")));
        partido.setGolesLocal(payload.get("golesLocal") != null ? ((Number) payload.get("golesLocal")).intValue() : 0);
        partido.setGolesVisitante(payload.get("golesVisitante") != null ? ((Number) payload.get("golesVisitante")).intValue() : 0);
        bindEquipos(partido, payload);
        partido.setId(null);
        return mongoTemplate.save(partido, "partidos");
    }

    @PutMapping("/{id}")
    public Partido actualizar(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        Partido existente = mongoTemplate.findById(id, Partido.class, "partidos");
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partido no encontrado");
        }
        existente.setEstadio((String) payload.get("estadio"));
        existente.setFecha(parseFecha(payload.get("fecha")));
        existente.setGolesLocal(payload.get("golesLocal") != null ? ((Number) payload.get("golesLocal")).intValue() : 0);
        existente.setGolesVisitante(payload.get("golesVisitante") != null ? ((Number) payload.get("golesVisitante")).intValue() : 0);
        bindEquipos(existente, payload);
        return mongoTemplate.save(existente, "partidos");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "partidos");
    }

    private void bindEquipos(Partido partido, Map<String, Object> payload) {
        Object localId = payload.get("localId");
        Object visitanteId = payload.get("visitanteId");
        partido.setLocal(localId != null ? mongoTemplate.findById(localId.toString(), Equipo.class, "equipos") : null);
        partido.setVisitante(visitanteId != null ? mongoTemplate.findById(visitanteId.toString(), Equipo.class, "equipos") : null);
    }

    private LocalDateTime parseFecha(Object rawFecha) {
        if (rawFecha == null) {
            return null;
        }
        return LocalDateTime.parse(rawFecha.toString());
    }
}
