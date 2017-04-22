const $ = global.jQuery = require('jquery')
// export for others scripts to use
window.$ = $
window.jQuery = jQuery

require('bootstrap')

const defaults = {
  root: 'body'
}

class Common {
  constructor(options) {
    this.settings = $.extend({}, defaults, options)
    this.$el = $(this.settings.root)

    this.initSidebar()
  }

  initSidebar() {
    $('#menu-toggle').on('click', () => {
      $('body').toggleClass('menu-opened')
    })
  }
}

module.exports = Common
