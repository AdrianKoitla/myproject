-- Created by Redgate Data Modeler (https://datamodeler.redgate-platform.com)
-- Last modification date: 2025-12-05 22:59:16.209

-- tables
-- Table: customer
CREATE TABLE customer (
                          id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                          first_name varchar(50)  NOT NULL,
                          last_name varchar(50)  NOT NULL,
                          phone varchar(20)  NULL,
                          CONSTRAINT customer_pk PRIMARY KEY (id)
);

-- Table: product
CREATE TABLE product (
                         id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                         product_type_id int  NOT NULL,
                         name varchar(100)  NOT NULL,
                         size varchar(10)  NOT NULL,
                         color varchar(100)  NOT NULL,
                         CONSTRAINT product_pk PRIMARY KEY (id)
);

-- Table: product_type
CREATE TABLE product_type (
                              id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                              type_name varchar(50)  NOT NULL,
                              CONSTRAINT product_type_ak_1 UNIQUE (type_name),
                              CONSTRAINT product_type_pk PRIMARY KEY (id)
);

-- Table: sale
CREATE TABLE sale (
                      id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                      product_id int  NOT NULL,
                      customer_id int  NOT NULL,
                      sale_date date  NOT NULL,
                      sale_price decimal(10,2)  NOT NULL,
                      CONSTRAINT sale_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: product_product_type (table: product)
ALTER TABLE product ADD CONSTRAINT product_product_type
    FOREIGN KEY (product_type_id)
        REFERENCES product_type (id);

-- Reference: sale_customer (table: sale)
ALTER TABLE sale ADD CONSTRAINT sale_customer
    FOREIGN KEY (customer_id)
        REFERENCES customer (id);

-- Reference: sale_product (table: sale)
ALTER TABLE sale ADD CONSTRAINT sale_product
    FOREIGN KEY (product_id)
        REFERENCES product (id);

-- End of file.

