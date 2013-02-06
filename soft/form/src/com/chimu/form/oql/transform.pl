

do 'getopts.pl';

$chosenTransformation = queryTransform;

$fileOrDirectory = shift;
$fileOrDirectory = '.' if $fileOrDirectory eq '';
$logFile = "transform.log";
open (LOG, ">$logFile");
    if (-d $fileOrDirectory) {
        &loopOverFilesInDirectory($fileOrDirectory,chosenJavaTransform);
    } else {
        &chosenJavaTransform($fileOrDirectory);
    }
    while ($fileOrDirectory = shift) {
        if (-d $fileOrDirectory) {
            &loopOverFilesInDirectory($fileOrDirectory,chosenJavaTransform);
        } else {
            &chosenJavaTransform($fileOrDirectory);
        }
    }
close (LOG);

sub chosenJavaTransform {
    my ($fileName) = @_;
    transformJavaFile($fileName,$chosenTransformation);

}

sub queryTransform {
    my ($line) = @_;
    $line =~ s|createConditionFor\(QueryDescription|newConditionFor_using(OqlQueryPi oql, QueryDescription|;
    $line =~ s|createQueryExpressionFor\(QueryDescription|newExpressionFor_using(OqlQueryPi oql, QueryDescription|;
    $line =~ s|createQueryInto\(QueryDescription|buildQueryFor_into(OqlQueryPi oql, QueryDescription|;

    $line =~ s|createConditionFor\(|newConditionFor_using(oql,|;
    $line =~ s|createQueryExpressionFor\(|newExpressionFor_using(oql,|;
    $line =~ s|createQueryInto\(|buildQueryFor_into(oql,|;

    return $line;
}

sub publicClassTransform {
    my ($line) = @_;
    $line =~ s|public class|/*package*/ class|;
    return $line;

}

sub lineTransform04to07 {
    my ($line) = @_;
    $line =~ s/setupMapperForDomainObjectAbsClass/configMapper_forDomainObject/;
    $line =~ s/DistinguishedObject/DistinguishingObject/;

    $line = '' if ($line =~ /mapper\.setupTableName/);
    $line = '' if ($line =~ /Getter\(\)\,/);
    $line = '' if ($line =~ /mapper\.setupFetchIdentityDuringInsert/);
    return $line;
}


sub lineTransform07010deprecated {
    my ($line) = @_;

    $line =~ s/QueryPack\.newQueryDescription/orm.newQueryDescription/g;
    $line =~ s/QueryPack\./query\./g;

    return $line;
}

sub lineTransform0701to0708 {
    my ($line) = @_;

        ##### Not needed for CollectiveObjectRetrievers #####
    $line = '' if ($line =~ /\.useIdentitySlotNamed/);
    $line = '' if ($line =~ /\.useDistinguisherSlotNamed/);
    $line = '' if ($line =~ /\.useAllSlotsFromRetrievers/);
    $line =~ s/addDistinguishingMapper_slotName\s*\(\s*([^\,\s]*)\s*,\s*([^\)]*)(\(\))?\s*\)/addDistinguishingMapper($1)/g;

        ##### Rename for AbsClasses ######
    $line =~ s/initializeFromMapper/form_initState/g;
    $line =~ s/extractValuesForMapperInto/form_extractStateInto/g;
    $line =~ s/form_extractStateInto\s*\(\s*Map/form_extractStateInto(KeyedArray/g;

    if ($line =~ /protected(.*)form_initState/) {
        $before = $`;
        $after =$';
        $match = $1;
        $line=$before.'/*friend:FORM*/ public'.$match.'form_initState'.$after;
    }

    if ($line =~ /protected(.*)form_extractStateInto/) {
        $before = $`;
        $after =$';
        $match = $1;
        $line=$before.'/*friend:FORM*/ public'.$match.'form_extractStateInto'.$after;
    }

        ##### Moved MappedObject ######
    $line =~ s/COM\.chimu\.form\.MappedObject/COM.chimu.form.mapping.MappedObject/g;

        ##### Renamed Classes ######
    $line =~ s/AssociationMapper/AssociationConnector/g;
    $line =~ s/SelectiveObjectRetriever/CollectiveObjectRetriever/g;

        ##### Supposedly NotNeeded Anymore  ######
    $line =~ s/DomainObject_1o_AbsClass/DomainObject_1_AbsClass/g;

        ##### setMapper rename ######
    $line =~ s/setMapper\s*\(/linkClass_toMapper(/g;
    $line =~ s/linkMapper_toClass\s*\(\s*([^\,\s]*)\s*,\s*([^\)]*)(\(\))\s*\)/linkClass_toMapper($2$3,$1)/g;

    $line =~ s/setupCreatorFunction/setupCreationFunction/g;
    if ($line =~ /basicNewFunction/) {
        $before =$`;
        $after = $';
        if ($after =~ /^\s*\(\s*\)\s*\{/) {
            #found our own preprocessor line... ignore
        } else {
            $line = $before.'form_creationFunction'.$after;
        }
    }

    ##########################
    #######  SLOTS ###########
    ##########################

    if ($line =~ /newDirectSlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\([^\,]+\,[^\)]*\)/) {
            $line = $before."newDirectSlot_column".$after;
        };
    };

    if ($line =~ /newConstantSlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\(/) {
            $line = $before."newConstantSlot_column_value".$after;
        };
    };

    if ($line =~ /newIdentitySlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\(/) {
            $line = $before."newIdentitySlot_column".$after;
        };
    };

    if ($line =~ /newTransformationSlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\(/) {
            $line = $before."newTransformSlot_column_decoder_encoder".$after;
        };
    };

    if ($line =~ /newForwardAssociationSlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\(/) {
            $line = $before."newForwardSlot_column_partner".$after;
        };
    };

    if ($line =~ /newReverseAssociationSlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\(/) {
            $line = $before."newReverseSlot_partner_partnerSlot".$after;
        };
    };

    if ($line =~ /newExternalAssociationSlot/) {
        $before = $`;
        $after = $';
        if ($after =~ /^\s*\(/) {
            $line = $before."newExternalSlot_connector_mySlot".$after;
        };
    };

    return $line;
}

##############

sub transformJavaFile{
    my($fileName,$lineTransformation) = @_;
    if ($fileName =~ /\.java$/) {
        print LOG $fileName."\n";
        rename($fileName, $fileName . '.bak');
        open (INPUT, "<$fileName\.bak");
        open (OUTPUT, ">$fileName");
        select(OUTPUT);
        $modified = '';

        while (<INPUT>) {
            my($startLine) = $_;
            my($line) = &$lineTransformation($startLine);

            if ($line eq $startLine) {
                print $startLine;
            } else {
                $modified = 1;
                chomp($startLine);
                if ($line eq '') {
                    print LOG $startLine." DELETED ";
                } else {
                    print $line;
                    chomp($line);
                    print LOG $startLine."->".$line."\n";
                }
            };
        };
        close (INPUT);
        close (OUTPUT);
        if (not $modified) {
            rename($fileName. '.bak', $fileName );
        };
        select(STDOUT);
    };
};

sub doToADirectory {
     my($fileName,$doToAFile) = @_;
     return if ($fileName =~ /\.$/o);
     return if ($fileName =~ /old/o);
     return if ($fileName =~ /tests$/o);
     return if ($fileName =~ /working$/o);
     loopOverFilesInDirectory($fileName,$doToAFile);
};

sub loopOverFilesInDirectory {
    my($directoryName,$doToAFile) = @_;
    my(@files, $fileName, $fullFileName);

    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;

    foreach $fileName (@files) {
      $fullFileName = $directoryName."\\".$fileName;
      if (-d $fullFileName) {
          doToADirectory($fullFileName,$doToAFile);
      } else {
          &$doToAFile($fullFileName);
      };
    };
};

sub printAFile {
    my($fileName) = @_;
    print "$fileName\n";
};

sub unbackupAFile {
    my($fileName) = @_;
    if ($fileName =~ /\.bak$/) {
        $prefix = $`;
        rename($fileName, $prefix);
        print "$prefix\n";
    };
};

