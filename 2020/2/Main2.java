import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2
{
    public static void main(String[] anArguments)
    {
        List<String> aList = parseInput(Utility.getInputFilename(2));
        int myResult = 0;
        for (String myLine : aList)
        {
            String myRegex = "(\\d+)-(\\d+) ([a-z]): ([a-z]+)";
            Pattern myPattern = Pattern.compile(myRegex);
            Matcher myMatcher = myPattern.matcher(myLine);
            if (myMatcher.find())
            {
                int myFirst = Integer.parseInt(myMatcher.group(1)) - 1;
                int mySecond = Integer.parseInt(myMatcher.group(2)) - 1;
                String myChar = myMatcher.group(3);
                String myPassword = myMatcher.group(4);
                if (isValid(myFirst, mySecond, myChar, myPassword))
                {
                    ++myResult;
                }
            }
        }
        print(myResult);
    }

    private static boolean isValid(int myFirst, int mySecond, String myChar, String myPassword)
    {
        return myPassword.charAt(myFirst) == myChar.charAt(0) && myPassword.charAt(mySecond) != myChar.charAt(0)
                || myPassword.charAt(mySecond) == myChar.charAt(0) && myPassword.charAt(myFirst) != myChar.charAt(0);
    }

    public static List<String> parseInput(String aFilename)
    {
        List<String> myResult = new ArrayList<>();
        try (Scanner myScanner = new Scanner(new File(aFilename)))
        {
            myScanner.useDelimiter("");
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
