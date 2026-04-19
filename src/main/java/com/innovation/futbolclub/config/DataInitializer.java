package com.innovation.futbolclub.config;

import com.innovation.futbolclub.model.Club;
import com.innovation.futbolclub.model.Equipo;
import com.innovation.futbolclub.model.Jugador;
import com.innovation.futbolclub.model.Partido;
import com.innovation.futbolclub.repository.ClubRepository;
import com.innovation.futbolclub.repository.EquipoRepository;
import com.innovation.futbolclub.repository.JugadorRepository;
import com.innovation.futbolclub.repository.PartidoRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner init(MongoTemplate mongoTemplate) {
        return args -> {
            try {
                // Si ya hay datos, no reinicializar (preservar datos del usuario)
                long clubCount = mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "clubs");
                if (clubCount > 0) {
                    System.out.println("✓ Base de datos ya contiene datos (" + clubCount + " clubs). Omitiendo inicialización.");
                    return;
                }

                System.out.println("✓ Colecciones vacías, insertando datos iniciales...");

                // 1. Insertar Clubs
                Club club1 = new Club();
                club1.setNombre("Real Madrid");
                club1.setCiudad("Madrid");
                club1.setFundacion(1902);
                Club savedClub1 = mongoTemplate.save(club1, "clubs");

                Club club2 = new Club();
                club2.setNombre("FC Barcelona");
                club2.setCiudad("Barcelona");
                club2.setFundacion(1899);
                Club savedClub2 = mongoTemplate.save(club2, "clubs");

                Club club3 = new Club();
                club3.setNombre("Atlético Madrid");
                club3.setCiudad("Madrid");
                club3.setFundacion(1903);
                Club savedClub3 = mongoTemplate.save(club3, "clubs");
                
                System.out.println("✓ Clubs creados: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "clubs"));

                // 2. Insertar Equipos
                Equipo equipo1 = new Equipo();
                equipo1.setNombre("Real Madrid A");
                equipo1.setCategoria("Primera División");
                equipo1.setClub(savedClub1);
                Equipo savedEquipo1 = mongoTemplate.save(equipo1, "equipos");

                Equipo equipo2 = new Equipo();
                equipo2.setNombre("Barcelona B");
                equipo2.setCategoria("Segunda División");
                equipo2.setClub(savedClub2);
                Equipo savedEquipo2 = mongoTemplate.save(equipo2, "equipos");

                Equipo equipo3 = new Equipo();
                equipo3.setNombre("Atlético Madrid A");
                equipo3.setCategoria("Primera División");
                equipo3.setClub(savedClub3);
                Equipo savedEquipo3 = mongoTemplate.save(equipo3, "equipos");
                
                System.out.println("✓ Equipos creados: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "equipos"));

                // 3. Insertar Jugadores
                Jugador j1 = new Jugador();
                j1.setNombre("Cristiano Ronaldo");
                j1.setDorsal(7);
                j1.setPosicion("Delantero");
                j1.setEquipo(savedEquipo1);
                mongoTemplate.save(j1, "jugadores");

                Jugador j2 = new Jugador();
                j2.setNombre("Vinícius Júnior");
                j2.setDorsal(20);
                j2.setPosicion("Extremo");
                j2.setEquipo(savedEquipo1);
                mongoTemplate.save(j2, "jugadores");

                Jugador j3 = new Jugador();
                j3.setNombre("Lionel Messi");
                j3.setDorsal(10);
                j3.setPosicion("Delantero");
                j3.setEquipo(savedEquipo2);
                mongoTemplate.save(j3, "jugadores");

                Jugador j4 = new Jugador();
                j4.setNombre("Robert Lewandowski");
                j4.setDorsal(9);
                j4.setPosicion("Delantero");
                j4.setEquipo(savedEquipo2);
                mongoTemplate.save(j4, "jugadores");

                Jugador j5 = new Jugador();
                j5.setNombre("Luis Suárez");
                j5.setDorsal(9);
                j5.setPosicion("Delantero");
                j5.setEquipo(savedEquipo3);
                mongoTemplate.save(j5, "jugadores");

                Jugador j6 = new Jugador();
                j6.setNombre("Ángel Correa");
                j6.setDorsal(10);
                j6.setPosicion("Delantero");
                j6.setEquipo(savedEquipo3);
                mongoTemplate.save(j6, "jugadores");
                
                System.out.println("✓ Jugadores creados: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "jugadores"));

                // 4. Insertar Partidos
                Partido p1 = new Partido();
                p1.setFecha(LocalDateTime.of(2026, 4, 20, 19, 0));
                p1.setEstadio("Santiago Bernabéu");
                p1.setLocal(savedEquipo1);
                p1.setVisitante(savedEquipo2);
                p1.setGolesLocal(3);
                p1.setGolesVisitante(2);
                mongoTemplate.save(p1, "partidos");

                Partido p2 = new Partido();
                p2.setFecha(LocalDateTime.of(2026, 4, 21, 20, 0));
                p2.setEstadio("Camp Nou");
                p2.setLocal(savedEquipo2);
                p2.setVisitante(savedEquipo3);
                p2.setGolesLocal(2);
                p2.setGolesVisitante(1);
                mongoTemplate.save(p2, "partidos");

                Partido p3 = new Partido();
                p3.setFecha(LocalDateTime.of(2026, 4, 22, 18, 30));
                p3.setEstadio("Wanda Metropolitano");
                p3.setLocal(savedEquipo3);
                p3.setVisitante(savedEquipo1);
                p3.setGolesLocal(1);
                p3.setGolesVisitante(2);
                mongoTemplate.save(p3, "partidos");
                
                System.out.println("✓ Partidos creados: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "partidos"));

                System.out.println("\n========================================");
                System.out.println("✓✓✓ BASE DE DATOS POBLADA EXITOSAMENTE ✓✓✓");
                System.out.println("✓ Clubs: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "clubs"));
                System.out.println("✓ Equipos: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "equipos"));
                System.out.println("✓ Jugadores: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "jugadores"));
                System.out.println("✓ Partidos: " + mongoTemplate.count(new org.springframework.data.mongodb.core.query.Query(), "partidos"));
                System.out.println("========================================\n");
                
            } catch (Exception e) {
                System.err.println("Error al poblar la BD: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
