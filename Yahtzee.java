import java.util.Map;

/**
 * Write a description of class Yahtzee here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Yahtzee
{
    Die6 die1 = new Die6();
    Die6 die2 = new Die6();
    Die6 die3 = new Die6();
    Die6 die4 = new Die6();
    Die6 die5 = new Die6();
    private int[] scoreUpper = new int[6];
    private int upperTotal;
    private int[] scoreOfAKind = new int[3];
    private int fullHouse = 0;
    private int NUM_DIE = 6;
    private int[] straight = new int[2];

    //constructor & rolls all dice for round one
    public Yahtzee() {
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
    }

    //rolls all dice
    public void rollAll() {
        Die6[] dice = new Die6[]{die1, die2, die3, die4, die5};

        for (Die6 die : dice) {
            die.roll();
        }
    }

    //Input an array, if value matches switch cases, rolls that die
    public void roll(int[] diceToRoll) {
        for (int i : diceToRoll) {
            switch (i) {
                case 1:
                    die1.roll();
                    break;
                case 2:
                    die2.roll();
                    break;
                case 3:
                    die3.roll();
                    break;
                case 4:
                    die4.roll();
                    break;
                case 5:
                    die5.roll();
                    break;
            }
        }
    }

    //For Yahtzee Testing
    public void setdice(int set) {
        Die6[] dice = new Die6[]{die1, die2, die3, die4, die5};

        for (Die6 die : dice) {
            die.value = set;
        }
    }

    //Returns the number of occurances of a die number
    public String summerize() {
        int[] two = new int[]{ val (1), val (2), val (3), val (4), val (5), val (6) };

        return("1-"+two[0]+"; 2-"+two[1]+"; 3-"+two[2]+"; 4-"+two[3]+"; 5-"+two[4]+"; 6-"+two[5]+";");        

    }

    //returns the value of all dice
    public String toString() {
        return("Dice Values: " + die1.value + " " + die2.value + " " + die3.value + " " + die4.value + " " + die5.value); 
    }

    //calculates the occurances of a die number
    private int val(int val) {
        int count = 0;
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};

        for(int i : dice) {
            if(i == val) {
                count++;
            }
        }
        return count;
    }

    /** stores scores for upper section into scoreUpper[]
     * checks for already initalized indexes of scoreUpper
     * checks for ArrayIndexOutOfBoundsException for 1 < score < 6
     */
    public int scoreUpper(int score) {
        int scoreNum = 0;
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        try{
            if(score > 6 || score <= 0) {
                throw new ArrayIndexOutOfBoundsException("Please enter 1-6");
            }
            if (scoreUpper[score-1] != 0) {
                return scoreUpper[score-1];
            }
            for(int i: dice) {
                if(i == score) {
                    scoreNum += score;
                }
            }
            if(score-1 >= 0 && score-1 < scoreUpper.length){
                scoreUpper[score-1] = scoreNum;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return scoreNum;
    }

    /** get all upper section scores for scoreUpper[]
     * intilize all leftover scoreUpper[], that hasn't been initalized in scoreUpper(int score) (consider removing)
     * adds bonus 35 for an Upper score over 63
     * stores total into upperTotal
     */
    public String getScoreUpper() {
        for (int i = 1; i <= NUM_DIE; i++) {
            scoreUpper(i);
        }
        int UpperTotal = scoreUpper[0] + scoreUpper[1]+ scoreUpper[2]+
            scoreUpper[3]+ scoreUpper[4]+ scoreUpper[5];

        this.upperTotal = UpperTotal;
        if(upperTotal > 63) {
            upperTotal += 35;
        }
        return (" Score 1: " + scoreUpper[0]+", Score 2: " + scoreUpper[1]+", Score 3: " + scoreUpper[2]+
            ", Score 4: " + scoreUpper[3]+", Score 5: " + scoreUpper[4]+", Score 6: " + scoreUpper[5]+
            ", UpperTotal:" + upperTotal);
    }

    /** calculates 3,4, & 5 (yahtzee) of a kind
     * Stores scores into scoreOfAKind[]
     * checks for already initalized scoreOfAKind indexes
     * gives bonus yahtzee score ONCE
     * checks for ArrayIndexOutOfBoundsException for 3 < type < 5
     */
    public int scoreOfAKind(int type) {
        int[] counts = new int[6];
        int score = 0;
        boolean yahtzeeBonus = false;
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        try{
            if(type > 5 || type < 3) {
                throw new ArrayIndexOutOfBoundsException("Please enter 3-5");
            }

            if (scoreOfAKind[type-3] != 0) {
                if(type == 5) {
                    if(!yahtzeeBonus) {
                        yahtzeeBonus = true;
                        score += 100;
                    }
                }
                return scoreOfAKind[type-3];
            }

            for (int i : dice) {
                counts[i - 1]++;
            }

            for (int i : counts) {
                if (i == type) {
                    for (int j : dice) {
                        score += j;
                    }
                    scoreOfAKind[type-3] = score;
                    return scoreOfAKind[type-3];
                }
            }
        } catch (ArrayIndexOutOfBoundsException g) {
            System.out.println("Error: " + g.getMessage());
        }
        return 0;
    }

    public int fullHouse() {
        int[] counts = new int[6];
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        for (int i : dice) {
            counts[i - 1]++;
        }
        for(int j: counts) {
            for(int a: counts) {
                if(j == 3 && a == 2) {
                    fullHouse = 25;
                    return fullHouse;
                }
            }
        }
        return fullHouse;
    }

    public int straight(int set) {
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        int count = 0;
        try{
            if(set > 5 || set < 4) {
                throw new ArrayIndexOutOfBoundsException("Please enter 4-5");
            }
            for(int i: dice) {
                for(int j: dice) {
                    if( i+1 == j) {
                        count++;
                    }
                }
            }
            if(count >= set-1) {
                if(set == 4) {
                    straight[0] = 30;
                    return straight[0];
                } else {
                    straight[1] = 40;
                    return straight[1];
                }
            }
        } catch (ArrayIndexOutOfBoundsException k) {
            System.out.println("Error: " + k.getMessage());
        }
        return 0;
    }
}

