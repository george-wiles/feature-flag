# Overview

![](./images/AircraftRefuellingPuzzle.png)

# General Approach

# 1. App Java based REST API
Location: api

# How to Run
T
## Pre-requistes
1. Built and tested with:
   1.1. Oracle OpenJDK version 18 \
   1.2. Gradle

Running test

1. Run `mvn clean install`
2. This will run the 3 tests described above
```
F|E|P|E|F|E|F
P|E|F|E|P|E|P
E|E|E|E|E|F|P
E|F|E|E|E|E|E
E|P|E|E|E|E|E
F|E|E|P|E|E|F
P|E|E|F|E|E|P
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.421 s - in nz.org.wiles.klm.puzzle.service.impl.AirportLayoutServiceImpTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

# Java springboot
This initial revision is written in Java with Spring-boot.


# 2. WebApp Angular consumes app REST API
Location: webapp

To run locally assumes:
* Angular cli installed
    * `cd webapp`
        * `npm install`
        * `npm run start`
* Java spring-boot
    * `cd app`
    * `mvn clean install`
    * `mvn sprint-boot:run`






