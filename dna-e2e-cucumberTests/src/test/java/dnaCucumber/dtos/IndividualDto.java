package dnaCucumber.dtos;

import com.google.gson.Gson;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import dnaCucumber.config.Config;
import dnaCucumber.utils.RestClient;

import java.time.LocalDate;
import java.util.*;
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
        String personAddress = person.get("addres");
        return new Individual()
                            .setAddress(getAddressDtos(personAddress))
                            .setName(getNameDtos(name))
                            .setDob(getPersonDob(personDob));
    }

    public Name getNameDtos(Map<String, String> nameParams) {
        return new Name()
                        .setTitle(Name.Title.MR)
                        .setFirstname(nameParams.get("firstName"))
                        .setSurname(nameParams.get("lastName"));
}

    public Address getAddressDtos(String address) {
        return new Address().setTown(address);
    }

    public LocalDate getPersonDob(String personDob) {
        int[] dob = Arrays.stream(personDob.split("-")).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dob[0], dob[1], dob[2]);
    }
}
