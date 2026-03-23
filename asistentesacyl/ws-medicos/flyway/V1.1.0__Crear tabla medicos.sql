CREATE TABLE MEDICOS
(
    ID                      VARCHAR(15),
    NOMBRE                  VARCHAR(100),


    CONSTRAINT "PK_MEDICOS"                    PRIMARY KEY (ID),
    CONSTRAINT "NN_MEDICOS.NOMBRE"             CHECK (NOMBRE IS NOT NULL )
);