import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Yahtzee Game
 *
 * @Nghi Phan
 * @1.0.0
 */
public class Yahtzee
{
    Scanner scanner = new Scanner(System.in);
    Die6 die1 = new Die6();
    Die6 die2 = new Die6();
    Die6 die3 = new Die6();
    Die6 die4 = new Die6();
    Die6 die5 = new Die6();
    private int[] scoreUpper = new int[6];
    private int upperTotal;
    private int[] scoreOfAKind = new int[3];
    private int fullHouse;
    private int NUM_DIE = 6;
    private int[] straight = new int[2];
    private int lowerTotal;
    private int grandTotal;
    private int Chance = 0;
    private String input;
    private static final int NUM_DICE = 5;
    private static final int NUM_ROUNDS = 13;
    private int round;
    private int score;
    //constructor & rolls all dice for turn one
    public Yahtzee() {
        round = 1;
        score = 0;
        rollAll();
    }

    public static void main(String[] args) {
        Yahtzee yahtzee = new Yahtzee();
        yahtzee.play();
    }

    public void play() {
        int turn = 0;
        int scoreRound = 0;
        boolean rolled = false;
        boolean scoreMarked = false;
        while(round <= NUM_ROUNDS) {
            System.out.print("\n[ "+toString()+" ]");
            System.out.println("\n{ - 0 - Quit | 4 - Next Turn }");
            System.out.println("{ -- roll - Rolls Specified Dice | rollAll - Rolls All Dice }");
            System.out.println("{ --- 1 - Mark Uppersection | 2 - Mark Lowersection | 3 - Getscores }\n");
            input = scanner.nextLine();

            switch(input) {
                case "rollAll":
                    if(!rolled) {
                        rollAll();
                        turn++;
                        System.out.println("Turn:"+turn);
                    } else {
                        System.out.println("(-Out of Turns)");
                    }
                    break;

                case "roll":
                    if(!rolled) {
                        System.out.println("{0 - back to the previous menu}");
                        System.out.println("Enter The Die You Wish To Roll Separated By a Space");
                        String stringToSplit = scanner.nextLine();

                        if(stringToSplit.equals("0")){
                            System.out.println("Going back to the previous menu");
                            break;
                        }

                        String[] split = stringToSplit.split(" ");
                        int[] array = new int[split.length];
                        for(int i = 0; i < array.length; i++) {
                            array[i] = Integer.parseInt(split[i]);
                        }
                        roll(array);
                        turn++;
                        System.out.println("Turn:"+turn);
                    } else {
                        System.out.println("(-Out of Turns)");
                    }
                    break;

                    //Done    
                case "1":
                    if (scoreMarked) {
                        System.out.println("(-Score already marked in this round.)");
                    } else {
                        System.out.println("{0 - back to the previous menu}");
                        System.out.println("Select a category (1-6)");

                        int categoryUpper = scanner.nextInt();

                        if(categoryUpper == 0) {
                            System.out.println("Going back to the previous menu");
                            break;
                        }

                        scanner.nextLine();
                        System.out.println(getScoreUpper(categoryUpper));
                        scoreMarked = true;
                        scoreRound += scoreUpper(categoryUpper);
                    }
                    break;

                case "2": 
                    if (scoreMarked) {
                        System.out.println("(-Score already marked in this round.)");
                    } else {
                        System.out.println("{0 - back to the previous menu}");
                        System.out.println("1 - Score Of A Kind");
                        System.out.println("2 - Score FullHouse");
                        System.out.println("3 - Score Straights");
                        System.out.println("4 - Score Chance");
                        int cateogoryLower = scanner.nextInt();
                        scanner.nextLine();
                        switch (cateogoryLower) {
                            case 0:
                                System.out.println("Going back to the previous menu");
                                break;
                            case 1:
                                System.out.println("{0 - back to the previous menu}");
                                System.out.println("Please enter 3-5");

                                int markScoreKind = scanner.nextInt();
                                scanner.nextLine();

                                switch(markScoreKind) {
                                    case 0:
                                        System.out.println("Going back to the previous menu");
                                        break;
                                    case 3:
                                        System.out.println(getScoreOfAKind(3));
                                        scoreRound += scoreOfAKind(3);
                                        break;
                                    case 4:
                                        System.out.println(getScoreOfAKind(4));
                                        scoreRound += scoreOfAKind(4);
                                        break;
                                    case 5:
                                        System.out.println(getScoreOfAKind(5));
                                        scoreRound += scoreOfAKind(5);
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Full House: "+fullHouse());
                                scoreRound += fullHouse();
                                break;
                            case 3:
                                System.out.println("{0 - back to the previous menu}");
                                System.out.print("Please enter 4 or 5: ");

                                int markScoreStraight = scanner.nextInt();
                                scanner.nextLine();

                                switch(markScoreStraight) {
                                    case 0:
                                        System.out.println("Going back to the previous menu");
                                        break;
                                    case 4:
                                        System.out.println(getStraight(4));
                                        scoreRound += straight(4);
                                        break;
                                    case 5:
                                        System.out.println(getStraight(5));
                                        scoreRound += straight(5);
                                        break;
                                }
                                break;
                            case 4:
                                System.out.println(getChance());
                                scoreRound += Chance();
                                break;
                        }

                        scoreMarked = true;
                    }
                    break;
                    //Done    
                case "3":
                    System.out.println("{0 - back to the previous menu}");
                    System.out.println("1 - Upper Scores");
                    System.out.println("2 - Lower Scores");
                    System.out.println("3 - Totals");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    switch(type) {
                        case 0:
                            System.out.println("Going back to the previous menu");
                            break;
                        case 1:
                            System.out.println(getScoreUpper(1));
                            System.out.println(getScoreUpper(2));
                            System.out.println(getScoreUpper(3));
                            System.out.println(getScoreUpper(4));
                            System.out.println(getScoreUpper(5));
                            System.out.println(getScoreUpper(6));
                            break;
                        case 2:
                            System.out.println(getScoreOfAKind(3));
                            System.out.println(getScoreOfAKind(4));
                            System.out.println(getScoreOfAKind(5));
                            System.out.println(getFullHouse());
                            System.out.println(getStraight(4));
                            System.out.println(getStraight(5));
                            System.out.println(getChance());
                            break;
                        case 3:
                            System.out.println(getUpperTotal());
                            System.out.println(getLowerTotal());
                            System.out.println(getGrandTotal());
                            break;
                    }
                    break;
                    // Done    
                case "4":
                    if(!rolled) {
                        System.out.println("You are required to roll");
                    } else {
                        round++;
                        System.out.println("Round: "+round);
                        scoreMarked = false;
                        rolled = false;
                        turn  = 0;
                        scoreRound = 0;
                    }
                    break;
            }

            if(scoreRound == 0) {
                scoreMarked = false;
            }

            if(turn >= 3) {
                rolled = true;
            }

            if(input.equals("0")){
                break;
            }
        }
    }

    //rolls all dice
    public void rollAll() {
        Die6[] dice = new Die6[]{die1, die2, die3, die4, die5};
        //iterates over object array and calls roll method
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

    /** Returns dice values
    *
    *@return a string of dice values
    */
    public String toString() {
        return("Dice Values: " + die1.value + " " + die2.value + " " + die3.value + " " + die4.value + " " + die5.value); 
    }

    /** Calculates scores for the upper section
     * ONly valid inputs are 1-6
     * 
     *@param score specifies upper section score to calculate
     *@return upper score at parameter
     *@return zero if conditions are not met
     */
    public int scoreUpper(int score) {
        int scoreNum = 0;
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        try{
            if(score > 6 || score <= 0) {
                throw new ArrayIndexOutOfBoundsException("Please enter 1-6");
            }
            //if there's a stored value in the array stops the calculations
            if (scoreUpper[score-1] != 0) {
                return scoreUpper[score-1];
            }
            //iterates over dice, calculates Uppersection and stores values in int scoreNum, and then stores it in the instances array scoreUpper[]
            for(int i: dice) {
                if(i == score) {
                    scoreNum += score;
                    scoreUpper[score-1] = scoreNum;
                }
            }
            //catches ArrayIndexOutOfBoundsException for 1 > score > 6
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return scoreNum;
    }

    /**Calculate scores for "of A kind" categories in Yahtzee
     *Only inputs 3, 4, and 5 are valid
     * 
     *@param type specifies score to calculate
     *@return the score for specified parameter
     *@return zero if conditions are not met
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
            //checks for already initalized scoreOfAKind[] indexes
            if (scoreOfAKind[type-3] != 0) {
                //checks in cases of a second Yahtzee
                if(type == 5) {
                    if(!yahtzeeBonus) {
                        yahtzeeBonus = true;
                        scoreOfAKind[type-3] += 100;
                        return scoreOfAKind[type-3];
                    }
                }
                //returns already stored value
                return scoreOfAKind[type-3];
            }
            //counts occurances of a die number and stores it in counts[]
            for (int i : dice) {
                counts[i - 1]++;
            }
            //iterates over counts[]
            for (int i : counts) {
                //if counts matches _ofAKind adds up all dice values and stores it into scores
                if(i == 5 && type == 5) {
                    scoreOfAKind[type-3] = 50;
                    return scoreOfAKind[type-3];
                } else if (i >= type) {
                    for (int j : dice) {
                        score += j;
                    }
                    //interalizes scoreOfAKind[] with values from score, and then returns the values
                    scoreOfAKind[type-3] = score;
                    return scoreOfAKind[type-3];
                }
            }
            //catches ArrayIndexOutOfBoundsException for 3 > type > 5
        } catch (ArrayIndexOutOfBoundsException g) {
            System.out.println("Error: " + g.getMessage());
        }
        //if there's no 3+ matching die numbers returns 0
        return 0;
    }

    /** Calculates the score for full house
     * 
     *@return score of full house
     *@return zero if conditions are not met
     */
    public int fullHouse() {
        int[] counts = new int[6];
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        //counts occurances of a die number and stores it in counts[]
        for (int i : dice) {
            counts[i - 1]++;
        }
        //interates over counts[] twice
        for(int j: counts) {
            for(int a: counts) {
                //initalizes fullHouse if conditions are met
                if(j == 3 && a == 2) {
                    fullHouse = 25;
                    return fullHouse;
                }
            }
        }
        //if conditions are not met return 0
        return 0;
    }

    /** Calculates for scores for both large and small straight
     * Only allows input of 4 and 5 
     *
     *@param set specifies either large or small straight to score
     *@return score for specified parameter
     *@return zero if conditions are not met
     */
    public int straight(int set) {
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        Arrays.sort(dice);
        int notConsecCount = 0;
        try{
            if(set > 5 || set < 4) {
                throw new ArrayIndexOutOfBoundsException("Please enter 4-5");
            }

            //if there's a stored value in the array stops the calculations
            if (straight[set-4] != 0) {
                return straight[set-4];
            }

            //caculates nonconsective sequences
            for(int a: dice) {
                if(a+1 != a) {
                    notConsecCount++;
                }
            }

            //checks number of sequences
            if(notConsecCount < 1) {
                straight[0] = 30;
                return straight[0];
            } else if(notConsecCount < 2) {
                straight[1] = 40;
                return straight[1];
            }
            //catches ArrayIndexOutOfBoundsException for 4 > set > 5
        } catch (ArrayIndexOutOfBoundsException k) {
            System.out.println("Error: " + k.getMessage());
        }
        //if conditions are not met returns 0
        return 0;
    }

    /**
     * Calculates the score for Chance
     * 
     * @return the score for Chance
     */
    public int Chance() {
        int[] dice = new int[]{die1.value,die2.value,die3.value,die4.value,die5.value};
        //checks if Chance is already initalized
        if(Chance != 0) {
            return Chance;
        }
        for(int i: dice) {
            Chance += i;
        }
        return Chance;
    }

    /**
     * Returns for the score Chance
     * 
     * @return the string for Chance
     */
    public String getChance() {
        return("Chance: " + Chance() );
    }

    /**
     * Returns the scores in scoreOfAKind
     * 
     * @param "get" an integer that specifies the call for scoreOfAKind
     * @return the string for scoreOfAKind
     */
    public String getScoreOfAKind(int get) {
        if(get == 5) {
            return ("Yahtzee: " + scoreOfAKind[2]);
        }
        return(get +" of a kind: " + scoreOfAKind[get-3]);
    }

    /**
     * Returns the score for Full House
     * 
     * @return the string for the score Full House
     */
    public String getFullHouse() {
        return("FullHouse: " + fullHouse);
    }

    /**
     * Returns the scores for straights
     * 
     * @param "get" an integer that specifies the straight score
     * @return a string for straights scores
     */
    public String getStraight(int get) {
        if(get == 4){
            return("Small Straight: " + straight[0]);
        } else {
            return("Large Straight: " + straight[1]);
        }
    }

    /**
     * Returns the scores from the Upper Section
     * 
     * @param "get" an integer that specifies the Upper score
     * @return a string for Upper Section score
     */
    public String getScoreUpper(int get) {
        return ("Score " + get + ": " + scoreUpper[get-1]);
    }

    /**
     * Returns the total scores in the Upper Section
     * Adds a bonus 35 score for a total upper score of over 63
     * 
     * @return a string for the total score of the Upper Section
     */
    public String getUpperTotal() {
        int UpperTotal = scoreUpper[0] + scoreUpper[1]+ scoreUpper[2]+
            scoreUpper[3]+ scoreUpper[4]+ scoreUpper[5];
        this.upperTotal = UpperTotal;
        if(upperTotal > 63) {
            upperTotal += 35;
        }

        return ("Upper Total: "+upperTotal);
    }

    /**
     * Returns the total scores in the Lower Section
     * 
     * @return a string for the total score of the Lower Section
     */
    public String getLowerTotal(){
        int LowerTotal = scoreOfAKind[0] + scoreOfAKind[1] + scoreOfAKind[2] + fullHouse + straight[0] + straight[1] + Chance;
        this.lowerTotal = LowerTotal;
        return ("Lower Total: " +lowerTotal);
    }

    /**
     * Returns the Grand Total
     * 
     * @return a string for the Grand Total
     */
    public String getGrandTotal() {
        grandTotal = upperTotal + lowerTotal;
        return ("Grand Total: " +grandTotal);
    }

}

