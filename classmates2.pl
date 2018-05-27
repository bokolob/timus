use strict;
use warnings;
use Data::Dumper;

=head2

10
2 3 0
4 5 7 0
6 9 0
0
0
8 10 0
0
0
0
0
2

=cut

my @table;

my $n = (<>);
my $i = 0;

my %managers;
my $nc = $n;

while($nc--) {
    $_ = (<>);
    chomp;
    my @subordinates = grep {$_ != 0} ( split(/\s+/, $_));
    
    foreach my $s (@subordinates) {
        $managers{$s-1} = $i;
        $table[$i][$s-1]=1;
    }

    $i++;
}

=head timeInRoot

Находим время обсчет дерева начиная с корня X.
Время обхода этого дерева это 

max( i + timeInRoot(k) для всех подчиненых X отсортированных по увеличинию продолжительности обсчета в них. )


(1+6) (2+5) (3+4) (4+3)

"Таня" разбивает дерево на две части - подчиненные кати и ее начальник. считаем начальника как подчиненного

=cut

my $tanya = <>;
$tanya--;

print_table(\@table);
change_root($tanya);
print "\n";
print_table(\@table);
print calc($tanya),"!!!";

use List::Util qw/max/;

sub calc
{
    my ($root) = @_;
    
    my $offt = 1;
    
    warn "calc($root)\n";
    
    if (!defined $table[$root]) {
        return 0;
    }

    my @subordinates = map { $_ + ($offt++) }
                       sort { $b <=> $a } 
                       map { calc($_) } 

                       grep { ($table[$root][$_] // 0) != 0 }
                       (0 .. (scalar (@{$table[$root]}) -1));
    
    if (!@subordinates) {
        return 0;
    }

    return max(@subordinates);
}

sub change_root
{
    my ($from) = @_;

    if (exists $managers{$from}) {
        $table[ $managers{$from} ][ $from ] = 0;
        $table[$from][ $managers{$from} ] = 1;

        change_root($managers{$from});
    }
}

sub print_table
{
    my ($result) = @_;

    for (my $i = 0; $i < @table; $i++) {
        for (my $j = 0; $j < $n; $j++) {
            print $table[$i][$j] // 0, " ";
        }
        print "\n";
    }
}

