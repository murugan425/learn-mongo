#LIST
things = ["hammer","nail","wall"]
mixedlist = ["tamil", "teju", ["malar", "mani"]]
print things
print mixedlist[0]
print mixedlist[1]
print mixedlist[2][0]
#######################
#SLICE
alphabet = ['a','b','c','d','e','f','g','h']
print alphabet[:]
print alphabet[0:2]
print alphabet[3:6]
print alphabet[:5]
print alphabet[3:]
#######################
#INCLUSION
if 'b' in alphabet:
	print "alphabet is present"
print ('z' in alphabet)
#######################
#DICTS - Dictionary 
# - similar to JSON but have minor differences
# DICTS - are not maintained in the same order they are entered like JSON
user = {'name':'Murugan','age':32,'skillsets':['Java','Python','Spring']}
print user
print user.keys()
print ('skillsets' in user) #checks whether the key exists
#######################
#FOR LOOP OF LISTS
fruit = ['apple', 'orange', 'kiwi'] #init the array
newfruitArray = [];
for item in fruit:	#iterate
	print item
	newfruitArray.append(item)	
	print newfruitArray

sum = 0
numbers = [1, 2, 3, 5, 8]
for i in numbers:
    sum = sum + i
print i
#######################
#FOR LOOP OF DICTS
people = {'name': 'Bob', 'hometown': "Palo Alto", 'favorite_color': 'red'}
for item in people:
    if (item == 'favorite_color'):
        print people[item]
for key in people:
	print "Value of " + key +" is " + people[key]
#######################
#WHILE LOOP
alpha = ['a','b','c','d','e','f','g','h']
i = 0
while(i < len(alpha)):
	print alpha[i]
	i = i + 1  #No ++ or --
#######################
#FUNCTIONS
alphabet_list = ['a','f','d','a','d','s','a','h','a','s','e','x']
def count_items(list):
	counts = {}
	for item in list:
		if item in counts:
			counts[item] = counts[item] + 1
		else:
			counts[item] = 1
	return counts

print count_items(alphabet_list)
#######################
#EXCEPTIONS
import sys

try:
    print 5 / 0
except Exception as e:
    print "exception : ZeroDivisionError occurred ", type(e), e

print "But still, Exception is handled in the except block above..."
#######################
#######################

