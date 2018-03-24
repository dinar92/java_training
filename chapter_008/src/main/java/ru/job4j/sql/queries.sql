1)SELECT * FROM product WHERE type_id = (SELECT id FROM type WHERE name='cheese');

2)SELECT * FROM product WHERE name LIKE '%мороженное%';

3)SELECT * FROM product WHERE EXTRACT(MONTH FROM expired_date)  = EXTRACT(MONTH FROM current_date) + 1;

4)SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product);

5)SELECT COUNT(*) FROM product WHERE type_id = (
SELECT id FROM type WHERE name = 'cheese');

6)SELECT * FROM product WHERE type_id IN (
SELECT id FROM type WHERE name IN ('cheese', 'milk'));

7)SELECT name FROM type WHERE id IN (SELECT type_id  FROM product GROUP BY type_id HAVING COUNT(type_id) < 10); 

8.1)SELECT product.*, type.name AS type FROM product INNER JOIN type ON (product.type_id = type.id);
8.2)SELECT product.*, type.name AS type FROM product, type WHERE product.type_id = type.id;
