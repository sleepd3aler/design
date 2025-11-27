
create table artists_tracks
(
    id        serial primary key,
    artist_id int references artists (id),
    track_id  int references tracks (id)
);