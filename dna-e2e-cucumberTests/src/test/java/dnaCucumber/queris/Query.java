package dnaCucumber.queris;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class Query {

    public static final String getPersonRecordByIdFromIndividualTable(String personId) {
        return String.format("SELECT * FROM individual WHERE individual_id = %d", Integer.parseInt(personId));
    }

    public static final String getPersonAddressFromAddressTable(String personId) {
        return String.format("SELECT * FROM address WHERE individual_id =" + Integer.parseInt(personId));
    }

    public static final String getAllPersonDetails(String personId) {
        return String.format("SELECT * FROM individual INNER JOIN address ON individual.id=address.individual_id WHERE individual.id=%d", Integer.parseInt(personId));
    }

    public static final String getPersonIdNumberByFirstName(String firstName) {
        return String.format("SELECT id FROM individual WHERE first_name = %s", firstName);
    }
}
