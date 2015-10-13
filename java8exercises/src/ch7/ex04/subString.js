//$ jjs < subString.js 
//特に何も起きない。古いバージョンのjava8だけ起こるらしい。

var sub = 'Hello'.slice(-2)
print(sub)
var c = sub.getClass()
print(c)
java.lang.String.class.cast(sub)