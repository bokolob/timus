use strict;
use warnings;
use bignum;

for (my $i = 50; $i <= 50; $i++) {
    for (my $j = 0; $j <= 1000; $j++) {
        next if $j % 2;
        my $res =  ` echo $i $j | ./a.out`;
        my $resPerl =  ` echo $i $j | perl ./lucky.pl`;
        chomp $res;

        print "C: $i $j <$res> $resPerl\n";
        $res =~/(\d+)\s+(\d+)/;
        my $p = $2;

        $resPerl =~ /(\d+)/;

        if ($p != $1) {
                die "XXX $i $j";
        }

#        if ($1**2 != $2) {
#            die "FOUND! $i $j";
#        }

    }
}
