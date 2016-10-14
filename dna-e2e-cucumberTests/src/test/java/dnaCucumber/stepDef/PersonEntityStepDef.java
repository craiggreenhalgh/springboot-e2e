package dnaCucumber.stepDef;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dnaCucumber.config.CommonUtil;
import dnaCucumber.config.Config;
import dnaCucumber.dtos.IndividualDto;
import dnaCucumber.utils.JsonUtil;
import dnaCucumber.utils.RestClient;
import jdk.nashorn.internal.objects.NativeJSON;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Vikram103069 on 12/10/2016.
 */
public class PersonEntityStepDef {

    private IndividualDto individualDto = IndividualDto.getInstance();
    private CommonUtil commonUtil = CommonUtil.getInstance();

    @Given("^I create the person entity and updated the person record with the following details$")
    public void i_creates_the_person_entity_with_the_following_details(Map<String, String> person) throws Throwable {

        Individual individualDtos = individualDto.getIndividualDtos(person);
        commonUtil.updateRecord(individualDtos, IndividualDto.INDIVIDUAL_URI);
    }

    @When("^I get the person detials from postgres db$")
    public void i_get_the_person_detials_from_postgres_db() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^person details name, dob and address are as below$")
    public void person_details_name_dob_and_address_are_as_below(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }
}
