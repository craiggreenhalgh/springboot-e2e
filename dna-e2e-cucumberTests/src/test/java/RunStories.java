import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Created by VikramD on 20-Sep-2016.
 */

//@RunWith(ExtendedCucumber.class)
//@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
//        retryCount = 3,
//        detailedReport = true,
//        detailedAggregatedReport = true,
//        overviewReport = true,
//        //coverageReport = true,
//        jsonUsageReport = "target/cucumber-usage.json",
//        usageReport = true,
//        toPDF = true,
//        excludeCoverageTags = {"@flaky" },
//        includeCoverageTags = {"@passed" },
//        outputFolder = "target")
//@CucumberOptions(plugin = { "html:target/cucumber-html-report",
//        "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
//        "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" },
//        features = { "src/test/resources/features/" },
//        glue = { "fr.tlasnier.cucumber" },
//        tags = {"@tags"})

@RunWith(Cucumber.class)

//@CucumberOptions(  monochrome = true,
//        tags = "@Maplin",
//        features = "src/test/resources/features/",
//        format = { "pretty","html: cucumber-html-reports",
//                "json: cucumber-html-reports/cucumber.json" },
//        dryRun = false,
//        glue = "fr.tlasnier.cucumber" )
@CucumberOptions(features = "src/test/resources/features",
        glue = {"dnaCucumber.stepDef"},
        dryRun = false,
        monochrome = true,
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" }
//        tags = "@macroFilter"
)
public class RunStories {

//    private BeforeAfterSteps beforeAfterSteps = new BeforeAfterSteps();
//
//    @BeforeSuite
//    public void globalSetup() {
//
//        beforeAfterSteps.start();
//    }
//
//    @AfterSuite
//    public void globalTearDown() {
//        beforeAfterSteps.teardown();
//    }
}