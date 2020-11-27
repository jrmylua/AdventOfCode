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
        List<Integer> myList = parseInput("/Users/jlua/Documents/ForFun/src/2019-1.txt");
        int myResult = myList.stream().map(Main::getFuel).reduce(0, Integer::sum);

        print(String.valueOf(myResult));
    }

    public static int getFuel(int aMass)
    {
        int aFuel = aMass / 3 - 2;
        return aFuel < 0 ? 0 : aFuel + getFuel(aFuel);
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

    public static void print(String aMessage)
    {
        System.out.println(aMessage);
    }
}
