use strict;
use warnings;
use Memoize;

memoize('calc');

#use bignum;

my $input = <>;
chomp $input;
$input =~ /\s*(\d+)\s+(\d+)/;

my ($total, $max) = ($1, $2);


for (my $i = 0; $i <= $total; $i++) {
    for (my $j = 0; $j <= $max; $j++) {
        calc($j, $i);
    }
}

my @cache;

for (my $i  = 0; $i <= $max; $i++) {
    $cache[0][$i] = 1;
}

#$cache[0][$max+1]=0;

for (my $len = 1; $len <= $total; $len++) {
    for (my $lemons = 0; $lemons <= $max; $lemons++) {
        
    $cache[$len][$lemons] = 0;
        if ($lemons < $max) {
            $cache[$len][$lemons] = $cache[$len - 1][$lemons +1];
        }

        $cache[$len][$lemons] +=  $cache[$len - 1][0];
    }
}

use Data::Dumper;

#warn Dumper(\@cache);

warn $cache[$total][0];

my $banan = kbanacci($total+1, $max+1);

warn Dumper($banan);

#my $result = calc(0, $total);

#warn $result;

sub calc
{
    my ($prev_lemons, $total_len, $d) = @_;

    $d //=0;

    if ($total_len == 0) {
        return 1;
    }
    
    my $rc = 0;

    if ($prev_lemons < $max) {
#        warn "".(" "x$d)."using: ".($prev_lemons + 1)." ".($total_len - 1);
        $rc += calc($prev_lemons + 1, $total_len - 1, $d+1);
    }

#    warn "".(" "x$d)."using: ".(0)." ".($total_len - 1);
    $rc += calc(0, $total_len - 1, $d+1);
    
    return $rc;
}

#Возвращает n-тое число k-баначи

sub kbanacci
{
    my ($n, $k) = @_;

    my @cache;

    $cache[0] = 1;
    $cache[1] = 1;
    

    if ($k > 1) {
        $cache[2] = 2;

        for (my $i = 3; $i <= $k; $i++) {
            $cache[$i] = $cache[$i-1]*2;
        }
    }

    for (my $i = 0; $i < $n; $i++) {
#        warn Dumper(\@cache);
        my $x = $cache[$k] - $cache[0] + $cache[$k];
        
        for (my $j = 1; $j <= $k; $j++) {
            $cache[$j - 1] = $cache[$j];
        }

        $cache[$k] = $x;
    }
    
    return \@cache;
}


