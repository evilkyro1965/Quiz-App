function ValueValidator(valueData,validationConf){
    this.valueData = valueData;
    this.validationConf = validationConf;
}

ValueValidator.prototype = {
    isValid : true,
    validate : function(){

        this.clean();
        for(var i=0;i<this.validationConf.length;i++) {
            var data = this.valueData;
            var validationObj = this.validationConf[i];
            if(data && data[validationObj.fieldName]!== undefined) {
                var fieldValue = data[validationObj.fieldName];
                if(validationObj.validationType=="IsNotEmpty") {
                    if(this.validIsNotEmpty(fieldValue)==false) {
                        this.isValid &= false;
                        $(""+validationObj.errorDiv).html("This field can't be empty!");
                        $(""+validationObj.errorDiv).show();
                    }
                }
                else if(validationObj.validationType=="IsValidCombo") {
                    if(this.validCombo(fieldValue)==false) {
                        this.isValid &= false;
                        $(""+validationObj.errorDiv).html("This field must be selected!");
                        $(""+validationObj.errorDiv).show();
                    }
                }
                else if(validationObj.validationType=="IsValidEmail") {
                    if(this.validEmail(fieldValue)==false) {
                        this.isValid &= false;
                        $(""+validationObj.errorDiv).html("This field is not valid email!");
                        $(""+validationObj.errorDiv).show();
                    }
                }
                else if(validationObj.validationType=="IsNotEmptyNumber") {
                    if(this.validIsNotEmptyNumber(fieldValue)==false) {
                        this.isValid &= false;
                        $(""+validationObj.errorDiv).html("This field can't be empty!");
                        $(""+validationObj.errorDiv).show();
                    }
                }
            }
            else {
                this.isValid &= false;
            }
        }

        return this.isValid;

    },
    clean : function() {
        for(var i=0;i<this.validationConf.length;i++) {
            var validationObj = this.validationConf[i];
            $(""+validationObj.errorDiv).hide();
        }
    },
    validIsNotEmpty : function(data) {
        if(data.trim()=="") {
            return false;
        }
        return true;
    },
    validIsNotEmptyNumber : function(data) {
        if(data=="" || isNaN(data)) {
            return false;
        }
        return true;
    },
    validEmail : function(data) {
        if(data.trim()=="") {
            return false;
        }
        return true;
    },
    validCombo : function(data) {
        if(data!=null&&data!="") {
            return true;
        }
        return false;
    }

};

