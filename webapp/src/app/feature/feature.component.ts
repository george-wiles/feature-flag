import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Feature } from '../model/feature.model';
import { Subscription } from 'rxjs';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-feature',
  standalone: true,
  imports: [CommonModule],
  providers: [HttpClient],
  templateUrl: './feature.component.html',
  styleUrl: './feature.component.scss'
})
export class FeatureComponent {
  private features: Feature[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    this.fetchData();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  private fetchData() {
    this.subscriptions.push(
      this.dataService.getFeatures().subscribe(data => {
        console.log('features: ' + data)
        this.features = data;
      })
    );
  }

  public getFeatures() {
    return this.features;
  }

}
