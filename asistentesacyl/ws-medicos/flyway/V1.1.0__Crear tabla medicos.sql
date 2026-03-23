CREATE TABLE MEDICOS
(
    DIN                      NUMERIC(9),
    NOMBRE                   VARCHAR(100),


    CONSTRAINT "PK_MEDICOS"                    PRIMARY KEY (DIN),
    CONSTRAINT "NN_MEDICOS.NOMBRE"             CHECK (NOMBRE IS NOT NULL )
);