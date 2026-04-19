package com.innovation.futbolclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/clubes")
    public String clubes() {
        return "clubes/listar";
    }

    @GetMapping("/equipos")
    public String equipos() {
        return "equipos/listar";
    }

    @GetMapping("/jugadores")
    public String jugadores() {
        return "jugadores/listar";
    }

    @GetMapping("/partidos")
    public String partidos() {
        return "partidos/listar";
    }
}
