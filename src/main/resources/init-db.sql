-- station definition
CREATE TABLE station
(
    id        int8         NOT NULL,
    capacity  int8         NULL,
    code      varchar(255) NOT NULL,
    latitude  float8       NULL,
    longitude float8       NULL,
    "name"    varchar(255) NOT NULL,
    CONSTRAINT station_pkey PRIMARY KEY (id)
);


-- station_state definition
CREATE TABLE station_state
(
    is_installed            bool         NULL,
    is_renting              bool         NULL,
    is_returning            bool         NULL,
    nb_docks_available      int8         NULL,
    nb_ebike_available      int8         NULL,
    nb_mechanical_available int8         NULL,
    state_date              timestamp(6) NOT NULL,
    station_id              int8         NOT NULL,
    state_timestamp         timestamp(6) NULL,
    id                      serial4      NOT NULL,
    CONSTRAINT station_state_pkey PRIMARY KEY (id)
);
