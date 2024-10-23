-- 코드를 입력하세요
SELECT b.title as TITLE, b.board_id as BOARD_ID, r.reply_id as REPLY_ID, r.writer_id as WRITER_ID, r.contents as CONTENTS, to_char(r.created_date, 'yyyy-mm-dd') as CREATED_DATE
from USED_GOODS_BOARD b 
inner join USED_GOODS_REPLY r
on b.board_id = r.board_id where b.created_date between to_date('2022-10-01', 'yyyy-mm-dd') and to_date('2022-10-31', 'yyyy-mm-dd')
order by CREATED_DATE , b.title ;