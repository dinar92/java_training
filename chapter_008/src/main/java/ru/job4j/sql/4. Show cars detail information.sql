SELECT c.model AS car, t.model AS transmission, e.model AS engine, g.model AS gear_box FROM car AS c 
INNER JOIN transmission AS t ON (c.transmission_id = t.id) 
INNER JOIN engine AS e ON (c.engine_id = e.id)
INNER JOIN gear_box AS g ON (c.gear_box_id = g.id);
