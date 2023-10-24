INSERT INTO inventory (id, stock)
SELECT generate_series(1, 10), floor(random() * 100)::int;