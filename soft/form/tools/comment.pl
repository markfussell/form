

do 'getopts.pl';
$currentDirectory = 'a:';

$type = "replaceAndInsertLeadingComments";
if (1 == 0) {
} elsif ($type eq "replaceAndInsertLeadingComments") {
    $currentDirectory = 'd:\usersbackup\chimu\development\java\src\com\chimu';
    &loopOverFilesInDirectory($currentDirectory,replaceAndInsertLeadingComments);
} elsif ($type eq "findLeadingComments") {
    $currentDirectory = 'd:\programs\visualcafe\java\src\com\chimu\form\tests\example2';
    &loopOverFilesInDirectory($currentDirectory,findLeadingComments);
} elsif ($type eq "replaceKernelCollections") {
    $currentDirectory = 'd:\programs\visualcafe\java\src\com\chimu';
    &loopOverFilesInDirectory($currentDirectory,replaceKernelCollections);
} elsif ($type eq "findCollections") {
    $currentDirectory = 'd:\programs\visualcafe\java\src\com\chimu';
    &loopOverFilesInDirectory($currentDirectory,findCollections);
} else {
#    $currentDirectory = 'd:\programs\visualcafe\java\src\com\chimu\test2';
#   &loopOverFilesInDirectory($currentDirectory,findCollections);
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

$buffer = "";

sub findLeadingComments{
    my($fileName) = @_;
    if ($fileName =~ /\.java$/) {
        $haveOutputFileName = '';
	$havePackage = '';
 	$buffer = "";
        open (INPUT, "<$fileName");
	print "==".$fileName."\n";
        while (<INPUT>) {
            $line = $_;
	    if (not $havePackage) {
		if ($line =~ /^\s*package/) {
			$havePackage = 1;
			print $buffer;
		} elsif ($line =~ /^\s*import/) {
			$havePackage = 1;
			print $buffer;
		} else {
			$buffer .= $line unless ($line =~/^\s*$/);
		};
	    };
	};
        close (INPUT);
    };
};


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
        			printHeader($fileName);
        			print $line;
        		} elsif ($line =~ /^\s*import/) {
        			$havePackage = 1;
        			printHeader($fileName);
        			print $line;
        		} elsif ($line =~ /\/\*\s*JJT/) {
        		    print $line;
        		} elsif ($line =~ /\/\*\s*Do not edit/) {
        		    print $line;
        		} elsif ($line =~ /^\s*\/\/FORM/) {
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
    my($fileName) = @_;
    my($outputFileName) = $fileName;
    if ($outputFileName =~ /java\\src\\com\\/) {
        $outputFileName = $';
    };
    $outputFileName =~ s/\\/\//g;

    &printOpen();
    print "**  File: $outputFileName\n";
    &printCopyright();
    if ($outputFileName =~ /\/collections\/[^\/]*$/) {
        &printCollectionsSection();
    };
#    &printWarranty();
    &printClose();
};


sub printCollectionsSection {
    print <<COLLECTIONS_SECTION;
**
**  Portions of these collection classes were originally written by
**  Doug Lea (dl\@cs.oswego.edu) and released into the public domain.
**  Doug Lea thanks the assistance and support of Sun Microsystems Labs,
**  Agorics Inc, Loral, and everyone contributing, testing, and using
**  this code.
**      ChiMu Corporation thanks Doug Lea and all of the above.
COLLECTIONS_SECTION
};


sub printOpen {
    print <<OPEN_SECTION;
/***********************************************************************
**
OPEN_SECTION
};

sub printCopyright {
    print <<COPYRIGHT_SECTION;
**
**  Copyright (c) 1997, ChiMu Corporation. All Rights Reserved.
**
**  This software is the confidential and proprietary information of
**  ChiMu Corporation ("Confidential Information").  You shall not
**  disclose such Confidential Information and shall use it only in
**  accordance with the terms of the license agreement you entered into
**  with ChiMu Corporation.
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
***********************************************************************/

CLOSE_SECTION
};

sub findCollections {
    my($fileName) = @_;
    if ($fileName =~ /\.java$/) {
        $haveOutputFileName = '';
        open (INPUT, "<$fileName");
        while (<INPUT>) {
            $line = $_;
            $found = '';
#            $found = ($line =~ /\.atIndex/go) unless $found;
#            $found = ($line =~ /\.\-1/go) unless $found;
            $found = ($line =~ /COM\.chimu\.collections\./igo) unless $found;
            $found = ($line =~ /COM\.chimu\.functors\./igo) unless $found;
#            $found = ($line =~ /sqlW/go) unless $found;
            if ($found) {
                if (!$haveOutputFileName) {
                    $haveOutputFileName = 1;
                    print $fileName."\n";
                };
                print $..":".$line;
            };
        };
        close (INPUT);
    };
};

