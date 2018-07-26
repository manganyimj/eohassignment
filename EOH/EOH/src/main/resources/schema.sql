CREATE schema EOH;
--
CREATE TABLE eoh.lineitem (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    description varchar(255) NOT NULL,
    quantity bigint(20) NOT NULL,
    unit_price decimal(5,2) NOT NULL,
    PRIMARY KEY (id)
);
--
INSERT INTO eoh.lineitem (id, description, quantity, unit_price) VALUES (1, 'Tiles', 10, 30.00);
INSERT INTO eoh.lineitem (id, description, quantity, unit_price) VALUES (2, 'Cement', 5, 25.50);
INSERT INTO eoh.lineitem (id, description, quantity, unit_price) VALUES (3, 'Pin Nails', 3, 11.00);
----
CREATE TABLE eoh.invoice (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    client varchar(255) NOT NULL,
    invoice_date date NOT NULL,
    vat_rate bigint(20) NOT NULL,
    line_item_id bigint(20) NOT NULL,
    FOREIGN KEY (line_item_id) REFERENCES lineitem(id),
    PRIMARY KEY (id)
);
--
INSERT INTO eoh.invoice (id, client, invoice_date, vat_rate, line_item_id) VALUES (1, 'John Manganyi', '2018-07-25', 15, 2);
INSERT INTO eoh.invoice (id, client, invoice_date, vat_rate, line_item_id) VALUES (2, 'Ncumisa Majodina', '2018-07-24', 15, 1);
INSERT INTO eoh.invoice (id, client, invoice_date, vat_rate, line_item_id) VALUES (3, 'Siyabonga Ntuli', '2018-07-23', 15, 3);
