--<ScriptOptions statementTerminator=";"/>

ALTER TABLE RATINGS DROP PRIMARY KEY;

ALTER TABLE Tag DROP INDEX FK_Tag_Books_BOOKID;

DROP TABLE SEQUENCE;

DROP TABLE Tag;

DROP TABLE SHIPPINGTYPE;

DROP TABLE SHOPPINGCART;

DROP TABLE ORDERDETAIL;

DROP TABLE USERDETAILS;

DROP TABLE BOOKS;

DROP TABLE LOGIN;

DROP TABLE RATINGS;

DROP TABLE BOOKINVENTORY;

DROP TABLE CREDITCARDINFO;

