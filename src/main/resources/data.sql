insert into anotherentity(external_pk)
values ('another_entity1'),
  ('another_entity2'),
  ('another_entity3'),
  ('another_entity4'),
  ('another_entity5');
insert into myentity(foo, bar, my_foreign_key)
values ('foo1', 'bar1', 'another_entity1'),
  ('foo2', 'bar2', 'another_entity2'),
  ('foo3', 'bar3', 'another_entity3'),
  ('foo4', 'bar4', 'another_entity4');