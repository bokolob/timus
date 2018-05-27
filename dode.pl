#!/usr/bin/perl

use strict;
use warnings;

use Data::Dumper;

my @state;
my $N = 4;

my $moves = [generate(1), generate(0), generate(1)];

warn Dumper($moves);

test($moves);


sub test
{
    my ($moves) = @_;
    for (my $pos = 0; $pos < $N; $pos++) {
        my $ok = 0;
        my $cur_pos = $pos;
        foreach my $m (@$moves) {

            warn join(" ", map { 
                                 if ($_ == $cur_pos && $_ == $m) {
                                     'X' 
                                 }
                                 elsif ($_ == $m) { '_' }
                                 elsif ($_ == $cur_pos) { '1' }
                                 else {' '}
                
                               } (0.. $N-1)),"\n";

            if ($cur_pos==$m) {
                $ok=1;
                last;
            }
            else {
                my $r = rand();
                if ($cur_pos == 0 || ($r < 0.9 && $cur_pos < $N-1)) {
                    $cur_pos++;
                }
                else {
                    $cur_pos--;
                }
            }
        }

        if (!$ok) {
            warn "test failed pos=$pos";
        }
        else {
            warn "test $pos passed";
        }
    }

}
#generate(1);

sub generate
{
    my ($odd) = @_;
    my @res;

    for (my $i = 0; $i < $N; $i++)  {
        if (($i+1) % 2==$odd) {
            $state[$i]=1;
        }
        else {
            $state[$i]=0;
        }
    }
    
    #print "++++++++++++\n";
    #print "   ".join(", ", @state),"\n";

    for (my $k = 0; $k < $N; $k++) {
        if ($state[$k]!=0) {
            #print "Touch $k\n";
            move($k);
            push @res, $k;
        }
    }

    return @res;
}


sub move
{
    my ($current) = @_;
    $state[$current] = 0;
#    print "A: ".join(", ", @state),"\n";
    
    my @todo;

    for (my $i = 0; $i < $N; $i++)  {
        next if $state[$i] == 0;

        $state[$i]--;

        if ($i > 0) {
            push @todo, $i-1;
        }

        if ($i < $N-1) {
            push @todo, $i+1;
        }
    }

    foreach my $j (@todo) {
        $state[$j]=1;
    }

    #print "B: ".join(", ", @state),"\n";
}


