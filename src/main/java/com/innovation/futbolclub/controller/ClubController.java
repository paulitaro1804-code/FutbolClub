package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Club;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/clubes")
public class ClubController {
    private final MongoTemplate mongoTemplate;

    public ClubController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping
    public List<Club> listar() {
        return mongoTemplate.find(new Query(), Club.class, "clubs");
    }

    @GetMapping("/{id}")
    public Club obtener(@PathVariable String id) {
        Club club = mongoTemplate.findById(id, Club.class, "clubs");
        if (club == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Club no encontrado");
        }
        return club;
    }

    @PostMapping
    public Club crear(@RequestBody Club club) {
        club.setId(null);
        return mongoTemplate.save(club, "clubs");
    }

    @PutMapping("/{id}")
    public Club actualizar(@PathVariable String id, @RequestBody Club payload) {
        Club existente = mongoTemplate.findById(id, Club.class, "clubs");
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Club no encontrado");
        }
        existente.setNombre(payload.getNombre());
        existente.setCiudad(payload.getCiudad());
        existente.setFundacion(payload.getFundacion());
        return mongoTemplate.save(existente, "clubs");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "clubs");
    }
}
