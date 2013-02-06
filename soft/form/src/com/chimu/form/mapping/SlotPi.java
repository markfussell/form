/*======================================================================
**
**  File: chimu/form/mapping/SlotPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;

/**


**/

/*package*/ interface SlotPi extends SlotSi {
};

 //   public Object readValue (Row row);


// Any slot can participate in initializing/setting or extracting/getting
// Some slots may not want to?
//
// IdentityKeySlots need to know how to fetch their identity after writing:
//
// .postInsertIdentityRetreiver(OracleTranslator.retrieveListNumberNamed_Functor(name));
// .preInsertIdentityGenerator(UuidGenerator(connection));
//
// newIdentitySlot("name", Function1Arg , Procedure2Arg, Column());
//      .postInsert
//      .preInsert
//
// any slot:
//      .refreshAfterInsert();
//      .refreshAfterUpdate();
//      .generateForInsert(Functor);
//      .generateForUpdate(Functor);
//
// derivable:
//      useSettersForRefresh (if all refresh slot have setters)
//      useInitializationForRefresh (if not);
//
// mapper.checkForOptimisticLocking(slotName);
//  "causes the client to check the value of the slot before updating the object"
//  "A lock is issued by calling the server and retrieving the relevant information"
//
// mapper.checkServerStateBeforeChanging;
//

// Sybase71Translator.retrieveIdentityColumnNamed_Functor(name)
// form.database.translators.OracleTranslatorAbsC.
// form.database.translators.Sybase...
// form.database.translators.

// retrieveListNumber:
//   select SEQ from dual...
//   @@IDENTITY
//

//  Associations: (Kilov)  Composition, Containment, Assembly, Package (default?), List, Hierarchical
//
//      .containsPartner() // If container is delete, containees are deleted
//      .updateByDeltas() // Change the database by reflecting the differences in what you started with and
//                          // what you finished with
//      .updateByReplacing() // Force the database to look like the current state of the client
//          // Irrelevant for -1 associations, Important for -N and M-N associations
//          // for -N it would mean any objects that were originally retrieved are subsequenctly
//          // compared to the final collection at write time.
//
//  DeltaCollection:
//      Records the original collection and then the final resulting collection so it can
//      diff between them.
//
//
