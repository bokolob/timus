#!/usr/bin/perl
use strict;
use warnings;

my $count = 60000;

print $count,"\n";

for (my $i = 1; $i < $count; $i++) {
    print "",($i-1)," ",$i," 1\n";
}

print "",($count-1),"\n";

for (my $i = 1; $i < $count; $i++) {
    print "1"," ",$i,"\n";
}

