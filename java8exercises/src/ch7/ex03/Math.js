//nashorn>var b = new java.math.BigInteger('1234567890987654321') 
//nashorn> b
//1234567890987654321
//nashorn> b.mod(java.math.BigInteger.TEN)
//1
//特に奇妙に表示されなかった

var b = new java.math.BigInteger('1234567890987654321')
b
b.mod(java.math.BigInteger.TEN)
