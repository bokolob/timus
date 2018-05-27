use strict;
use warnings;

use Memoize;
memoize('calc');

sub calc
{
    my ($n) = @_;

    if ($n == int(sqrt($n))**2) {
#        warn "$n is aquared! ".sqrt($n);
        return 1;
    }
    
    my $min = 'inf';

    for (my $i = 1; $i < int($n/2); $i++) {
        my $count = calc($i) + calc($n-$i);
        if ($count < $min) {
            $min = $count;
        }
    }
    
    return $min;
}

my $n = 99;

for (1..$n) {
    calc($_);
    warn "c($_)=".calc($_);
}

warn calc($n);
