package by.hembar.controllers;

import by.hembar.beans.UserRoles;
import by.hembar.dao.CheckupDAO;
import by.hembar.dao.PatientDAO;
import by.hembar.dao.TreatmentDAO;
import by.hembar.models.Patient;
import by.hembar.models.Person;
import by.hembar.models.Treatment;
import by.hembar.models.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientsController {
    @Autowired
    CheckupDAO checkupDAO;
    @Autowired
    TreatmentDAO treatmentDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    UserRoles userRoles;

    @GetMapping("/{id}")
    public String getPatientPage(HttpServletRequest request,
                                 @PathVariable("id") int id,
                                 Model model) {

        Patient patient = patientDAO.read(id);
        patient.setCheckupList(checkupDAO.findAllByPatientID(patient.getId()));
        Treatment currentTreatment = treatmentDAO.getLastTreatment(patient.getId());
        model.addAttribute("patient", patient);
        model.addAttribute("currentTreatment", currentTreatment);
        Role role = userRoles.getRole(request.getRemoteAddr());
        model.addAttribute("role", role);
        return "patients/personal-page";
    }

    @GetMapping
    public String getAllPatients(Model model,
                                 HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("patients", patientDAO.readAll());
            return "patients/all-patients";
        }
        return "errors/no-acccess";
    }

    @PostMapping
    public String create(@ModelAttribute("patient") @Valid Patient patient,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/new-patient";
        }
        patientDAO.create(patient);

        return "redirect:patients/all-patients";
    }

    @GetMapping("/new")
    public String newPatient(Model model,
                             HttpServletRequest request) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("patient", new Patient());
            return "patients/new-patient";
        } else return "errors/no-acccess";
    }

    @GetMapping("{id}/edit")
    public String editPatient(HttpServletRequest request,
                              @PathVariable("id") int id,
                              Model model) {
        if (userRoles.isDoctor(request.getRemoteAddr())) {
            model.addAttribute("patient", patientDAO.read(id));
            return "patients/edit-patient";
        } else return "errors/no-acccess";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("patient") @Valid Patient patient,
                         Model model) {
        Patient checkingForNullPatient = patientDAO
                .findByName(patient.getName(), patient.getLastname(), patient.getSurname());
        if (checkingForNullPatient != null) {
            model.addAttribute("message", "Patient with this name already exists");
            return "/patients/edit-patient";
        }
        patientDAO.update(id, patient);
        return "redirect:patients/all-patients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        patientDAO.delete(id);
        return "patients/all-patients";
    }
}