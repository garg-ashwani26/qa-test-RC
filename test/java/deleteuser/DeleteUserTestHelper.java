package deleteuser;

import model.dbResult;
import utility.DBUtility;

import java.util.ArrayList;

public class DeleteUserTestHelper {

    public ArrayList<dbResult> fetchFirstRecord()
    {
        return new DBUtility().derbyFetchFirstRecord();
    }

    public int dbFetchLastID()
    {
        return new DBUtility().derbyFetchLastID();
    }

    public void dbDeleteRow(int ID)
    {
        new DBUtility().derbyDeleteRow(ID);
    }
}
