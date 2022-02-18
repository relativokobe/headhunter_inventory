CREATE TABLE vehicle(id VARCHAR(100) PRIMARY KEY,
inventory_code VARCHAR(100) NOT NULL,
name VARCHAR(200) NOT NULL,
model VARCHAR(200) NOT NULL,
color VARCHAR(100) NOT NULL,
UNIQUE KEY inventory_code_unique(inventory_code)
);