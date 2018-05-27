#!/usr/bin/perl

use strict;
use warnings;
#use bignum;

use Data::Dumper;

my $len = 123;
my $sols = 43;
my @fix;

sub YES {0};
sub NO  {1};

sub count_right_answers
{
    my @table;

    for (my $i = 0; $i < $len; $i++) {
        $table[0][$i][YES] = 0;
        $table[0][$i][NO] = 0;
    }

    $table[0][1][NO] = 1;
    
    for (my $i = 1; $i < $sols; $i++) {
        for (my $j = 0; $j < $len; $j++) {
            $table[$i][$j][YES] = 0;
            $table[$i][$j][NO] = 0;

            #last answer is 'yes'
            if ($fix[$i][$j][YES]) {
                if ($fix[$i-1][$j-3][YES]) { #and prev answer.txt was yes
                    $table[$i][$j][YES] += $table[$i-1][$j - 3][YES];
                }

                if ($fix[$i-1][$j-3][NO]) {
                    #prev answer was NO
                    $table[$i][$j][YES] += $table[$i-1][$j - 3][NO] + 1;
                }
            }

            #last answer is 'no'
            if ($fix[$i][$j][NO]) {
                if ($fix[$i-1][$j-2][YES]) { #and prev answer.txt was yes
                    $table[$i][$j][NO] += $table[$i-1][$j - 2][YES] + 1;
                }

                if ($fix[$i-1][$j-2][NO]) {
                    #prev answer was NO
                    $table[$i][$j][NO] += $table[$i-1][$j - 2][NO];
                }
            }
        }
    }

#    warn Dumper(\@table);
    return ($table[$sols-1][$len-1][YES]+$table[$sols-1][$len-1][NO]);
}

my $strings = count_strings();

warn count_right_answers();
warn $strings;

sub count_strings
{
    my @table;

    for (my $i = 0; $i < $len; $i++) {
        $table[0][$i] = 0;
        $fix[0][$i][YES]=0;
        $fix[0][$i][NO]=0;
    }

    $table[0][1] = 1;
    $table[0][2] = 1;
   
    $fix[0][1][NO]=1;
    $fix[0][2][YES]=1;

    for (my $i = 1; $i < $sols; $i++) {
        for (my $j = 0; $j < $len; $j++) {
            $table[$i][$j] = 0;
            
            $fix[$i][$j][YES]=0;
            $fix[$i][$j][NO]=0;

            #last answer is 'yes'
            if ($j >= 3+2-1) {
                $table[$i][$j] += $table[$i-1][$j - 3];
                $fix[$i][$j][YES]= $table[$i-1][$j - 3] > 0 ? 1 : 0;
            }

            #last answer is 'no'
            if ($j >= 2+2-1) {
                $table[$i][$j] += $table[$i-1][$j - 2];
                $fix[$i][$j][NO]=$table[$i-1][$j - 2] >0 ? 1 : 0 ;
            }
        }
    }

#    warn (($table[$sols-1][$len-1][YES]+$table[$sols-1][$len-1][NO])/factorial($sols));
#    warn Dumper(\@table, \@fix);
    return $table[$sols-1][$len-1];
}



