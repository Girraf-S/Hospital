package by.hembar.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Card {
    private int id;
    @NotEmpty(message = "Passport number should not be empty")
    @Size(min = 14, max = 14, message = "Passport number should have only 14 characters")
    private String passportNumber;

    @NotEmpty(message = "Registration place should not be empty, if registration place is not known, write unknown")
    @Size(min = 2, max = 256, message = "Registration place should be between 2 and 256 characters")
    private String registrationPlace;

    @NotEmpty(message = "Birth date should not be empty, if it is not known, write 0.0.0")
    private String birthDate;

    public Card() {
    }

    public Card(String passportNumber, String registrationPlace, String birthDate) {
        this.passportNumber = passportNumber;
        this.registrationPlace = registrationPlace;
        this.birthDate = birthDate;
    }

    public Card(int id, String passportNumber, String registrationPlace, String birthDate) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.registrationPlace = registrationPlace;
        this.birthDate = birthDate;
    }
}
