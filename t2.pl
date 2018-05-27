use strict;
use warnings;
use Memoize;

my @cache;

memoize('cnt');

sub cnt
{
    my ($total, $prevLength, $d) = @_;

    if ($total == 0) {
        return 1;
    }

    my $count = 0;

    for (my $i = $prevLength+1; $i <= $total; $i++) {
        $count += cnt( $total - $i, $i, $d+1 );
    }

    return $count;
}

my $n = <>;
chomp($n);

my $count= 0;

for (my $i = 1; $i < $n; $i++) {
    $count += cnt($n-$i, $i,0);
}

warn $count;
