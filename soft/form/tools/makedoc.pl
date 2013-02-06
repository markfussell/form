

do 'getopts.pl';

$makeJavacDestination = 'd:\programs\visualcafe\java\lib';
$srcDirectory = 'd:\programs\visualcafe\java\src\COM\chimu';
$targetDirectory = 'd:\programs\visualcafe\java\doc\COM\chimu';
$rootDirectoryPath = '../../../';
$rootDocDirectoryPath = '../';
$serverRootPath ='http://www.chimu.com/';


#@makeDirectories=('dome','form','kernel','testenv','tools');
@makeDirectories=('tools');
foreach $directorySuffix (@makeDirectories) {
#    print $currentDirectory."\\".$directorySuffix;
	javadocADirectory($directorySuffix, $srcDirectory."\\".$directorySuffix,'',javacADirectory);
    doToADirectory($targetDirectory."\\".$directorySuffix,cleanUpJavadoc,doToADirectory);
};



sub loopOverFilesInDirectory {
    my($directoryName,$doToAFile,$doToADirectory) = @_;
    my(@files, $fileName, $fullFileName);
    return () if ($directoryName =~ /\.$/o);
    return () if ($directoryName =~ /old/o);
    return () if ($directoryName =~ /test$/o);
    print $directoryName."\n";

    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;

    foreach $fileName (@files) {
      $fullFileName = $directoryName."\\".$fileName;
      if (-d $fullFileName) {
          &$doToADirectory($fullFileName,$doToAFile,$doToADirectory);
      } else {
          &$doToAFile($fullFileName);
      };
    };
};

sub collectSubdirectoryNames {
    my($directoryName) = @_;
    my(@results,@files,$fileName,$fullFileName);
    return () if ($directoryName =~ /\.$/o);
    return () if ($directoryName =~ /old/o);
    return () if ($directoryName =~ /test$/o);
    return () if ($directoryName =~ /tests$/o);
    return () if ($directoryName =~ /working/o);
#    print "$directoryName\n";




    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;

    if (grep(/\.java$/,@files)) {
        @results=($directoryName);
    } else {
        @results=();
    };


    foreach $fileName (@files) {
      $fullFileName = $directoryName."\\".$fileName;
      if (-d $fullFileName) {
          @results = (@results, &collectSubdirectoryNames($fullFileName));
      };
    };

#    print @results;
    return @results;
};


sub javadocADirectory {
##($directorySuffix, $currentDirectory."\\".$directorySuffix,'',javacADirectory);
    my ($directorySuffix, $directoryName, $stuff, $stuff) = @_;

    @mydirectories = collectSubdirectoryNames($directoryName);
    $packageString="";

    foreach $fileName (@mydirectories) {
        $packageEntry = $fileName;
        if ($packageEntry =~ /\\src\\/) {
            $packageEntry = $';
        };
        $packageEntry=~ s/\\/\./g;
        $packageString .= " ".$packageEntry;
    }
    print "javadoc -d $directorySuffix -sourcepath ".'..\..\..\src'." ".$packageString."\n";
    exec "javadoc -d $directorySuffix -sourcepath ".'..\..\..\src'." ".$packageString."\n";
};







