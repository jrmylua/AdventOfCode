import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main3
{
    public static void main(String[] anArguments)
    {
        List<String> aList = parseInput(Utility.getInputFilename(3));

        int myNumTrees = getNumTrees(aList, 1, 1)
                * getNumTrees(aList, 3, 1)
                * getNumTrees(aList, 5, 1)
                * getNumTrees(aList, 7, 1)
                * getNumTrees(aList, 1, 2);

        print(myNumTrees);
    }

    private static int getNumTrees(List<String> aList, int aRight, int aDown)
    {
        int myNumTrees = 0;
        int myHorizontalPosition = 0;
        for (int i = 0; i < aList.size(); i += aDown)
        {
            String myRow = aList.get(i);
            if (myRow.charAt(myHorizontalPosition) == '#')
            {
                ++myNumTrees;
            }
            myHorizontalPosition += aRight;
            myHorizontalPosition %= myRow.length();
        }
        return myNumTrees;
    }

    public static List<String> parseInput(String aFilename)
    {
        List<String> myResult = new ArrayList<>();
        try (Scanner myScanner = new Scanner(new File(aFilename)))
        {
            while (myScanner.hasNext())
            {
                myResult.add(myScanner.nextLine());
            }
        }
        catch (Exception ignored)
        {
            print("File could not be opened");
        }
        return myResult;
    }

    public static void print(int aNum)
    {
        print(String.valueOf(aNum));
    }

    public static void print(String aMessage)
    {
        System.out.println(aMessage);
    }
}