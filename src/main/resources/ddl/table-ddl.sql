-- table definition
CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT,
    writer VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
