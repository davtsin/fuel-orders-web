CREATE TABLE COMPANY (
    ID BIGINT NOT NULL, 
    NAME VARCHAR(255), 
    PRIMARY KEY (ID)
)

CREATE TABLE filling_station (
    ID BIGINT NOT NULL, 
    ADDRESS VARCHAR(255), 
    CODE VARCHAR(255), 
    company_fk BIGINT, 
    PRIMARY KEY (ID)
)

CREATE TABLE fuel_order (
    ID BIGINT NOT NULL, 
    amount_liters FLOAT, 
    cost_per_liter FLOAT, 
    creation_date DATE, 
    DISCOUNT FLOAT, 
    fuel_type VARCHAR(255), 
    total_cost FLOAT, 
    filling_station_fk BIGINT, 
    PRIMARY KEY (ID)
)

ALTER TABLE filling_station 
    ADD CONSTRAINT fllngsttioncmpnyfk 
    FOREIGN KEY (company_fk) 
    REFERENCES COMPANY (ID)
	
ALTER TABLE fuel_order 
    ADD CONSTRAINT flrdrfllngsttionfk 
    FOREIGN KEY (filling_station_fk) 
    REFERENCES filling_station (ID)
	
CREATE TABLE SEQUENCE (
    SEQ_NAME VARCHAR(50) NOT NULL, 
    SEQ_COUNT DECIMAL(15), 
    PRIMARY KEY (SEQ_NAME)
)

INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)