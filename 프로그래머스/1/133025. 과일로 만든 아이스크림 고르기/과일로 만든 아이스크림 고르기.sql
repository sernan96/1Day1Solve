-- 코드를 입력하세요
SELECT first.flavor
from FIRST_HALF first, ICECREAM_INFO info
where first.flavor = info.flavor and first.total_order>3000 and info.INGREDIENT_TYPE ='fruit_based'
order by first.total_order desc;