module.exports = {
    variants: {
        items: {
            resize: {
                thumb: "300x300",
                original: "100%",
                medium: "80%",
                small: "400"
            }
        }
    },

    storage: {
        S3: {
            key: 'AKIAJGKMYW5XI6CVNSUQ',
            secret: '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',
            bucket: 'brandzoomr',
            storageClass: 'REDUCED_REDUNDANCY',
            secure: false, // (optional) if your BUCKET_NAME contains dot(s), set this to false. Default is `true`
            cdn: 'CDN_URL' // (optional) if you want to use Amazon cloudfront cdn, enter the cdn url here
        }
    },
    debug: true
};
