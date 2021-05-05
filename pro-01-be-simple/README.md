
On FE code, we can just use data type Date for this field. Then it will be converted seamlessly.

__`OffsetDateTime` vs. `ZonedDateTime`:__
https://www.youtube.com/watch?v=nEQhx9hGutQ
-  In my opinion, using `OffsetDateTime` for RestAPI and DB will make it easier to maintain for client app.
   Actually, `OffsetDateTime` is recommended for PostgreSQL:
   https://stackoverflow.com/questions/58434148/what-is-better-to-persist-in-db-offsetdatetime-or-instant

-  Daylight Saving Time (DST) should be handled from client app by using ZoneId.

I also thought about `Instance` which represent a moment in UTC: https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime
However, OffsetDateTime is good enough to represent UTC while also support more functions.
So I'm more lean to using OffsetDateTime.
