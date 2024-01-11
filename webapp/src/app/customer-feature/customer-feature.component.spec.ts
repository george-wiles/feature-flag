import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFeatureComponent } from './customer-feature.component';

describe('CustomerFeatureComponent', () => {
  let component: CustomerFeatureComponent;
  let fixture: ComponentFixture<CustomerFeatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerFeatureComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomerFeatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
