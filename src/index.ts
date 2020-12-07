import express from 'express';
import path from 'path';
import 'reflect-metadata';
import { urlencoded, json } from 'body-parser';
import { useExpressServer } from 'routing-controllers';
import { ShowsController} from './shows/controllers/shows';

const app = express();
const port = 3000;
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(urlencoded());
app.use(json());


useExpressServer(app, {
    controllers: [ShowsController]
})

app.listen(port);