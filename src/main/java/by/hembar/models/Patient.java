package by.hembar.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Patient {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String name;

    @NotEmpty(message = "Last name should not be empty, if lastname is not known, write unknown")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String lastname;
    @Size(max = 45, message = "Surname should be maximum a 45 characters")
    private String surname;
    @NotEmpty(message = "Please, write a patient condition")
    @Size(min = 5, max = 45, message = "Condition should be maximum a 45 characters")
    private String condition;
    @NotEmpty(message = "Citizenship should not be empty, if citizenship is not known, write unknown")
    @Size(min = 2, max = 45, message = "Citizenship should be maximum a 45 characters")
    private String citizenship;
    @NotEmpty
    private int numOfCard;
    private List<Checkup> checkupList;
    public Patient(int id, String name, String lastname, String surname, String condition, String citizenship, int numOfCard) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.condition = condition;
        this.citizenship = citizenship;
        this.numOfCard = numOfCard;
    }

    public Patient(String name, String lastname, String surname, String condition, String citizenship, int numOfCard) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.condition = condition;
        this.citizenship = citizenship;
        this.numOfCard = numOfCard;
    }

    public Patient() {
    }
    public void addToCheckupList(Checkup checkup){
        checkupList.add(checkup);
    }
}
