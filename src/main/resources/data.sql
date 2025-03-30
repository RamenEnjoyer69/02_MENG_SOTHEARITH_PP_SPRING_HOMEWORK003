INSERT INTO attendees (attendee_name, email) VALUES
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Smith', 'bob.smith@example.com'),
('Charlie Davis', 'charlie.davis@example.com'),
('Diana Roberts', 'diana.roberts@example.com'),
('Camilla Wilson', 'camilla.wilson@example.com');


INSERT INTO venues (venue_name, location) VALUES
('Grand Hall', 'New York, NY'),
('Ocean View Center', 'Los Angeles, CA'),
('Mountain Retreat', 'Denver, CO'),
('Sunset Pavilion', 'Miami, FL'),
('Skyline Ballroom', 'Chicago, IL');


INSERT INTO events (event_name, event_date, venue_id) VALUES
('Tech Conference 2025', '2025-06-15 09:00:00+00', 1),
('Music Festival', '2025-07-20 18:30:00+00', 2),
('Business Summit', '2025-09-10 10:00:00+00', 3),
('Gaming Expo', '2025-08-05 12:00:00+00', 4),
('Art Exhibition', '2025-10-01 14:00:00+00', 5);

INSERT INTO event_attendee (event_id, attendee_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5);
