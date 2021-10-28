insert into anotherentity(external_pk)
values ('another_entity1'),
  ('another_entity2'),
  ('another_entity3'),
  ('another_entity4'),
  ('another_entity5'),
  ('another_entity6'),
  ('another_entity7'),
  ('another_entity8');
insert into myentity(foo, bar, my_foreign_key)
values ('foo1', 'bar1', 'another_entity1'),
  ('foo2', 'bar2', 'another_entity2'),
  ('foo3', 'bar3', 'another_entity3'),
  ('foo4', 'bar4', 'another_entity4');
insert into myentity_embeddedid(foo, bar, my_foreign_key)
values ('foo1', 'bar1', 'another_entity5'),
  ('foo2', 'bar2', 'another_entity6'),
  ('foo3', 'bar3', 'another_entity7'),
  ('foo4', 'bar4', 'another_entity8');