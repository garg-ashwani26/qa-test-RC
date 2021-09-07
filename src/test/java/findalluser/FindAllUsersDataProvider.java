package findalluser;

import org.testng.annotations.DataProvider;
import utility.CSVReaderUtility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


public class FindAllUsersDataProvider {

    @DataProvider(name = "FindAllUsersAPIData")
    public Iterator<Object[]> findAllUsersDataProvider(Method method)
    {
        String fileName = "findAllUsersData.csv";
        System.out.println(method.getName());
        Collection<Object[]> iterableObject = new ArrayList<>();
        for(Map<String,String> map:CSVReaderUtility.csvReader(fileName))
        {
            if(map.get("testname").equals(method.getName())) {
                map.remove("testname");
                iterableObject.add(new Object[]{map});
            }
        }
        return iterableObject.iterator();
    }
}
