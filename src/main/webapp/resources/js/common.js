const $ = global.jQuery = require('jquery');

require('bootstrap');

const defaults = {
  root: 'body'
};

class Common {
  constructor(options) {
    this.settings = $.extend({}, defaults, options);
    this.$el = $(this.settings.root);

    this.initSidebar();
  }

  initSidebar() {
    $('#menu-toggle').on('click', function () {
      $('body').toggleClass('menu-opened');
    });
  }
}

module.exports = Common;
