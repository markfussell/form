

do 'getopts.pl';

sub recordImportLine {
    my($importLine) = @_;
    my(@packageParts,@packageSubstrings);
    if ($importLine =~ m/^\s*import\s+([\w\.]+)\.([\w\*]+)/) {
        $package = $1;
        $fileOrStar = $2;
        print "$package->$fileOrStar"."\n";

        @packageParts = split(/\./,$package);

        $substring = '';
        foreach $part (@packageParts) {
            $substring .= $part;
            push(@packageSubstrings,$substring);
            $substring .= '.'
        }

        $orderIndex = 1000;
        foreach $substring (@packageSubstrings) {
            print "FIND:".$substring;
            $matchIndex = $orderMap{$substring};
            if (!$matchIndex eq '') {
                print "==>".$matchIndex."\n";
                if ($matchIndex < $orderIndex) {
                    $orderIndex = $matchIndex;
                };
            } else {
                print " nope"."\n";
            }
        }
        print '#'.$orderIndex."\n";
        if ($fileOrStar eq '*') {
            $starEntries{$orderIndex}.=$importLine;
        } else {
            $fileEntries{$orderIndex}.=$importLine;
        }

            #$fileNameOrStar = pop(@importParts);
    } else {
        print "?".$importLine;
    }
};

%starEntries=();
%fileEntries=();

%orderMap=();

%groupMap=();

$i = 5;
%firstLevelOrderMap=(
    'COM.chimu.formTools'    =>'1',
    'COM.chimu.kernelTools'  =>'2',
    'COM.chimu.dome'         =>'3',
    'COM.chimu.form'         =>'4',
    'COM.chimu.form.mapping'=>'4',
    'COM.chimu.form.database'=>'4',
    'COM.chimu.kernel.functors'=>++$i,
    'COM.chimu.kernel.exceptions'=>$i,
    'COM.chimu.kernel.collections'=>$i,
    'COM.chimu.kernel'=>$i,
    'java.sql'=>++$i,
    'java.util'=>$i,
    'java'=>$i

);

@firstLevelOrder2=(
    'COM.chimu.formTools'   ,
    'COM.chimu.kernelTools'  ,
    'COM.chimu.dome'         ,
    'COM.chimu.form'         ,
    'COM.chimu.form.mapping',
    'COM.chimu.form.database',
    'COM.chimu.kernel.functors',
    'COM.chimu.kernel.exceptions',
    'COM.chimu.kernel.collections',
    'COM.chimu.kernel',
    'java.sql',
    'java.util',
    'java'

);

$entry = 1;
foreach $firstLevelString (@firstLevelOrder2) {
    $orderMap{$firstLevelString}=$entry;
    $groupMap{$entry}=$firstLevelOrderMap{$firstLevelString};
    $entry++;
};
$groupMap{1000}=1000;

print %orderMap;
print %groupMap;
print "\n";

while ($line = <STDIN>) {
    print $line;
    &recordImportLine($line);
};

$oldGroup = 0;
foreach $key (sort {$a <=> $b} keys %starEntries) {
    $newGroup = $groupMap{$key};
    if ($newGroup > $oldGroup) {
        print '----'."\n";
        $oldGroup = $newGroup;
    }
    print $key.'=>'.$starEntries{$key};
}

foreach $key (sort {$a <=> $b} keys %fileEntries) {
    print $key.'=>'.$fileEntries{$key};
}
