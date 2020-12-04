import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main4
{
    public static void main(String[] anArguments)
    {
        List<String> aList = parseInput(Utility.getInputFilename(4));
        print(getValidPassports(aList));
    }

    private static int getValidPassports(List<String> aList)
    {
        PassportValidity myPassportValidity = new PassportValidity();
        int aNumValid = 0;
        for (String aLine : aList)
        {
            if (aLine.isEmpty())
            {
                if (myPassportValidity.isValid())
                {
                    ++aNumValid;
                }
                myPassportValidity = new PassportValidity();
                continue;
            }
            String[] mySplitOutput = aLine.split(" ");
            for (String myData : mySplitOutput)
            {
                String[] myPair = myData.split(":");
                String myValue = myPair[1];
                switch (myPair[0])
                {
                    case "byr":
                        myPassportValidity.setTheBirthYear(isValidBirthYear(myValue));
                        break;
                    case "iyr":
                        myPassportValidity.setTheIssueYear(isValidIssueYear(myValue));
                        break;
                    case "eyr":
                        myPassportValidity.setTheExpirationYear(isValidExpirationYear(myValue));
                        break;
                    case "hgt":
                        myPassportValidity.setTheHeight(isValidHeight(myValue));
                        break;
                    case "hcl":
                        myPassportValidity.setTheHairColor(isValidHairColor(myValue));
                        break;
                    case "ecl":
                        myPassportValidity.setTheEyeColor(isValidEyeColor(myValue));
                        break;
                    case "pid":
                        myPassportValidity.setThePassportId(isValidPassportId(myValue));
                        break;
                    default:
                        break;
                }
            }
        }
        return aNumValid;
    }

    private static boolean isValidBirthYear(String aBirthYear)
    {
        return isValidWithinRange(1920, 2002, aBirthYear) && aBirthYear.length() == 4;
    }

    private static boolean isValidIssueYear(String aIssueYear)
    {
        return isValidWithinRange(2010, 2020, aIssueYear) && aIssueYear.length() == 4;
    }

    private static boolean isValidExpirationYear(String aExpirationYear)
    {
        return isValidWithinRange(2020, 2030, aExpirationYear) && aExpirationYear.length() == 4;
    }

    private static boolean isValidWithinRange(int aMin, int aMax, String aPotentialValue)
    {
        try
        {
            int myParsedValue = Integer.parseInt(aPotentialValue);
            return myParsedValue >= aMin && myParsedValue <= aMax;
        }
        catch (Exception aUnused)
        {
            return false;
        }
    }

    private static boolean isValidHeight(String aHeight)
    {
        Pattern myPattern = Pattern.compile("(\\d+)(in|cm)");
        Matcher myMatcher = myPattern.matcher(aHeight);
        if (myMatcher.find())
        {
            String myNum = myMatcher.group(1);
            if (myMatcher.group(2).equals("in"))
            {
                return isValidWithinRange(59, 76, myNum);
            }
            else
            {
                // cm
                return isValidWithinRange(150, 193, myNum);
            }
        }
        return false;
    }

    private static boolean isValidHairColor(String aHairColor)
    {
        Pattern myPattern = Pattern.compile("#([a-f0-9]{6})");
        Matcher myMatcher = myPattern.matcher(aHairColor);
        return myMatcher.find();
    }

    public static final Set<String> VALID_COLORS = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
    private static boolean isValidEyeColor(String aEyeColor)
    {
        return VALID_COLORS.contains(aEyeColor);
    }

    private static boolean isValidPassportId(String aPassportId)
    {
        if (aPassportId.length() != 9)
        {
            return false;
        }

        try
        {
            int myId = Integer.parseInt(aPassportId);
            return true;
        }
        catch (Exception aUnused)
        {
            return false;
        }
    }

    private static class PassportValidity
    {
        private boolean theBirthYearValid;
        private boolean theIssueYearValid;
        private boolean theExpirationYearValid;
        private boolean theHeightValid;
        private boolean theHairColorValid;
        private boolean theEyeColorValid;
        private boolean thePassportIdValid;

        public void setTheBirthYear(boolean theBirthYear)
        {
            theBirthYearValid = theBirthYear;
        }

        public void setTheIssueYear(boolean theIssueYear)
        {
            theIssueYearValid = theIssueYear;
        }

        public void setTheExpirationYear(boolean theExpirationYear)
        {
            theExpirationYearValid = theExpirationYear;
        }

        public void setTheHeight(boolean theHeight)
        {
            theHeightValid = theHeight;
        }

        public void setTheHairColor(boolean theHairColor)
        {
            theHairColorValid = theHairColor;
        }

        public void setTheEyeColor(boolean theEyeColor)
        {
            theEyeColorValid = theEyeColor;
        }

        public void setThePassportId(boolean thePassportId)
        {
            thePassportIdValid = thePassportId;
        }

        public boolean isValid()
        {
            return theBirthYearValid && theExpirationYearValid && theEyeColorValid && theHairColorValid
                    && theHeightValid && theIssueYearValid && thePassportIdValid;
        }
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