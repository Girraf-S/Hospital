package by.hembar.controllers;

import by.hembar.beans.UserRoles;
import by.hembar.dao.DiseaseHistoryDAO;
import by.hembar.dao.DoctorDAO;
import by.hembar.models.DiseaseHistory;
import by.hembar.models.Doctor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorsController {
    @Autowired
    DoctorDAO doctorDAO;
    @Autowired
    DiseaseHistoryDAO diseaseHistoryDAO;
    @Autowired
    UserRoles userRoles;

    @GetMapping
    public String getAllDoctors(Model model,
                                HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("doctors", doctorDAO.readAll());
            return "doctors/all-doctors";
        }
        return "errors/no-access";
    }

    @PostMapping
    public String create(@ModelAttribute("doctor") @Valid Doctor doctor,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "doctors/new-doctor";
        }
        doctorDAO.create(doctor);
        return "redirect:doctors/all-doctors";
    }

    @GetMapping("/new")
    public String newDoctor(Model model,
                          HttpServletRequest request) {

        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("", new Doctor());
            return "doctors/new-doctor";
        } else return "errors/no-access";
    }

    @GetMapping("{id}/edit")
    public String edit(HttpServletRequest request,
                       @PathVariable("id") int id,
                       Model model) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("doctor", doctorDAO.read(id));
            return "doctors/edit-doctor";
        } else return "errors/no-access";
    }

    @GetMapping("/{id}")
    public String get(HttpServletRequest request,
                      @PathVariable("id") int id,
                      Model model) {
        if(userRoles.isDoctor(request.getRemoteAddr())) {
            Doctor doctor = doctorDAO.read(id);
            List<DiseaseHistory> histories = diseaseHistoryDAO.readAllHistByDoctorID(id);
            model.addAttribute("doctor", doctor);
            model.addAttribute("histories", histories);
            return "doctors/doctor-page";
        }else return "errors/no-access";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("doctor") @Valid Doctor doctor) {
        doctorDAO.update(id, doctor);
        return "/doctors/all-doctors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        doctorDAO.delete(id);
        return "doctors/all-doctors";
    }
}
