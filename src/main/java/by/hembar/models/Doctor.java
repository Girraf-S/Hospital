package by.hembar.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Doctor {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String name;

    @NotEmpty(message = "Last name should not be empty, if lastname is not known, write unknown")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String lastname;
    private String surname;
    @NotEmpty(message = "Passport number should not be empty")
    @Size(min = 14, max = 14, message = "Passport number should have only 14 characters")
    private String passportNumber;

    @NotEmpty(message = "Category should not be empty")
    @Size(min=5, max=45)
    private String category;

    public Doctor() {
    }

    public Doctor(String name, String lastname, String surname, String passportNumber, String category) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.passportNumber = passportNumber;
        this.category = category;
    }

    public Doctor(int id, String name, String lastname, String surname, String passportNumber, String category) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.passportNumber = passportNumber;
        this.category = category;
    }
}