sub replaceKernelCollections{
    my($fileName) = @_;
#    print "Found $fileName\n";
    if ($fileName =~ /\.java$/) {
        rename($fileName, $fileName . '.bak');
        open (INPUT, "<$fileName\.bak");
        open(OUTPUT, ">$fileName");
        select(OUTPUT);
        while (<INPUT>) {
            $line = $_;
            $line =~ s/COM\.chimu\.collections/COM\.chimu\.kernel\.collections/o;
            $line =~ s/COM\.chimu\.functors/COM\.chimu\.kernel\.functors/o;
            print $line;
        };
        close (INPUT);
        close (OUTPUT);
        select(STDOUT);
    };
};



sub findAtIndex {
    my($fileName) = @_;
    if ($fileName =~ /\.java$/) {
        $haveOutputFileName = '';
        open (INPUT, "<$fileName");
        while (<INPUT>) {
            $line = $_;
            $found = '';
#            $found = ($line =~ /\.atIndex/o) unless $found;
#            $found = ($line =~ /\.\-1/o) unless $found;
            $found = ($line =~ /0/o) unless $found;
            $found = ($line =~ /1/o) unless $found;
            if ($found) {
                if (!$haveOutputFileName) {
                    $haveOutputFileName = 1;
                    print $fileName."\n";
                };
                print $..":".$line;
            };
        };
        close (INPUT);
    };
};


#//{{FunctorBuilder(asdf,asdfs,asdfds,)
#//}}FunctorBuilder
sub findEnumeration {
    my($fileName) = @_;
    if ($fileName =~ /\.java$/) {
        $haveOutputFileName = '';
        open (INPUT, "<$fileName");
        while (<INPUT>) {
            $line = $_;
            $found = '';
            $found = ($line =~ /CollectionEnumeration/o) unless $found;
            if ($found) {
                if (!$haveOutputFileName) {
                    $haveOutputFileName = 1;
                    print $fileName."\n";
                };
                print $..":".$line;
            };
        };
        close (INPUT);
    };
};



sub findHashtablesAndVectors {
    my($fileName) = @_;
    if ($fileName =~ /\.java$/) {
        $haveOutputFileName = '';
        open (INPUT, "<$fileName");
        while (<INPUT>) {
            $line = $_;
            $found = '';
            $found = ($line =~ /Hashtable/o) unless $found;
            $found = ($line =~ /Vector/o) unless $found;
            if ($found) {
                if (!$haveOutputFileName) {
                    $haveOutputFileName = 1;
                    print $fileName."\n";
                };
                print $..":".$line;
            };
        };
        close (INPUT);
    };
};

sub removeCollectionEnumerationFromAFile {
    my($fileName) = @_;
#    print "Found $fileName\n";
    if ($fileName =~ /\.java$/) {
        rename($fileName, $fileName . '.bak');
        open (INPUT, "<$fileName\.bak");
        open(OUTPUT, ">$fileName");
        select(OUTPUT);
        while (<INPUT>) {
            $line = $_;
            $line =~ s/CollectionEnumeration/Enumeration/o;
            print $line;
        };
        close (INPUT);
        close (OUTPUT);
        select(STDOUT);
    };
};



sub removeUtilFromAFile {
    my($fileName) = @_;
#    print "Found $fileName\n";
    if ($fileName =~ /\.java$/) {
        rename($fileName, $fileName . '.bak');
        open (INPUT, "<$fileName\.bak");
        open(OUTPUT, ">$fileName");
        select(OUTPUT);
        while (<INPUT>) {
            $line = $_;
            $line =~ s/COM\.chimu\.util/COM.chimu/o;
#            $line =~ s/predicate\.evaluate/predicate\.isTrue/o;
#            $line =~ s/\.evaluate/\.value/o;
#           $line =~ s/\.perform/\.execute/o;
            $line =~ s/icate\.evaluate/icate\.isTrue/o;
            $line =~ s/evaluate/value/o;
            $line =~ s/perform/execute/o;
            print $line;
        };
        close (INPUT);
        close (OUTPUT);
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