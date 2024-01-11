// app.component.ts
import { Component } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { DataService } from './service/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [],
  standalone: true,
  providers: [DataService],
  imports: [HeaderComponent, RouterModule, HttpClientModule]
})
export class AppComponent { }
