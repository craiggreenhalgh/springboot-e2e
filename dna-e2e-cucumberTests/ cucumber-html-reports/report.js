$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("S01_PersonEntity.feature");
formatter.feature({
  "line": 2,
  "name": "Individual person fields",
  "description": "",
  "id": "individual-person-fields",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Validate the person entity fields",
  "description": "",
  "id": "individual-person-fields;validate-the-person-entity-fields",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I create the person entity and updated the person record with the following details",
  "rows": [
    {
      "cells": [
        "firstName",
        "Tory"
      ],
      "line": 6
    },
    {
      "cells": [
        "lastName",
        "Cameron"
      ],
      "line": 7
    },
    {
      "cells": [
        "Title",
        "Mr"
      ],
      "line": 8
    },
    {
      "cells": [
        "dob",
        "1955-10-26"
      ],
      "line": 9
    },
    {
      "cells": [
        "address",
        "10 Downing Street"
      ],
      "line": 10
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I get the person detials from postgres db",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "person details name, dob and address are as below",
  "rows": [
    {
      "cells": [
        "Tory"
      ],
      "line": 13
    },
    {
      "cells": [
        "1955-10-26"
      ],
      "line": 14
    },
    {
      "cells": [
        "10 Downing Street"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "PersonEntityStepDef.i_creates_the_person_entity_with_the_following_details(String,String\u003e)"
});
formatter.result({
  "duration": 833283022059,
  "status": "passed"
});
formatter.match({
  "location": "PersonEntityStepDef.i_get_the_person_detials_from_postgres_db()"
});
formatter.result({
  "duration": 2281750,
  "error_message": "cucumber.api.PendingException: TODO: implement me\r\n\tat dnaCucumber.stepDef.PersonEntityStepDef.i_get_the_person_detials_from_postgres_db(PersonEntityStepDef.java:57)\r\n\tat ✽.When I get the person detials from postgres db(S01_PersonEntity.feature:11)\r\n",
  "status": "pending"
});
formatter.match({
  "location": "PersonEntityStepDef.person_details_name_dob_and_address_are_as_below(DataTable)"
});
formatter.result({
  "status": "skipped"
});
});