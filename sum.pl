#!/usr/bin/perl

use strict;
use warnings;

use Data::Dumper;

my @z=(5, 5, 5, 5, 5, 2, 3, 1, 2, 3);
my @s;

$s[0] = $z[0];

for (my $i = 1; $i < @z; $i++) {
    $s[$i] = $s[$i-1] ^ $z[$i];
}


for (my $i = 0; $i < scalar(@z)-1; $i++) {
    $s[$i] = $s[$i] ^ $s[-1];
}


warn Dumper(\@s, \@z);
