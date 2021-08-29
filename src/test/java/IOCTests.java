import org.junit.jupiter.api.Test;
import processing.IOC;

import static org.junit.jupiter.api.Assertions.*;

public class IOCTests {

    @Test
    void shouldReturnIndexOfCoincidenceForEnUsTextFile(){
        double value = IOC.computeIndexOfCoincidence("cifradoEnUs.txt");

        assertTrue(value > 0.0);
    }

    @Test
    void shouldReturnIndexOfCoincidenceForPtBrTextFile(){
        double value = IOC.computeIndexOfCoincidence("cifradoPtBr.txt");

        assertTrue(value > 0.0);
    }

}
