package saveuser;

import org.testng.annotations.DataProvider;
import utility.CSVReaderUtility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class SaveUserDataProvider {

    @DataProvider(name = "SaveUserAPIData")
    public Iterator<Object[]> findAllUsersDataProvider(Method method)
    {
        String fileName = "saveUserData.csv";
        Collection<Object[]> iterableObject = new ArrayList<>();
        for(Map<String,String> map: CSVReaderUtility.csvReader(fileName))
        {
            if(map.get("testname").equals(method.getName())) {
                map.remove("testname");
                iterableObject.add(new Object[]{map});
            }
        }
        return iterableObject.iterator();
    }
}
