package by.hembar.models;

import by.hembar.models.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.security.PublicKey;

@Data
public class Person {

    public static final String patient ="patient";
    public static final String doctor ="doctor";

    @Size(min = 2, max = 45, message = "Firstname should be between 2 and 45 characters")
    private String firstname;

    @NotEmpty(message = "Last name should not be empty, if lastname is not known, write unknown")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String lastname;
    private String surname;
    @NotEmpty(message = "Enter a your job: patient or doctor")
    @Size(min=6, max=7, message = "Enter a your job: patient or doctor")
    private String role;

    public Person(String firstname, String lastname, String surname, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.role = role;
    }

    public Person() {
    }
    public boolean isPatient(){
        return role.equals(patient);
    }
    public boolean isDoctor(){
        return role.equals(doctor);
    }
    public Role getPersonRole(){
        Role personeRole;
        switch (role){
            case doctor -> personeRole=Role.DOCTOR;
            case patient -> personeRole=Role.PATIENT;
            default -> personeRole=Role.UNDEFINED;
        }
        return personeRole;
    }


}
