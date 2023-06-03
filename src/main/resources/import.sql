INSERT INTO employee (name, last_name, dni, long_goal, short_goal, coach_id) VALUES ('Jose', 'Gonzalez', '34809457', 'Quiero aprender mas java', 'Quiero ser ssr', 1 );
INSERT INTO employee (name, last_name, dni, long_goal, short_goal, coach_id) VALUES ('Daniela', 'Perez', '34502489', 'Quiero ser Sr', 'Quiero ser Jr', 2);
INSERT INTO employee (name, last_name, dni, long_goal, short_goal, coach_id) VALUES ('Marta', 'Sanchez', '41523556', 'Quiero trabajar en data', 'Quiero ser trainee', 3);
INSERT INTO assigner (id, dni, name, lastname, short_goal, long_goal) VALUES (1, '40789632', 'Karen', 'Cofone', 'Aprender Java', 'Ser team leader');
INSERT INTO assigner (id, dni, name, lastname, short_goal, long_goal) VALUES (2, '32548147', 'Camila', 'González', 'Aprender Angular', 'Ser Java SR');
INSERT INTO assigner (id, dni, name, lastname, short_goal, long_goal) VALUES (3, '35896321', 'Gonzalo', 'Gimenez', 'Aprender NodeJS', 'Ser Operations Leader');
INSERT INTO assigner (id, dni, name, lastname, short_goal, long_goal) VALUES (4, '30164845', 'Linda', 'Paz', 'Trabajar en Data Analitics', 'Ser CEO de ITR');
INSERT INTO opportunity (id, code, opportunity_name, description) VALUES (1, 'JAVA1', 'Java Developer Jr', 'Manejo de Spring Boot y JPA');
INSERT INTO skill (id, skill_code, name, seniority) VALUES (1, '0001', 'Java', 'Jr');
INSERT INTO skill (id, skill_code, name, seniority) VALUES (2, '0002', 'Python', 'SSr');
INSERT INTO coach (id, dni, name, lastname, short_goal, long_goal, role) VALUES (1, '23405678', 'Juan', 'Perez', 'Aprender Comunicacion Asertiva', 'Ser coach Ssr', 'soy HRBP');
INSERT INTO coach (id, dni, name, lastname, short_goal, long_goal, role) VALUES (2, '26456123', 'Mariana', 'Carlone', 'Reuniones', 'Ser Team Lead', 'soy HRBP');
INSERT INTO role(id, description, name, seniority, department_id) VALUES (1, 'developer en java', 'Dev', 'trinee', 1);
INSERT INTO role(id, description, name, seniority, department_id) VALUES (1, 'tech lead en proyectos de banco chaco', 'tech lead', 'Sr', 2);
INSERT INTO resource(id, description, name, url) VALUES (1, 'curso de introduccion a java gratuito', 'Java', 'https://open-bootcamp.com/aprender-programar/que-es-java');
INSERT INTO resource(id, description, name, url) VALUES (1, 'curso gratuito SQL', 'SQL', 'https://www.udemy.com/courses/search/?src=ukw&q=sql');
INSERT INTO department(id, name) VALUES (1, 'Operations');
INSERT INTO department(id, name) VALUES (2, 'Factory');
INSERT INTO department(id, name) VALUES (3, 'Sales & Marketing');