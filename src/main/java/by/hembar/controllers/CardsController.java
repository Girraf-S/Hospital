package by.hembar.controllers;

import by.hembar.beans.UserRoles;
import by.hembar.dao.CardDAO;
import by.hembar.dao.DiseaseHistoryDAO;
import by.hembar.models.Card;
import by.hembar.models.DiseaseHistory;
import by.hembar.models.Person;
import by.hembar.models.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    CardDAO cardDAO;
    @Autowired
    DiseaseHistoryDAO diseaseHistoryDAO;
    @Autowired
    UserRoles userRoles;

    @GetMapping
    public String getAllCards(Model model,
                              HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("cards", cardDAO.readAll());
            return "cards/all-cards";
        }
        return "errors/no-access";
    }

    @PostMapping
    public String create(@ModelAttribute("card") @Valid Card card,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cards/new-card";
        }
        cardDAO.create(card);
        return "redirect:cards/all-cards";
    }

    @GetMapping("/new")
    public String newCard(Model model,
                          HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("card", new Card());
            return "cards/new-card";
        } else return "errors/no-access";
    }

    @GetMapping("{id}/edit")
    public String editCard(HttpServletRequest request,
                           @PathVariable("id") int id,
                           Model model) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("card", cardDAO.read(id));
            return "cards/edit-card";
        } else return "errors/no-access";
    }

    //diseaseHistories it's a list of dis. hist.,
    // patient and doctor can only look, but doctor have a button
    //'treatments' near a all histories, and doctor can look treatments
    @GetMapping("/{id}")
    public String getCard(@PathVariable("id") int id,
                          HttpServletRequest request,
                          Model model) {
        Card card = cardDAO.read(id);
        List<DiseaseHistory> diseaseHistories = diseaseHistoryDAO.readAllHistByCardID(id);
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("role", Role.DOCTOR);
        } else if (userRoles.isPatient(request.getRemoteAddr())) {
            model.addAttribute("role", Role.PATIENT);
        } else {
            model.addAttribute("role", Role.UNDEFINED);
        }
        model.addAttribute("card", card);
        model.addAttribute("diseaseHistories", diseaseHistories);
        return "cards/card-page";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("card") @Valid Card card) {
        cardDAO.update(id, card);
        return "/cards/all-cards";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cardDAO.delete(id);
        return "cards/all-cards";
    }
}
