#!/usr/bin/perl

use strict;
use warnings;

use Data::Dumper;

my @L = qw/A G G G A A $/;
my @F = sort @L;


my %pre;
my %preL;

for (my $i = 0; $i < @F; $i++) {
    push @{ $pre{ $F[$i] } }, $i;
    push @{ $preL{ $L[$i] } }, $i;
}

warn Dumper([\@F, \@L, \%pre, \%preL]);

my $pos;

my @result;

for (my $i = 0; $i < @F; $i++) {
    $pos = $L[$i];
    push @resul, $L[$i];
}
