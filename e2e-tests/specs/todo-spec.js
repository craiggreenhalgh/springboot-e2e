var homePage = require("./../pages/HomePage.js");
describe('bannr text displays correct text', function() {
    beforeEach(function() {
        browser.get('https://angularjs.org/');
        browser.waitForAngular();
    });

    it('type a text to display in the below defined banner', function() {
        homePage.Login('Vikram');
        expect(homePage.bannerText()).toEqual("Hello Vikram!");
    });
    afterEach(function() {
        browser.driver.get('https://google.co.uk');
    })
});
