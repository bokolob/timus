#!/usr/bin/perl

my @result;
my $CNT = 10;

for my $i (1..$CNT) {
    push @result, int( rand(2**31-1) );
}

my @sorted = sort { $a <=> $b } @result;

print $CNT,"\n";
print join("\n", @result),"\n";

if ($CNT %2 ==1 ) {
    warn "ANS: ".$sorted[ $CNT / 2 ];
}
else {
    warn "ANS: ".($sorted[ $CNT / 2 - 1 ]+ $sorted[ $CNT / 2])/2;
}

