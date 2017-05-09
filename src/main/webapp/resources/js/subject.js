let Common = require('./common.js');

const $ = require('jquery');

class Subject extends Common {

    constructor(options) {
        super(options);

        this.initCKEDITOR();
    }

    initCKEDITOR() {
        if (typeof CKEDITOR === "undefined") {
            // CKEDITOR has not been loaded
            return;
        }

        CKEDITOR.replace('description');
    }

}

let app = new Subject();
