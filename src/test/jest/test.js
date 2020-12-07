const puppeteer = require('puppeteer');
const expectPuppeteer = require("expect-puppeteer");
const flushPromises = () => new Promise(setImmediate);
let browser, page;


describe('Shows Test', () => {
        beforeAll(async () => {
                browser = await puppeteer.launch(/*{headless:false}*/);
                page = await browser.newPage();
                await page.goto("http://localhost:3000/shows/");
                await expect(page).toMatch('Search');
        });

        afterAll(async () => {
            await browser.close();
        });

        test('should validate empty search box', async () => {
                await flushPromises();
                await expectPuppeteer(page).toFill('input[name="search"]', '');
                console.log("Search box is clean");
        });
        test('should click on search button', async () => {
                await expectPuppeteer(page).toClick('button', {text: 'Search'});
                console.log("Button was pressed correctly")

        });
        test('should be unable to navigate', async () => {
                await page.waitForXPath("/html/body/div/div/div/form/span");
                let spanTarget = await page.$x('/html/body/div/div/div/form/span');
                //Get "Search cannot be empty." text from the element
                let stringText = await page.evaluate(element => element.textContent, spanTarget[0]);
                console.log(stringText + " Please insert text.");

        });

        test('Search for "Guitar" shows', async () =>{
                await expectPuppeteer(page).toFill('input[name="search"]', 'guitar');
                await expectPuppeteer(page).toClick('button', {text: 'Search'});

        });
        test('Validate that first card in "Guitar" search has an image within', async () =>{
                await flushPromises();
                await page.waitForXPath('/html/body/div/div[1]/div/div/div[1]/div/div[1]/img');
                await page.waitForSelector('div.card-content.white-text');
                let imgInCard = await page.$(
                    'body > div > div:nth-child(3) > div > div > div.card-content.white-text > div > div >img'); //ElementHandle for present image
                await expect(imgInCard).toBeTruthy();
        })
        test('Validate that seventh card in "Guitar" search has NO image within', async () =>{
                let noImgInCard = await page.$(
                    'body > div > div:nth-child(9) > div > div > div.card-content.white-text > div > div > img'); //ElementHandle for no image card
                await expect(noImgInCard).toBeFalsy();
        });
});



