SELECT t.model AS transmission, e.model AS engine, g.model AS gear_box FROM car AS c 
FULL OUTER JOIN transmission AS t ON (c.transmission_id = t.id) 
FULL OUTER JOIN engine AS e ON (c.engine_id = e.id)
FULL OUTER JOIN gear_box AS g ON (c.gear_box_id = g.id) WHERE c.model IS NULL;
