const $ = global.jQuery = require('jquery');
// export for others scripts to use
window.$ = $;
window.jQuery = jQuery;

require('bootstrap-sass');

const defaults = {
    root: 'body'
};

class Common {
    constructor(options) {
        this.settings = $.extend({}, defaults, options);
        this.$el = $(this.settings.root);

        this.initSidebar();
        this.initEventsHandlers();
    }

    initSidebar() {
        $('#menu-toggle').on('click', function() {
            $('body').toggleClass('menu-opened');
        });
    }

    initEventsHandlers() {
        $('body').on('click', '[data-submit]', function(e) {
            var idForm  = $(this).data('submit'),
                $form   = $('#'+idForm);

            $form.trigger('submit');

            e.preventDefault();
        });
    }
}

module.exports = Common;
