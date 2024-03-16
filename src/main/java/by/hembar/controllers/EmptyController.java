package by.hembar.controllers;

import by.hembar.beans.UserRoles;
import by.hembar.dao.DoctorDAO;
import by.hembar.models.Card;
import by.hembar.models.Person;
import by.hembar.models.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public class EmptyController {
/*    @Autowired
    DoctorDAO DAO;
    @Autowired
    UserRoles userRoles;

    @GetMapping
    public String getAllCards(Model model,
                              HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("cards",DAO.readAll());
            return "/all-";
        }
        return "/no-access";
    }

    @PostMapping
    public String create(@ModelAttribute("") @Valid  ,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new-";
        }
        return "redirect:/all-";
    }

    @GetMapping("/new")
    public String new(Model model,
                          HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("", new ());
            return "/new-";
        } else return "errors/no-access";
    }

    @GetMapping("{id}/edit")
    public String edit(HttpServletRequest request,
                           @PathVariable("id") int id,
                           Model model) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("card", DAO.read(id));
            return "cards/edit-card";
        } else return "errors/no-access";
    }

    @GetMapping("/{id}")
    public String getCard(@PathVariable("id") int id,
                          Model model) {
          = DAO.read(id);
        model.addAttribute("", );
        return "/-page";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("") @Valid  ) {
        DAO.update(id, );
        return "//all-";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        DAO.delete(id);
        return "/all-";
    }*/
}
