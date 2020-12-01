import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] anArguments)
    {
        List<Integer> aList = parseInput("/Users/jlua/Documents/ForFun/2020/input.txt");

        print(get2020(aList));
    }

    public static int get2020(List<Integer> aList)
    {
        for (int i = 0; i < aList.size(); ++i)
        {
            int myVal = aList.get(i);
            for (int j = i + 1; j < aList.size(); ++j)
            {
                int mySecondVal = aList.get(j);
                for (int k = j + 1; k < aList.size(); ++k)
                {
                    int myThirdVal = aList.get(k);
                    if (myVal + mySecondVal + myThirdVal == 2020)
                    {
                        return myVal * mySecondVal * myThirdVal;
                    }
                }
            }
        }
        return -1;
    }

    public static List<Integer> parseInput(String aFilename)
    {
        try (Stream<String> stream = Files.lines(Paths.get(aFilename))) {
            return stream.map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        }
        catch (Exception ignored)
        {
            print("File could not be opened");
        }
        return Collections.emptyList();
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
