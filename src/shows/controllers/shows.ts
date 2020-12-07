import { Controller, Get, Res, Body, Post } from 'routing-controllers';
import { Response } from 'express';
import * as showService from '../services/shows.service';

@Controller('/shows')
export class ShowsController {

    @Get('/')
    getAll(@Res() res: Response) {
        res.render('shows/index');
    }

    @Post('/search')
    search(@Body() form: any, @Res() res: Response) {
        if (form.search !== '') {
            const shows = showService.searchShow(form.search);
            return shows.then((body: any) => {
                const data = JSON.parse(body);
                res.render('shows/list', { shows: data });
            }).catch((err: any) => console.log(err));
        }
        else {
            res.render('shows/index', { errors: { message: 'Search cannot be empty.'}})
        }
    }
}