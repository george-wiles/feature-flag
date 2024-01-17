import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AddFeature, Feature } from '../model/feature.model';
import { Subscription } from 'rxjs';
import { DataService } from '../service/data.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-feature',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  providers: [HttpClient],
  templateUrl: './feature.component.html',
  styleUrl: './feature.component.scss'
})
export class FeatureComponent {
  private features: Feature[] = [];
  private subscriptions: Subscription[] = [];
  featureForm: FormGroup;

  constructor(private dataService: DataService,
    private formBuilder: FormBuilder) {
      this.featureForm = this.formBuilder.group({
        displayName: ['', [Validators.required, Validators.maxLength(250)]],
        technicalName: ['', [Validators.required, Validators.maxLength(250)]],
        description: ['', [Validators.required, Validators.maxLength(250)]]
      });

      console.log('feature form is', this.featureForm)
    }

  ngOnInit(): void {
    this.fetchData();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  private fetchData() {
    this.subscriptions.push(
      this.dataService.getFeatures().subscribe(data => {
        console.log('features: ', data)
        this.features = data;
      })
    );
  }

  public getFeatures() {
    return this.features;
  }

  addFeature() {
    console.log('add feature', this.featureForm)
    if (this.featureForm.valid) {
      const add: AddFeature = {
        displayName: this.featureForm.get('displayName')?.value,
        technicalName: this.featureForm.get('technicalName')?.value,
        description: this.featureForm.get('description')?.value
      };
      this.subscriptions.push(this.dataService.createFeature(add)
        .subscribe({
          next: (feature: Feature) => {
            console.log('added feature', feature);
            this.fetchData();
          }
        }));
    }
  }
}
