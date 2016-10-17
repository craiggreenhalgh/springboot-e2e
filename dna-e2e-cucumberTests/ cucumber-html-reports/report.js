$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("S01_PersonEntity.feature");
formatter.feature({
  "line": 1,
  "name": "Individual person fields",
  "description": "",
  "id": "individual-person-fields",
  "keyword": "Feature"
});
formatter.before({
  "duration": 697009221,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Validate the person entity fields",
  "description": "",
  "id": "individual-person-fields;validate-the-person-entity-fields",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I create the person entity and updated the person record with the following details",
  "rows": [
    {
      "cells": [
        "firstName",
        "Tory"
      ],
      "line": 5
    },
    {
      "cells": [
        "lastName",
        "Cameron"
      ],
      "line": 6
    },
    {
      "cells": [
        "Title",
        "Mr"
      ],
      "line": 7
    },
    {
      "cells": [
        "dob",
        "1955-10-26"
      ],
      "line": 8
    },
    {
      "cells": [
        "address",
        "10 Lambeth Street, Hemel Hempstead, Hertfordshire, UK, HP1 2DT; 20 Nelson Street, Lambeth, Lambeth Council, UK, SW1 2UF"
      ],
      "line": 9
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "I get the person detials from postgres db",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "person details name, dob and address are as below",
  "rows": [
    {
      "cells": [
        "Mr"
      ],
      "line": 12
    },
    {
      "cells": [
        "Tory"
      ],
      "line": 13
    },
    {
      "cells": [
        "Cameron"
      ],
      "line": 14
    },
    {
      "cells": [
        "1955-10-26"
      ],
      "line": 15
    },
    {
      "cells": [
        "10 Downing Street"
      ],
      "line": 16
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "PersonEntityStepDef.i_creates_the_person_entity_with_the_following_details(String,String\u003e)"
});
formatter.result({
  "duration": 11989199941,
  "status": "passed"
});
formatter.match({
  "location": "PersonEntityStepDef.i_get_the_person_detials_from_postgres_db()"
});
formatter.result({
  "duration": 56918119449302,
  "status": "passed"
});
formatter.match({
  "location": "PersonEntityStepDef.person_details_name_dob_and_address_are_as_below(String,String\u003e)"
});
