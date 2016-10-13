var homePageLocators = require('./../locators/HomePageLocators.js')
var AngularJsHomepage = function () {
    this.Login = function (yourName) {
        homePageLocators.yourName.sendKeys(yourName);
    }
    this.bannerText = function () {
        return homePageLocators.outcome.getText();
    }
};
module.exports = new AngularJsHomepage();