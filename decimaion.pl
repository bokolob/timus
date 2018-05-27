#0000000001000000001000000001000000001000
#1234567890123456789012345678901234567890

use strict;
use warnings;
use Data::Dumper;

#my @data = (0,0,0,0,0,0,0,0,0,1,);
#my @data = split(//,'1111111111');
my @data = split(//,'11111001110001110001');
#my @data = split(//,'11111001110001110001');
#my @data = split(//,'111110111110111110111110111110111110111110111110111110111110111110111110111110111110111110');
#my @data = split(//,'0001111111');
#my @data = split(//,'00000000100000000001');
#my @data = split(//,'1110001011');
my @table;
my @positions;
my $friends = 3;

my $cnt = 0;
for (my $i = 0; $i < @data; $i++) {
    if (($i+1) % 10 == 0 && $data[$i]==1) {
        $cnt++;
    }

    $table[0][$i] = $cnt;
    $positions[0][$i] = 0;
}

for (my $i = 0; $i < scalar(@data)+$friends; $i++) {
    for (my $j = 1; $j <= $friends; $j++) {
        if ($i < $j || $i >= (scalar(@data)+$j)) {
            $table[$j][$i] = 0;
            $positions[$j][$i] = 1;
            next;
        }
        else {
            
            my $in_current_pos = (($data[ $i - $j ] == 1) && (($i+1) % 10 == 0)) ? 1 : 0;
            my $new_one_cost = (($i+1) % 10 == 0) ? 1 : 0;

            if ($table[$j][$i - 1] + $in_current_pos < $table[$j -1][$i - 1] + $new_one_cost) {
                $table[$j][$i] = $table[$j][$i - 1] + $in_current_pos;
                $positions[$j][$i] = 0;
            }
            else {
                $table[$j][$i] = $table[$j -1][$i - 1] + $new_one_cost;
                $positions[$j][$i] = 1;
            }

#            $table[$j][$i] = min(
#                                  $table[$j][$i - 1] + $in_current_pos,
#                                  $table[$j -1][$i - 1] + ((($i+1) % 10 == 0) ? 1 : 0)
#                                );

#           if ($table[$j][$i] == ($table[$j][$i - 1] + $in_current_pos)) {
#                $positions[$j][$i] = 0;
#           }
#           else {
#               warn "Put in $j $i\n";
#                $positions[$j][$i] = 1;
#           }
        }
    }
    
}

my $min_penalty='inf';
my $min_cnt;

for (my $i = 0; $i <= $friends; $i++) {
    if ($table[$i][scalar(@data) + $i - 1] < $min_penalty) {
        $min_penalty = $table[$i][scalar(@data)+$i-1];
        $min_cnt = $i;
    }
}

warn "$min_penalty $min_cnt";

while($min_cnt >= 0) {
    my $start = scalar(@data) + $min_cnt - 1;
    
    while ($positions[$min_cnt][$start]==0 && $start >= $min_cnt) {
        warn "m=$min_cnt s=$start";
        $start--;
    }
    
    if ($positions[$min_cnt][$start]==1) {
        warn "p: $start";
        $min_cnt--;
    }
    else {
        last;
    }
}


sub min
{
    my ($c, $d) = @_;

    return $c < $d ? $c : $d;
}

sub print_table
{
    my ($result) = @_;

    print join(' ', @data),"\n";

    for (my $i = 0; $i < @$result; $i++) {
        print join(" ", @{ $result->[$i] }), "\n";
    }

}

print_table(\@table);
print("\n");
print_table(\@positions);

