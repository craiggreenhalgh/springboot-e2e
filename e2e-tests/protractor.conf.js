//jshint strict: false
exports.config = {

  allScriptsTimeout: 11000,

  specs: [
    '../**/todo-spec.js'
  ],

  capabilities: {
    'browserName': 'chrome'
  },

  onPrepare: function() {
    // browser.driver.manage().window().setSize(1600, 800);
    browser.driver.manage().window().maximize();
    var folderName = (new Date()).toString().split(' ').splice(1, 4).join(' ');
    var mkdirp = require('mkdirp');
    var newFolder = "./reports/" + folderName;
    require('jasmine-reporters');

    mkdirp(newFolder, function(err) {
      if (err) {
        console.error(err);
      } else {
        jasmine.getEnv().addReporter(new jasmine.JUnitXmlReporter(newFolder, true, true));
      }
    });
  },


  baseUrl: 'http://localhost:8000/',

  framework: 'jasmine',

  jasmineNodeOpts: {
    defaultTimeoutInterval: 30000,
    isVerbose: true,
    includeStackTrace: true
  }

};
