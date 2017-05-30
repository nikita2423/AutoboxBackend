module.exports = ( AppUser, server, helper) => {
    const SECRET_KEY = "BRANDZOOMR_PASSWORD_@!%#_SNAPHY";
    //AppUser.validatesUniquenessOf('email');
    //Remove email verification..
    delete AppUser.validations.email;
    AppUser.observe("before save", function(ctx, next){
        const instance = ctx.instance || ctx.data;
        const currentInstance = ctx.currentInstance;
        //Adding password
        instance.password = SECRET_KEY;

        if(ctx.isNewInstance){
            instance.added = new Date();
            instance.updated = new Date();

        }else{
            instance.updated = new Date();
        }

        next();

    });
};
