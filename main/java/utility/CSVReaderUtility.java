package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CSVReaderUtility {

    public static ArrayList<LinkedHashMap<String,String>> csvReader(String fileName){

        LinkedHashMap<String,String> mapRowData = new LinkedHashMap<>();
        ArrayList<LinkedHashMap<String,String>> listOfDataRows = new ArrayList<>();
        List<Object> columnNameList = new ArrayList();

        URL csvURL = CSVReaderUtility.class.getClassLoader().
                getResource(File.separatorChar + "csvdata" +  File.separatorChar + fileName);
        if (csvURL == null)
        {
            throw new IllegalArgumentException("File not found");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(csvURL.toURI())))) {
            String line;
            int lineCounter = 0;
            while ((line = br.readLine()) != null) {
                if(lineCounter == 0)
                {
                    columnNameList = Arrays.asList(line.split(";"));
                    lineCounter++;
                    continue;
                }
                String[] values = line.split(";");
                for(int i = 1; i < columnNameList.size(); i++)
                {
                    mapRowData.put((String) columnNameList.get(i), values[i]);
                }
                listOfDataRows.add(new LinkedHashMap<>(mapRowData));
                mapRowData.clear();
            }
            System.out.println(listOfDataRows);

        }catch(IOException exp)
        {
            System.out.println("File Exception" + exp);
        }
        catch(URISyntaxException exp)
        {
            System.out.println("File Exception" + exp);
        }
        return listOfDataRows;
    }
}
