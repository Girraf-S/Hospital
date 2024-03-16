package by.hembar.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class Treatment {
    private int id;
    @NotEmpty(message = "Medicines should not be empty")
    @Size(min = 2, max = 270, message = "Medicines should be between 2 and 270 characters")
    private String medicines;
    @NotEmpty(message = "Procedures should not be empty")
    @Size(min = 2, max = 270, message = "Procedures should be between 2 and 270 characters")
    private String procedures;
    @NotEmpty(message = "Supplies should not be empty")
    @Size(min = 2, max = 270, message = "Supplies should be between 2 and 270 characters")
    private String supplies;
    private int numOfHistory;

    public Treatment(String medicines, String procedures, String supplies, int numOfHistory) {
        this.medicines = medicines;
        this.procedures = procedures;
        this.supplies = supplies;
        this.numOfHistory = numOfHistory;
    }

    public Treatment(int id, String medicines, String procedures, String supplies, int numOfHistory) {
        this.id = id;
        this.medicines = medicines;
        this.procedures = procedures;
        this.supplies = supplies;
        this.numOfHistory = numOfHistory;
    }
}
