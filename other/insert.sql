INSERT INTO public.objects_types(object_type_id, parent_id, name, description) VALUES
	(1,null,'container','main class'),
	(11,1,'user','user class'),
	(12,1,'role','role class');

INSERT INTO public.objects(object_id, parent_id, object_type_id, name, description) VALUES
	(1, null, 11, 'admin','user admin'),
	(2, null, 11, 'manager','user manager'),
	(3, null, 11, 'developer','user developer'),
	(4, null, 11, 'QAengineer','user QAengineer'),
	(5, null, 11, 'designer','user designer'),
	(6, null, 12, 'role1','role role1'),
	(7, null, 12, 'role2','role role2'),
	(8, null, 12, 'role3','role role3'),
	(9, null, 12, 'role4','role role4');
    
INSERT INTO public.attribute_types(attribute_type_id, name) VALUES
	(1, 'personal'),
	(2, 'action');

INSERT INTO public.attributes(attribute_id, object_type_id, attribute_type_id, name) VALUES
	(1, 11, 1, 'name'),
	(2, 11, 1, 'password'),
	(3, 12, 2, 'value');
    
INSERT INTO public.params(object_id, attribute_id, text_value, number_value) VALUES
	(1, 1, 'Tom', null),
	(1, 2, '123qw', null),
	(2, 1, 'John', null),
	(2, 2, 'well78', null),
	(3, 1, 'Adam', null),
	(3, 2, 'zxc', null),
	(4, 1, 'Mark', null),
	(4, 2, 'qwerty', null),
	(5, 1, 'Jery', null),
	(5, 2, 'rb', null),
	(6, 3, null, 1),
	(7, 3, null, 2),
	(8, 3, null, 3),
	(9, 3, null, 4);

	
