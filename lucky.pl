use strict;
use warnings;

use Data::Dumper;
use bignum;

my @cache;

sub mCount
{
    my ($digits, $sum, $d) = @_;
    
#    return count($digits, $sum, $d);

    $cache[$digits][$sum] //= count($digits, $sum, $d);

    return $cache[$digits][$sum];
}

#сколькими способами можно составить число с суммй sum из digits разрядов
sub count
{
    my ($digits, $sum, $d) = @_;
    
    if ($digits == 0 && $sum == 0) {
        #   warn "".(" "x$d)."count(".($digits).",".($sum).")=1";
        return 1;
    }
    elsif ($digits == 0) {
        #    warn "".(" "x$d)."count(".($digits).",".($sum).")=0";
        return 0;
    }

    my $c = 0;

    for (my $i = 0; $i <= min(9, $sum); $i++) {
        #    warn "".(" "x$d)."count(".($digits-1).",".($sum-$i).")";
        $c += mCount($digits-1, $sum-$i);
    }

    #warn "".(" "x$d)."d=$digits s=$sum=$c";
    return $c;
}

sub min {
    return $_[0] < $_[1] ? $_[0] : $_[1];
}

my $input = <>;

$input =~ /\s*(\d+)\s+(\d+)/;

my @cache2;
my $sum = $2/2;
my $digits = $1;

print mCount($digits, $sum)**2;
exit;

$cache2[0][0] = 1;

for (my $i = 1; $i <= $sum; $i++) {
    $cache2[0][$i]=0;
}

for (my $i = 1; $i <= $digits; $i++) {
    for (my $j = 0; $j <= $sum; $j++) {
        $cache2[$i][$j] = 0;
        for (my $k = $j; $k>=0 && $k > $j - 10; $k--) {
            $cache2[$i][$j] +=  $cache2[$i-1][$k];
        }
    }
}

printf int($cache2[$digits][$sum]**2);


#warn Dumper(\@cache2, \@cache);
