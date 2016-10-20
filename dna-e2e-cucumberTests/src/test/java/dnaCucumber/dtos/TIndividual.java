package dnaCucumber.dtos;

import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import dnaCucumber.config.CommonUtil;
import dnaCucumber.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class TIndividual implements IDto{

    private static final Logger LOG = LoggerFactory.getLogger(TIndividual.class);
    Config config = Config.getInstance();
    RestTemplate restTemplate = new RestTemplate();

    HttpMethod httpMethod;
    private static TIndividual instance = null;
    private String m_personAddress = null;
    public static final String INDIVIDUAL_URI = "/individual";
    public static final String ADDRESS_URI = "/individual/{individualId}/address";
    private String m_individualId = "{individualId}";
    private Integer personId;
    private List<String> addresses;

    private TIndividual() {
    }
    public static TIndividual getInstance() {
        if(instance == null) {
            instance = new TIndividual();

        }
        return instance;
    }

    public Individual getIndividualDtos(Map<String, String> person) {
        Map<String, String> nameDetails = new HashMap<>();
        List<String> nameParams = Arrays.asList("Title", "firstName", "lastName");
        nameParams.forEach(param -> {
            if(param.equals("firstName")) {
                nameDetails.put(param, person.get(param).concat(CommonUtil.getInstance().getScenarioVal()));
            }
            else {
                nameDetails.put(param, person.get(param));
            }
        });
        String personDob = person.get("dob");
        m_personAddress = person.get("address");
        return new Individual()
                                .setName(getNameDtos(nameDetails))
                                .setDob(getPersonDob(personDob));
    }

    public Integer getPersonId(Object dto, String URI) {
        ResponseEntity<Individual> response = restTemplate.exchange(Config.getInstance().getBaseUrl() + URI, HttpMethod.POST, new HttpEntity(dto, getHeaders()), Individual.class);
        Integer personId = response.getBody().getId();
        assert personId != null;
        setPersonId(personId);
        return personId;
    }

    private MultiValueMap<String, String> getHeaders()
    {
        final HttpHeaders headers = new HttpHeaders();
            addSecurityHeader(headers);
        return headers;
    }

    private void addSecurityHeader(final HttpHeaders headers)
    {
        final byte[] encodedAuth = Base64.getEncoder().encode(
                String.format("%s:%s", config.getUsername(), config.getPassword())
                        .getBytes(Charset.forName("UTF-8")));
        headers.set("Authorization", "Basic " + new String(encodedAuth));
    }

    public void updateAddress(int personId) {
        Set<Address> addressDtos = getAddressDtos(m_personAddress);
        String URI = ADDRESS_URI.replace(m_individualId, String.valueOf(personId));
        addressDtos.forEach(addressDto -> {
            ResponseEntity<Address> addressResponse = restTemplate.exchange(Config.getInstance().getBaseUrl().concat(URI), HttpMethod.POST, new HttpEntity(addressDto, getHeaders()), Address.class);
            assertThat(addressResponse.getStatusCode(), is(equalTo(org.springframework.http.HttpStatus.OK)));
        });
    }

    private Name getNameDtos(Map<String, String> nameParams) {
        return new Name()
                        .setTitle(Name.Title.MR)
                        .setFirstname(nameParams.get("firstName"))
                        .setSurname(nameParams.get("lastName"));
}

    public Set<Address> getAddressDtos(String addresses) {
        List<String> listOfAddressesString = Arrays.stream(addresses.split(";")).collect(Collectors.toList());
        setAddresses(listOfAddressesString);
        Set<Address> setOfAddresses = new LinkedHashSet<>();
        listOfAddressesString.forEach(addressString -> {
            List<String> addressParams = Arrays.stream(addressString.split(",")).collect(Collectors.toList());
            setOfAddresses.add(getSingleAddress(addressParams));
        });
        return setOfAddresses;
    }

    private Address getSingleAddress(List<String> addressParams) {
        return new Address()
                .setLine1(addressParams.get(0))
                .setLine2(addressParams.get(1).concat(CommonUtil.getInstance().getScenarioVal()))
                .setCounty(addressParams.get(2))
                .setCountry(addressParams.get(3))
                .setPostCode(addressParams.get(4));
    }

    private LocalDate getPersonDob(String personDob) {
        int[] dob = Arrays.stream(personDob.split("-")).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dob[0], dob[1], dob[2]);
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() {
        return addresses;
    }
}
