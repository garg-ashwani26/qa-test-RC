package saveuser;

import org.testng.annotations.DataProvider;
import utility.CSVReaderUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class SaveUserDataProvider {

    @DataProvider(name = "SaveUserAPIData")
    public Iterator<Object[]> findAllUsersDataProvider()
    {
        String fileName = "saveUserData.csv";
        Collection<Object[]> iterableObject = new ArrayList<>();
        for(Map<String,String> map: CSVReaderUtility.csvReader(fileName))
        {
            iterableObject.add(new Object[]{map});
        }
        return iterableObject.iterator();
    }
}
