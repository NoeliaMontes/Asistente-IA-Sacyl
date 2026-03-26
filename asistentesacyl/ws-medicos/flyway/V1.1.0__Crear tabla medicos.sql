CREATE TABLE MEDICOS
(
    DIN                      NUMERIC(9),
    NOMBRE                   VARCHAR(100),
    TIPO                     VARCHAR(15),


    CONSTRAINT "PK_MEDICOS"                    PRIMARY KEY (DIN),
    CONSTRAINT "NN_MEDICOS.NOMBRE"             CHECK (NOMBRE IS NOT NULL ),
    CONSTRAINT "NN_MEDICOS.TIPO"               CHECK (NOMBRE IS NOT NULL )
);