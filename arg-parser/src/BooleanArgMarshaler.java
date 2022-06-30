import java.util.Iterator;

public class BooleanArgMarshaler implements  ArgsMarshaler{
    private boolean booleanValue = false;
    @Override
    public void set(Iterator<String> currArgument) {
        booleanValue = true;
    }
    public static boolean getValue(ArgsMarshaler am){
        if (am instanceof BooleanArgMarshaler){
            return ((BooleanArgMarshaler) am).booleanValue;
        }
        else
            return false;
    }
}
