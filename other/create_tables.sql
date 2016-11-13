CREATE TABLE Objects (
	object_id serial NOT NULL,
	parent_id integer,
	object_type_id integer NOT NULL,
	name varchar NOT NULL,
	description varchar NOT NULL,
	CONSTRAINT Objects_pk PRIMARY KEY (object_id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Params (
	object_id integer NOT NULL,
	attribute_id integer NOT NULL,
	text_value varchar,
	number_value integer 
) WITH (
  OIDS=FALSE
);



CREATE TABLE Attribute_types (
	attribute_type_id serial NOT NULL,
	name varchar NOT NULL,
	CONSTRAINT Attribute_types_pk PRIMARY KEY (attribute_type_id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Attributes (
	attribute_id serial NOT NULL,
	object_type_id integer NOT NULL,
	attribute_type_id integer NOT NULL,
	name varchar NOT NULL,
	CONSTRAINT Attributes_pk PRIMARY KEY (attribute_id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Objects_types (
	object_type_id serial NOT NULL,
	parent_id integer,
	name varchar NOT NULL,
	description varchar,
	CONSTRAINT Objects_types_pk PRIMARY KEY (object_type_id)
) WITH (
  OIDS=FALSE
);



ALTER TABLE Objects ADD CONSTRAINT Objects_fk0 FOREIGN KEY (parent_id) REFERENCES Objects(object_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE Objects ADD CONSTRAINT Objects_fk1 FOREIGN KEY (object_type_id) REFERENCES Objects_types(object_type_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Params ADD CONSTRAINT Params_fk0 FOREIGN KEY (object_id) REFERENCES Objects(object_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE Params ADD CONSTRAINT Params_fk1 FOREIGN KEY (attribute_id) REFERENCES Attributes(attribute_id) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE Attributes ADD CONSTRAINT Attributes_fk0 FOREIGN KEY (object_type_id) REFERENCES Objects_types(object_type_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE Attributes ADD CONSTRAINT Attributes_fk1 FOREIGN KEY (attribute_type_id) REFERENCES Attribute_types(attribute_type_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Objects_types ADD CONSTRAINT Objects_types_fk0 FOREIGN KEY (parent_id) REFERENCES Objects_types(object_type_id)ON UPDATE CASCADE ON DELETE CASCADE;


