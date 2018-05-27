use strict;
use warnings;

my @list=(100,110,170,200);
#my @list=(100, 110, 160, 170,);
#my @list = (100,120,160,180);


sub func2
{
    my ($left, $right, $coin, $level) = @_;
    
    warn "".(" "x$coin)."$left $right $coin";

    if ($left == 0 && $right == 0) {
        return ([], 1);
    }
    elsif ($left < 0 || $right < 0) {
        return (undef, 0);
    }
    elsif ($coin > scalar(@list) - 1) {
        return (undef, 0);
    }

    my ($ll, $cnt1) = func2($left - $list[$coin], $right, $coin+1); 
    my ($rl, $cnt2) = func2($left, $right - $list[$coin], $coin+1); 
    
    if ($ll) {
        return ([$coin, @$ll], $cnt1+$cnt2);
    }
    elsif ($rl) {
        return ($rl, $cnt1+$cnt2);
    }
    
    return (undef, 0);
}

use List::Util qw/sum/;
use Data::Dumper;

#warn Dumper( func(270,310, { map {$_=>1} @list }) );

my $sum = 0;
$sum += $_ for @list;

warn $sum;

warn Dumper(func2($sum-270, 270,0));



