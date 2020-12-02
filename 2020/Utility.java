public enum Utility {
    ;

    private static final String INPUT_PREFIX = "/Users/jlua/Documents/ForFun/2020/";
    private static final String INPUT_SUFFIX = "/input.txt";

    public static String getInputFilename(int aDay)
    {
        return INPUT_PREFIX + String.valueOf(aDay) + INPUT_SUFFIX;
    }
}
