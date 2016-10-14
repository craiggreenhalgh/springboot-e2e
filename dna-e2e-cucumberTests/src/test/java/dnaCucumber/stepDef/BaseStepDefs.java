package dnaCucumber.stepDef;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class BaseStepDefs {

    private List<Map<String, String>> individualRecords;
    private ResultSet individualRecords1;

    public void setIndividualRecords1(ResultSet individualRecords1) {
        this.individualRecords1 = individualRecords1;
    }

    public ResultSet getIndividualRecords1() {
        return individualRecords1;
    }
}
