package dnaCucumber.stepDef;

import java.util.List;
import java.util.Map;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class BaseStepDefs {

    private List<Map<String, String>> individualRecords;

    public void setIndividualRecords(List<Map<String, String>> personDetails) {
        this.individualRecords = personDetails;
    }

    public List<Map<String, String>> getIndividualRecords() {
        return individualRecords;
    }
}
