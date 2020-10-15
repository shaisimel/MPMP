program MPMP16;
Uses Math;

function countPascalOdd(line: longint) : longint;
var
        count, n : longint;
begin
        count := 0;
        n := line;
        while (n > 0)  do
        begin
            count := count + (n and 1);
            n := n >> 1;
        end;

        countPascalOdd := 2 ** count;
end;

var
        i, sum, total: longint;
        per : float;
begin

  sum := 0;
  total := 0;

  for i := 0 to 127 do
  begin
        sum := sum + countPascalOdd(i);
        total := total + i + 1;
  end;

  per := sum/total*100;

  write ('Total odd numbers: ');
  writeln (sum);
  write ('Total numbers: ');
  writeln (total);
  write ('Percentage of odd numbers: ');
  write (per:3:10);
  writeln ('%');
  readln;
end.


