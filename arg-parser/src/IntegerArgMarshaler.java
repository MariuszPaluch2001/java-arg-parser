import java.util.Iterator;

public class IntegerArgMarshaler implements ArgsMarshaler{
    private int intValue = 0;
    private String stringValue = null;
    @Override
    public void set(Iterator<String> currArgument) {
        try{
            stringValue = currArgument.next();
            intValue = Integer.parseInt(stringValue);
        } catch (NumberFormatException e){
            System.out.println(e);
            throw e;
        }
    }

    public static int getValue(ArgsMarshaler am){
        if (am instanceof IntegerArgMarshaler)
            return ((IntegerArgMarshaler) am).intValue;
        else
            return 0;
    }
}
