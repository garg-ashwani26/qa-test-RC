package findalluser;

import org.testng.annotations.DataProvider;
import utility.CSVReaderUtility;

import java.util.*;


public class FindAllUsersDataProvider {

    @DataProvider(name = "FindAllUsersAPIData")
    public Iterator<Object[]> findAllUsersDataProvider()
    {
        String fileName = "findAllUsersData.csv";
        Collection<Object[]> iterableObject = new ArrayList<>();
        for(Map<String,String> map:CSVReaderUtility.csvReader(fileName))
        {
            iterableObject.add(new Object[]{map});
        }
        return iterableObject.iterator();
    }
}
