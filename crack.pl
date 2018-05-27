use strict;
use warnings;

use Data::Dumper;
use List::Util qw/max/;

my $input = <>;
my @words = map {length($_)} split(/\W/, $input);

warn Dumper(\@words);
warn iterate_mode();
warn max( map { max_path($_) } (0..@words-1) );

sub max_path
{
    my ($from, $d) = @_;

    $d //= 0;

    my $max_cost = $words[$from]; #говорим только слово from и заканчиваем на этом

    for (my $edge = $from + 2; $edge < @words; $edge++) { #говорим еще одно слово
#        warn "".(" "x$d)."testing route from <$from, $edge>";

        my $new_cost = $words[$from] + max_path($edge,$d+1);

        if ($new_cost > $max_cost) {
            $max_cost = $new_cost;
        }
    }
    
#    warn "".(" "x$d)."route from <$from>=$max_cost";
    return $max_cost;
}

sub iterate_mode
{
    my @cache;

    my $max = -1;
    
    $cache[@words-1] = $words[@words - 1];
    $cache[@words-2] = $words[@words - 2];

    for (my $j = scalar(@words)-3; $j >=0; $j--) {
        my $x = $words[$j] + $cache[$j + 2];
        my $y = $words[$j];

        if ($j+3 < scalar(@words)) {
            $y += $cache[$j+3];
        }

        $cache[$j] = max($x,$y);
        
        if ($cache[$j] > $max) {
            $max = $cache[$j];
        }
    }
    
    return $max;
}

