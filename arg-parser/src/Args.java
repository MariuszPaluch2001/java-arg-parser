import java.util.*;
public class Args {
    private Map<Character, ArgsMarshaler> marshalers;
    public Args(String patterns, String [] args){
        marshalers = new HashMap<Character, ArgsMarshaler>();
        parsePatterns(patterns);
    }
    private void parsePatterns(String patterns){
        for (String pattern : patterns.split(" ")) {
            parsePatternsElement(pattern);
        }
    }
    private void parsePatternsElement(String element){
        char patternID = element.charAt(0);
        String patternType = element.substring(1);
        if (patternType.length() == 0)
            marshalers.put(patternID, new BooleanArgMarshaler());
        else if (patternType.equals("*"))
            marshalers.put(patternID, new StringArgMarshaler());
        else if (patternType.equals("#"))
            marshalers.put(patternID, new IntegerArgMarshaler());
        else if (patternType.equals("##"))
            marshalers.put(patternID, new DobuleArgMarshaler());
        else if (patternType.equals("[*]"))
            marshalers.put(patternID, new StringArrayMarshaler());
    }

    public boolean getBoolean(char arg){
        return BooleanArgMarshaler.getValue(marshalers.get(arg));
    }
}
