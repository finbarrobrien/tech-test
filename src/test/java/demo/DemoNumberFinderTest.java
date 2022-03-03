package demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class DemoNumberFinderTest
{
    private static final String JSON_SAMPLE = "src/test/resources/demo.json";
    private static final String EMPTY       = "src/test/resources/empty.json";
    private static final String EMPTY_ARRAY = "src/test/resources/empty_array.json";
    private static final String BAD_FORMAT = "src/test/resources/bad_format.json";

    @Test
    public void shouldReadTheDemoFile(){
        DemoNumberFinder d = new DemoNumberFinder();
        List<CustomNumberEntity> numbers = d.readFromFile(JSON_SAMPLE);
        assertTrue(numbers.size() == 7);
    }

    @Test
    public void shouldReadTheBadFile(){
        DemoNumberFinder d = new DemoNumberFinder();
        List<CustomNumberEntity> numbers = d.readFromFile(BAD_FORMAT);
        assertTrue(numbers.isEmpty());
    }

    @Test
    public void shouldReadTheEmptyFile(){
        DemoNumberFinder d = new DemoNumberFinder();
        List<CustomNumberEntity> numbers = d.readFromFile(EMPTY);
        assertTrue(numbers.isEmpty());
    }

    @Test
    public void shouldReadTheEmptyArrayFile(){
        DemoNumberFinder d = new DemoNumberFinder();
        List<CustomNumberEntity> numbers = d.readFromFile(EMPTY_ARRAY);
        assertTrue(numbers.isEmpty());
    }

    @Test
    public void shouldFindTheNumber(){
        DemoNumberFinder d = new DemoNumberFinder();
        List<CustomNumberEntity> numbers = d.readFromFile(JSON_SAMPLE);
        assertTrue(d.contains(67, numbers));
        assertTrue(d.contains(45, numbers));
        assertTrue(d.contains(-3, numbers));
        assertTrue(d.contains(12, numbers));
        assertTrue(d.contains(100, numbers));
        assertTrue(d.contains(3, numbers));
    }

    @Test
    public void shouldNotFindTheNumber(){
        DemoNumberFinder d = new DemoNumberFinder();
        List<CustomNumberEntity> numbers = d.readFromFile(JSON_SAMPLE);
        assertFalse(d.contains(1, numbers));
        assertFalse(d.contains(1000, numbers));
        assertFalse(d.contains(100000, numbers));
    }
}
