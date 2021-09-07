package findalluser;

import model.dbResult;
import utility.DBUtility;

import java.util.*;

public class FindAllUsersTestHelper {

    ArrayList<dbResult> fetchDBData()
    {
        return new DBUtility().derbyFetchData();
    }


    ArrayList<dbResult> fetchDbData(LinkedHashMap<String, String> params)
    {
        String queryCondition = queryConditionBuilder(params);
        ArrayList<dbResult> resultList;
        if (queryCondition.isEmpty()){
            resultList = new DBUtility().derbyFetchData();
        }
        else {
            resultList = new DBUtility().derbyFetchDataWithCondition(queryCondition);
        }
        return resultList;
    }

    String queryConditionBuilder(LinkedHashMap<String, String> params)
    {
        List<String> conditionList = new ArrayList<>();
        String whereCondition = "";

        String offsetCondition = params.get("page").equals("0") && params.get("size").equals("0") ?
                " FETCH FIRST 20 ROWS ONLY" : params.get("page").equals("0") && !params.get("size").equals("NA") ?
                " OFFSET 0 ROWS" : params.get("page").equals("0") && params.get("size").equals("NA") ?
                " FETCH FIRST 20 ROWS ONLY" : !params.get("page").equals("NA") && params.get("size").equals("0") ?
                " OFFSET " + (Integer.parseInt(params.get("page")) * 20 + 1) + " ROWS FETCH FIRST 20 ROWS ONLY":
                !params.get("page").equals("NA") && !params.get("size").equals("NA") ?
                " OFFSET " + Integer.parseInt(params.get("page"))*Integer.parseInt(params.get("size")) + " ROWS" :
                !params.get("page").equals("NA") && params.get("size").equals("NA") ?
                " OFFSET " + (Integer.parseInt(params.get("page")) * 20 + 1) + " ROWS FETCH FIRST 20 ROWS ONLY":
                        params.get("page").equals("NA") && !params.get("size").equals("NA") ? " OFFSET 0 ROWS" :
                                " FETCH FIRST 20 ROWS ONLY";

        String sizeCondition = params.get("page").equals("0") && params.get("size").equals("0") ?
                null : !params.get("size").equals("NA") ? " FETCH NEXT " + params.get("size") + " ROWS ONLY"
                : null;

        String sortCondition = !params.get("sort").equals("NA") ? "ORDER BY " + params.get("sort").replace(","," ") :
                !params.get("page").equals("NA") && params.get("size").equals("NA") ||
                params.get("page").equals("NA") && !params.get("size").equals("NA") ||
                !params.get("page").equals("NA") && !params.get("size").equals("NA") ? "ORDER BY id" : null;

        conditionList.add(sortCondition);
        conditionList.add(offsetCondition);
        conditionList.add(sizeCondition);

        for(String condition : conditionList)
            if (condition != null)
            {
                whereCondition = whereCondition.concat(condition);
            }
        return whereCondition;
    }

    public HashMap<String,String> getParamsMap(HashMap<String,String> paramsData)
    {
        HashMap<String, String> params = new HashMap();
        Set<String> keys = paramsData.keySet();
        for(String key : keys){
            if(!paramsData.get(key).equals("NA")){
                params.put(key, paramsData.get(key));}
        }
        System.out.println("API Request Parameters" + params);
        return params;
    }

    public HashMap<String,String> getParamsMapInvalid(HashMap<String,String> paramsData)
    {
        //HashMap<String, String> params = new HashMap();
        int lastID = new DBUtility().derbyFetchLastID();
        paramsData.replace("Page",String.valueOf(((int)lastID/20) + 1));
        paramsData = getParamsMap(paramsData);
        return paramsData;
    }

    public int dbFetchLastID()
    {
        return new DBUtility().derbyFetchLastID();
    }

}
