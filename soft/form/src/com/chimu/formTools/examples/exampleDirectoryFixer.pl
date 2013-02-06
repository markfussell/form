# munch on all schema directories and rename the java example files with
# proper pattern (Ex$schemaNumber_*.java)

do 'getopts.pl';

#######################################################
#######################################################

$fileOrDirectory = shift;
$fileOrDirectory = '.' if $fileOrDirectory eq '';
$logFile = "transform.log";
open (LOG, ">$logFile");
    if (-d $fileOrDirectory) {
        &loopOverFilesInRootDirectory($fileOrDirectory,fileTransform);
    } else {
        &fileTransform($fileOrDirectory);
    }
    while ($fileOrDirectory = shift) {
        if (-d $fileOrDirectory) {
            &loopOverFilesInRootDirectory($fileOrDirectory,fileTransform);
        } else {
            &fileTransform($fileOrDirectory);
        }
    }
close (LOG);

sub loopOverFilesInRootDirectory {
    my($directoryName,$doToAFile) = @_;
    my(@files, $fileName, $fullFileName);

    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;

    foreach $fileName (@files) {
      $fullFileName = $directoryName."\\".$fileName;
      if (-d $fullFileName) {
          doToADirectory($fullFileName,$doToAFile);
      };
    };
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

sub doToADirectory {
    my($directoryName,$doToAFile) = @_;
    return if ($directoryName =~ /\.$/o);
    return if ($directoryName =~ /old/o);
    return if ($directoryName =~ /tests$/o);
    return if ($directoryName =~ /working$/o);
    loopOverFilesInDirectory($directoryName,$doToAFile);

    if ($directoryName =~ /com\\/i) {
        &buildSchemaExamplesFile($directoryName);
    } else {
        &buildSchemaExamplesFileStub($directoryName);
    }
};

sub buildSchemaExamplesFile{
    my($directoryName) = @_;
    my(@files, $fileName, $fullFileName);
    my(@exampleNames,$exampleName);

    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;

    my($hadExamples);
    foreach $fileName (@files) {
        if ($fileName =~ /Ex(\d+\w?)?\_/) {
            if ($fileName =~ /\.java$/) {
                $hadExamples=1;
                $exampleName = $`;
                push(@exampleNames,$exampleName);
            };
        }
    };

    return unless $hadExamples;

    if ($directoryName =~ /^\.\\/) {
        $directoryName = $';
    }
    my($fullOutPath) = $directoryName."\\"."SchemaExamples.java";
    open (OUTPUT, ">$fullOutPath");
    select(OUTPUT);

    my ($packageName,$className) = &packageFromFileName($fullOutPath);
    printHeader($packageName,$className);
    printPackage2($packageName);
    printSchemaExamplesTop($packageName);

    foreach $exampleName (sort @exampleNames) {
        print "        "."testNames.add(prefix+\"$exampleName\");\n";
    }
    printSchemaExamplesBottom();

    close (OUTPUT);

}

sub buildSchemaExamplesFileStub {
    my($directoryName) = @_;

    if ($directoryName =~ /^\.\\/) {
        $directoryName = $';
    }
    my($fullOutPath) = $directoryName."\\"."SchemaExamples.java";
    open (OUTPUT, ">$fullOutPath");
    select(OUTPUT);

    my ($packageName,$className) = &packageFromFileName($fullOutPath);
    printHeader($packageName,$className);
    printPackage2($packageName);
    ## print "public class SchemaExamples extends FormTestSchemaAbsClass {\n}\n" ;
    print <<END_TEXT;

import com.chimu.formTools.test.FormTestSchemaAbsClass;

public class SchemaExamples extends FormTestSchemaAbsClass {
}
END_TEXT

    close (OUTPUT);
}


sub printSchemaExamplesTop {
    my($packageName) = @_;
    $packageName .= ".";
    print <<END_TEXT;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "${packageName}";
END_TEXT
}

sub printSchemaExamplesBottom {
    print <<END_TEXT;
        return testNames;
    }
}
END_TEXT
}

sub packageFromFileName {
    my ($fullFileName) = @_;
#print "$fullFileName";
    if ($fullFileName =~ /\\([^\\]+)$/) {
        $fileName = $1;
        $directoryName = $`;
        if ($directoryName =~ /\\com\\/i) {
            $packageDirectory = "com\\".$';
        } elsif ($directoryName =~ /\\schema/) {
            $packageDirectory = "schema".$';
        } else {
            $packageDirectory = $directoryName;
        };
    } else {
        $packageDirectory = "";
        $fileName = $fullFileName;
    };

    if ($fileName =~ /\.java$/) {
        $className = $`;
    };

#print "->$packageDirectory\n";
    $packageDirectory =~ tr/\\/\./;
    return ($packageDirectory,$className);
}

sub fileTransform {
    my ($fullFileName) = @_;
    if ($fullFileName =~ /Ex(\d+\w?)?\_([^\_\.]+)(\_\d+)?\.java$/) {
        $fullFileName = &transformExampleNumbering($fullFileName);
    };
    if ($fullFileName =~ /\.java$/) {
        &replaceAndInsertLeadingComments($fullFileName);
    };
    if ($fullFileName =~ /\.java$/) {
        &renameFile($fullFileName);
    };
}

################################################################
################################################################
################################################################

sub renameFile{
    my($fullFileName) = @_;
    my($newFileName,$directoryName,$fileName);
    if ($fullFileName =~ /\\([^\\]+)$/) {
        $directoryName = $`."\\";
        $fileName = $1;
    }

#print "$fullFileName \{$directoryName,$fileName\}\n";
    if ($fullFileName =~ /\.java$/i) {
    	open (INPUT, "<$fullFileName");
    	while (<INPUT>) {
    	    $line = $_;
    	    if ($line =~ m/^\s*(public\s+)?(abstract\s+)?(class|interface)\s+(\w+)/) {
    		    $newFileName = $directoryName.$4.".java";
    	    }
    	};

        close (INPUT);
        if ($newFileName) {
            if ($newFileName ne $fullFileName) {
    	        #rename ($fileName, $newFileName);
                print "RENAME $fullFileName $newFileName\n";
            } else {
                print "NO RENAME $fullFileName\n";
            };
        } else {
            print "IGNORED $fullFileName\n";
        }
   };
};

################################################################
################################################################
################################################################

sub exampleTransform {
    my ($line,$schemaNumber) = @_;
    $line =~ s/Ex(\d+\w?)?\_/Ex$schemaNumber\_/g;
    return $line;
}

############################################
############################################

sub transformExampleNumbering{
    my($fileName) = @_;
    if ($fileName =~ /Ex(\d+\w?)?\_([^\_\.]+)(\_\d+)?\.java$/) {
    	$directory = $`;
        $fileSchemaNumber = $1;
        $className = $2.$3;

    	if ( $directory =~ m/schema(\d+[a-z]?)/ ) {
            $schemaNumber = $1;
        }
        $fileSchemaNumber = "?" if (!$fileSchemaNumber);


    	$outFileName = "Ex$schemaNumber\_".$className.".java";
    	$fullOutPath = $directory.$outFileName;
        print LOG $fileName." --> ".$outFileName."\n";

        rename($fileName, $fileName . '.bak');
        open (INPUT, "<$fileName\.bak");
        open (OUTPUT, ">$fullOutPath");
        select(OUTPUT);

        $modified = 0;
        while ($startLine=<INPUT>) {
            my($line) = &exampleTransform($startLine,$schemaNumber);

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
    	}

        close (INPUT);
        close (OUTPUT);
        select(STDOUT);
        if ($modified == 0) {
            rename($fileName.'.bak',$fullOutPath);
        };
        return $fullOutPath;
   };
   return $fileName;
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


################################################################
################################################################

# Go through the .java files and place a leading copyright information on the file.

sub replaceAndInsertLeadingComments{
    my($fileName) = @_;

    if ($fileName =~ /\.java$/) {
        rename($fileName, $fileName . '.bak');
        $havePackage = '';
        open (INPUT, "<$fileName\.bak");
        open (OUTPUT, ">$fileName");
        select(OUTPUT);
        while (<INPUT>) {
            $line = $_;
    	    if (not $havePackage) {
        		if ($line =~ /^\s*package/) {
        			$havePackage = 1;

                    my ($packageName,$className) = &packageFromFileName($fileName);
        			printHeader($packageName,$className);
        			printPackage2($packageName);

        		} elsif ($line =~ /^\s*import/) {
        			$havePackage = 1;

                    my ($packageName,$className) = &packageFromFileName($fileName);
        			printHeader($packageName,$className);
        			printPackage2($packageName);

        			print $line;
        		};
        	} else {
        	    print $line;
        	};
        };
        close (INPUT);
        close (OUTPUT);
        select(STDOUT);
        if (not $havePackage) {
            rename($fileName.'.bak',$fileName);
        };
    };
};

sub printHeader {
    my($packageName,$className) = @_;
    my($outputFileName) = $packageName.".".$className;
    $outputFileName =~ tr/\./\//;
    $outputFileName .= ".java";

    &printOpen();
    print "**  File: $outputFileName\n";
    &printCopyright();
    if ($outputFileName =~ /\/collections\//) {
        &printCollectionsSection();
    };
#    &printWarranty();
    &printClose();
};



sub printOpen {
    print <<OPEN_SECTION;
/*======================================================================
**
OPEN_SECTION
};

sub printCopyright {
    print <<COPYRIGHT_SECTION;
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
COPYRIGHT_SECTION
};
sub printWarranty {
    print <<WARRANTY_SECTION;
 *
 * CHIMU CORPORATION PROVIDES THIS SOFTWARE ON AN AS "AS IS" BASIS, WITHOUT
 * ANY WARRANTIES OR REPRESENTATIONS EXPRESS, IMPLIED OR STATUTORY;
 * INCLUDING, WITHOUT LIMITATION, WARRANTIES OF QUALITY, PERFORMANCE,
 * NONINFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 * NOR ARE THERE ANY WARRANTIES CREATED BY A COURSE OF DEALING, COURSE OF
 * PERFORMANCE OR TRADE USAGE. CHIMU CORPORATION DOES NOT WARRANT THAT THE
 * SOFTWARE WILL MEET CUSTOMER'S NEEDS OR BE FREE FROM ERRORS, OR THAT THE
 * OPERATION OF THE SOFTWARE WILL BE UNINTERRUPTED.
 *
 * Your licensing agreement with ChiMu Corporation may explicitly grant
 * warranties to you.
WARRANTY_SECTION
};

sub printClose {
    print <<CLOSE_SECTION;
**
======================================================================*/

CLOSE_SECTION
};

sub printPackage {
    my($fileName) = @_;
    my($packageName,$className) = &packageFromFileName($fileName);

    if ($packageName ne "") {
        print "package $packageName;\n";
    };
};

sub printPackage2 {
    my($packageName) = @_;
    if ($packageName ne "") {
        print "package $packageName;\n";
    };
};