sub cleanUpJavadoc {
    my($fileName) = @_;
#    print "Found $fileName\n";
    if ($fileName =~ /\.html$/) {
        $currentFilePath = $fileName;
        rename($fileName, $fileName . '.bak');
        open (INPUT, "<$fileName\.bak");
        open(OUTPUT, ">$fileName");
        select(OUTPUT);
        while (<INPUT>) {
            $line = $_;
            while ($line ne "") {
                $line =~ s/interface interface/interface/;
                if ($line =~ /\<([\/\w]+)[^\>]*\>/) {
                    $tagName = $1;
                    $element = $&;
                    $before = $`;
                    $after = $';
                    if ($tagName =~/IMG/io) {
                        if ($element =~ /images\/red\-ball/) {
                            print "   ";
                        } elsif ($element =~ /images\/green\-ball/) {
                            print "(s) ";
                        } elsif ($element =~ /images\/yellow\-ball/) {
                            print "   ";
                        } elsif ($element =~ /images\/magenta\-ball/) {
                            print " - ";
                        } elsif ($element =~ /alt\s*\=\s*\"([^\"]*)\"/) {
                            $text = $1;
                            print $text;
                        };
                        print $before;
                    } elsif ($tagName =~/^BODY/io) {
                        print $before;
                        printBodyStart();
                    } elsif ($tagName =~/^\/BODY/io) {
                        print $before;
                        printFooter();
                        print $element;
                    } else {
                        print $before;
                        print $element;
                    };
                    $line = $after;
                } else {
                    print $line;
                    $line = '';
                };
            };
        };

        close (INPUT);
        close (OUTPUT);
        select(STDOUT);
    };
};

sub printBodyStart {
    $backgroundGifFile=$rootDirectoryPath."images/backgrounds/"."marb18.jpg";
    print "\n"."<BODY TEXT=\"#000000\" BACKGROUND=\"$backgroundGifFile\" BGCOLOR=\"#F0F0F0\" LINK=\"#0000EE\" VLINK=\"#551A8B\" ALINK=\"#FF0000\">"."\n";
};


sub printFooter {
    $chimuImagesDir = $rootDirectoryPath."images/chimu/";
    $chimuIconGifFile = $chimuImagesDir."pyrs1b2t.gif";
    $copyrightFileName= $rootDocDirectoryPath."copyright.html";
    $validHtmlGifFile = $rootDirectoryPath."images/validate/"."valid_html3_2.gif";
    $chimuHomeFileName= $rootDirectoryPath;
    print <<EndFooter1;
<P>
<TABLE width="100%" border=0>
<TR><TD valign=top><A href="$rootDirectoryPath"><IMG src="$chimuIconGifFile" border=0></A></TD>
<TD width="100%" valign = bottom align=left><FONT SIZE="-1"><A href="$copyrightFileName">Copyright (c) 1997</A>, Mark L. Fussell.
<BR>&nbsp;&nbsp;Confidential and Proprietary.</FONT></TD>
</TR>
</TABLE>
EndFooter1
#<TD valign = bottom align=center>
#<A HREF="http://ugweb.cs.ualberta.ca/~gerald/validate/?url=
#$serverRootPath$currentFilePath">
#<IMG HEIGHT=32 WIDTH=48
#     SRC="$validHtmlGifFile"
#     ALT="HTML 3.2 Checked!" BORDER=0></A></TD>

};
sub loopOverSubdirectoriesInDirectory {
    my($directoryName,$doToAFile,$doToADirectory) = @_;
    my(@files, $fileName, $fullFileName);

    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;

    foreach $fileName (@files) {
      $fullFileName = $directoryName."\\".$fileName;
      if (-d $fullFileName) {
          &$doToADirectory($fullFileName,$doToAFile,$doToADirectory);
      };
    };
};



sub javacADirectory {
     my($directoryName,$doToAFile,$doToADirectory) = @_;
     return if ($directoryName =~ /\.$/o);
     return if ($directoryName =~ /old/o);
     return if ($directoryName =~ /tests$/o);
     return if ($directoryName =~ /test$/o);
     return if ($directoryName =~ /working/o);

     print "javac -d $makeJavacDestination $directoryName\\*.java\n";
#     exec  "javac -d $makeJavacDestination $directoryName\\*.java\n";
     loopOverSubdirectoriesInDirectory($directoryName,$doToAFile,$doToADirectory);

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
     my($directoryName,$doToAFile,$doToADirectory) = @_;
     return if ($directoryName =~ /\.$/o);
     return if ($directoryName =~ /old/o);
     return if ($directoryName =~ /test$/o);
     return if ($directoryName =~ /tests$/o);
     loopOverFilesInDirectory($directoryName,$doToAFile,$doToADirectory);
};