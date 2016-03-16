import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KonstantinSysoev on 21.02.16.
 */
public class SimpleTest {

    @Test
    public void testGetSum() throws Exception {
        // Установка
        Math math = new Math(3, 2);

        // Действие
        int result = math.getSum();

        // Проверка
        assertEquals(5, result);
    }
}