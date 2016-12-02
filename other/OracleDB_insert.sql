CREATE TABLE objects_types
( object_type_id INTEGER NOT NULL,
  parent_id INTEGER,
  name varchar2(50),
  description varchar2(50),
  CONSTRAINT objects_types_pk PRIMARY KEY (object_type_id),
  constraint fk_objects_types foreign key(parent_id) references objects_types(object_type_id)
);

CREATE TABLE objects
( object_id INTEGER NOT NULL,
  parent_id INTEGER,
  object_type_id integer,
  name varchar2(50),
  description varchar2(50),
  CONSTRAINT objects_pk PRIMARY KEY (object_id),
  constraint fk_objects foreign key(parent_id) references objects(object_id),
  constraint fk_objects_types2 foreign key(object_type_id) references objects_types(object_type_id)
);

CREATE TABLE Attribute_types
( attribute_type_id INTEGER NOT NULL,
  name varchar2(50),
  CONSTRAINT Attribute_types_pk PRIMARY KEY (attribute_type_id)
);

CREATE TABLE Attributes
( attribute_id INTEGER NOT NULL,
  object_type_id INTEGER,
  attribute_type_id INTEGER,
  name varchar2(50),
  CONSTRAINT attributes_pk PRIMARY KEY (attribute_id),
  constraint fk_objects_types3 foreign key(object_type_id) references objects_types(object_type_id),
  constraint fk_attribute_types foreign key(attribute_type_id) references Attribute_types(attribute_type_id)
);

CREATE TABLE Params
( object_id INTEGER,
  attribute_id INTEGER,
  text_value varchar2(100),
  number_value integer,
  constraint fk_objects2 foreign key(object_id) references objects(object_id),
  constraint fk_attributes foreign key(attribute_id) references Attributes(attribute_id)
);

INSERT ALL
  INTO objects_types (object_type_id, parent_id,name,description) VALUES (1,null,'container','main class')
  INTO objects_types (object_type_id, parent_id,name,description) VALUES (11,1,'user','user class')
  INTO objects_types (object_type_id, parent_id,name,description) VALUES (12,1,'role','role class')
  
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (1, null, 11, 'admin', 'user admin') 
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (2, null, 11, 'manager', 'user manager')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (3, null, 11, 'developer', 'user developer')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (4, null, 11, 'QAengineer', 'user QAengineer')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (5, null, 11, 'designer', 'user designer')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (6, null, 12, 'role1', 'role role1')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (7, null, 12, 'role2', 'role role2')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (8, null, 12, 'role3', 'role role3')
  INTO objects (object_id, parent_id, object_type_id, name, description) VALUES (9, null, 12, 'role4', 'role role4')
  
  INTO Attribute_types (attribute_type_id, name) VALUES (1, 'personal')
  INTO Attribute_types (attribute_type_id, name) VALUES (2, 'action')
  
  INTO Attributes (attribute_id, object_type_id, attribute_type_id, name) VALUES (1, 11, 1, 'name')
  INTO Attributes (attribute_id, object_type_id, attribute_type_id, name) VALUES (2, 11, 1, 'password')
  INTO Attributes (attribute_id, object_type_id, attribute_type_id, name) VALUES (3, 12, 2, 'value')
  
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (1, 1, 'Tom', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (1, 2, '123qw', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (2, 1, 'John', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (2, 2, 'well78', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (3, 1, 'Adam', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (3, 2, 'zxc', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (4, 1, 'Mark', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (4, 2, 'qwerty', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (5, 1, 'Jery', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (5, 2, 'rb', null)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (6, 3, null, 1)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (7, 3, null, 2)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (8, 3, null, 3)
  INTO Params (object_id, attribute_id, text_value, number_value) VALUES (9, 3, null, 4)
  
SELECT * FROM dual;
