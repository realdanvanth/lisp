x=100901,i=2,f=0;
loop (N;greater i x) (N;= i (N;add i '1');
if (N;equal (N;mod x i) '0') 
(N;= f (N;add f '1');get '') (N;get ''));
if (N;equal f '1') (N;get 'is prime') (N;get 'is not prime')
