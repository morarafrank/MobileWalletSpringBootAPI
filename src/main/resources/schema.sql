CREATE TABLE customers (
    pin VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
    customer_id VARCHAR(50) PRIMARY KEY
);

CREATE TABLE accounts (
    customer_id VARCHAR(50),
    account_no VARCHAR(50) PRIMARY KEY,
    balance DECIMAL(15, 2),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
CREATE TABLE IF NOT EXISTS transactions (
    transaction_id VARCHAR(50) PRIMARY KEY,
    customer_id VARCHAR(50),
    account_no VARCHAR(50),
    amount DECIMAL,
    transaction_type VARCHAR(50),
    debit_or_credit VARCHAR(50),
    balance DECIMAL
);
