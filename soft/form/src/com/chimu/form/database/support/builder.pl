

@javaDirectPrimitives=(
    'boolean',
    'byte',
    'char',
    'byte',
    'short',
    'int',
    'long',
    'float',
    'double',
    );

@javaPrimitiveWrappers=(
    "Boolean",
    "Character",
    "Byte",
    "Short",
    "Integer",
    "Long",
    "Float",
    "Double"
    );

%javaPrimitivesToWrappers=(
    'boolean'=>"Boolean",
    'char'=>"Character",
    'byte'=>"Byte",
    'short'=>"Short",
    'int'=>"Integer",
    'long'=>"Long",
    'float'=>"Float",
    'double'=>"Double"
    );

#'Void','Class'
@databaseClasses=(
    'Object',
    'String',
    'java.util.Date',
    'java.sql.Date',
    'java.sql.Time',
    'java.sql.Timestamp',
    'java.math.BigDecimal'
    );


@allDatabaseClasses=(@javaPrimitiveWrappers,@databaseClasses);

sub builder1 {
foreach $primitive (@javaDirectPrimitives) {
    $primitiveWrapper = $javaPrimitivesToWrappers{$primitive};
    $spacer = " " x (12 - length $primitiveWrapper);
    print "    datatypeClasses.atKey_put(${primitiveWrapper}.TYPE,$spacer${primitiveWrapper}.class);\n";
}

foreach $className (@javaPrimitiveWrappers) {
    $spacer = " " x (12 - length $className);
    print "    datatypeClasses.atKey_put(${className}.class,$spacer${className}.class);\n";
}


foreach $className (@databaseClasses) {
    $spacer = " " x (22 - length $className);
    print "    datatypeClasses.atKey_put(${className}.class,$spacer${className}.class);\n";
}
}

sub builder2 {
@allDatabaseClassesPlusPrimitives=(@javaDirectPrimitives,@javaPrimitiveWrappers,@databaseClasses);
foreach $className (@allDatabaseClassesPlusPrimitives) {
    print "    databaseTypes.atKey_put(\"${className}\",\"\");\n";
}
}

foreach $className (@allDatabaseClasses) {
    $className =~ tr/\./\_/;
    $spacer = " " x (12 - length $className);
    print "    case TypeConstants.TYPE_${className}:$spacer   return sqlStringForObject(value);\n";
}

foreach $className (@allDatabaseClasses) {
    $className =~ tr/\./\_/;
    $spacer = " " x (12 - length $className);
    print "    case TypeConstants.TYPE_${className}:$spacer   return sqlStringFor${className}(($className) value);\n";
}
