
/**
 * Write a description of class LowerScore here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class lowerScore
{
  private int[] scoreUpper = new int[6];
  private int upperTotal;

  public static void main (String[]args)
  {
    Main man = new Main ();
    for (int i = 1; i < 7; i++)
      {
	System.out.println (man.scoreUpper(i));
      }
    System.out.println(man.getScoreUpper());
    System.out.println(man.upperTotal);
  }

  public int scoreUpper (int score)
  {
    int scoreNum = 0;
    int[] dice = new int[]{ 2, 2, 4, 4, 5 };

    try
    {
      if (score > 6 || score <= 0)
	{
	  throw new IllegalArgumentException ("Please enter 1-6");
	}
    for (int i:dice)
	{
	  if (i == score)
	    {
	      scoreNum += score;
	    }
	}
    }
    catch (IllegalArgumentException a)
    {
      System.out.println (a.getMessage ());
    }
    if (scoreNum % score == 0)
      {
	scoreUpper[score - 1] = scoreNum;
      }
    return scoreUpper[score - 1];
  }

  public String getScoreUpper ()
  {
    int UpperTotal = scoreUpper[0] + scoreUpper[1]+ scoreUpper[2]+ scoreUpper[3]+ scoreUpper[4]+ scoreUpper[5];
   this.upperTotal = UpperTotal;
    return String.format(" Score 1: " + scoreUpper[0] + "\n Score 2: " + scoreUpper[1] +
      "\n Score 3: " + scoreUpper[2] + "\n Score 4: " + scoreUpper[3] +
      "\n Score 5: " + scoreUpper[4] + "\n Score 6: " + scoreUpper[5] + "\n UpperTotal:" + upperTotal);
  }

}
