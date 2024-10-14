import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import { Show } from '../model/show';

/**
 * @title Card with footer
 */
@Component({
  selector: 'card-component',
  templateUrl: 'card-component.html',
  styleUrl: 'card-component.css',
  standalone: true,
  imports: [MatCardModule, MatChipsModule, MatProgressBarModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CardComponent {
  @Input() showsArray?: Show[] = [{id: 0, title:"nessun dato"}];
}


/**  Copyright 2024 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at https://angular.io/license */