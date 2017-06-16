let Common = require('./common.js');

const $ = global.jQuery = require('jquery');

class Appointment extends Common {

    constructor(options) {
        super(options);

        this.initCKEDITOR();
        this.initTimer();
    }

    initCKEDITOR() {
        if (typeof CKEDITOR === "undefined") {
            // CKEDITOR has not been loaded
            return;
        }

        CKEDITOR.replace('comment');
    }

    initTimer() {
        // Start/Stop timer buttons
        let $buttonStart = $('button[data-start-timer]'),
            $buttonStop  = $('button[data-stop-timer]');

        // Timer inputs
        let $timerInput       = $('input[data-timer]'),
            $timerStringInput = $('input[data-timer-text]');

        // Current status of the timer (started or not)
        this.timerStarted = false;

        // Time spent in the appointment (in seconds)
        this.timeSpent = 0;

        let that = this;

        $buttonStart.on('click', function (event) {
            event.preventDefault();

            // Is the timer already started?
            if (that.timerStarted) return;

            that.timerStarted = true;

            $buttonStart.attr('disabled', true);
            $buttonStop.attr('disabled', false);

            that.timer = setInterval(function () {
                that.timeSpent += 1;

                let minutes = that.pad(Math.floor(that.timeSpent / 60), 2);
                let seconds = that.pad(that.timeSpent % 60, 2);

                let timeSpentString = minutes+":"+seconds;

                $timerInput.val(that.timeSpent);
                $timerStringInput.val(timeSpentString);
            }, 1000);
        });

        $buttonStop.on('click', function (event) {
            event.preventDefault();

            // Is the timer started?
            if (!that.timerStarted) return;

            that.timerStarted = false;

            $buttonStop.attr('disabled', true);
            $buttonStart.attr('disabled', false);

            clearInterval(that.timer);
        });
    }

    pad(num, size){ return ('000000000' + num).substr(-size); }

}

let app = new Appointment();
