const jsdom = require("jsdom");
const {JSDOM} = jsdom;

const resourceLoader = new jsdom.ResourceLoader({
    userAgent: "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36",
});

const html = `<!DOCTYPE html><p>Hello world</p>`;

const xx = new JSDOM(html, {
    url: "https://www.toutiao.com",
    referrer: "https://example.com/",
    contentType: "text/html",
    resources: resourceLoader,
});

console.log(xx.window.location)
console.log(xx.window.navigator.userAgent)
console.log(xx.window.document.referrer)

