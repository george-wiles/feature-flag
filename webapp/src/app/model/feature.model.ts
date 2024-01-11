export interface Feature {
  id: number;
  displayName: string;
  technicalName: string;
  description: string;
}

export interface CustomerFeature {
  name: string;
  active: boolean;
  inverted: boolean;
  expired: boolean;
}

export interface CustomerFeatureRequest {
  customerId: number;
  features: [
    { name: string }
  ]
}

export interface CustomerFeatureResponse {
  features: CustomerFeature[]
}
