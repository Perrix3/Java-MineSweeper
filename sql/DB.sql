CREATE TABLE IF NOT EXISTS Users(
    Userid INT NOT NULL AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    Attempts INT NOT NULL,
    Wins INT NOT NULL,
    Losses INT NOT NULL,
    PRIMARY KEY (Userid)
); 
--Change tables, so i can have more
--User table, Score table, and idk one more