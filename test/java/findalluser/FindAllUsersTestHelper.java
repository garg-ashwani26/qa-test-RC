package findalluser;

import helper.findAllUsersHelper;
import io.restassured.response.Response;
import model.dbResult;
import utility.ApiHelperUtil;
import utility.BaseApi;
import utility.DBUtility;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FindAllUsersTestHelper {

    public Response apiInvokeAndValidate(String basePath, BaseApi.HTTP_METHOD httpMethod, Map<String, String> headers,
                                             Map<String, String> params, Object requestDTO)
    {
        return ApiHelperUtil.invokeApi(basePath, httpMethod, headers, params, requestDTO);
    }

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

        String offsetCondition = params.get("page").equals("0") && !params.get("size").equals("NA") ?
                " OFFSET 0 ROWS" : params.get("page").equals("0") && params.get("size").equals("NA") ?
                " OFFSET 0 ROWS" : !params.get("page").equals("NA") && !params.get("size").equals("NA") ?
                " OFFSET " + Integer.parseInt(params.get("page"))*Integer.parseInt(params.get("size")) + " ROWS" :
                !params.get("page").equals("NA") && params.get("size").equals("NA") ?
                " OFFSET " + (Integer.parseInt(params.get("page")) * 20 + 1) + " ROWS":
                        params.get("page").equals("NA") && !params.get("size").equals("NA") ? " OFFSET 0 ROWS" : null;

        String sizeCondition = !params.get("size").equals("NA") ? " FETCH NEXT " + params.get("size") + " ROWS ONLY"
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


}
