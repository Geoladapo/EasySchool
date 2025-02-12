CREATE TABLE IF NOT EXISTS contact_msg (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    mobile_num VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    message VARCHAR(500) NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `holidays` (
    day varchar(20) NOT NULL,
    reason varchar(100) NOT NULL,
    type varchar(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by varchar(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by varchar(50) DEFAULT NULL
);