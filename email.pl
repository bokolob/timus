use strict;
use warnings;
use Data::Dumper;

my @test = (
#[qw/a b/, 2],
#[qw/a a/, 1],
#[qw/aa a/, 1],
#[qw/a aa/, 1],
#[qw/aba bab/, 2],
#[qw/ab ababab/, 1],
[qw/abcab cba 4/,  ],
#[qw/abc cba 6/],
[qw/ba bab 1/],
[qw/bab baba 1/],
[qw/mara khao 1/],
#[qw/abcabc cba 5/],
#[qw/abcbaabdca abcada 1/],
#[qw/abcabbaca abcabbc 1/],
);

my $str_a;
my $str_b;

foreach my $tst (@test) {
    $str_a = [split(//, $tst->[0])];
    $str_b = [split(//, $tst->[1])];
    
    my $result = dp_count();

    if ($result != $tst->[2]) {
        warn "FAILED $result ".join(' ',$tst->[0], $tst->[1]);
    }
    else {
        warn "OK";
    }

}


sub dp_count
{
    my $lengths = dp_length();
    my @result;

    for (my $i = 0; $i < @$str_a; $i++) {
        if ($str_a->[$i] eq $str_b->[0] || $i > 0 && $result[0][$i-1] == 1) {
            $result[0][$i] = 1;
        }
        else {
            $result[0][$i] = $i + 2;
        }
    }

    for (my $i = 1; $i < @$str_b; $i++) {
        if ($str_b->[$i] eq $str_a->[0] || $i > 0 && $result[$i-1][0] == 1) {
            $result[$i][0] = 1;
        }
        else {
            $result[$i][0] = $result[$i-1][0] + 1;
        }
    }

    for (my $i = 1; $i < @$str_b; $i++) {
        for (my $j = 1; $j < @$str_a; $j++) {
            if ($str_b->[$i] eq $str_a->[$j]) {
                $result[$i][$j] = $result[$i-1][$j-1];
            }
            else {
                if ($lengths->[$i][$j-1] < $lengths->[$i-1][$j]) {
                    $result[$i][$j] = $result[$i][$j-1];
                }
                elsif ($lengths->[$i-1][$j] < $lengths->[$i][$j-1]) {
                    $result[$i][$j] = $result[$i-1][$j];
                }
                else {
                    $result[$i][$j] = $result[$i-1][$j] + $result[$i][$j-1];
                }
            }
        }
    }
    
    print_table(\@result);
    return $result[ scalar(@$str_b) - 1  ][ scalar(@$str_a) -1];
}

sub print_table
{
    my ($result) = @_;

    print "  ",join(' ', @$str_a),"\n";

    for (my $i = 0; $i < @$str_b; $i++) {
        print join(" ", $str_b->[$i], @{ $result->[$i] }), "\n";
    }

}

sub dp_length
{
    my @result;

    my $prev = 1;
    my $equal = 0;

    for (my $i = 0; $i < @$str_a; $i++) {
        if ($str_a->[$i] eq $str_b->[0] && !$equal) {
            $result[0][$i] = $prev + 0;
            $equal = 1;
        }
        else {
            $result[0][$i] = $prev + 1;
        }

        $prev = $result[0][$i];
    }
    
    $equal = 0;
    for (my $i = 1; $i < @$str_b; $i++) {
        if ($str_b->[$i] eq $str_a->[0] && !$equal) {
            $result[$i][0] = $result[$i-1][0];
            $equal = 1;
        }
        else {
            $result[$i][0] = $result[$i-1][0] + 1;
        }
    }

    #warn Dumper(\@result);

    for (my $i = 1; $i < @$str_b; $i++) {
        for (my $j = 1; $j < @$str_a; $j++) {
            if ($str_b->[$i] eq $str_a->[$j]) {
                $result[$i][$j] = $result[$i-1][$j-1]+1;
            }
            else {
                if ($result[$i][$j-1] < $result[$i-1][$j]) {
                    $result[$i][$j] = $result[$i][$j-1] + 1;
                }
                elsif ($result[$i-1][$j] < $result[$i][$j-1]) {
                    $result[$i][$j] = $result[$i-1][$j] + 1;
                }
                else {
                    $result[$i][$j] = $result[$i-1][$j] + 1;
                }
            }
        }
    }

    print_table(\@result); print"\n";
    #warn Dumper(\@result);
    return \@result;
}

