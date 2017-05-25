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
        this.$functionalityContainer = $('[data-functionalities]');
        this.functionalityCounter = this.$functionalityContainer.find('[data-functionality]').length;

        let that = this;

        $('[data-functionalities]').on('click', '[data-functionality-up]', function (e) {
            let $functionality = $(e.target).closest('[data-functionality]'),
                actualPriority = parseInt($functionality.attr('data-functionality-priority'));

            if (actualPriority == 1) {
                e.preventDefault();

                return;
            }

            // Switch priorities
            that.$functionalityContainer.find('[data-functionality-priority="'+(actualPriority-1)+'"]').attr('data-functionality-priority', actualPriority);
            $functionality.attr('data-functionality-priority', actualPriority - 1);

            that.reOrderFunctionalities();

            e.preventDefault();
        });

        $('[data-functionalities]').on('click', '[data-functionality-down]', function (e) {
            let $functionality = $(e.target).closest('[data-functionality]'),
                actualPriority = parseInt($functionality.attr('data-functionality-priority'));

            if (actualPriority == that.functionalityCounter) {
                e.preventDefault();

                return;
            }

            // Switch priorities
            that.$functionalityContainer.find('[data-functionality-priority="'+(actualPriority+1)+'"]').attr('data-functionality-priority', actualPriority);
            $functionality.attr('data-functionality-priority', actualPriority + 1);

            that.reOrderFunctionalities();

            e.preventDefault();
        });

        $('[data-functionalities]').on('click', '[data-functionality-remove]', function (e) {
            let $functionality  = $(e.target).closest('[data-functionality]'),
                functionalityID = $functionality.find('input[name$="id"]').val();

            if (parseInt(functionalityID) > 0) {
                let ids         = that.$functionalityContainer.find('input[name="functionalities_to_delete"]').val().split(","),
                    filteredIds = ids.filter(function (id) {
                        return isNaN(parseInt(id));
                    });

                filteredIds.push(functionalityID);

                console.log(filteredIds);

                that.$functionalityContainer.find('input[name="functionalities_to_delete"]').val(filteredIds.join(","));
            }

            $functionality.remove();

            that.reIndexFunctionalities();
            that.reOrderFunctionalities();

            e.preventDefault();
        });

        $('a[data-add-functionality]').on('click', function (e) {
            that.newFunctionality();

            e.preventDefault();
        });
    }

    reIndexFunctionalities() {
        this.functionalityCounter = 1;

        let that = this;

        $('[data-functionality]').each(function() {
            let $functionality = $(this);

            $functionality.attr('data-functionality-priority', that.functionalityCounter);

            $functionality.find('input[name$="id"]').attr('name', 'functionalities['+(that.functionalityCounter - 1)+'].id');
            $functionality.find('input[name$="priority"]').attr('name', 'functionalities['+(that.functionalityCounter - 1)+'].priority').val(that.functionalityCounter);
            $functionality.find('input[name$="name"]').attr('name', 'functionalities['+(that.functionalityCounter - 1)+'].name');

            that.functionalityCounter += 1;
        });
    }

    reOrderFunctionalities() {
        let $functionalities = this.$functionalityContainer.children('[data-functionality]');

        let that = this;

        $functionalities.detach().sort(function (a, b) {
            let $a = $(a), $b = $(b);

            return parseInt($b.attr('data-functionality-priority')) < parseInt($a.attr('data-functionality-priority'));
        });

        this.$functionalityContainer.append($functionalities);

        $functionalities.each(function() {
            let $functionality = $(this);

            if (parseInt($functionality.attr('data-functionality-priority')) === 1) {
                $functionality.find('[data-functionality-up]').attr('disabled', 'disabled');
                $functionality.find('[data-functionality-down]').removeAttr('disabled');
            } else if (parseInt($functionality.attr('data-functionality-priority')) === that.functionalityCounter) {
                $functionality.find('[data-functionality-up]').removeAttr('disabled');
                $functionality.find('[data-functionality-down]').attr('disabled', 'disabled');
            } else {
                $functionality.find('[data-functionality-up]').removeAttr('disabled');
                $functionality.find('[data-functionality-down]').removeAttr('disabled');
            }

            $functionality.find('input[name$="priority"]').val($functionality.attr('data-functionality-priority'));
        });

        this.reIndexFunctionalities();
    }
    
    newFunctionality() {
        this.functionalityCounter += 1;

        let $functionality    = $('<tr data-functionality data-functionality-priority="'+this.functionalityCounter+'">'),
            $priorityCol      = $('<td>'),
            $functionalityCol = $('<td>'),
            $actionsCol       = $('<td class="text-center"><div class="btn-group">');

        $functionality.append($('<input type="hidden" name="functionalities['+(this.functionalityCounter - 1)+'].id" value="0">'));
        $functionality.append($priorityCol);
        $functionality.append($functionalityCol);
        $functionality.append($actionsCol);

        let $priority = $('<input type="text">');
        $priority.addClass('form-control');
        $priority.val(this.functionalityCounter);
        $priority.attr('name', 'functionalities['+(this.functionalityCounter-1)+'].priority');
        $priority.attr('disabled', 'disabled');

        $priorityCol.append($priority);

        let $input = $('<input type="text">');
        $input.addClass('form-control');
        $input.attr('name', 'functionalities['+(this.functionalityCounter-1)+'].name');
        $input.attr('placeholder', this.settings.i18n.name);

        $functionalityCol.append($input);

        $actionsCol.append('<a class="btn btn-primary" data-functionality-up'+(this.functionalityCounter === 1 ? ' disabled' : '')+'><i class="glyphicon glyphicon-arrow-up"></i></a>');
        $actionsCol.append('<a class="btn btn-primary" data-functionality-down disabled><i class="glyphicon glyphicon-arrow-down"></i></a>');
        $actionsCol.append('<a class="btn btn-danger" data-functionality-remove><i class="glyphicon glyphicon-remove"></i></a>');

        this.$functionalityContainer.append($functionality);

        this.reOrderFunctionalities();
    }

}

let app = new Subject(typeof settings !== 'undefined' ? settings : {});