package dnaCucumber.stepDef;

import com.mastek.dna.model.Individual;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dnaCucumber.config.CommonUtil;
import dnaCucumber.dtos.TIndividual;
import dnaCucumber.queris.Query;
import dnaCucumber.utils.PostgresClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Vikram103069 on 12/10/2016.
 */
public class PersonEntityStepDef extends BaseStepDefs{

    private static final Logger LOG = LoggerFactory.getLogger(PersonEntityStepDef.class);
    private TIndividual tIndividual;
    private CommonUtil commonUtil;
    private PostgresClient postgresClient;
    private String scenario_val = null;

    @Before
    public void setup() {
        tIndividual = TIndividual.getInstance();
        commonUtil = CommonUtil.getInstance();
        postgresClient = new PostgresClient();
        if(scenario_val == null) {
            scenario_val = Long.toString(System.currentTimeMillis());
            commonUtil.setScenarioVal(scenario_val);
        }
        LOG.info("The value of scenario_val is : %s", scenario_val);
    }

    @After
    public void tearDown() {
        commonUtil.setScenarioVal(null);
    }

    @Given("^I create the person entity and updated the person record with the following details$")
    public void i_creates_the_person_entity_with_the_following_details(Map<String, String> person) throws Throwable {

        Individual individualDtos = tIndividual.getIndividualDtos(person);
        int personId = tIndividual.getPersonId(individualDtos, TIndividual.INDIVIDUAL_URI);
        tIndividual.updateAddress(personId);
    }

    @When("^I get the person detials from postgres db$")
    public void i_get_the_person_detials_from_postgres_db() throws Throwable {
        List<Map<String, String>> personDetails = postgresClient.executeQueryAsList(Query.getAllPersonDetails(tIndividual.getPersonId().toString()));
        setIndividualRecords(personDetails);
    }

    @Then("^person details name, dob and address are as below$")
    public void person_details_name_dob_and_address_are_as_below(Map<String, String> person) throws Throwable {
        List<Map<String, String>> individualRecords = getIndividualRecords();
        individualRecords.forEach(record -> {
            assertThat(record.get("first_name"), is(equalTo(person.get("firstName").concat(commonUtil.getScenarioVal()))));
            assertThat(record.get("last_name"), is(equalTo(person.get("lastName"))));
            String address = String.join(",", record.get("address_line1"), record.get("address_line2").replace(scenario_val, ""), record.get("county"), record.get("country"), record.get("post_code"));
            assertThat(tIndividual.getAddresses(), hasItem(address));
        });
    }
}
