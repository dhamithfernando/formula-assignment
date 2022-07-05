package src;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static java.lang.Math.round;
import static src.ChampionshipData.allFormula1Drivers;
import static src.ChampionshipData.allRaces;
import static src.Formula1ChampionshipManager.validateId;

public class GUI {

    public GUI() {

        // Main jFrame
        JFrame jFrame = new JFrame();

        // label for the heading
        JLabel jLabel = new JLabel("The Formula1 Championship", SwingConstants.CENTER);
        jLabel.setFont(new Font("Arial", Font.BOLD, 20));
        jLabel.setBounds(100, 20, 600, 22);

        // jpanel for the Search Fields
        JPanel jPanelSearch = new JPanel();
        jPanelSearch.setBounds(250, 50, 300, 50);
        jPanelSearch.setLayout(null);

        JTextField searchText = new JTextField();
        searchText.setBounds(0, 0, 150, 30);

        JButton search = new JButton("Search");
        search.setBorder(new RoundedBorder(10));
        search.setBounds(170, 0, 100, 30);

        JLabel errorLabel = new JLabel();
        errorLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        errorLabel.setBounds(30, 32, 100, 20);

        jPanelSearch.add(errorLabel);
        jPanelSearch.add(searchText);
        jPanelSearch.add(search);

        // jpanel for the Buttons
        JPanel jPanelButtons = new JPanel();
        jPanelButtons.setBounds(50, 100, 800, 30);
        jPanelButtons.setLayout(null);

        JButton sortByPoints = new JButton("Sort By Points");
        sortByPoints.setBorder(new RoundedBorder(10));
        sortByPoints.setBounds(0, 0, 120, 30);

        JButton sortByWins = new JButton("Sort By Wins");
        sortByWins.setBorder(new RoundedBorder(10));
        sortByWins.setBounds(150, 0, 120, 30);

        JButton displayRace = new JButton("Display Races");
        displayRace.setBorder(new RoundedBorder(10));
        displayRace.setBounds(300, 0, 120, 30);

        JButton generate = new JButton("Generate Race");
        generate.setBorder(new RoundedBorder(10));
        generate.setBounds(450, 0, 120, 30);

        JButton reset = new JButton("Reset");
        reset.setBorder(new RoundedBorder(10));
        reset.setBounds(600, 0, 120, 30);

        jPanelButtons.add(sortByPoints);
        jPanelButtons.add(sortByWins);
        jPanelButtons.add(displayRace);
        jPanelButtons.add(generate);
        jPanelButtons.add(reset);

        // Table for the Configurations
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setLayout(new ScrollPaneLayout());
        jScrollPane.setBounds(15, 160, 850, 400);

        // Table Data
        String[] colData = {"Id", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "No. of Points", "No. of Races"};
        String[] headersForRace = new String[]{"Date", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"};
        DefaultTableModel model = new DefaultTableModel(colData, 0);

        JTable jTable = new JTable(model);
        jTable.setRowHeight(25);
        jTable.setCellSelectionEnabled(true);
        jTable.setFont(new Font("Arial", Font.ITALIC, 14));
        jTable.setAutoCreateRowSorter(true); // Enable in-build sorter

        JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        jScrollPane.setViewportView(jTable);

        // search button ActionListeners
        search.addActionListener(e -> {
            if (validateId(Integer.parseInt(searchText.getText()))) {
                if (!Objects.equals(searchText.getText(), "")) {
                    List<Race> filteredList;
                    filteredList = getAllRacesByDriverId(searchText.getText());
                    String[][] sortByPointData = populateRaceData(filteredList);
                    model.setDataVector(sortByPointData, headersForRace);
                } else {
                    errorLabel.setText("Invalid Driver Id");
                }
            } else {
                errorLabel.setText("Invalid Driver Id");
            }
        });

        //     sort the data by total number of points and display it
        sortByPoints.addActionListener(e -> {
            model.setDataVector(populateTableBySortedPoints(), colData);
        });

        //     sort the data by total number of wins and display it
        sortByWins.addActionListener(e -> {
            model.setDataVector(populateTableBySortedWins(), colData);
        });

        //     the whole races placed in the season
        displayRace.addActionListener(e -> {
            String[][] sortByPointData = populateRaceData(allRaces);
            model.setDataVector(sortByPointData, headersForRace);
        });

        generate.addActionListener(e -> {
            try {
                generateRandomRace(); // random race generation
                String[][] sortByPointData = populateRaceData(allRaces);
                model.setDataVector(sortByPointData, headersForRace);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

//        reset button action
        reset.addActionListener(e -> {
            populateTable(model,colData);
        });

        // setting all subComponents to the Parent Components
        jFrame.add(jLabel);
        jFrame.add(jPanelSearch);
        jFrame.add(jPanelButtons);
        jFrame.add(jScrollPane);

        // setting uop main jframe options
        jFrame.setTitle("Formula1 Championship - GUI");
        jFrame.setAlwaysOnTop(true);
        jFrame.setSize(880, 600);
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        populateTable(model,colData);
        jFrame.setVisible(true);
    }

    private void populateTable(DefaultTableModel model, String[] colData) {
        String[][] newTableData = new String[allFormula1Drivers.size()][9];
        for (int i = 0; i < allFormula1Drivers.size(); i++) {
            Formula1Driver allFormula1Driver = allFormula1Drivers.get(i);
            String[] rawData = {
                    String.valueOf(allFormula1Driver.getDriverId()),
                    allFormula1Driver.getDriverName(),
                    allFormula1Driver.getDriverLocation(),
                    allFormula1Driver.getDriverTeam(),
                    String.valueOf(allFormula1Driver.getFirstPositions()),
                    String.valueOf(allFormula1Driver.getSecondPositions()),
                    String.valueOf(allFormula1Driver.getThirdPositions()),
                    String.valueOf(allFormula1Driver.getPoints()),
                    String.valueOf(allFormula1Driver.getNoParticipation())
            };
            newTableData[i] = rawData;
        }
        model.setDataVector(newTableData, colData);
    }

    private String[][] populateTableBySortedPoints() {
        ArrayList<Formula1Driver> list = new ArrayList<>(allFormula1Drivers);
        list.sort((o1, o2) -> o1.getPoints() - o2.getPoints());
        String[][] newTableData = new String[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            Formula1Driver allFormula1Driver = list.get(i);
            String[] rawData = {
                    String.valueOf(allFormula1Driver.getDriverId()),
                    allFormula1Driver.getDriverName(),
                    allFormula1Driver.getDriverLocation(),
                    allFormula1Driver.getDriverTeam(),
                    String.valueOf(allFormula1Driver.getFirstPositions()),
                    String.valueOf(allFormula1Driver.getSecondPositions()),
                    String.valueOf(allFormula1Driver.getThirdPositions()),
                    String.valueOf(allFormula1Driver.getPoints()),
                    String.valueOf(allFormula1Driver.getNoParticipation())
            };
            newTableData[i] = rawData;
        }
        return newTableData;
    }

    private String[][] populateTableBySortedWins() {
        ArrayList<Formula1Driver> list = new ArrayList<>(allFormula1Drivers);
        list.sort((o1, o2) -> o2.getFirstPositions() - o1.getFirstPositions());
        String[][] newTableData = new String[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            Formula1Driver allFormula1Driver = list.get(i);
            String[] rawData = {
                    String.valueOf(allFormula1Driver.getDriverId()),
                    allFormula1Driver.getDriverName(),
                    allFormula1Driver.getDriverLocation(),
                    allFormula1Driver.getDriverTeam(),
                    String.valueOf(allFormula1Driver.getFirstPositions()),
                    String.valueOf(allFormula1Driver.getSecondPositions()),
                    String.valueOf(allFormula1Driver.getThirdPositions()),
                    String.valueOf(allFormula1Driver.getPoints()),
                    String.valueOf(allFormula1Driver.getNoParticipation())
            };
            newTableData[i] = rawData;
        }
        return newTableData;
    }

    public void generateRandomRace() throws ParseException {
        Map<Integer, Integer> driverIdPlaceMap = new HashMap<>();
        int numberOfParticipants = allFormula1Drivers.size();

        // Random Date
        boolean isValidGeneratedDate = true;
        String generatedDateFromRandomValues;
        do {
            generatedDateFromRandomValues = new StringBuilder().append(1 + (int) round(Math.random() * (31 - 1))).append("/").append(1).append((int) round(Math.random() * (12 - 1))).append("/").append(2020).append((int) round(Math.random() * (2021 - 2020))).toString();
            if (Formula1ChampionshipManager.validateDate(generatedDateFromRandomValues)) isValidGeneratedDate = false;
        } while (isValidGeneratedDate);

        // Places for Drivers is being generated
        List<Integer> occupiedPlaceByDrivers = new ArrayList<>();

        List<Integer> allDriverIds = new ArrayList<>();
        for (Formula1Driver allFormula1Driver : allFormula1Drivers) {
            allDriverIds.add(allFormula1Driver.getDriverId());
        }

        List<Integer> randomIds = new ArrayList<>();
        int randomIdMaxLimit = Math.min(allFormula1Drivers.size(), 10);
        for (int i = 0; i < randomIdMaxLimit; i++) {
            boolean isIdTaken = true;
            int id;
            do {
                id = allDriverIds.get((int) round(Math.random() * ((allDriverIds.size() - 1))));
                if (!randomIds.contains(id)) {
                    isIdTaken = false;
                }
            } while (isIdTaken);
            randomIds.add(id);
        }

        // Find the first place
        int firstPlaceDriverId = getRaceChampion(randomIds);
        driverIdPlaceMap.put(firstPlaceDriverId, 1);
        occupiedPlaceByDrivers.add(1);

        for (int driverId : randomIds) {
            if (driverId != firstPlaceDriverId) {
                int place;
                boolean rankAlreadyUsed = true;
                do {
                    place = 1 + (int) round(Math.random() * (numberOfParticipants - 1));
                    if (!occupiedPlaceByDrivers.contains(place)) {
                        rankAlreadyUsed = false;
                        occupiedPlaceByDrivers.add(place);
                    }
                } while (rankAlreadyUsed);

                driverIdPlaceMap.put(driverId, place);
            }
        }
        Map<Formula1Driver, Integer> formula1DriverIntegerMap = new HashMap<>();


        for (Map.Entry<Integer, Integer> entry : driverIdPlaceMap.entrySet()) {
            Integer driver = entry.getKey();
            Integer place = entry.getValue();
            List<Formula1Driver> list = new ArrayList<>();
            for (Formula1Driver item : allFormula1Drivers) {
                if (item.getDriverId() == driver) {
                    list.add(item);
                }
            }
            Formula1Driver tempDriver = list.stream().findFirst().orElse(null);
            allFormula1Drivers.set(allFormula1Drivers.indexOf(tempDriver), updateDriverStatsByRace(tempDriver, place));

            formula1DriverIntegerMap.put(tempDriver, place);
        }
        // Add New Race to the List
        allRaces.add(new Race(new SimpleDateFormat("dd/MM/yyyy").parse(generatedDateFromRandomValues), formula1DriverIntegerMap));

        // Update Both Databases in .txt
        Formula1ChampionshipManager.saveData();
    }

    private Formula1Driver updateDriverStatsByRace(Formula1Driver driver, Integer value) {
        int[] placePoints = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        if (value == 1) {
            driver.setFirstPositions(driver.getFirstPositions() + 1);
            driver.setPoints(driver.getPoints() + placePoints[value - 1]);
        } else if (value == 2) {
            driver.setSecondPositions(driver.getSecondPositions() + 1);
            driver.setPoints(driver.getPoints() + placePoints[value - 1]);
        } else if (value == 3) {
            driver.setThirdPositions(driver.getThirdPositions() + 1);
            driver.setPoints(driver.getPoints() + placePoints[value - 1]);
        } else {
            driver.setPoints(driver.getPoints() + placePoints[value - 1]);
        }
        driver.setNoParticipation(driver.getNoParticipation() + 1);
        return driver;
    }

    public int getRaceChampion(List<Integer> randomIds) {
        int[] probability = {40, 30, 10, 10, 2, 2, 2, 2, 2};
        List<Integer> ids = randomIds;

        int maxLimit = 0;
        for (int i = 0; i < ids.size(); i++) {
            maxLimit += probability[i];
        }


        List<Integer> probabilisticArray = new ArrayList<>(maxLimit);
        for (int i = 0; i < ids.size(); i++) {
            Collections.addAll(probabilisticArray, Collections.nCopies(probability[i], ids.get(i)).toArray(new Integer[probability[i]]));
        }
        return probabilisticArray.get((int) round(Math.random() * ((maxLimit - 1))));
    }

    public List<Race> getAllRacesByDriverId(String id) {
        List<Race> tempRaceList = new ArrayList<>();
        // Filter Race By Driver ID
        for (Race race : allRaces) {
            race.getDriversPlaces().forEach((key, value) -> {
                if (key.getDriverId() == Integer.parseInt(id)) {
                    tempRaceList.add(race);
                }
            });
        }
        return tempRaceList;
    }

    // valid only for the Race List
    private String[][] populateRaceData(java.util.List<Race> source) {
        source.sort(new Comparator<Race>() {
            @Override
            public int compare(Race o1, Race o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        String[][] formattedArray = new String[source.size()][11];
        for (int i = 0; i < source.size(); i++) {
            Race race = source.get(i);
            String[] temp = new String[11];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            temp[0] = simpleDateFormat.format(race.getDate());
            race.getDriversPlaces().forEach((key, value) -> {
                temp[value] = key.getDriverName();
            });
            formattedArray[i] = temp;
        }
        return formattedArray;
    }

    private static class RoundedBorder implements Border {

        private int borderRadius;

        RoundedBorder(int borderRadius) {
            this.borderRadius = borderRadius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.borderRadius + 1, this.borderRadius + 1, this.borderRadius + 2, this.borderRadius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, borderRadius, borderRadius);
        }
    }
}
