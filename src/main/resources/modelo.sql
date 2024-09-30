CREATE TABLE usuarios
(
    id_usuario   SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE empenhos
(
    id_empenho      SERIAL PRIMARY KEY,
    numeroEmpenho   VARCHAR(255)   NOT NULL,
    data_empenho    DATE           NOT NULL,
    data_vencimento DATE           NOT NULL,
    valor_empenho   DECIMAL(10, 2) NOT NULL,
    id_usuario      INT            NOT NULL,
    CONSTRAINT fk_usuario
        FOREIGN KEY (id_usuario)
            REFERENCES usuarios (id_usuario)
            ON DELETE CASCADE
);

CREATE TABLE liquidacoes
(
    id_liquidacao     SERIAL PRIMARY KEY,
    numero_liquidacao VARCHAR(255)   NOT NULL,
    data_liquidacao   DATE           NOT NULL,
    id_empenho        INT            NOT NULL,
    valor_liquidacao  DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_empenho
        FOREIGN KEY (id_empenho)
            REFERENCES empenhos (id_empenho)
            ON DELETE CASCADE
);

CREATE TABLE pagamentos
(
    id_pagamento     SERIAL PRIMARY KEY,
    numero_pagamento VARCHAR(255)   NOT NULL,
    data_pagamento   DATE           NOT NULL,
    valor_pagamento  DECIMAL(10, 2) NOT NULL,
    tipo_pagamento   VARCHAR(255)   NOT NULL,
    status_pagamento Enum('PAGO', 'PENDENTE', 'CANCELADO') NOT NULL,
    observacao       VARCHAR(255)   NOT NULL,
    data_vencimento  DATE           NOT NULL,
    id_liquidacao    INT            NOT NULL,
    CONSTRAINT fk_liquidacao
        FOREIGN KEY (id_liquidacao)
            REFERENCES liquidacoes (id_liquidacao)
            ON DELETE CASCADE
);

