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

    int[] scoreUpper = new int[6];
    private int upperTotal;
    
    //constructor & round one
    public Yahtzee() {
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
    }

    //rolls all five dice
    public void roll(){
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
    }

    //rolls a specified by number
    public void roll(int dieNumber) {
        if(dieNumber == 1) {
            die1.roll();
        } else if (dieNumber == 2) {
            die2.roll();
        } else if (dieNumber == 3) {
            die3.roll();
        } else if (dieNumber == 4) {
            die4.roll();
        } else if(dieNumber == 5) {
            die5.roll();
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
                throw new IllegalArgumentException("Please enter 1-6");
            }
            for(int i: dice) {
                if(i == score) {
                    scoreNum += score;
                }
            }
        } catch (IllegalArgumentException a) {
            System.out.println(a.getMessage());
        }
        if(scoreNum %score == 0) {
            try{
                scoreUpper[score-1] = scoreNum;
            } catch (ArrayIndexOutOfBoundsException b) {
            }
        }
        return scoreUpper[score-1];
    }
    
    public String getScoreUpper() {
        int UpperTotal = scoreUpper[0] + scoreUpper[1]+ scoreUpper[2]+ scoreUpper[3]+ scoreUpper[4]+ scoreUpper[5];
        this.upperTotal = UpperTotal;
        return String.format(" Score 1: " + scoreUpper[0]+"\n Score 2: " + scoreUpper[1]+"\n Score 3: " + scoreUpper[2]+
                             "\n Score 4: " + scoreUpper[3]+"\n Score 5: " + scoreUpper[4]+"\n Score 6: " + scoreUpper[5]+
                             "\n UpperTotal:" + upperTotal);
    }
}

