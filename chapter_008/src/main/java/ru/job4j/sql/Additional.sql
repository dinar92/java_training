SELECT p.name, c.name 
FROM person p INNER JOIN company c 
ON p.company_id=c.id 
WHERE c.id<>5;


WITH company_persons (com_name, count_pers) AS (
SELECT company.name AS com_name, COUNT(person.name) AS count_pers 
    FROM person INNER JOIN company 
    ON person.company_id = company.id 
    GROUP BY company.name)
SELECT com_name, count_pers 
FROM 
    company_persons 
WHERE count_pers = 
    (SELECT MAX(count_pers) 
    FROM 
        company_persons);
