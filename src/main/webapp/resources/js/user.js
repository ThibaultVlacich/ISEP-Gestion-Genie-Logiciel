let Common = require('./common.js');

const $ = global.jQuery = require('jquery');

class User extends Common {

    constructor(options) {
        super(options);

        $('.form-signin input').on('change keyup', function(e) {
            var $this = $(this),
                $login = $('#inputLogin'),
                $password = $('#inputPassword');

            if ($this.val().length > 3) {
                $this.closest('.form-group').addClass('has-success').removeClass('has-error');
            } else {
                $this.addClass('has-error').removeClass('has-success');
            }
        });
    }

}

let app = new User();
