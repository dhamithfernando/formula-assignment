package src;

import java.io.Serializable;

abstract public class Driver implements Serializable {
    private int driverId;
    private String driverName;
    private String driverLocation;
    private String driverTeam;

    public Driver(int driverId, String driverName, String driverLocation, String driverTeam) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.driverTeam = driverTeam;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverName='" + driverName + '\'' +
                ", driverLocation='" + driverLocation + '\'' +
                ", driverTeam='" + driverTeam + '\'' +
                '}';
    }
}
