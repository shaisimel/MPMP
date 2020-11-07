# set in NUMBER_OF_KENNELS the target solution you want. You will get all up to that target.
NUMBER_OF_KENNELS = 50

UNDEFINED=-1

zeros = [UNDEFINED]*NUMBER_OF_KENNELS
ones = [UNDEFINED]*NUMBER_OF_KENNELS
	
zeros[0] = 1
ones[0]=1

def rec(level, lead):
	z = zeros[level];
	o = ones [level]
	
	if z==UNDEFINED:
		z=rec(level-1,0)
		zeros[level] = z
	if o==UNDEFINED:
		o=rec(level-1,1)
		ones[level]=o
	
	if(lead==0):
		return  z+o
	return z

for i in range(1,NUMBER_OF_KENNELS+1):
	total = rec(i-1,0)	
	print (f"for {i} kennels there are {total} ways to arange the cats and dogs")