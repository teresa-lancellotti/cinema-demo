import { Component } from '@angular/core';
import { ShowService } from './api/show.service';
import { CardComponent } from './common-components/card-component';
import { Show } from './model/show';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [ShowService],
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'cinema-app';

  shows?: Show[] = [];

  constructor(private showService: ShowService, private cardComponent: CardComponent) { }

  callApiFindByDates() {
    console.log("Call api method -> callApiFindByDates");
    this.showService.getShowsByFromTo("2024-10-14")
                    .subscribe(
                      result => {
                        console.log(result.showList);
                        console.log(result.outcome);
                        this.shows = result.showList?? [];
                        console.log(this.cardComponent.showsArray);
                      });
  }

  callApiFindOld() {
    console.log("Call api method -> callApiFindOld");
    this.showService.getOldShows()
                    .subscribe(
                      result => {
                        console.log(result.showList);
                        console.log(result.outcome);
                        this.shows = result.showList?? [];
                        console.log(this.cardComponent.showsArray);
                      });
  }
}
