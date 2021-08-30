import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;
import processing.IOC;

import static org.junit.jupiter.api.Assertions.*;

public class IOCTests {

    @Test
    void shouldReturnIndexOfCoincidenceForEnUsTextFile(){
        double value = IOC.computeIndexOfCoincidence("cifradoEnUs.txt");

        double rounded = Precision.round(value, 2);

        assertEquals(0.04, rounded);
    }

    @Test
    void shouldReturnIndexOfCoincidenceForPtBrTextFile(){
        double value = IOC.computeIndexOfCoincidence("cifradoPtBr.txt");

        double rounded = Precision.round(value, 2);

        assertEquals(0.05, rounded);
    }

    @Test
    void shouldReturnTableIndexForPtBr(){
        double value = IOC.getIndexByLanguage("pt-BR");

        assertEquals(0.074, value);
    }

    @Test
    void shouldReturnTableIndexForEnUs(){
        double value = IOC.getIndexByLanguage("en-US");

        assertEquals(0.066, value);
    }

}
