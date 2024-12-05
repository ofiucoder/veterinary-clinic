import java.time.LocalDate;


public class Treatment {
    private int id;
    private LocalDate date;
    private TreatmentType type;
    private String note;

    public enum TreatmentType{
        MEDICATION, THERAPY, SURGERY, CHECKUP
    }
public Treatment(int id, LocalDate date, TreatmentType type, String note) {
    this.id = id;
    this.date = date;
    this.type = type;
    this.note = note;
}

public void setId(int id) {
    this.id = id;
}

public int getId() {
    return Id;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public LocalDate getDate(){
    return date;
}

public void setType(TreatmentType type) {
    this.type = type;
}

public TreatmentType getType() {
    return type;
}

public void setNote(String note) {
    this.note = note;
}

public String getNote () {
    return note;
}
}
