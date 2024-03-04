package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.app.model.FormInfo;

@Controller
@Scope(value = "session")
@SessionAttributes("formInfo")
public class MasterMindController {

    @Autowired
    public MasterMindService masterMindService;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("formInfo", new FormInfo());
        return "indexView";
    }

    @GetMapping ("/juego")
    public String showGame(@ModelAttribute("formInfo") FormInfo formInfo, Model model) {
        model.addAttribute("listaIntentos", masterMindService.masterMind.getListaIntentos());
        model.addAttribute("estadoJuego", masterMindService.masterMind.getEstadoJuego());
        model.addAttribute("numeroVecesIntentadas", (masterMindService.masterMind.getNumeroVecesIntentadas()));
        return "juegoView";
    }

    @PostMapping("/juego")
    public String newTry(@ModelAttribute("formInfo") FormInfo formInfo, Model model) {

        masterMindService.comprobarIntento(formInfo.getIntento(), formInfo);
        return "redirect:/juego";

    }

    @PostMapping ("/nuevoJuego")
    public String newGame(@ModelAttribute("formInfo") FormInfo formInfo) {
        masterMindService.nuevoJuego(formInfo);
        return "redirect:/juego";
    }
}
