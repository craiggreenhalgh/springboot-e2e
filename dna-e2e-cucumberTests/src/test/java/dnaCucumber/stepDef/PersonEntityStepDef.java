package dnaCucumber.stepDef;

import com.mastek.dna.model.Individual;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dnaCucumber.config.CommonUtil;
import dnaCucumber.dtos.IndividualDto;
import dnaCucumber.queris.Query;
import dnaCucumber.utils.PostgresClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Vikram103069 on 12/10/2016.
 */
public class PersonEntityStepDef extends BaseStepDefs{

    private static final Logger LOG = LoggerFactory.getLogger(PersonEntityStepDef.class);
    private IndividualDto individualDto;
    private CommonUtil commonUtil;
    private PostgresClient postgresClient;

    @Before
    public void setup() {
        individualDto = IndividualDto.getInstance();
        commonUtil = CommonUtil.getInstance();
        postgresClient = new PostgresClient();
    }

    @After
    public void tearDown() {

    }

    @Given("^I create the person entity and updated the person record with the following details$")
    public void i_creates_the_person_entity_with_the_following_details(Map<String, String> person) throws Throwable {

        Individual individualDtos = individualDto.getIndividualDtos(person);
        commonUtil.updateRecord(individualDtos, IndividualDto.INDIVIDUAL_URI);
    }

    @When("^I get the person detials from postgres db$")
    public void i_get_the_person_detials_from_postgres_db() throws Throwable {
        ResultSet individualRecords1 = postgresClient.executeQuery(Query.getPersonRecordByIdFromIndividualTable("1"));
        setIndividualRecords1(individualRecords1);
    }

    @Then("^person details name, dob and address are as below$")
    public void person_details_name_dob_and_address_are_as_below(Map<String, String> person) throws Throwable {
        List<String> nameParams = Arrays.asList("Title", "firstName", "lastName");
        ResultSet individualRecords1 = getIndividualRecords1();
        individualRecords1.next();

        nameParams.forEach(nameParam -> {
            for (int i = 0; i < nameParams.size(); i++) {
                try {
                    assertThat(individualRecords1.getObject(nameParams.get(i)).equals(person.get(i)), is(equalTo(true)));
                } catch (SQLException e) {
                    try {
                        LOG.info("%s is not specified in Indiviual Table for the person id %s", nameParams.get(i), individualRecords1.getObject("individual_id"));
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
