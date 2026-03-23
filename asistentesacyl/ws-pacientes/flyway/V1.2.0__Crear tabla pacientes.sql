CREATE TABLE PACIENTES
(
    ID                      VARCHAR(15),
    NOMBRE                  VARCHAR(100),


    CONSTRAINT "PK_PACIENTES"                    PRIMARY KEY (ID),
    CONSTRAINT "NN_PACIENTES.NOMBRE"             CHECK (NOMBRE IS NOT NULL )
);