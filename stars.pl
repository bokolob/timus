#!/usr/bin/perl
use strict;
use warnings;

my %name2dists;

my $cnt =  <>;

for (my $i = 0; $i < $cnt; $i++) {
    my ($x, $y) = split(/ /, <>);
    $name2dists{$i} = $x + $y;
}
use Data::Dumper;
warn Dumper(\%name2dists);

my @sorted_names = sort { $name2dists{$a} <=> $name2dists{$b}  } keys %name2dists;

warn Dumper(\@sorted_names);

