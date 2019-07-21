DROP TABLE order_info IF EXISTS;

CREATE TABLE order_info  (
    order_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    txn_id VARCHAR(64),
    amount VARCHAR(64),
    status VARCHAR(20),
    ip_address VARCHAR(20)
);