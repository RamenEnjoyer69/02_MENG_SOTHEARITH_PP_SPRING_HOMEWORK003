CREATE TABLE IF NOT EXISTS attendees (
    attendee_id SERIAL PRIMARY KEY,
    attendee_name varchar(255) not null,
    email varchar(255) not null
);

CREATE TABLE IF NOT EXISTS venues (
    venue_id SERIAL PRIMARY KEY,
    venue_name varchar(255) not null,
    location varchar(255) not null
);

CREATE TABLE IF NOT EXISTS events (
    event_id SERIAL PRIMARY KEY,
    event_name varchar(255) not null,
    event_date TIMESTAMPTZ,
    venue_id INT not null,
    CONSTRAINT fk_venue FOREIGN KEY (venue_id) references venues (venue_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS event_attendee (
    id SERIAL PRIMARY KEY,
    event_id INT not null,
    attendee_id INT not null,
    CONSTRAINT fk_event FOREIGN KEY (event_id) references events (event_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_attendee FOREIGN KEY (attendee_id) references attendees (attendee_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT unique_event_attendee UNIQUE (event_id, attendee_id)
);