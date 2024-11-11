select b.category, sum(s.sales)
from book b, book_sales s
where b.book_id = s.book_id
and sales_date between to_date('2022-01-01', 'yyyy-mm-dd') and to_date('2022-01-31', 'yyyy-mm-dd')
group by b.category
order by b.category asc;