CREATE TABLE Person (
                        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        surname varchar(30) NOT NULL,
                        name varchar(30) NOT NULL,
                        birthday DATE
);

CREATE TABLE Account (
                         id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         card_number varchar(16) NOT NULL UNIQUE,
                         balance bigint,
                         isSalary bool,
                         person_id int REFERENCES Person(id) ON DELETE SET NULL
);