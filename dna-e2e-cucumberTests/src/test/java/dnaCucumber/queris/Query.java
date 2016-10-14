package dnaCucumber.queris;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class Query {

    public static final String getPersonRecordByIdFromIndividualTable(String personId) {
        return String.format("SELECT * FROM individual WHERE individual_id =" + Integer.parseInt(personId));
    }

    public static final String getPersonAddressFromAddressTable(String personId) {
        return String.format("SELECT * FROM address WHERE individual_id =" + Integer.parseInt(personId));
    }
}
