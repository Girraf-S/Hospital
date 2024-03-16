package by.hembar.models;

import lombok.Data;

@Data
public class CheckupResult {
    private int id;
    private int num_checkup;
    private int num_treatment;

    public CheckupResult(int id, int num_checkup, int num_treatment) {
        this.id = id;
        this.num_checkup = num_checkup;
        this.num_treatment = num_treatment;
    }

    public CheckupResult(int num_checkup, int num_treatment) {
        this.num_checkup = num_checkup;
        this.num_treatment = num_treatment;
    }

    public CheckupResult() {
    }
}
