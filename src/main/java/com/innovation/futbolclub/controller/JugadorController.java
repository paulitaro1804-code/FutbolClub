package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Equipo;
import com.innovation.futbolclub.model.Jugador;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    private final MongoTemplate mongoTemplate;

    public JugadorController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping
    public List<Jugador> listar(@RequestParam(required = false) String equipoId) {
        if (equipoId == null) {
            return mongoTemplate.find(new Query(), Jugador.class, "jugadores");
        }
        Query query = new Query(Criteria.where("equipo.$id").is(equipoId));
        return mongoTemplate.find(query, Jugador.class, "jugadores");
    }

    @GetMapping("/{id}")
    public Jugador obtener(@PathVariable String id) {
        Jugador jugador = mongoTemplate.findById(id, Jugador.class, "jugadores");
        if (jugador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado");
        }
        return jugador;
    }

    @PostMapping
    public Jugador crear(@RequestBody Map<String, Object> payload) {
        Jugador jugador = new Jugador();
        jugador.setNombre((String) payload.get("nombre"));
        jugador.setDorsal(payload.get("dorsal") != null ? ((Number) payload.get("dorsal")).intValue() : null);
        jugador.setPosicion((String) payload.get("posicion"));
        bindEquipo(jugador, payload);
        jugador.setId(null);
        return mongoTemplate.save(jugador, "jugadores");
    }

    @PutMapping("/{id}")
    public Jugador actualizar(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        Jugador existente = mongoTemplate.findById(id, Jugador.class, "jugadores");
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado");
        }
        existente.setNombre((String) payload.get("nombre"));
        existente.setDorsal(payload.get("dorsal") != null ? ((Number) payload.get("dorsal")).intValue() : null);
        existente.setPosicion((String) payload.get("posicion"));
        bindEquipo(existente, payload);
        return mongoTemplate.save(existente, "jugadores");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "jugadores");
    }

    private void bindEquipo(Jugador jugador, Map<String, Object> payload) {
        Object equipoId = payload.get("equipoId");
        if (equipoId != null) {
            Equipo equipo = mongoTemplate.findById(equipoId.toString(), Equipo.class, "equipos");
            jugador.setEquipo(equipo);
        } else {
            jugador.setEquipo(null);
        }
    }
}
