let Common = require('./common.js');

const $ = require('jquery');

class Subject extends Common {
    
    constructor(options) {
        super(options);

        this.initCKEDITOR();
        this.initFunctionalities();
    }

    initCKEDITOR() {
        if (typeof CKEDITOR === "undefined") {
            // CKEDITOR has not been loaded
            return;
        }

        CKEDITOR.replace('description');
    }

    initFunctionalities() {
        this.$functionalityContainer = $('div[data-functionalities]');
        this.functionalityCounter = this.$functionalityContainer.find('[data-functionality]').length;

        let that = this;

        $('[data-functionalities]').on('click', '[data-functionality-remove]', function (e) {
            $(e.target).closest('[data-functionality]').remove();

            that.functionalityCounter = that.$functionalityContainer.find('[data-functionality]').length;

            e.preventDefault();
        });

        $('a[data-add-functionality]').on('click', function (e) {
            that.newFunctionality();

            e.preventDefault();
        });
    }
    
    newFunctionality() {
        this.functionalityCounter += 1;

        let $functionality    = $('<div class="form-group" data-functionality>'),
            $functionalityCol = $('<div class="col-sm-11">');

        $functionality.append($('<input type="hidden" name="functionalities['+(this.functionalityCounter - 1)+'].id" value="0">'));
        $functionality.append($functionalityCol);

        let $input = $('<input type="text">');
        $input.addClass('form-control');
        $input.attr('name', 'functionalities['+(this.functionalityCounter-1)+'].name');
        $input.attr('placeholder', this.settings.i18n.name);

        $functionalityCol.append($input);

        $functionality.append('<div class="col-sm-1"><a class="btn btn-danger" data-functionality-remove><i class="glyphicon glyphicon-remove"></i></a></div>');

        this.$functionalityContainer.append($functionality);
    }

}

let app = new Subject(typeof settings !== 'undefined' ? settings : {});