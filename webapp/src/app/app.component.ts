// app.component.ts
import { Component } from '@angular/core';
import { HeaderComponent } from './header/header.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [],
  standalone: true,
  imports: [HeaderComponent]
})
export class AppComponent { }
