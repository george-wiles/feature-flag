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
