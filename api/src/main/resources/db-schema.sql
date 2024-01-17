drop table customer_feature;
drop table customer;
drop table feature;

CREATE TABLE FEATURE (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DISPLAY_NAME VARCHAR(250),
    TECHNICAL_NAME VARCHAR(250) NOT NULL,
    DESCRIPTION VARCHAR(250)
);

CREATE TABLE CUSTOMER (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(250)
);

CREATE TABLE CUSTOMER_FEATURE (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(250),
    FEATURE_ID BIGINT,
    CUSTOMER_ID BIGINT,
    IS_ACTIVE BOOLEAN,
    IS_INVERTED BOOLEAN,
    EXPIRATION_DATE TIMESTAMP,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (ID),
    FOREIGN KEY (FEATURE_ID) REFERENCES FEATURE (ID)
);

INSERT INTO FEATURE (DISPLAY_NAME, TECHNICAL_NAME, DESCRIPTION)
VALUES ('my-feature-a', 'my.feature.a', 'feature a');

INSERT INTO FEATURE (DISPLAY_NAME, TECHNICAL_NAME, DESCRIPTION)
VALUES ('my-feature-b', 'my.feature.b', 'feature b');

INSERT INTO FEATURE (DISPLAY_NAME, TECHNICAL_NAME, DESCRIPTION)
VALUES ('my-feature-c', 'my.feature.c', 'feature c');

INSERT INTO FEATURE (DISPLAY_NAME, TECHNICAL_NAME, DESCRIPTION)
VALUES ('my-feature-d', 'my.feature.d', 'feature d');

INSERT INTO CUSTOMER (NAME)
VALUES ('george wiles');

INSERT INTO CUSTOMER (NAME)
VALUES ('jane brown');

-- contains hypersonic specific date syntax
INSERT INTO CUSTOMER_FEATURE (EXPIRATION_DATE, IS_ACTIVE, IS_INVERTED, CUSTOMER_ID, FEATURE_ID)
VALUES (DATEADD('MONTH', 1, CURRENT_TIMESTAMP), TRUE, TRUE, 1, 1);

INSERT INTO CUSTOMER_FEATURE (EXPIRATION_DATE, IS_ACTIVE, IS_INVERTED, CUSTOMER_ID, FEATURE_ID)
VALUES (DATEADD('MONTH', 2, CURRENT_TIMESTAMP), TRUE, FALSE, 1, 2);

INSERT INTO CUSTOMER_FEATURE (EXPIRATION_DATE, IS_ACTIVE, IS_INVERTED, CUSTOMER_ID, FEATURE_ID)
VALUES (DATEADD('MONTH', 3, CURRENT_TIMESTAMP), TRUE, TRUE, 1, 3);

-- expires this customer feature
INSERT INTO CUSTOMER_FEATURE (EXPIRATION_DATE, IS_ACTIVE, IS_INVERTED, CUSTOMER_ID, FEATURE_ID)
VALUES (DATEADD('MONTH', -3, CURRENT_TIMESTAMP), FALSE, TRUE, 1, 4);

INSERT INTO CUSTOMER_FEATURE (EXPIRATION_DATE, IS_ACTIVE, IS_INVERTED, CUSTOMER_ID, FEATURE_ID)
VALUES (DATEADD('MONTH', 1, CURRENT_TIMESTAMP), TRUE, TRUE, 2, 1);

INSERT INTO CUSTOMER_FEATURE (EXPIRATION_DATE, IS_ACTIVE, IS_INVERTED, CUSTOMER_ID, FEATURE_ID)
VALUES (DATEADD('MONTH', 2, CURRENT_TIMESTAMP), TRUE, FALSE, 2, 2);
