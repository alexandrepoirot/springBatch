SELECT kcqd8_icagenda_events.id as idevent,
kcqd8_icagenda_events.title as titleEvent,
kcqd8_icagenda_events.alias,
kcqd8_icagenda_events.created,
kcqd8_icagenda_events.username,
kcqd8_icagenda_events.created_by_alias, 
kcqd8_icagenda_events.catid as categoryid, 
kcqd8_icagenda_events.startdate,
kcqd8_icagenda_events.enddate,
kcqd8_icagenda_events.period,
kcqd8_icagenda_events.dates,
kcqd8_icagenda_events.next,
kcqd8_icagenda_events.place, 
kcqd8_icagenda_events.city,
kcqd8_icagenda_events.country,
kcqd8_icagenda_events.address,
kcqd8_icagenda_events.lat,
kcqd8_icagenda_events.lng,
kcqd8_icagenda_events.shortdesc,
kcqd8_icagenda_events.desc, 
kcqd8_icagenda_events.version_customfields, 
kcqd8_icagenda_category.title as categoryname
FROM kcqd8_icagenda_events,kcqd8_icagenda_category
WHERE kcqd8_icagenda_events.catid in (1,2,3,4,6,10,11,12,13) 
and kcqd8_icagenda_events.catid = kcqd8_icagenda_category.id
order BY kcqd8_icagenda_events.next DESC;