package dnaCucumber.dtos;

import com.google.gson.Gson;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import dnaCucumber.config.Config;
import dnaCucumber.utils.RestClient;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class IndividualDto implements IDto{

    private static IndividualDto instance = null;
    public static final String INDIVIDUAL_URI = "/individual";
    private IndividualDto() {
    }
    public static IndividualDto getInstance() {
        if(instance == null) {
            instance = new IndividualDto();
        }
        return instance;
    }

    public Individual getIndividualDtos(Map<String, String> person) {
        Map<String, String> name = new HashMap<>();
        List<String> nameParams = Arrays.asList("Title", "firstName", "lastName");
        nameParams.forEach(param -> {
            name.put(param, person.get(param));
        });
        String personDob = person.get("dob");
        String personAddress = person.get("address");
        return new Individual()
                                .setAddresses(getAddressDtos(personAddress))
                                .setName(getNameDtos(name))
                                .setDob(getPersonDob(personDob));
    }

    public Name getNameDtos(Map<String, String> nameParams) {
        return new Name()
                        .setTitle(Name.Title.MR)
                        .setFirstname(nameParams.get("firstName"))
                        .setSurname(nameParams.get("lastName"));
}

    public Set<Address> getAddressDtos(String addresses) {
        List<String> listOfAddressesString = Arrays.stream(addresses.split(";")).collect(Collectors.toList());
        Set<Address> setOfAddresses = new LinkedHashSet<>();
        listOfAddressesString.forEach(addressString -> {
            List<String> addressParams = Arrays.stream(addressString.split(",")).collect(Collectors.toList());
            Address address = new Address()
                    .setLine1(addressParams.get(0))
                    .setLine2(addressParams.get(1))
                    .setCounty(addressParams.get(2))
                    .setCountry(addressParams.get(3))
                    .setPostCode(addressParams.get(4));
            setOfAddresses.add(address);
        });
        return setOfAddresses;
    }

    public LocalDate getPersonDob(String personDob) {
        int[] dob = Arrays.stream(personDob.split("-")).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dob[0], dob[1], dob[2]);
    }
}
