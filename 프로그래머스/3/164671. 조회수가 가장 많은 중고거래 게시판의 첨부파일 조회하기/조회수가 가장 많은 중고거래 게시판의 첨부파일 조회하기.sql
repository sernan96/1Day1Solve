select '/home/grep/src/'||f.board_id||'/'||f.file_id||f.file_name||f.file_ext
from used_goods_board b, used_goods_file f
where b.board_id = f.board_id and b.board_id =(select board_id from used_goods_board where VIEWS=(select max(views) from used_goods_board)) 
order by f.file_id desc;