function main ():
	num,max,min = 0,0,0
	
	scanner(num)
	max=num
	min=num
	
	while (num~=999) do
		if(num > max) then max = num end
		if(num < min) then min = num end
		scanner(num)
	end
	
	print(max)
	print(min)
end