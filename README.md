# Overview

![](./Swisscom-Homne-Assignment.pdf)

# General Approach

# 1. App Java based REST API

Location: api

# How to Run

## Pre-requistes

1. Built and tested with:
   1.1. Oracle OpenJDK version 18 with Spring-boot and lombok
   1.2. Gradle
   1.3. Does not use spring profiles for dev/production configuration, current configuraiton uses an in memory H2 RDS
   1.4. Some data is populated by resources/data.sql
   1.5. Uses some Java constructs requiring JDK <15> i.e. record for immutable data containers
   1.6. APIS exist to add/query Customer, Feature and CustomerFeature
   1.7. Archiving features has not been implemented due to time constraints.

# Java springboot

This initial revision is written in Java with Spring-boot.

# 2. WebApp Angular consumes app REST API

Location: webapp

To run locally assumes:

- Angular cli installed
  - `cd webapp`
    - `npm install`
    - `npm run start`
- Java spring-boot
  - `cd app`
  - `./gradlew bootRun` 


Future revisions

#1 Archiving: Soft delete archiving of customer_feature

```

@Entity
@Table(name = "ARCHIVED_CUSTOMER_FEATURE")
public class ArchivedCustomerFeature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "FEATURE_ID")
    private Long featureId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "IS_INVERTED")
    private boolean isInverted;

    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
}

```

introduced an archived flag in CustomerFeature

## issues: db partitioning for archived data

#2 Archiving: warehousing of customer_feature data via archive

## Move feature to archive to warehouse db and remove from application database

# issues: if business want to view / restore archived data 
may not be best solution as it would then couple the feature service with warehouses etc.