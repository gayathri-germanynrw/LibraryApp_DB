select count(*) from books;

select count(*) from users;

select * from book_borrow
where is_returned = 0;

select name from book_categories;

select name,isbn,year,author,description from books
where name = 'Agile Testing';

select full_name from users
where email = 'librarian55@library';

select status from users where email='anisa.stokes@gmail.com';

-- Live Session
-- 1 - Admin
-- 2 - Librarian
-- 3 - Student

-- UI - 1763
-- DB - 1769

select count(*) from users
where status='ACTIVE' and user_group_id<>1 ;

-- How many book we have in certain category
select count(*)
from books b
         inner join book_categories bc on bc.id=b.book_category_id
where bc.name='Anthology';

-- How many book we have in each category name ?
select bc.name,count(*)
from books b
         inner join book_categories bc on bc.id=b.book_category_id
group by bc.name;

-- Get me category name which have highest book number ?
select bc.name,count(*) as book_count
from books b
         inner join book_categories bc on bc.id=b.book_category_id
group by bc.name
order by book_count desc
limit 1;