import java.util.*;
public class Args {
    private Map<Character, ArgsMarshaler> marshalers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;
    public Args(String patterns, String [] args){
        marshalers = new HashMap<Character, ArgsMarshaler>();
        argsFound = new HashSet<Character>();
        parsePatterns(patterns);
        parseArgStrings(Arrays.asList(args));
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

    private void parseArgStrings(List<String> argsList){
        for(currentArgument= argsList.listIterator(); currentArgument.hasNext();){
            String argString = currentArgument.next();
            if(argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1));
            } else {
                currentArgument.previous();
                break;
            }
        }
    }

    private void parseArgumentCharacters(String argChars){
        for (int i = 0; i < argChars.length(); i++){
            parseArgumentCharacter(argChars.charAt(i));
        }
    }
    private void parseArgumentCharacter(char argChar){
        ArgsMarshaler m = marshalers.get(argChar);
        if (m == null)
            throw new NullPointerException("Temporary exception");
        else {
            argsFound.add(argChar);
            try {
                m.set(currentArgument);
            } catch (Exception e){ // @TO DO - add proprer exception
                throw e;
            }
        }
    }

    public boolean getBoolean(char arg){
        return BooleanArgMarshaler.getValue(marshalers.get(arg));
    }
    public int getInteger(char arg){
        return IntegerArgMarshaler.getValue(marshalers.get(arg));
    }
}
