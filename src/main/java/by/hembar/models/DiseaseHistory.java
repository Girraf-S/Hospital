package by.hembar.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiseaseHistory {
    private int id;
    @NotEmpty(message = "Diagnosis should not be a empty")
    @Size(min = 2, max = 135, message = "Diagnosis should be between 2 and 135 characters")
    private String diagnosis;
    @NotEmpty(message = "")
    @Size(min=2, max = 270, message = "should be between 2 and 256 characters")
    private String conclusion;

    @NotEmpty(message = "Treatment period should not be a empty")
    @Size(min = 25, max=25, message = "Treatment period should have only 14 characters")
    private String treatmentPeriod;
    private int numOfCard;
    private int numOfDoctor;
    private List<Treatment> treatmentList = new ArrayList<>();
    public DiseaseHistory(String diagnosis, String conclusion, String treatmentPeriod, int numOfCard, int numOfDoctor) {
        this.diagnosis = diagnosis;
        this.conclusion = conclusion;
        this.treatmentPeriod = treatmentPeriod;
        this.numOfCard = numOfCard;
        this.numOfDoctor = numOfDoctor;
    }

    public DiseaseHistory(int id, String diagnosis, String conclusion, String treatmentPeriod, int numOfCard, int numOfDoctor) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.conclusion = conclusion;
        this.treatmentPeriod = treatmentPeriod;
        this.numOfCard = numOfCard;
        this.numOfDoctor = numOfDoctor;
    }
}
