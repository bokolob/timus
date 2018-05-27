use strict;
use warnings;

use Data::Dumper;
my $k = 2;

my %cache;

sub calc
{
    my ($n) = @_;

    return $cache{$n} //= _calc($n);
}

sub _calc
{
    my ($n) = @_;
 
    if ($n <= 0) {
        return 0;
    }
    elsif ($n == 1) {
        return $k - 1 ;
    }
    else {
        return  calc1($n-2) * ($k-1) + calc1($n-1) * $k;
    }
}

sub calc1
{
    my ($n) = @_;
    
    if ($n <= 0) {
        return 0;
    }
    elsif ($n == 1) {
        return $k - 1;
    }
    else {
        return calc($n-1) * ($k-1);
    }
}

warn calc(10)+1;
warn Dumper(\%cache);

