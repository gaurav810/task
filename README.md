# Parcel Tracking Service

This Spring Boot application provides APIs to create and manage parcel tracking information with a unique 16-character alphanumeric tracking number.

---

## Tech Stack

- Java 17
- Spring Boot
- Maven
- PostgreSQL

---

## How to Start the Application

```
mvn clean install -DskipTests

mvn spring-boot:run
```
---
## Table schema

```
CREATE TABLE IF NOT EXISTS public.tracking_number
(
    id integer NOT NULL DEFAULT nextval('tracking_number_id_seq'::regclass),
    origin_country_id character varying(2) COLLATE pg_catalog."default" NOT NULL,
    destination_country_id character varying(2) COLLATE pg_catalog."default" NOT NULL,
    weight numeric(10,3) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    customer_id text COLLATE pg_catalog."default" NOT NULL,
    customer_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    customer_slug character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tracking_number character varying(16) COLLATE pg_catalog."default" NOT NULL,
    generated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT tracking_number_pkey PRIMARY KEY (id),
    CONSTRAINT tracking_number_tracking_number_key UNIQUE (tracking_number)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tracking_number
    OWNER to postgres;
```
---
## API Details

#### API End Point:

````
http://localhost:8080/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2025-07-20T19:29:32%2B08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics
````
#### Method:
````
GET
````
#### Success Response:
````
{
    "code": 200,
    "success": true,
    "message": "Tracking number generated.",
    "data": {
        "tracking_number": "MDCLGSHT0V97E3MC",
        "created_at": "2025-07-21T09:47:09+05:30"
    }
}
````
#### Failed Response:
````
{
    "code": 400,
    "success": false,
    "message": "origin_country_id: must not be blank"
}
````

---
## Token Generation Logic

#### The application automatically generates a unique 16-character tracking number every time a new tracking request is created.

- The tracking number consists of uppercase letters and numbers.

- It is created using the current timestamp and random characters.

- This ensures that each tracking number is unique and not predictable.

- The tracking number is stored in the database along with other tracking details like origin, destination, customer, etc.