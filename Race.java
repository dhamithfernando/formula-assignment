package src;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Race implements Serializable {
    private Date date;
    private Map<Formula1Driver, Integer> driversPlaces;

    public Race(Date date, Map<Formula1Driver, Integer> driversPlaces) {
        this.date = date;
        this.driversPlaces = driversPlaces;
    }

    public Race() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Formula1Driver, Integer> getDriversPlaces() {
        return driversPlaces;
    }

    public void setDriversPlaces(Map<Formula1Driver, Integer> driversPlaces) {
        this.driversPlaces = driversPlaces;
    }
}
