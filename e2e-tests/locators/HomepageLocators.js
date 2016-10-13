var HomePageLocators = function() {
    this.yourName= element(by.model('yourName'));
    this.outcome = element(by.binding('yourName'));
}
module.exports = new HomePageLocators();