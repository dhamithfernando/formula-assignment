package src;

import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {

    private int firstPositions;
    private int secondPositions;
    private int thirdPositions;
    private int points;
    private int noParticipation;

    public Formula1Driver(int driverId, String driverName, String driverLocation, String driverTeam, int firstPositions, int secondPositions, int thirdPositions, int points, int noParticipation) {
        super(driverId,driverName, driverLocation, driverTeam);
        this.firstPositions = firstPositions;
        this.secondPositions = secondPositions;
        this.thirdPositions = thirdPositions;
        this.points = points;
        this.noParticipation = noParticipation;
    }

    public int getFirstPositions() {
        return firstPositions;
    }

    public void setFirstPositions(int firstPositions) {
        this.firstPositions = firstPositions;
    }

    public int getSecondPositions() {
        return secondPositions;
    }

    public void setSecondPositions(int secondPositions) {
        this.secondPositions = secondPositions;
    }

    public int getThirdPositions() {
        return thirdPositions;
    }

    public void setThirdPositions(int thirdPositions) {
        this.thirdPositions = thirdPositions;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNoParticipation() {
        return noParticipation;
    }

    public void setNoParticipation(int noParticipation) {
        this.noParticipation = noParticipation;
    }

    @Override
    public String toString() {
        return "Formula1Driver{" +
                "driverName='" + super.getDriverName() +
                ", driverLocation='" + super.getDriverLocation() +
                ", driverTeam='" + super.getDriverTeam() +
                ", firstPositions=" + firstPositions +
                ", secondPositions=" + secondPositions +
                ", thirdPositions=" + thirdPositions +
                ", points=" + points +
                ", noParticipation=" + noParticipation +
                '}';
    }
}
