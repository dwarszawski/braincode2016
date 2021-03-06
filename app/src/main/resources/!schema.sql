DROP TABLE OFFERS;

CREATE TABLE OFFERS (
  ID                 BIGINT NOT NULL,
  VERSION            INTEGER,
  MAKE               VARCHAR(100),
  MODEL              VARCHAR(100),
  MODEL2             VARCHAR(100),
  YEAR_OF_PRODUCTION INTEGER,
  POWER              VARCHAR(50),
  FUEL_TYPE          VARCHAR(50),
  PROVINCE           VARCHAR(50),
  MILEAGE            INTEGER,
  PRICE              INTEGER,
  END_DATE           DATE
);