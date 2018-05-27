use strict;
use warnings;

use Memoize;
use List::Util qw/max/;

memoize('remove_branch');

my %tree;
my @apples_matrix;
my @branches_matrix;
my $branches = 0;

while(<>) {
    chomp;
    next unless $_;
    $branches++;
    my ($left, $right, $apples) = split(/\s+/);
    push @{$tree{$left}}, $right;
    push @{$tree{$right}}, $left;

    $apples_matrix[$left][$right]=$apples;
    $apples_matrix[$right][$left]=$apples;
}

warn Dumper(\%tree);

integrate_apples('', 1);
warn "ib: ".integrate_branches('', 1);
use Data::Dumper;
#warn Dumper( \@branches_matrix);

warn remove_branch(1, 2, '');

sub integrate_apples
{
    my ($from, $to) = @_;

    my $cnt = 0;

    foreach my $branch (grep { $_ ne $from } @{$tree{$to}}) {
        $cnt += integrate_apples($to, $branch);
    }
    
    if ($from) {
        $apples_matrix[$from][$to] += $cnt;
        $apples_matrix[$to][$from] += $cnt;
    }

    return $apples_matrix[$from][$to];
}

sub integrate_branches
{
    my ($from, $to) = @_;

    my $cnt = 0;

    foreach my $branch (grep { $_ ne $from } @{$tree{$to}}) {
        $cnt += 1 + integrate_branches($to, $branch);
    }

    if ($from) {
        $branches_matrix[$from][$to] =  $cnt;
        $branches_matrix[$to][$from] =  $branches_matrix[$from][$to];
    }

    warn "$from $to = $cnt";

    return $cnt;
}

sub remove_branch
{
    my ($root, $n, $from) = @_;
    
    return $apples_matrix[$from][$root] unless $n;

    my @branches = grep { $_ ne $from } @{$tree{$root}};
    my @costs;
    
    if ($from ne '') {
        foreach my $to (@branches) {
            if ($branches_matrix[$root][$to] <= $n) {

                warn Dumper(
                    {
                    "fr $from $root" => $apples_matrix[$from][$root],
                    "rt $root $to" => $apples_matrix[$root][$to],
                    }

                );

                push @costs, $apples_matrix[$from][$root] - $apples_matrix[$root][$to];
            }
        }
    }

    foreach my $cnt (0..$n+1) {
        my $saved = 0;

        if ($branches[0]) {
            $saved += remove_branch($branches[0], $cnt, $root);
        }

        if ($branches[1]) {
             $saved += remove_branch($branches[1], $n-$cnt, $root);
        }

        push @costs, $saved;
    }
    
    warn Dumper({costs => \@costs});

    return max @costs;
}


