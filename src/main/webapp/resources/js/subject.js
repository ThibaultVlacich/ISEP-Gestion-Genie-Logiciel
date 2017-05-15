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

        let that = this;

        $('a[data-add-functionality]').on('click', function (e) {
            that.newFunctionality();

            e.preventDefault();
        });
    }
    
    newFunctionality() {
        let $functionality    = $('<div class="form-group">'),
            $functionalityCol = $('<div class="col-sm-12">');

        $functionality.append($functionalityCol);

        let $input = $('<input type="text">');
        $input.addClass('form-control');
        $input.attr('name', 'functionality[]');
        $input.attr('placeholder', 'Name of the functionality');

        $functionalityCol.append($input);

        this.$functionalityContainer.append($functionality);
    }

}

let app = new Subject();
