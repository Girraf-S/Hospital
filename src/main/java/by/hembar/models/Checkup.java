package by.hembar.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Data
public class Checkup {
    private int id;
    @Getter
    private boolean routine;
    @NotEmpty//если пусто тор записывать текущее время
    private String date;
    @NotEmpty//если пусто тор записывать текущее время
    private String time;
    private int numOfDoctor;
    private int numOfPatient;

    public Checkup(int id, boolean routine, String date, String time, int numOfDoctor, int numOfPatient) {
        this.id = id;
        this.routine = routine;
        this.date = date;
        this.time = time;
        this.numOfDoctor = numOfDoctor;
        this.numOfPatient = numOfPatient;
    }

    public Checkup(boolean routine, String date, String time, int numOfDoctor, int numOfPatient) {
        this.routine = routine;
        this.date = date;
        this.time = time;
        this.numOfDoctor = numOfDoctor;
        this.numOfPatient = numOfPatient;
    }

}
