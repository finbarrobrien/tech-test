package demo;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DemoNumberFinder implements NumberFinder {

    private FastestComparator c = new FastestComparator();

    @Override
    public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
        // The easy way
        //return list.parallelStream().filter(num -> c.compare(valueToFind, num) == 0).findFirst().isPresent();

        // Manual divide and conquer recursion way
        if(list.size() == 1){
            return c.compare(valueToFind, list.get(0)) == 0;
        }
        return contains(valueToFind, list.subList(0,list.size()/2)) || contains(valueToFind, list.subList(list.size()/2, list.size()));
    }

    @Override
    public List<CustomNumberEntity> readFromFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<CustomNumberEntity> numbers = null;
        try {
            /**
             * 1. Read the file
             * 2. deserialize json array to a list of CustomNumberEntity
             * 3. Remove anything that's not numeric to avoid the NumberFormatException in
             *    FastestComparator.compare since we are instructed not to modify it or CustomNumberEntity
             * */
            return mapper.readValue(new File(filePath), new TypeReference<List<CustomNumberEntity>>(){})
                    .parallelStream()
                    .filter(c -> {
                        try {
                            Integer.parseInt(c.getNumber());
                            return true;
                        }catch (NumberFormatException e){
                            return false;
                        }
                    }).collect(Collectors.toList());
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}
