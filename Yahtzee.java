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

    //constructor & round one
    public Yahtzee() {
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
    }

    public void rollAll() {
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
    }

    //rolls a specified by number
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

    public void setdice(int set) {
        Die6[] dice = new Die6[]{die1, die2, die3, die4, die5};
        for (Die6 die : dice) {
            die.value = set;
        }
    }

    //returns the amount of occurances of a die number
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

    public String getScoreUpper() {
        for (int i = 1; i < 7; i++) {
            scoreUpper(i);
        }
        int UpperTotal = scoreUpper[0] + scoreUpper[1]+ scoreUpper[2]+ scoreUpper[3]+ scoreUpper[4]+ scoreUpper[5];
        this.upperTotal = UpperTotal;
        if(upperTotal > 63) {
            upperTotal += 35;
        }
        return (" Score 1: " + scoreUpper[0]+", Score 2: " + scoreUpper[1]+", Score 3: " + scoreUpper[2]+
            ", Score 4: " + scoreUpper[3]+", Score 5: " + scoreUpper[4]+", Score 6: " + scoreUpper[5]+
            ", UpperTotal:" + upperTotal);
    }

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
                if (i >= type) {
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
}

