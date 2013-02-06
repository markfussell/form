
#$jc="javac";
$jc="sj";

$devRoot = 'e:\users\mark\work\development\form\v1.1\java';
$javaRoot = 'D:\programs\VisualCafe\Java';

$classpath  =     $javaRoot.'\LIB\CLASSES.ZIP' ;
$classpath .= ';'.$javaRoot.'\LIB' ;
$classpath .= ';'.$javaRoot.'\LIB\jgl.jar' ;
$classpath .= ';'.$devRoot .'\src' ;
$classpath .= ';C:\WINNT35\Java\Classes\classes.zip';

do 'getopts.pl';

$makeJavacDestination = $devRoot.'\lib';
$currentDirectory = $devRoot.'\src\com\chimu';

$srcExamplesDir = $currentDirectory.'\formTools\examples\create';
$destExamplesDir = $makeJavacDestination.'\com\chimu\formTools\examples\create';

@makeDirectories=('jdk12','kernel','kernelTools','form','formTools','dome');
#@makeDirectories=('jdk12');
foreach $directorySuffix (@makeDirectories) {
	javacADirectory($currentDirectory."\\".$directorySuffix,'',javacADirectory);
};

#exec("mkdir $destExamplesDir") unless -d $destExamplesDir;
print "=== "."copy /s $srcExamplesDir $destExamplesDir\n";
exec("copy /s $srcExamplesDir $destExamplesDir");

sub loopOverFilesInDirectory {
    my($directoryName,$doToAFile,$doToADirectory) = @_;
    my(@files, $fileName, $fullFileName);

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
     return if ($directoryName =~ /zold/o);
     return if ($directoryName =~ /working/o);
     return if ($directoryName =~ /wrk/o);
     return if ($directoryName =~ /tst/o);
#     return if ($directoryName =~ /tests$/o);
#     return if ($directoryName =~ /test$/o);

    opendir DIRECTORY,$directoryName;
    @files = readdir(DIRECTORY);
    closedir DIRECTORY;
    if (grep (/\.java$/,@files)) {
        print "=== "."compiling $directoryName\\*.java\n";
        #print "===== ".$jc." -deprecation -d $makeJavacDestination -classpath $classpath $directoryName\\*.java\n";
        exec  $jc." -deprecation -d $makeJavacDestination -classpath $classpath $directoryName\\*.java\n";
    } else {
        $length = length($makeJavacDestination);
        print "=== "."ignored   $directoryName\n";
    }
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
     my($fileName,$doToAFile) = @_;
     return if ($fileName =~ /\.$/o);
     return if ($fileName =~ /old/o);
     return if ($fileName =~ /zold/o);
     return if ($fileName =~ /wrk/o);
 #    return if ($fileName =~ /test/o);
     loopOverFilesInDirectory($fileName,$doToAFile);
};