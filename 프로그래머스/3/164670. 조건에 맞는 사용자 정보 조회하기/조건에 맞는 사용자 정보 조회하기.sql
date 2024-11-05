select u.user_id, u.nickname, u.city||' '||u.street_address1||' '||u.street_address2, (SUBSTR(u.tlno, 1, 3) || '-' ||SUBSTR(u.tlno, 4, 4) || '-' ||SUBSTR(u.tlno, 8))
from used_goods_user u, used_goods_board b
where u.user_id = b.writer_id
group by (u.user_id, u.nickname, u.city||' '||u.street_address1||' '||u.street_address2, (SUBSTR(u.tlno, 1, 3) || '-' ||SUBSTR(u.tlno, 4, 4) || '-' ||SUBSTR(u.tlno, 8)))
having count(*) >=3
order by u.user_id desc;