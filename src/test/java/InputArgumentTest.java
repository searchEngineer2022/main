import com.EVA.L.InputArgument;
import org.junit.Test;

public class InputArgumentTest {
    @Test
    public void testOptions(){
        String[] args = new String[]{"-h"};
        InputArgument inputArgument=new InputArgument(args);
        System.exit(0);
    }
}
