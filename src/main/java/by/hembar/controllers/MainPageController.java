package by.hembar.controllers;

import by.hembar.beans.UserRoles;
import by.hembar.dao.DoctorDAO;
import by.hembar.dao.PatientDAO;
import by.hembar.models.Doctor;
import by.hembar.models.Patient;
import by.hembar.models.Person;
import by.hembar.models.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
public class MainPageController {
    @Autowired
    private PatientDAO patientDAO;
    @Autowired
    private DoctorDAO doctorDAO;
    @Autowired
    private UserRoles userRoles;
    @GetMapping
    public String mainPage() {
        return "index";
    }

    @PostMapping
    public String redirectGuest(@ModelAttribute("person") @Valid Person person,
                                BindingResult bindingResult,
                                HttpServletRequest request) {
        if(bindingResult.hasErrors())
            return "redirect:/";
        if(person.isPatient()){
            Patient patient = findPatient(person);
            userRoles.put(request.getRemoteAddr(), Role.PATIENT);
            return "/patients/"+patient.getId();
        }else if(person.isDoctor()){
            Doctor doctor = findDoctor(person);
            userRoles.put(request.getRemoteAddr(), Role.DOCTOR);
            return "/doctors"+doctor.getId();
        }
        return "/errors/index-error";
    }
    private Patient findPatient(Person person){
        return patientDAO.findByName(person.getFirstname(),
                person.getLastname(),
                person.getSurname()
                );
    }
    private Doctor findDoctor(Person person){
        return doctorDAO.findByName(person.getFirstname(),
                person.getLastname(),
                person.getSurname()
        );
    }
    private String getAddresUser(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        return ip==null?null:ip.getHostAddress();
    }
}
