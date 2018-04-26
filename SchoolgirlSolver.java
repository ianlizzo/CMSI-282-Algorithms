import java.util.Arrays;

public class SchoolgirlSolver {
    private static final int NUMBER_OF_GIRLS = 15;
    private static final int NUMBER_OF_DAYS = 7;
    private static final int INVALID_GIRL_ID = -1;
    private static final SchoolGirl INVALID_GIRL = new SchoolGirl();

    public static class SchoolGirl {
        int id;
        int adjacencyArray[];

        public SchoolGirl() {
            this.id = INVALID_GIRL_ID;
        }

        public SchoolGirl(int g) {
            this.id = g;
            adjacencyArray = new int[NUMBER_OF_GIRLS];
            Arrays.fill(adjacencyArray, INVALID_GIRL_ID);
            this.adjacencyArray[0] = this.id;
        }

        public int getId() {
            return this.id;
        }

        public int[] getAdjacencyArray() {
            return this.adjacencyArray;
        }

        public boolean isAdjacentTo(int g) {
            for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
                if (this.adjacencyArray[i] == g) {
                    return true;
                }
            }
            return false;
        }

        public int nextGirlNotAdjacentTo() {
            for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
                if (!this.isAdjacentTo(i)) {
                    return this.adjacencyArray[i];
                }
            }
            return -1;
        }

        public void addAdjacency(int g) {
            if (!this.isAdjacentTo(g)) {
                for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
                    if (this.adjacencyArray[i] == -1) {
                        this.adjacencyArray[i] = g;
                        break;
                    }
                }
            }
        }

        public void removeAdjacency(int g) {
            for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
                if (this.adjacencyArray[i] == g) {
                    this.adjacencyArray[i] = INVALID_GIRL_ID;
                    break;
                }
            }
        }

        public String getCharForGirl() {
            return this.id > 0 && this.id < 27 ? String.valueOf((char) (this.id + 'A' - 1)) : null;
        }

        public boolean equals(SchoolGirl g) {
            return this.id == g.id;
        }
    }

    public static class Week {
        SchoolGirl[][] lines;
        // int currentGirlNum;
        // int currentDay;

        public Week() { //NOTE just initialize as empty Week, and then fill in MON in kirkmanSolver()
            // v1
            /*
            this.currentGirlNum = 0;
            this.currentDay = 1;
            this.lines = new SchoolGirl[7][15];
            for (int r = 0; r < lines.length; r++) { // populates lines
                for (int c = 0; c < lines[0].length; c++) {
                    if (r == 0) {
                        this.lines[r][c] = new SchoolGirl(c + 1);
                    } else if (c <= 6 && c % 3 == 0) { //NOTE maybe delete? probably
                        this.lines[r][c] = new SchoolGirl((c / 3) + 1);
                    } else {
                        this.lines[r][c] = new SchoolGirl();
                    }
                }
            }
            */

            //v2 NOTE: initialize with default day in kirkmanSolver()
            // this.currentGirlNum = 0;
            // this.currentDay = 0;
            this.lines = new SchoolGirl[7][15];
            for (int r = 0; r < NUMBER_OF_DAYS; r++) {
                for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                    this.lines[r][c] = new SchoolGirl();
                }
            }
        }

        public void addSchoolGirl(SchoolGirl g, int day) {
            for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                if (this.lines[day][c].equals(INVALID_GIRL)) {
                    if (c % 3 == 1) {
                        this.lines[day][c - 1].addAdjacency(g.getId());
                        g.addAdjacency(this.lines[day][c - 1].getId());
                    }
                    if (c % 3 == 2) {
                        this.lines[day][c - 1].addAdjacency(g.getId());
                        g.addAdjacency(this.lines[day][c - 1].getId());
                        this.lines[day][c - 2].addAdjacency(g.getId());
                        g.addAdjacency(this.lines[day][c - 2].getId());
                    }
                    this.lines[day][c] = g;
                    // this.currentGirlNum++;
                    break;
                }
            }
        }

        public void removeSchoolGirl(SchoolGirl g, int day) {
            for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                if (this.lines[day][c].equals(g)) {
                    if (c % 3 == 1) {
                        this.lines[day][c - 1].removeAdjacency(g.getId());
                        g.removeAdjacency(this.lines[day][c - 1].getId());
                    }
                    if (c % 3 == 2) {
                        this.lines[day][c - 1].removeAdjacency(g.getId());
                        g.removeAdjacency(this.lines[day][c - 1].getId());
                        this.lines[day][c - 2].removeAdjacency(g.getId());
                        g.removeAdjacency(this.lines[day][c - 2].getId());
                    }
                    this.lines[day][c] = INVALID_GIRL;
                    // this.currentGirlNum--;
                    break;
                }
            }
        }

        public boolean contains(SchoolGirl g, int day) {
            for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                if (this.lines[day][c].equals(g)) {
                    return true;
                }
            }
            return false;
        }

        public int indexOf(SchoolGirl g, int day) {
            for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                if (this.lines[day][c].equals(g)) {
                    return c;
                }
            }
            return -1;
        }

        public int getNextSpot(SchoolGirl g, int day, int index) { //TODO
            SchoolGirl girlOne;
            SchoolGirl girlTwo;
            SchoolGirl girlThree;
            int startIndex = this.contains(g, day) ? (index / 3) * 3 : 0;
            for (int c = startIndex; c < NUMBER_OF_GIRLS; c += 3) {
                girlOne = this.lines[day][c].equals(INVALID_GIRL) ? INVALID_GIRL : this.lines[day][c];
                girlTwo = this.lines[day][c].equals(INVALID_GIRL) ? INVALID_GIRL : this.lines[day][c + 1];
                girlThree = this.lines[day][c].equals(INVALID_GIRL) ? INVALID_GIRL : this.lines[day][c + 2];
                if (girlOne.equals(INVALID_GIRL)) {
                    return c;
                } else if (girlTwo.equals(INVALID_GIRL) && !girlTwo.isAdjacentTo(girlOne.getId())) {
                    return c + 1;
                } else if (girlThree.equals(INVALID_GIRL)
                            && !girlThree.isAdjacentTo(girlOne.getId())
                            && !girlThree.isAdjacentTo(girlOne.getId())) {
                    return c + 2;
                }
            }
            return -1;
        }

        /*
        public void goToNextSpot(SchoolGirl g, int day, int nextSpot) {
            // int nextSpot = getNextSpot(g, day, indexOf(g, day));
            if (nextSpot != -1) {
                this.removeSchoolGirl(g, day);
                this.lines[day][nextSpot] = g;
                if (nextSpot % 3 == 1) {
                    g.addAdjacency(this.lines[day][nextSpot - 1].getId());
                    this.lines[day][nextSpot - 1].addAdjacency(g.getId());
                } else if (nextSpot % 3 == 2) {
                    g.addAdjacency(this.lines[day][nextSpot - 1].getId());
                    this.lines[day][nextSpot - 1].addAdjacency(g.getId());
                    g.addAdjacency(this.lines[day][nextSpot - 2].getId());
                    this.lines[day][nextSpot - 2].addAdjacency(g.getId());
                }
            }
        }
        */

        public void printWeek() {
            System.out.println("MON TUE WED THU FRI SAT SUN");
            System.out.println("--- --- --- --- --- --- ---");
            for (int r = 0; r < NUMBER_OF_DAYS; r++) {
                for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                    System.out.print(this.lines[r][c].getCharForGirl());
                    if (c % 3 == 2 && c != NUMBER_OF_GIRLS - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void SchoolgirlSolver() {
        Week week = new Week();
        int currentDay;
        SchoolGirl currentGirl;
        int nextGirlId;
        SchoolGirl nextGirl;

        for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
            week.addSchoolGirl(new SchoolGirl(c), 0);
        }

        for (int r = 1; r < NUMBER_OF_DAYS; r++) {
            for (int c = 0; c < NUMBER_OF_GIRLS; c++) {
                currentGirl = new SchoolGirl(c);
                nextGirl = currentGirl.nextGirlNotAdjacentTo();
                nextGirl = new SchoolGirl(nextGirlId);
                int nextSpot = week.getNextSpot(currentGirl, r, c);
                if (nextSpot != -1) {
                    week.addSchoolGirl(currentGirl, r);
                } else {

                }

            }
        }

        //TODO





        week.printWeek();
    }







































    //UNCOMMENT ONLY IF DEFCON 1
    /*
    public static void schoolgirlSolver() {
        System.out.println("Sun    Mon    Tue    Wed    Thu    Fri    Sat");
        System.out.println("---    ---    ---    ---    ---    ---    ---");
        System.out.println("ABC    ADG    AEJ    AFO    AHK    AIM    ALN");
        System.out.println("DEF    BEH    BFL    BDM    BGN    BKO    BIJ");
        System.out.println("GHI    CJM    CHO    CGL    CFI    CEN    CDK");
        System.out.println("JKL    FKN    DIN    EIK    DJO    DHL    EGO");
        System.out.println("MNO    ILO    GKM    HJN    ELM    FGJ    FHM");
    }
    */

    public static void main(String[] args) {
        // System.out.println(getCharForGirl(3));
        // System.out.println(getCharForGirl(6));
        // System.out.println(getCharForGirl(9));
        // System.out.println(getCharForGirl(12));
        // System.out.println(getCharForGirl(15));
    }
}
