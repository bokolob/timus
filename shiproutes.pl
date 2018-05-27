use strict;
use warnings;
use bignum;
use Data::Dumper;

my %cache;
my $n = 11;

sub bfs
{
    my ($params, $prev, $path) = @_;

    my $a = ($prev + 1) % 3;
    my $b = ($prev + 2) % 3;

    my $val_a = $params->[$a];
    my $val_b = $params->[$b];
    my $val_c = $params->[$prev];
    
    my $key = join(" ", $val_a, $val_b, $val_c, $prev);

    if (exists $cache{$key}) {
#        warn "SSS";
        return $cache{$key};
    }

    my $result = 0;

    #warn Dumper({a => $a, b => $b, prev => $prev, pa => $params->[$a], pb => $params->[$b] });

    if ($params->[$a]==0 && $params->[$b] == 0) {
        if ($prev != 0 && $params->[$prev] == 0) {
            warn "HIT! ".join(", ",0, @$path,0);
            return 1;
        }
        else {
            return 0;
        }
    }
    
    if ($params->[$a] > 0) {
        $params->[$a]--;

        push @$path, $a;

        $result += bfs($params, $a,  $path);

        pop @$path;

        $params->[$a]++;
    }
    
    if ($params->[$b] > 0) {
        $params->[$b]--;
        push @$path, $b;
        $result += bfs($params, $b, $path);
        pop @$path;
        $params->[$b]++;
    }
    
#    warn "[$val_a $val_b $val_c $prev] $result";
    
    $cache{$key} = $result;
    return $result;
}

sub factorial
{
    my ($n) = @_;

    my $i = 1;
    my $result = 1;

    while($n) {
        $result *= $n;
        $n--;
    }

    return $result;
}

my $result = bfs([$n-1, $n, $n], 0, []) * factorial($n-1) * factorial($n)**2;

warn $result/2;

