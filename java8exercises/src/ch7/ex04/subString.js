//$ jjs < subString.js 
//jjs> jjs> jjs> jjs> jjs> jjs> jjs> jjs> lo
//jjs> jjs> class java.lang.String
//jjs> class java.lang.Class
//jjs> jjs> java.lang.ClassCastException: Cannot cast java.lang.Class to java.lang.String
//getClass()で取得したクラスはClass<String>なので、Stringクラスへはcastできない

var sub = 'Hello'.slice(-2)
sub
var c = sub.getClass()
c
c.getClass()

java.lang.String.class.cast(c)