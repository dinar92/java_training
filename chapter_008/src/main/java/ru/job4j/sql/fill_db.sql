INSERT INTO roles (name) VALUES ('Junior');
INSERT INTO roles (name) VALUES ('Middle');
INSERT INTO roles (name) VALUES ('Tester');

INSERT INTO rules (name) VALUES ('Rule group1');
INSERT INTO rules (name) VALUES ('Rule group2');
INSERT INTO rules (name) VALUES ('Rule group3');

INSERT INTO role_rule (role_id, rule_id) VALUES  
((SELECT role_id FROM roles WHERE name='Junior'),
(SELECT rule_id FROM rules WHERE name='Rule group1'));
INSERT INTO role_rule (role_id, rule_id) VALUES  
((SELECT role_id FROM roles WHERE name='Junior'),
(SELECT rule_id FROM rules WHERE name='Rule group2'));
INSERT INTO role_rule (role_id, rule_id) VALUES  
((SELECT role_id FROM roles WHERE name='Tester'),
(SELECT rule_id FROM rules WHERE name='Rule group1'));
INSERT INTO role_rule (role_id, rule_id) VALUES  
((SELECT role_id FROM roles WHERE name='Middle'),
(SELECT rule_id FROM rules WHERE name='Rule group2'));
INSERT INTO role_rule (role_id, rule_id) VALUES  
((SELECT role_id FROM roles WHERE name='Middle'),
(SELECT rule_id FROM rules WHERE name='Rule group3'));

INSERT INTO users (name, role_id) VALUES (
'Joe',
(SELECT role_id FROM roles WHERE name='Junior'));
INSERT INTO users (name, role_id) VALUES (
'Amy',
(SELECT role_id FROM roles WHERE name='Middle'));
INSERT INTO users (name, role_id) VALUES (
'Bob',
(SELECT role_id FROM roles WHERE name='Tester'));

INSERT INTO categories (name) VALUES ('Technical');
INSERT INTO categories (name) VALUES ('Corporate');
INSERT INTO categories (name) VALUES ('Software');

INSERT INTO states (name) VALUES ('In line for review');
INSERT INTO states (name) VALUES ('In processing');
INSERT INTO states (name) VALUES ('Completed');

INSERT INTO items (name, category_id, state_id, user_id) VALUES (
 'Computer does not turn on',
 (SELECT category_id FROM categories WHERE name='Technical'),
 (SELECT state_id FROM states WHERE name='In line for review'),
 (SELECT user_id FROM users WHERE name='Amy'));
INSERT INTO items (name, category_id, state_id, user_id) VALUES (
	'I deserve to be promoted',
	(SELECT category_id FROM categories WHERE name='Corporate'),
	(SELECT state_id FROM states WHERE name='In line for review'),
	(SELECT user_id FROM users WHERE name='Joe'));

INSERT INTO comments (comment, item_id) VALUES (
'Try to turn on the power',
(SELECT item_id FROM items WHERE name='Computer does not turn on'));
INSERT INTO comments (comment, item_id) VALUES (
'You deserved it',
(SELECT item_id FROM items WHERE name='I deserve to be promoted'));

INSERT INTO attachment (data, item_id) VALUES (
'424D38400000000000003600000028000000',
(SELECT item_id FROM items WHERE name='Computer does not turn on'));
INSERT INTO attachment (data, item_id) VALUES (
'AAFD38400000000000003600000028000000',
(SELECT item_id FROM items WHERE name='Computer does not turn on'));
