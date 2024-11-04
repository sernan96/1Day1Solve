-- 코드를 입력하세요
SELECT u.user_id, u.nickname, sum(b.price)
from used_goods_board b, used_goods_user u
where b.writer_id= u.user_id
and status ='DONE'
group by u.user_id, u.nickname
having 700000<=sum(b.price)
order by sum(b.price);