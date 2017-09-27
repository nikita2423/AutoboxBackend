module.exports = {
    variants: {
        items: {
            resize: {
                thumb: "80x80",
                original: "100%",
                medium: "80%",
                small: "300"
            }
        }
    },

    storage: {
        S3: {
            key: 'AKIAJDJMX5CFG443RZBA',
            secret: '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',
            bucket: 'autoboxsnaphy',
            storageClass: 'REDUCED_REDUNDANCY',
            secure: false, // (optional) if your BUCKET_NAME contains dot(s), set this to false. Default is `true`
            cdn: 'CDN_URL' // (optional) if you want to use Amazon cloudfront cdn, enter the cdn url here
        }
    },
    debug: true
};
