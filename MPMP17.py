def isValid(n):
	while n!=0:
		if (n&3)==3:
			return False
		n>>=1
	return True

NUMBER_OF_KENNELS=10
count=0

for i in range(2 ** NUMBER_OF_KENNELS):
	b= isValid(i)
	print(f"{i:010b}={b}")
	if b:
		count+=1

print(f"for {NUMBER_OF_KENNELS} kennels there are {count} ways to set the cats and dogs")